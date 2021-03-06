import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private BufferedReader reader;
    private InputStreamReader in;
    private PrintWriter writer;
    private Socket socket;
    private String clientMsg = new String();
    private GameServer server;
    private Game game;

    ClientThread(Socket ss, GameServer server) throws IOException {
        super("ClientThread");
        socket = ss;
        System.out.println("Client conected");
        in = new InputStreamReader(socket.getInputStream());
        reader = new BufferedReader(in);
        writer = new PrintWriter(socket.getOutputStream());
        this.server = server;
        System.out.println(server.toString());
    }

    public void playerOneGame() throws InterruptedException, IOException {
        boolean running = true;
        while (game.getNrOfPlayers() == 1) {
            Thread.sleep(1);
        }
        writer.println("Games starts");
        writer.flush();
        while (running) {
            if(game.gameEnded()!=0){
                if(game.getGameEnded()==1){
                    writer.println("You won");
                    writer.flush();
                    running = false;
                }
                else{
                    writer.println("You lose");
                    writer.flush();
                    running = false;
                }
                game.endPlayerOneTurn();
            }
            else {
                moveSubmit();
                game.endPlayerOneTurn();
                Thread.sleep(100);
                while (game.getPlayerTurn() != 1) {
                    Thread.sleep(1);
                }

            }

        }
    }

    private void moveSubmit() throws IOException, InterruptedException {
        writer.println("Your turn");
        writer.flush();
        writer.println(server.getGame(game.getGameID()).getGameBoard().toString());
        writer.flush();
        Thread.sleep(100);
        clientMsg=reader.readLine();
        server.getGame(game.getGameID()).getGameBoard().addPiece(game.getPlayerTurn(),Integer.parseInt(clientMsg.substring(0,2)),Integer.parseInt(clientMsg.substring(2,4)));
        writer.println(game.getGameBoard().toString());
        writer.flush();
        Thread.sleep(100);
    }

    public void playerTwoGame() throws InterruptedException, IOException {
        boolean running = true;
        while (game.getNrOfPlayers() == 1) {
            Thread.sleep(1);
        }
        writer.println("Games starts");
        writer.flush();
        while (running) {
            Thread.sleep(100);
            while(game.getPlayerTurn()!=2){
                Thread.sleep(1);
            }
            if(game.gameEnded()!=0){
                if(game.getGameEnded()==2){
                    writer.println("You won");
                    writer.flush();
                    running = false;
                }
                else{
                    writer.println("You lose");
                    writer.flush();
                    running = false;
                }
            }
            else {
                moveSubmit();
            }
            game.endPlayerTwoTurn();
        }
    }

    private void createGame() throws InterruptedException, IOException {
        String gameInfo = clientMsg.substring(12);
        int aux = gameInfo.indexOf(" ");
        String gameID = gameInfo.substring(0, aux);
        String password = gameInfo.substring(aux);
        if (server.getGame(gameID) == null) {
            server.addGame(gameID, password);
            this.game = server.getGame(gameID);
            writer.println("You go first");
            writer.flush();
            playerOneGame();
            System.out.printf("gata");
        } else {
            writer.println("Game already exists");
            writer.flush();
        }
    }

    private void joinGame() throws InterruptedException, IOException {
        String gameInfo = clientMsg.substring(10);
        int aux = gameInfo.indexOf(" ");
        String gameID = gameInfo.substring(0, aux);
        String password = gameInfo.substring(aux);
        if (server.getGame(gameID) != null) {
            if (server.getGame(gameID).getPassword().equals(password)) {
                if (server.getGame(gameID).getNrOfPlayers() == 1) {
                    this.game = server.getGame(gameID);
                    game.addPlayer();
                    writer.println("You go second");
                    writer.flush();
                    playerTwoGame();
                } else {
                    writer.println("Game is full");
                    writer.flush();
                }
            } else {
                writer.println("Wrong password");
                writer.flush();
            }
        } else {
            writer.println("Game not found");
            writer.flush();
        }

    }


    private void clientCommunication() throws IOException, InterruptedException {
        while (!clientMsg.equals("exit")) {
            while (!reader.ready()) {
                Thread.sleep(1);
            }
            clientMsg = reader.readLine();
            System.out.println("Client: " + clientMsg);
            if (clientMsg.equals("exit")) {
                writer.println("exited");
                writer.flush();
            }/*else if(clientMsg.length()==16 && clientMsg.substring(0,12).equals("submit move ")){

            }*/ else if (clientMsg.length() >= 14 && clientMsg.substring(0, 12).equals("create game ")) {
                createGame();
            } else if (clientMsg.length() >= 12 && clientMsg.substring(0, 10).equals("join game ")) {
                joinGame();
            } else {
                writer.println("resolved " + clientMsg);
                writer.flush();
            }
        }
    }

    public void run() {
        try {
            clientCommunication();
            reader.close();
            writer.close();
            in.close();
            socket.close();
            System.out.println("Client has quit");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}
