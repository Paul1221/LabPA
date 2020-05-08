public class Game {
    private Board gameBoard = new Board();
    private String gameID;
    private String password;
    private int nrOfPlayers;
    private int playerTurn;
    private int gameEnded;

    public Game(String gameID, String password) {

        this.gameID = gameID;
        this.password = password;
        nrOfPlayers = 1;
        gameEnded = 0;
        playerTurn = 1;
    }

    public void addPlayer() {
        nrOfPlayers = 2;
    }

    public void endPlayerOneTurn() {
        playerTurn = 2;
    }

    public void endPlayerTwoTurn() {
        playerTurn = 1;
    }

    public int gameEnded() {
        if (gameBoard.isGameFinished() == 1) {
            playerTurn=0;
            gameEnded = 1;
            return 1;
        } else if(gameBoard.isGameFinished() == 2) {
            playerTurn=0;
            gameEnded = 2;
            return 2;
        }
        return 0;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public int getGameEnded() {
        return gameEnded;
    }

    public int getNrOfPlayers() {
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
