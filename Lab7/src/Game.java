public class Game {
    private Player player1;
    private Player player2;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void startGame() throws InterruptedException {
        Thread t1 = new Thread(player1);
        Thread t2 = new Thread(player2);
        t1.start();
        Thread.sleep(10);
        t2.start();

    }
}
