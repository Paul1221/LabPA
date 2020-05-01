public class Game {
    private Board gameBoard = new Board();
    private String gameID;
    private String password;
    private int nrOfPlayers;

    public Game(String gameID, String password) {

        this.gameID = gameID;
        this.password = password;
        nrOfPlayers = 1;
    }

    public void addPlayer(){
        nrOfPlayers = 2;
    }

    public int getNrOfPlayers(){
        return nrOfPlayers;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public String getGameID() {
        return gameID;
    }

    public String getPassword() {
        return password;
    }
}
