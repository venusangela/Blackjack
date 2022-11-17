import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.lang.Thread;

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
            BufferedReader InputStreamString = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
            System.out.println("Player 1 (" + soc.getLocalAddress() + ") has joined");
            out.println("Welcome Player 1, please wait for Player 2 to join");
            Socket soc2 = ss.accept(); 
            BufferedReader InputStreamString2 = new BufferedReader(new InputStreamReader(soc2.getInputStream()));
            PrintWriter out2 = new PrintWriter(soc2.getOutputStream(), true);
            System.out.println("Player 2 (" + soc2.getLocalAddress() + ") has joined");
            out2.println("Welcome Player 2");
            System.out.println("Starting Blackjack");
            out.println("Welcome to Blackjack");
            out2.println("Welcome to Blackjack");

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

            String player1String = deck.toString(player1);
            String player2String = deck.toString(player2);

            out.println(Integer.toString(player2[0]));
            out2.println(Integer.toString(player1[0]));
            out.println(player1String);
            out2.println(player2String);

            score1 = deck.SumCards(player1);
            score2 = deck.SumCards(player2);

            out.println(Integer.toString(score1));
            out2.println(Integer.toString(score2));

            System.out.println("Player 1 cards: " + player1String + " | Total: " + score1);
            System.out.println("Player 2 cards: " + player2String + " | Total: " + score2);


            if(score1==21){
                play1Stand = true;
                play1Win =true;
            }

            out.println(Boolean.toString(play1Stand));
            out.println(Boolean.toString(play1Win));
            out2.println(Boolean.toString(play1Stand));
            out2.println(Boolean.toString(play1Win));
            
            if(score2==21){
                play2Stand = true;
                play2Win = true;
            }

            out.println(Boolean.toString(play2Stand));
            out.println(Boolean.toString(play2Win));
            out2.println(Boolean.toString(play2Stand));
            out2.println(Boolean.toString(play2Win));

            while(!play1Stand && !play2Win){
                out.println("Hit or Stand?");
                out2.println("It's your opponent turn! Don't play the game! Press Enter");
                String choice = InputStreamString.readLine();
                String input2 = InputStreamString2.readLine();
                //Thread.sleep(5000);

                if(choice.toLowerCase().equals("stand")){
                    play1Stand = true;
                    out.println(Boolean.toString(play1Stand));
                    out2.println(Boolean.toString(play1Stand));
                    break;
                }
                System.out.println("Play1Stand delivered");
                player1[p1++] = deck.TakeRandom(deck.SumCards(player1));
                player1String = deck.toString(player1);
                out.println("Your cards: " + player1String);
                System.out.println("Player 1 cards: " + player1String);
                score1 = deck.SumCards(player1);
                out.println(score1);
                System.out.println("Total: " + score1);
                if(score1>21){
                    play1Stand = true;
                    out.println(Boolean.toString(play1Stand));
                    out2.println(Boolean.toString(play1Stand));
                    play1Lose = true;
                    play2Win = true;
                    break;
                }
                else if(score1==21){
                    play1Stand = true;
                    out.println(Boolean.toString(play1Stand));
                    out2.println(Boolean.toString(play1Stand));
                    play1Win = true;
                    play2Lose = true;
                    break;
                }
                out.println(Boolean.toString(play1Stand));
                out2.println(Boolean.toString(play1Stand));
            }

            player1String = deck.toString(player1);

            out.println(player1String);
            out2.println(player1String);
            
            out.println(Integer.toString(score1));
            out2.println(Integer.toString(score1));

            out.println(Boolean.toString(play1Stand));
            out2.println(Boolean.toString(play1Stand));

            out.println(Boolean.toString(play1Win));
            out2.println(Boolean.toString(play1Win));

            out.println(Boolean.toString(play1Lose));
            out2.println(Boolean.toString(play1Lose));

            out.println(Boolean.toString(play2Lose));
            out2.println(Boolean.toString(play2Lose));

            out.println(Boolean.toString(play2Win));
            out2.println(Boolean.toString(play2Win));

            while(!play2Stand && !play1Lose && !play1Win){
                out.println("It's your opponent turn! Don't play the game! Press Enter");
                out2.println("Hit or Stand?");
                String choice = InputStreamString2.readLine();
                System.out.println(choice);
                String input1 = InputStreamString.readLine();
                //Thread.sleep(5000);
                if(choice.toLowerCase().equals("stand")){
                    play2Stand = true;
                    out.println(Boolean.toString(play2Stand));
                    out2.println(Boolean.toString(play2Stand));
                    break;
                }
                System.out.println("Play2Stand delivered");
                player2[p2++] = deck.TakeRandom(deck.SumCards(player2));
                player2String = deck.toString(player2);
                out2.println("Your cards: " + player2String);
                System.out.println("Player 2 cards: " + player2String);
                score2 = deck.SumCards(player2);
                out2.println(score2);
                System.out.println("Total: " + score2);
                if(score2>21){
                    play2Stand = true;
                    out.println(Boolean.toString(play2Stand));
                    out2.println(Boolean.toString(play2Stand));
                    play2Lose = true;
                    play1Win = true;
                    break;
                }
                else if(score2==21){
                    play2Stand = true;
                    out.println(Boolean.toString(play2Stand));
                    out2.println(Boolean.toString(play2Stand));
                    play2Win = true;
                    play1Lose = true;
                    break;
                }
                out.println(Boolean.toString(play2Stand));
                out2.println(Boolean.toString(play2Stand));
            }

            if(!play1Win && !play2Win){
                if(score1>score2){
                    play1Win = true;
                }
                else if(score2>score1){
                    play2Win = true;
                }
                else{
                    play1Win = true;
                    play2Win = true;
                }
            }

            player1String = deck.toString(player1);
            player2String = deck.toString(player2);

            String cardsToPlayer1 = ("Your cards: " + player1String + " | Your total: " + score1 + 
                                    " | Opponent's cards: " + player2String + " | Opponent's total: " + score2);
            
            String cardsToPlayer2 = ("Your cards: " + player2String + " | Your total: " + score2 + 
                                    " | Opponent's cards: " + player1String + " | Opponent's total: " + score1);

            System.out.println("Player 1 cards: " + player1String + "\nPlayer 1 Total: " + score1);
            System.out.println("Player 2 cards: " + player2String + "\nPlayer 2 Total: " + score2);

            out.println(cardsToPlayer1);
            out2.println(cardsToPlayer2);

            if((play1Win && play2Win) || (play1Lose && play2Lose)){
                    String result = "Tie";
                    System.out.println(result);
                } 
                else if(play1Win){
                    String resultToPlayer1 = "You win! :D";
                    String resultToPlayer2 = "You Lose! T_T";
                    System.out.println("Player 1 wins!");
                    out.println(resultToPlayer1);
                    out2.println(resultToPlayer2);
                }
                else if(play2Win){
                    String resultToPlayer1 = "You Lose! T_T";
                    String resultToPlayer2 = "You Win! :D";
                    System.out.println("Player 2 wins!");
                    out.println(resultToPlayer1);
                    out2.println(resultToPlayer2);
                }

            Thread.sleep(1000);
            // while(soc.isConnected() || soc2.isConnected()){
                
            // }
            soc.close();
            soc2.close();
            ss.close();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}   
