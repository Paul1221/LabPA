import java.util.*;

public class Player implements Runnable {
    private String name;
    private List<Token> playerTokens;
    private Board playingBoard;
    private List<Token> playerBlankTokens;

    public Player(String name, Board playingBoard) {
        this.name = name;
        this.playingBoard = playingBoard;
        this.playerTokens=new ArrayList<>();
        this.playerBlankTokens = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        int blankTokens=playerBlankTokens.size();
        if (playerTokens.size() != 0) {
            int i;
            Collections.sort(playerTokens);
            int sum = playerTokens.get(0).getValue(), maxSum = sum;
            for (i = 1; i < playerTokens.size(); i++) {
                if (playerTokens.get(i).getValue() == playerTokens.get(i - 1).getValue() + 1) {
                    sum = sum + playerTokens.get(i).getValue();
                } else {
                    if(playerTokens.get(i).getValue()>playerTokens.get(i-1).getValue()+blankTokens+1) {
                        for(int j=1;j<=blankTokens;j++){
                            sum=sum+playerTokens.get(i-1).getValue()+j;
                        }
                        if (sum > maxSum) {
                            maxSum = sum;
                        }
                        sum = playerTokens.get(i).getValue();
                        blankTokens=playerBlankTokens.size();
                    }
                    else{
                        for(int j =1;j<=playerTokens.get(i).getValue()-playerTokens.get(i-1).getValue()-1;j++){
                            sum=sum+playerTokens.get(i-1).getValue()+j;
                            blankTokens--;
                        }
                        sum=sum+playerTokens.get(i).getValue();
                    }
                }
            }

            return maxSum;
        }
        return 0;
    }

    private void extractToken() {
        List<Token> auxTokenList = playingBoard.getTokenList();
        Token auxToken = auxTokenList.get(new Random().nextInt(auxTokenList.size()));
        auxTokenList.remove(auxToken);
        playingBoard.setTokenList(auxTokenList);
        if(auxToken.getValue()!=0) {
            playerTokens.add(auxToken);
        }
        else{
            playerBlankTokens.add(auxToken);
        }
    }

    public void  showList(){
        System.out.println(name);
        for(Token token: playerTokens){
            System.out.print(token.getValue());
            System.out.print(" ");
        }
        System.out.println();
        System.out.println(playerBlankTokens.size());
        System.out.println(getScore());
        System.out.println();
    }

    @Override
    public void run() {
        synchronized (this) {
            while (playingBoard.getTokenList().size() != 0) {
                extractToken();

                showList();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
