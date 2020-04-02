import java.util.Random;

public class Token implements Comparable{
    private int value;

    public Token(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Object o) {
        return this.value-((Token) o).getValue();
    }
}
