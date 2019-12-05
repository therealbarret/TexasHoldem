import java.util.Arrays;

class HandEval
{
    private Card[] availableCards = new Card[7];

    //sharing same value for each
    private final static int one = 1;
    private final static int two = 2;
    private final static int three = 3;
    private final static int four = 4;

    // Constructor
    HandEval()
    {
    }

    //methods
    void addCard(Card card, int i)
    {
        availableCards[i] = card;
    }

    private void sortByRank()
    {
        Arrays.sort(availableCards, new rankComparator());
    }

    private void sortByRankThenSuit()
    {
        Arrays.sort(availableCards, new rankComparator());
        Arrays.sort(availableCards, new suitComparator());
    }

    String evaluateHand()
    {
        int[] Rank = new int[13];
        int[] Suit = new int[4];

        //initializations
        for (int i = 0; i < Rank.length; i++)
        {
            Rank[i] =0;
        }

        //Loop through sorted cards and ranks
        for (Card availableCard : availableCards)
        {
            Rank[availableCard.getValue()]++;
            Suit[availableCard.getSuit()]++;
        }

        //sort cards for evaluation
        this.sortByRankThenSuit();

        //check for royal flush
        String Result = RoyalFlush(Rank, Suit);

        //check for straight flush
        if (Result == null || Result.length() == 0)
        {
            Result = StraightFlush(Suit);
        }

        //check for four of a kind
        if (Result == null || Result.length() == 0)
        {
            Result = FourOfAKind(Rank);
        }

        //check for full house
        if (Result == null || Result.length() == 0)
        {
            Result = FullHouse(Rank);
        }

        //check for flush
        if (Result == null || Result.length() == 0)
        {
            Result = Flush(Rank);
        }

        //check for straight
        if (Result == null || Result.length() == 0)
        {
            this.sortByRank();
            Result = Straight(Rank);
        }

        //check for three of a kind
        if (Result == null || Result.length() == 0)
        {
            Result = ThreeOfAKind(Rank);
        }

        //check for two pair
        if (Result == null || Result.length() == 0)
        {
            Result = TwoPair(Rank);
        }

        //check for one pair
        if (Result == null || Result.length() == 0)
        {
            Result = OnePair(Rank);
        }

        //check for highCard
        if (Result == null || Result.length() == 0)
        {
            Result = HighCard(Rank);
        }
        return Result;
    }

    private String RoyalFlush(int[] rankCounter, int[] suitCounter)
    {
        String result = "";

        //Check for Royal Flush
        if ((rankCounter[9] >= 1 &&
                rankCounter[10] >= 1 &&
                rankCounter[11] >= 1 &&
                rankCounter[12] >= 1 &&
                rankCounter[0] >= 1)
                && (suitCounter[0] > 4 || suitCounter[1] > 4 ||
                suitCounter[2] > 4 || suitCounter[3] > 4)){

            royalSearch:
            for (int i = 0; i < 3; i++)
            {
                //Check IF first card is the ace.
                if (availableCards[i].getValue() == 0)
                {
                    for (int j = 1; j < 4 - i; j++)
                    {
                        if ((availableCards[i+j].getValue() == 9 &&
                                availableCards[i+j+1].getValue() == 10 &&
                                availableCards[i+j+2].getValue() == 11 &&
                                availableCards[i+j+3].getValue() == 12)
                                &&
                                (availableCards[i].getSuit() == availableCards[i+j].getSuit() &&
                                        availableCards[i].getSuit() == availableCards[i+j+1].getSuit() &&
                                        availableCards[i].getSuit() == availableCards[i+j+2].getSuit() &&
                                        availableCards[i].getSuit() == availableCards[i+j+3].getSuit())){
                            // Found Royal Flush, break and return
                            result = "Royal Flush!! Suit: " + Card.SuitString(availableCards[i].getSuit());
                            break royalSearch;
                        }
                    }
                }
            }
        }
        return result;
    }

    //Evaluate Straight flush
    private String StraightFlush(int[] suitCounter)
    {
        String result = "";

        if (suitCounter[0] > 4 || suitCounter[1] > 4 ||
                suitCounter[2] > 4 || suitCounter[3] > 4)
        {
            for (int i = availableCards.length - 1; i > 3; i--)
            {
                if ((availableCards[i].getValue() - one == availableCards[i - one].getValue() &&
                        availableCards[i].getValue() - two == availableCards[i - two].getValue() &&
                        availableCards[i].getValue() - three == availableCards[i - three].getValue() &&
                        availableCards[i].getValue() - four == availableCards[i - four].getValue())
                        &&
                        (availableCards[i].getSuit() == availableCards[i - one].getSuit() &&
                                availableCards[i].getSuit() == availableCards[i - two].getSuit() &&
                                availableCards[i].getSuit() == availableCards[i - three].getSuit() &&
                                availableCards[i].getSuit() == availableCards[i - four].getSuit())){
                    //Found Straight Flush, break and return
                    result = "Straight Flush!! " + Card.RankString(availableCards[i].getValue()) + " high of " + Card.SuitString(availableCards[i].getSuit());
                    break;
                }
            }
        }
        return result;
    }

