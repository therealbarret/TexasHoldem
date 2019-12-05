import java.util.*;

public class Card
{
    private short value, suit;

    private static String[] values = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    private static String[] suits = {"Diamonds", "Clubs", "Hearts", "Spades"};

    //Constructor
    Card(short rank, short suit)
    {
        this.value = rank;
        this.suit = suit;
    }

    //Get and Set functions
    int getSuit()
    {
        return suit;
    }

    int getValue()
    {
        return value;
    }

    //methods
    static String RankString(int rank)
    {
        return values[rank];
    }

    static String SuitString(int suit)
    {
        return suits[suit];
    }

    public @Override String toString()
    {
        return value + " of " + suit;
    }

    //Print card
    String printCard()
    {
        return values[value] + " of " + suits[suit];
    }

}

class rankComparator implements Comparator<Object>
{
    public int compare(Object card1, Object card2) throws ClassCastException
    {
        if (!((card1 instanceof Card) && (card2 instanceof Card)))
        {
            throw new ClassCastException("Parameter 1: " + card1.getClass()
                    + " Parameter 2: " + card2.getClass());
        }

        int rank1 = ((Card)card1).getValue();
        int rank2 = ((Card)card2).getValue();

        return rank1 - rank2;
    }
}

class suitComparator implements Comparator<Object>
{
    public int compare(Object card1, Object card2) throws ClassCastException
    {
        if (!((card1 instanceof Card) && (card2 instanceof Card)))
        {
            throw new ClassCastException("Parameter 1: " + card1.getClass()
                    + " Parameter 2: " + card2.getClass());
        }

        int suit1 = ((Card)card1).getSuit();
        int suit2 = ((Card)card2).getSuit();

        return suit1 - suit2;
    }
}
