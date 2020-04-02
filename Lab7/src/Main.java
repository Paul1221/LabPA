public class Main {
    public static void main(String argv[]) throws InterruptedException {
        Board gameBoard = new Board(15, 11);
        Player p1 = new Player("Paul",gameBoard);
        Player p2 = new Player("Andrei",gameBoard);
        Game game=new Game(p1,p2);
        game.startGame();
    }
}
