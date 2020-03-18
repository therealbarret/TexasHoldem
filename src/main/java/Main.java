import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class Main
{
    private static String playerName;

    public static void main(String[] args) throws Exception
    {
        //variables
        Deck TexasHoldEm = new Deck();
        int Card = 0;
        int Burn = 0;
        int Board = 0;
        arrayofCards board = new arrayofCards();

        //initializations
        int numPlayers = getPlayers();
        Player[] player = new Player[numPlayers];

        //3 shuffles
        for(int i = 0; i < 3; i++)
        {
            TexasHoldEm.shuffle();
        }

        //Initialize players
        for (int i = 0; i < numPlayers; i++)
        {
            player[i] = new Player();
        }

        //Deal hole cards to players
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < numPlayers; j++)
            {
                player[j].setCard(TexasHoldEm.getCard(Card++), i);
            }
        }

        //Deal arrayofCards
        board.setBurnCard(TexasHoldEm.getCard(Card++), Burn++);

        //deal flop
        for (int i = 0; i < 3; i++)
        {
            board.setBoardCard(TexasHoldEm.getCard(Card++), Board++);
        }

        //Burn one card before turn
        board.setBurnCard(TexasHoldEm.getCard(Card++), Burn++);

        //deal turn
        board.setBoardCard(TexasHoldEm.getCard(Card++), Board++);

        //Burn one card before river
        board.setBurnCard(TexasHoldEm.getCard(Card++), Burn++);

        //deal river
        board.setBoardCard(TexasHoldEm.getCard(Card++), Board++);

        //end of dealing
        System.out.println("Hand is complete!\n");

        //print deck
        TexasHoldEm.printDeck();

        //print board
        board.printBoard();

        //print burn cards
        board.printBurnCards();

        //Hand comparison
        for (int i = 0; i < numPlayers; i++)
        {
            HandEval handToEval = new HandEval();

            //populate player cards
            for (int j = 0; j < player[i].holeCardsSize(); j++)
            {
                handToEval.addCard(player[i].getCard(j), j);
            }

            //populate board cards
            for (int j = player[i].holeCardsSize(); j < (player[i].holeCardsSize() + board.boardSize()); j++)
            {
                handToEval.addCard(board.getBoardCard(j - player[i].holeCardsSize()),j);
            }

            System.out.println("Player " + (getPlayerName()) + " hand value: " + handToEval.evaluateHand());
        }
    }

    //inializes amount of players
    private static int getPlayers() throws Exception
    {
        int Player = 0;
        String userInput = "";

        //Get name of user
        System.out.println("Enter number of players 1 to 4, player Bank is considered the 5th player: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try
        {
            userInput = br.readLine();
        } catch (IOException ioe)
        {
            System.out.println("Error trying to read input!");
            System.exit(1);
        }

        //convert user input to an integer
        try
        {
            Player = Integer.parseInt(userInput);
        } catch (NumberFormatException nfe)
        {
            System.out.println("Not a valid Integer!");
            System.exit(1);
        }

        if ((Player < 1) || (Player > 4))
        {
            throw new Exception("Number of players must be between 1 and 4");
        }
        return Player;
    }

    //player name
    private static String getPlayerName()
    {
        Scanner name = new Scanner(System.in);

        System.out.println("What is your name? ");
        String playerName = name.nextLine();
        return playerName;
    }
}
