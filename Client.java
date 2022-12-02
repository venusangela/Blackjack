import java.net.Socket;
import java.io.*; 

public class Client {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
   public static void main(String[] args) {

        try{
            System.out.println("Client started");
            Socket soc = new Socket("localhost", 9806);
            System.out.println(ANSI_RED + "██████╗ ██╗      █████╗  ██████╗██╗  ██╗     ██╗ █████╗  ██████╗██╗  ██╗");
            System.out.println("██╔══██╗██║     ██╔══██╗██╔════╝██║ ██╔╝     ██║██╔══██╗██╔════╝██║ ██╔╝");
            System.out.println("██████╔╝██║     ███████║██║     █████╔╝      ██║███████║██║     █████╔╝");
            System.out.println("██╔══██╗██║     ██╔══██║██║     ██╔═██╗ ██   ██║██╔══██║██║     ██╔═██╗ ");
            System.out.println("██████╔╝███████╗██║  ██║╚██████╗██║  ██╗╚█████╔╝██║  ██║╚██████╗██║  ██╗");
            System.out.println("╚═════╝ ╚══════╝╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝ ╚════╝ ╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝" + ANSI_RESET);

            String choice;
            int OpponentScore;

            BufferedReader InputStreamString = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(soc.getOutputStream(), true);

            System.out.println(InputStreamString.readLine());
            System.out.println(InputStreamString.readLine());

            String OpponentString = InputStreamString.readLine();
            String PlayerString = InputStreamString.readLine();

            System.out.println(ANSI_YELLOW+"Opponent card: "+ OpponentString + " | |");
            System.out.println("Your cards: " + PlayerString);

            int PlayerScore = Integer.parseInt(InputStreamString.readLine());
            System.out.println("Total: " + PlayerScore + ANSI_RESET);
            
            //Menerima data dari server untuk cek apakah ada pemain yang langsung menang
            //player 1 win?
            Boolean OpponentStand = Boolean.parseBoolean(InputStreamString.readLine());
            Boolean OpponentWin = Boolean.parseBoolean(InputStreamString.readLine());

            //player 2 win?
            Boolean PlayerStand = Boolean.parseBoolean(InputStreamString.readLine());
            Boolean PlayerWin = Boolean.parseBoolean(InputStreamString.readLine());

            // PERMAINAN UNTUK PLAYER 1
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
                    System.out.println(ANSI_YELLOW+"-------------------\n" + OpponentString);
                    OpponentScore = Integer.parseInt(InputStreamString.readLine());
                    System.out.println("Total: " + OpponentScore + ANSI_RESET);
                }
                OpponentStand = Boolean.parseBoolean(InputStreamString.readLine());
            }

            // Menerima data dari server
            OpponentString = InputStreamString.readLine();
            OpponentScore = Integer.parseInt(InputStreamString.readLine());
            OpponentStand = Boolean.parseBoolean(InputStreamString.readLine());
            OpponentWin = Boolean.parseBoolean(InputStreamString.readLine());
            Boolean OpponentLose = Boolean.parseBoolean(InputStreamString.readLine());
            Boolean PlayerLose = Boolean.parseBoolean(InputStreamString.readLine());
            PlayerWin = Boolean.parseBoolean(InputStreamString.readLine());

