import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

class Cards{
    int[][] cards = {{11,2,3,4,5,6,7,8,9,10,10,10,10}, {11,2,3,4,5,6,7,8,9,10,10,10,10},
    {11,2,3,4,5,6,7,8,9,10,10,10,10}, {11,2,3,4,5,6,7,8,9,10,10,10,10}};

    public int TakeRandom(int sumCard){
        int val, rnd1,rnd2;
        do{
            rnd1 = new Random().nextInt(4);
            rnd2 = new Random().nextInt(13);
        }while(cards[rnd1][rnd2]<0);
        val = cards[rnd1][rnd2];
        if(sumCard>10 && val == 11){
            val = 1;
        }
        cards[rnd1][rnd2] = -1;
        return val;
    }

    public int SumCards(int[] arr){
        int total = 0;
        for(int i = 0; i < arr.length; i++){
            total += arr[i];
        }
        return total;  
    }
    public void PrintCards(int[] pcards){
        for(int i =0; i<pcards.length;i++){
            if(pcards[i]==0) break;
            System.out.println(pcards[i]);
        }
    }
    public String toString(int[] pcards){
        String cardString = "";
        for(int i =0; i<pcards.length;i++){
            if(pcards[i]==0) break;
            cardString = cardString + " "+ pcards[i];
        }
        return cardString;
    }
}

public class Server {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        try
        {
            System.out.println("Waiting for clients...");
            ServerSocket ss = new ServerSocket(9806);
            InetAddress inetAddress = InetAddress.getLocalHost();
            System.out.println("Server opened at: "+inetAddress.getHostAddress());
            Socket soc = ss.accept();
            System.out.println("Connection established");
            System.out.println("Welcome to Blackjack");

            int[] player1 = {0,0,0,0,0,0,0,0,0,0};
            int score1 = 0;
            int p1 = 0;
            int[] player2 = {0,0,0,0,0,0,0,0,0,0};
            int score2 = 0;
            int p2 = 0;
            Boolean play1Stand = false;
            Boolean play2Stand = false;
            Boolean play1Win=false, play1Lose = false;
            Boolean play2Win=false, play2Lose  = false;
            Cards deck = new Cards();

            for(int i=0;i<2;i++){
                int p1card = deck.TakeRandom(deck.SumCards(player1));
                int p2card = deck.TakeRandom(deck.SumCards(player2));
                player1[p1] = p1card;
                player2[p2] = p2card;
                p1++;
                p2++; 
            }

            //ObjectOutputStream outputStream = new ObjectOutputStream(soc.getOutputStream());
            //ObjectInputStream InputStream = new ObjectInputStream(soc.getInputStream());
            //DataOutputStream dataOutputStream = new DataOutputStream(soc.getOutputStream());
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader InputStreamString = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
            String player1String = deck.toString(player1);
            out.println(Integer.toString(player1[0]));
            //outputStream.writeObject(player1);

            String player2String = deck.toString(player2);
            out.println(player2String);

            System.out.println("Opponent card: "+ player2[0]);
            System.out.println("Your cards:");
            for(int i = 0;i<2;i++){
                System.out.println(player1[i]);
            }

            score1 = deck.SumCards(player1);
            score2 = deck.SumCards(player2);
           // dataOutputStream.writeInt(score2);
            //outputStream.writeObject(score2);
            out.println(Integer.toString(score2));
            System.out.println("Total: " + score1);

            if(score1==21){
                play1Stand = true;
                play1Win =true;
            }

            //outputStream.writeObject(play1Stand);
            out.println(Boolean.toString(play1Stand));
            out.println(Boolean.toString(play1Win));
            
            if(score2==21){
                play2Stand = true;
                play2Win = true;
            }

            out.println(Boolean.toString(play2Stand));
            out.println(Boolean.toString(play2Win));

            while(!play1Stand && !play2Win){
                System.out.println("Hit or Stand?\n");
                String choice = serverInput.readLine();
                out.println(choice);
                System.out.println("choice delivered");
                if(choice.toLowerCase().equals("stand")){
                    System.out.println("stand true");
                    play1Stand = true;
                    out.println(Boolean.toString(play1Stand));
                    break;
                }
                System.out.println("Play1Stand delivered");
                player1[p1++] = deck.TakeRandom(deck.SumCards(player1));
                System.out.println("Your cards: ");
                deck.PrintCards(player1);
                score1 = deck.SumCards(player1);
                System.out.println("Total: " + score1);
                if(score1>21){
                    play1Stand = true;
                    out.println(Boolean.toString(play1Stand));;
                    play1Lose = true;
                    play2Win = true;
                    break;
                }
                else if(score1==21){
                    play1Stand = true;
                    out.println(Boolean.toString(play1Stand));
                    play1Win = true;
                    play2Lose = true;
                    break;
                }
                out.println(Boolean.toString(play1Stand));
            }

            //outputStream.writeObject(player1);
            player1String = deck.toString(player1);
            out.println(player1String);
            //outputStream.writeObject(score1);
            out.println(Integer.toString(score1));
            out.println(Boolean.toString(play1Stand));
            out.println(Boolean.toString(play1Win));
            out.println(Boolean.toString(play1Lose));
            out.println(Boolean.toString(play2Lose));
            out.println(Boolean.toString(play2Win));
            // outputStream.writeObject(play1Stand);
            // outputStream.writeObject(play1Win);
            // outputStream.writeObject(play1Lose);
            // outputStream.writeObject(play2Lose);
            // outputStream.writeObject(play2Win);

            while(!play2Stand && !play1Lose && !play1Win){
                System.out.println("Before reading choice2");
                String choice2 = InputStreamString.readLine();
                System.out.println("After reading choice 2");
                System.out.println("Your opponent choose: " + choice2);
                if(choice2.toLowerCase().equals("stand")){
                    play2Stand = true;
                    out.println(Boolean.toString(play2Stand));
                    break;
                }
                out.println(Boolean.toString(play2Stand));
                System.out.println("Play2Stand delivered");
                player2[p2++] = deck.TakeRandom(deck.SumCards(player2));
                player2String = deck.toString(player2);
                out.println(player2String);
                score2 = deck.SumCards(player2);
                out.println(Integer.toString(score2));
                //play2Stand = (Boolean) InputStream.readObject();
                play2Stand = Boolean.parseBoolean(InputStreamString.readLine());
            }

            //play2Lose = (Boolean) InputStream.readObject();
            play2Lose = Boolean.parseBoolean(InputStreamString.readLine());
            //play1Win = (Boolean) InputStream.readObject();
            play1Win = Boolean.parseBoolean(InputStreamString.readLine());
            //play2Win = (Boolean) InputStream.readObject();
            play2Win = Boolean.parseBoolean(InputStreamString.readLine());

            player1String = deck.toString(player1);
            out.println(player1String);

            System.out.println("Your Cards: ");
            deck.PrintCards(player1);
            System.out.println("Your Score: " + score1);

            System.out.println("Opponent's Cards: ");
            deck.PrintCards(player2);
            System.out.println("Opponent's Score: " + score2);

            if((play1Win && play2Win) || (play1Lose && play2Lose)){
                String result = "Tie";
                System.out.println(result);
            } 
            else if(play1Win){
                String result = "You win! :D";
                System.out.println(result);
            }
            else if(play2Win){
                String result = "You Lose! T_T";
                System.out.println(result);
            }

            Thread.sleep(1000);
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}   