    //Evaluate Four of a kind
    private String FourOfAKind(int[] rankCounter)
    {
        String result = "";

        for (int i = 0; i < rankCounter.length; i++)
        {
            if (rankCounter[i] == four)
            {
                result = "Four of a Kind, " + Card.RankString(i) +"'s";
                break;
            }
        }
        return result;
    }

    // Evaluate Full house
    private String FullHouse(int[] rankCounter)
    {
        String result = "";
        int threeOfKindRank = -1;
        int twoOfKindRank = -1;

        for (int i = rankCounter.length; i > 0; i--)
        {
            if ((threeOfKindRank < 0) || (twoOfKindRank < 0))
            {
                if ((rankCounter[i - one]) > 2)
                {
                    threeOfKindRank = (i - one);
                }
                else if ((rankCounter[i - one]) > 1)
                {
                    twoOfKindRank = (i - one);
                }
            }
            else
            {
                break;
            }
        }

        if ((threeOfKindRank >= 0) && (twoOfKindRank >= 0))
        {
            result = "Full House: " + Card.RankString(threeOfKindRank) + "'s full of " + Card.RankString(twoOfKindRank) + "'s";
        }

        return result;
    }

    //Evaluate Flush
    private String Flush(int[] suitCounter)
    {
        String result = "";

        if (suitCounter[0] > 4 || suitCounter[1] > 4 ||
                suitCounter[2] > 4 || suitCounter[3] > 4)
        {
            for (int i = availableCards.length - 1; i > 3; i--)
            {
                if (availableCards[i].getSuit() == availableCards[i - one].getSuit() &&
                        availableCards[i].getSuit() == availableCards[i - two].getSuit() &&
                        availableCards[i].getSuit() == availableCards[i - three].getSuit() &&
                        availableCards[i].getSuit() == availableCards[i - four].getSuit()){
                    //Found Flush, break and return.
                    result = "Flush!! " + Card.RankString(availableCards[i].getValue()) + " high of " + Card.SuitString(availableCards[i].getSuit());
                    break;
                }
            }
        }
        return result;
    }

    //Evaluate Straight
    private String Straight(int[] rankCounter)
    {
        String result = "";

        for (int i = rankCounter.length; i > 4; i--)
        {
            if ((rankCounter[i - 1] > 0) &&
                    (rankCounter[i - 2] > 0) &&
                    (rankCounter[i - 3] > 0) &&
                    (rankCounter[i - 4] > 0) &&
                    (rankCounter[i - 5] > 0)){
                result = "Straight " + Card.RankString(i - 1) + " high";
                break;
            }
        }
        return result;
    }

    //Evaluate Three of a kind
    private String ThreeOfAKind(int[] Rank)
    {
        String result = "";

        for (int i = Rank.length; i > 0; i--)
        {
            if (Rank[i - 1] > 2)
            {
                result = "Three of a Kind " + Card.RankString(i - 1) + "'s";
                break;
            }
        }
        return result;
    }

    //Evaluate Two pair
    private String TwoPair(int[] Rank)
    {
        String result = "";
        int FirstPair = -1;
        int SecondPair = -1;

        for (int i = Rank.length; i > 0; i--)
        {
            if ((FirstPair < 0) || (SecondPair < 0))
            {
                if (((Rank[i - one]) > 1) && (FirstPair < 0))
                {
                    FirstPair = (i - one);
                }
                else if ((Rank[i - one]) > 1)
                {
                    SecondPair = (i - one);
                }
            }
            else
            {
                break;
            }
        }

        //populate output
        if ((FirstPair >= 0) && (SecondPair >= 0))
        {
            if (SecondPair == 0)
            {
                result = "Two Pair: " + Card.RankString(SecondPair) + "'s and " + Card.RankString(FirstPair) + "'s";
            }
            else
            {
                result = "Two Pair: " + Card.RankString(FirstPair) + "'s and " + Card.RankString(SecondPair) + "'s";
            }
        }

        return result;
    }

    //Evaluate One
    private String OnePair(int[] Rank)
    {
        String result = "";

        for (int i = Rank.length; i > 0; i--)
        {
            if((Rank[i - one]) > 1)
            {
                result = "One Pair: " + Card.RankString(i - one) + "'s";
                break;
            }
        }
        return result;
    }

    //Evaluate High
    private String HighCard(int[] Rank)
    {
        String result = "";

        for (int i = Rank.length; i > 0; i--)
        {
            if((Rank[i - one]) > 0)
            {
                result = "High Card: " + Card.RankString(i - one);
                break;
            }
        }
        return result;
    }
}
