import java.util.Arrays;

public class Board {
    private int size = 16;
    private int[][] boardLayout = new int[size][size];

    Board() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                boardLayout[i][j] = 0;
            }
        }
    }

    public int getSize() {
        return size;
    }

    public int[][] getBoardLayout() {
        return boardLayout;
    }

    public void addPiece(int player, int i, int j) {
        boardLayout[i][j] = player;
    }

    @Override
    public String toString() {
        StringBuilder aux = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                aux.append(boardLayout[i][j]);
            }
        }
        return aux.toString();
    }

    public int isGameFinished() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 11; j++) {
                if (boardLayout[i][j] == 1 && boardLayout[i][j+1] == 1 && boardLayout[i][j+2] == 1 && boardLayout[i][j+3] == 1 && boardLayout[i][j+4] == 1){
                    return 1;
                }
                if (boardLayout[i][j] != 2 && boardLayout[i][j+1] == 2 && boardLayout[i][j+2] == 2 && boardLayout[i][j+3] == 2 && boardLayout[i][j+4] == 2){
                    return 2;
                }
            }
        }
        return 0;
    }
}
