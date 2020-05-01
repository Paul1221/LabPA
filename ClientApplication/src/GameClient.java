import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GameClient {

    Socket s = new Socket("localhost",4200);
    InputStreamReader in = new InputStreamReader(s.getInputStream());
    PrintWriter writer= new PrintWriter(s.getOutputStream());
    BufferedReader reader = new BufferedReader(in);
    Scanner console = new Scanner(System.in);
    String serverMsg="";

    private void firstPlayerGame() throws IOException {
        String row;
        String column;
        boolean rowFlag = false;
        boolean columnFlag = false;
        System.out.println("Waiting for a player to join");
        serverMsg = reader.readLine();
        System.out.println("Server: " + serverMsg + ". You are first");
        while (!rowFlag) {
            System.out.println("Submit move:");
            System.out.println("Choose a row(00-15):");
            row = console.nextLine();
            if (Integer.parseInt(row) >= 0 && Integer.parseInt(row)  <= 15) {
                rowFlag=true;
                while(!columnFlag) {
                    System.out.println("Choose a column(00-15):");
                    column = console.nextLine();
                    if (Integer.parseInt(column) >= 0 && Integer.parseInt(column) <= 15) {
                        columnFlag=true;
                        writer.println(row + column);
                        writer.flush();
                    }
                    else{
                        System.out.println("Chosen column is out of boundaries");
                    }
                }
            }else{
                System.out.println("Chosen row is out of boundaries");
            }
        }

    }

    private void secondPlayerGame() throws IOException {
        System.out.println("Server:Game started. You are second");
        serverMsg = reader.readLine();
        System.out.println("Server: " + serverMsg);
    }


    GameClient() throws IOException {
        System.out.println("conectat");

        while(!serverMsg.equals("exited")) {
            System.out.println("Enter command:");
            writer.println(console.nextLine());
            writer.flush();
            serverMsg = reader.readLine();
            if(serverMsg.equals("You go first")){

                firstPlayerGame();
            }else{
                System.out.println("Server: " + serverMsg);
            }

        }
    }
}
