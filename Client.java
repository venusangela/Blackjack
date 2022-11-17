import java.net.Socket;
import java.io.*; 

public class Client {
    
   public static void main(String[] args) {

        try{
            System.out.println("Client started");
            Socket soc = new Socket("localhost", 9806);
            System.out.println( " _     _            _    _            _    ");
            System.out.println("| |   | |          | |  (_)          | |   ");
            System.out.println("| |__ | | __ _  ___| | ___  __ _  ___| | __");
            System.out.println("| '_ \\| |/ _` |/ __| |/ / |/ _` |/ __| |/ /");
            System.out.println("| |_) | | (_| | (__|   <| | (_| | (__|   < ");
            System.out.println("|_.__/|_|\\__,_|\\___|_|\\_\\ |\\__,_|\\___|_|\\_\\");
            System.out.println("                       _/ |                ");
            System.out.println("                      |__/        ");

            String choice;
            int OpponentScore;

            BufferedReader InputStreamString = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(soc.getOutputStream(), true);

            System.out.println(InputStreamString.readLine());
            System.out.println(InputStreamString.readLine());

            String OpponentString = InputStreamString.readLine();
            String PlayerString = InputStreamString.readLine();

            System.out.println("Opponent card: "+ OpponentString + "| |");
            System.out.println("Your cards: " + PlayerString);

            int PlayerScore = Integer.parseInt(InputStreamString.readLine());
            System.out.println("Total: " + PlayerScore);
            
            //check if initial win
            //player 1 win?
            Boolean OpponentStand = Boolean.parseBoolean(InputStreamString.readLine());
            Boolean OpponentWin = Boolean.parseBoolean(InputStreamString.readLine());

            //player 2 win?
            Boolean PlayerStand = Boolean.parseBoolean(InputStreamString.readLine());
            Boolean PlayerWin = Boolean.parseBoolean(InputStreamString.readLine());

            // FOR PLAYER 1
            while(!OpponentStand && !OpponentWin && !PlayerWin){
                System.out.println("-------------------\n"+InputStreamString.readLine());
                choice = clientInput.readLine();
                out.println(choice);
                //Thread.sleep(5000);
                if(choice.toLowerCase().equals("stand")){
                    OpponentStand = Boolean.parseBoolean(InputStreamString.readLine());
                    break;
                }
                else if(choice.toLowerCase().equals("hit")){
                    OpponentString = InputStreamString.readLine();
                    System.out.println("-------------------\n" + OpponentString);
                    OpponentScore = Integer.parseInt(InputStreamString.readLine());
                    System.out.println("Total: " + OpponentScore);
                }
                OpponentStand = Boolean.parseBoolean(InputStreamString.readLine());
            }

            OpponentString = InputStreamString.readLine();
            OpponentScore = Integer.parseInt(InputStreamString.readLine());
            OpponentStand = Boolean.parseBoolean(InputStreamString.readLine());
            OpponentWin = Boolean.parseBoolean(InputStreamString.readLine());
            Boolean OpponentLose = Boolean.parseBoolean(InputStreamString.readLine());
            Boolean PlayerLose = Boolean.parseBoolean(InputStreamString.readLine());
            PlayerWin = Boolean.parseBoolean(InputStreamString.readLine());


            while(!PlayerStand && !OpponentWin && !OpponentLose){
                System.out.println("\n-----------------\n" + InputStreamString.readLine());
                String choice2 = clientInput.readLine();
                out.println(choice2);
                //Thread.sleep(5000);
                if(choice2.toLowerCase().equals("stand")){
                    PlayerStand = Boolean.parseBoolean(InputStreamString.readLine());
                    break;
                }
                else if(choice2.toLowerCase().equals("hit")){
                    PlayerString = InputStreamString.readLine();
                    System.out.println("-------------------\n" + PlayerString);
                    PlayerScore = Integer.parseInt(InputStreamString.readLine());
                    System.out.println("Total: " + PlayerScore);
                }
                PlayerStand = Boolean.parseBoolean(InputStreamString.readLine());
            }

            String cardsOutput = InputStreamString.readLine();
            System.out.println(cardsOutput);

            String result = InputStreamString.readLine();
            System.out.println("-----------------\n" + result + "\n-----------------");

            soc.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