            // PERMAINAN UNTUK PLAYER 2
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
                    System.out.println(ANSI_YELLOW + "-------------------\n" + PlayerString);
                    PlayerScore = Integer.parseInt(InputStreamString.readLine());
                    System.out.println("Total: " + PlayerScore + ANSI_RESET);
                }
                PlayerStand = Boolean.parseBoolean(InputStreamString.readLine());
            }
            
            // Menerima data pemain dari server dan menampilkannya
            String cardsOutput = InputStreamString.readLine();
            System.out.println(ANSI_YELLOW + cardsOutput + ANSI_RESET);
            
            // Menerima hasil menang kalah dan menampilkannya
            String result = InputStreamString.readLine();
            if(result.equals("lose")){
                System.out.println("\n\n");
                System.out.println(ANSI_RED + "▓██   ██▓ ▒█████   █    ██     ██▓     ▒█████    ██████ ▓█████ ");
                System.out.println("▒██  ██▒▒██▒  ██▒ ██  ▓██▒   ▓██▒    ▒██▒  ██▒▒██    ▒ ▓█   ▀  ");
                System.out.println("▒██ ██░▒██░  ██▒▓██  ▒██░   ▒██░    ▒██░  ██▒░ ▓██▄   ▒███     ");
                System.out.println("░ ▐██▓░▒██   ██░▓▓█  ░██░   ▒██░    ▒██   ██░  ▒   ██▒▒▓█  ▄   ");
                System.out.println("░ ██▒▓░░ ████▓▒░▒▒█████▓    ░██████▒░ ████▓▒░▒██████▒▒░▒████▒  ");
                System.out.println("██▒▒▒ ░ ▒░▒░▒░ ░▒▓▒ ▒ ▒    ░ ▒░▓  ░░ ▒░▒░▒░ ▒ ▒▓▒ ▒ ░░░ ▒░ ░   ");
                System.out.println("▓██ ░▒░   ░ ▒ ▒░ ░░▒░ ░ ░    ░ ░ ▒  ░  ░ ▒ ▒░ ░ ░▒  ░ ░ ░ ░  ░ ");
                System.out.println("▒ ▒ ░░  ░ ░ ░ ▒   ░░░ ░ ░      ░ ░   ░ ░ ░ ▒  ░  ░  ░     ░    ");  
                System.out.println("░ ░         ░ ░     ░            ░  ░    ░ ░        ░     ░  ░ ");
                System.out.println("░ ░                                                            "+ ANSI_RESET);
            } else if(result.equals("win")){
                System.out.println("\n\n");
                System.out.println(ANSI_GREEN+"            ▄         ▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄         ▄       ▄         ▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄        ▄ ");
                System.out.println("            ▐░▌       ▐░▌▐░░░░░░░░░░░▌▐░▌       ▐░▌     ▐░▌       ▐░▌▐░░░░░░░░░░░▌▐░░▌      ▐░▌");
                System.out.println("            ▐░▌       ▐░▌▐░█▀▀▀▀▀▀▀█░▌▐░▌       ▐░▌     ▐░▌       ▐░▌ ▀▀▀▀█░█▀▀▀▀ ▐░▌░▌     ▐░▌");
                System.out.println("            ▐░█▄▄▄▄▄▄▄█░▌▐░▌       ▐░▌▐░▌       ▐░▌     ▐░▌   ▄   ▐░▌     ▐░▌     ▐░▌ ▐░▌   ▐░▌");
                System.out.println("            ▐░░░░░░░░░░░▌▐░▌       ▐░▌▐░▌       ▐░▌     ▐░▌  ▐░▌  ▐░▌     ▐░▌     ▐░▌  ▐░▌  ▐░▌");
                System.out.println("             ▀▀▀▀█░█▀▀▀▀ ▐░▌       ▐░▌▐░▌       ▐░▌     ▐░▌ ▐░▌░▌ ▐░▌     ▐░▌     ▐░▌   ▐░▌ ▐░▌");
                System.out.println("                 ▐░▌     ▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄█░▌     ▐░▌░▌   ▐░▐░▌ ▄▄▄▄█░█▄▄▄▄ ▐░▌     ▐░▐░▌");
                System.out.println("                 ▐░▌     ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌     ▐░░▌     ▐░░▌▐░░░░░░░░░░░▌▐░▌      ▐░░▌");
                System.out.println("                  ▀       ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀       ▀▀       ▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀        ▀▀" + ANSI_RESET);
                
            } else {
                System.out.println("\n\n");
                System.out.println(ANSI_CYAN+"████████ ██ ███████ ");
                System.out.println("   ██    ██ ██  ");     
                System.out.println("   ██    ██ █████ "); 
                System.out.println("   ██    ██ ██     ");      
                System.out.println("   ██    ██ ███████ "+ANSI_RESET);  
            }
            soc.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
