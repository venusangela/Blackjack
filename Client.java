import java.io.*;
import java.net.Socket;

public class Client {
    
   public static void main(String[] args) {
        try{
            System.out.println("Client started");
            Socket soc = new Socket("localhost", 9806);

            String choice1;
            
            BufferedReader InputStreamString = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
            String player1String = InputStreamString.readLine();
            String player2String = InputStreamString.readLine();

            System.out.println("Opponent card: "+ player1String);
            System.out.println("Your cards: " + player2String);
            int score2 = Integer.parseInt(InputStreamString.readLine());
            System.out.println("Total: " + score2);

            Boolean play1Stand = Boolean.parseBoolean(InputStreamString.readLine());
            Boolean play1Win = Boolean.parseBoolean(InputStreamString.readLine());

            //player 2 win?
            Boolean play2Stand = Boolean.parseBoolean(InputStreamString.readLine());
            Boolean play2Win = Boolean.parseBoolean(InputStreamString.readLine());

            while(!play1Stand && !play2Win){
                System.out.println("While loop start");
                choice1 = InputStreamString.readLine();
                System.out.println("after choice");
                System.out.println("Your opponent chose: " + choice1);
                play1Stand = Boolean.parseBoolean(InputStreamString.readLine());
            }

            player1String = InputStreamString.readLine();
            int score1 = Integer.parseInt(InputStreamString.readLine());
            play1Stand = Boolean.parseBoolean(InputStreamString.readLine());
            play1Win = Boolean.parseBoolean(InputStreamString.readLine());
            Boolean play1Lose = Boolean.parseBoolean(InputStreamString.readLine());
            Boolean play2Lose = Boolean.parseBoolean(InputStreamString.readLine());
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

                player2String = InputStreamString.readLine();
                score2 = Integer.parseInt(InputStreamString.readLine());
                System.out.println("Your cards: " + player2String);
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
                out.println(Boolean.toString(play2Stand));
            }

            System.out.println("Delivering Start");
            out.println(Boolean.toString(play2Lose));
            System.out.println("Play2Lose delivered");
            out.println(Boolean.toString(play1Win));
            System.out.println("Play1Win delivered");
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
