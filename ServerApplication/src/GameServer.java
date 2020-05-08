import javax.naming.ldap.SortKey;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameServer {
    private List<ClientThread> clients = new ArrayList<>();
    private ServerSocket serverSocket ;
    private List<Game> games = new ArrayList<>();

    public GameServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public Game getGame(String id) {
        for(Game g:games){
            if(g.getGameID().equals(id)){
                return g;
            }
        }
        return null;
    }


    public void addGame(String id , String password){
        games.add(new Game(id,password));
        System.out.println("adaugat jocul : " + id);
    }

    public void runServer() throws IOException {
        ClientThread client;
        while(true){
            System.out.println("Waitng for client");
            client = new ClientThread(serverSocket.accept(),this);
            client.start();
            System.out.println("DAAADA");
            clients.add(client);
        }

    }



}
