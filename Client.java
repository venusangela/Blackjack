import java.io.*;
import java.net.Socket;

public class Client {
    
   public static void main(String[] args) {
        // int[] player2 = new int[10];
        // int p2 = 0;
        try{
            System.out.println("Client started");
            Socket soc = new Socket("localhost", 9806);

            String choice1;

            //ObjectOutputStream outputStream = new ObjectOutputStream(soc.getOutputStream());
            //ObjectInputStream InputStream = new ObjectInputStream(soc.getInputStream());
            //DataInputStream dataInputStream = new DataInputStream(soc.getInputStream());
            BufferedReader InputStreamString = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
            //int[] player1 = (int[]) InputStream.readObject();
            String player1String = InputStreamString.readLine();
            String player2String = InputStreamString.readLine();

            System.out.println("Opponent card: "+ player1String);
            System.out.println("Your cards: " + player2String);
            //int score2 = dataInputStream.readInt();
            //int score2 = (int) InputStream.readObject();
            int score2 = Integer.parseInt(InputStreamString.readLine());
            System.out.println("Total: " + score2);
            
            //check if initial win
            //player 1 win?
            // Boolean play1Stand = (Boolean) InputStream.readObject();
            Boolean play1Stand = Boolean.parseBoolean(InputStreamString.readLine());
            // Boolean play1Win = (Boolean) InputStream.readObject();
            Boolean play1Win = Boolean.parseBoolean(InputStreamString.readLine());

            //player 2 win?

            // Boolean play2Stand = (Boolean) InputStream.readObject();
            Boolean play2Stand = Boolean.parseBoolean(InputStreamString.readLine());
            // Boolean play2Win = (Boolean) InputStream.readObject();
            Boolean play2Win = Boolean.parseBoolean(InputStreamString.readLine());

            // System.out.println(play1Stand);
            // System.out.println(play2Win);
            while(!play1Stand && !play2Win){
                System.out.println("While loop start");
                choice1 = InputStreamString.readLine();
                System.out.println("after choice");
                System.out.println("Your opponent chose: " + choice1);
                // if(choice1.toLowerCase() == "stand"){
                //     play1Stand = (Boolean) InputStream.readObject();
                // }
                // play1Stand = (Boolean) InputStream.readObject();
                play1Stand = Boolean.parseBoolean(InputStreamString.readLine());
                //System.out.println(play1Stand);
            }

            // player1 = (int[]) InputStream.readObject();
            player1String = InputStreamString.readLine();
            // int score1 = (int) InputStream.readObject();
            int score1 = Integer.parseInt(InputStreamString.readLine());
            // play1Stand = (Boolean) InputStream.readObject();
            play1Stand = Boolean.parseBoolean(InputStreamString.readLine());
            // play1Win = (Boolean) InputStream.readObject();
            play1Win = Boolean.parseBoolean(InputStreamString.readLine());
            // Boolean play1Lose = (Boolean) InputStream.readObject();
            Boolean play1Lose = Boolean.parseBoolean(InputStreamString.readLine());
            // Boolean play2Lose = (Boolean) InputStream.readObject();
            Boolean play2Lose = Boolean.parseBoolean(InputStreamString.readLine());
            // play2Win = (Boolean) InputStream.readObject();
            play2Win = Boolean.parseBoolean(InputStreamString.readLine());

            while(!play2Stand && !play2Win && !play1Win){
                System.out.println("Hit or Stand: ");
                String choice = clientInput.readLine();
                System.out.println("Choice delivered");
                out.println(choice);
                play2Stand = Boolean.parseBoolean(InputStreamString.readLine());
                System.out.println(play2Stand);
                if(play2Stand){
                    if(score1==score2){
                        play2Win = true;
                        play1Win = true;
                    }
                    else if(score1>score2){
                        play1Win = true;
                    }
                    else{
                        play2Win = true;
                    }
                    break;
                }
                //player2 = (int[]) InputStream.readObject();
                player2String = InputStreamString.readLine();
                score2 = Integer.parseInt(InputStreamString.readLine());
                System.out.println("Your cards: " + player2String);
                // for(int i = 0; i < player2.length; i++){
                //     //if(player2[i]==0) break;
                //     System.out.println(player2[i]);
                // }
                System.out.println("Total: "+ score2);
                if(score2>21){
                    play2Stand = true;
                    play2Lose = true;
                    play1Win = true;
                }
                else if(score2==21){
                    play2Stand = true;
                    play2Win = true;
                }
                // outputStream.writeObject(play2Stand);
                out.println(Boolean.toString(play2Stand));
            }

            System.out.println("Delivering Start");
            // outputStream.writeObject(play2Lose);
            out.println(Boolean.toString(play2Lose));
            System.out.println("Play2Lose delivered");
            // outputStream.writeObject(play1Win);
            out.println(Boolean.toString(play1Win));
            System.out.println("Play1Win delivered");
            // outputStream.writeObject(play2Win);
            out.println(Boolean.toString(play2Win));
            System.out.println("Play2Win delivered");

            player1String = InputStreamString.readLine();
            System.out.println("Opponent's cards: ");
            System.out.println(player1String);
            System.out.println("Opponent's score: " + score1);

            System.out.println("Your cards: ");
            System.out.println(player2String);
            System.out.println("Your score: " + score2);

            if((play1Win && play2Win) || (play1Lose && play2Lose)){
                String result = "Tie";
                System.out.println(result);
            } 
            else if(play1Win){
                String result = "You lose! T_T";
                System.out.println(result);
            }
            else if(play2Win){
                String result = "You win! :D";
                System.out.println(result);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}