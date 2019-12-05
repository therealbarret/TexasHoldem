import java.util.Random;

class Deck
{
    private Card[] playingcards = new Card[52];

    //Constructor
    Deck()
    {
        int i = 0;
        for (short j = 0; j < 4; j++)
        {
            for (short k = 0; k < 13; k++)
            {
                playingcards[i++] = new Card(k, j);
            }
        }
    }

    // Print entire deck in order
    void printDeck()
    {
        for(int i = 0; i < playingcards.length; i++)
        {
            System.out.println(i + 1 + ": " + playingcards[i].printCard());
        }
        System.out.println("\n");
    }

    //return specified card from deck
    Card getCard(int cardNum)
    {
        return playingcards[cardNum];
    }

    void shuffle()
    {
        int length = playingcards.length;
        Random random = new Random();

        for (int i = 0; i < length; i++)
        {
            int change = i + random.nextInt(length - i);
            swapCards(i, change);
        }
    }

    //Swap cards in array
    private void swapCards(int i, int change)
    {
        Card temp = playingcards[i];
        playingcards[i] = playingcards[change];
        playingcards[change] = temp;
    }
}
