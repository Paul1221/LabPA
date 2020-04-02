import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private List<Token> tokenList;
    private int size;
    private int tokenMaxValue;

    public Board(int size, int tokenMaxValue) {
        this.size = size;
        this.tokenMaxValue = tokenMaxValue;
        tokenList = new ArrayList<>();
        List<Integer> auxList = new ArrayList<>();
        for (int i = 1; i <= tokenMaxValue; i++) {
            auxList.add(i);
        }
        Random r = new Random();
        int aux;
        for (int i = 1; i <= size; i++) {
            if (i <= tokenMaxValue) {
                aux = auxList.get(r.nextInt(auxList.size()));
                tokenList.add(new Token(aux));
                auxList.remove(auxList.indexOf(aux));
            } else {
                tokenList.add(new Token(0));
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Token> getTokenList() {
        return tokenList;
    }



    public void setTokenList(List<Token> tokenList) {
        this.tokenList = tokenList;
    }

    public void display() {
        for (Token t : tokenList) {
            System.out.println(t.getValue());
        }
    }
}
