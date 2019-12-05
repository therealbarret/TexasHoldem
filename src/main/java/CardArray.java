class arrayofCards
{
    private Card[] boardCards = new Card[5];
    private Card[] burnCards = new Card[3];

    //constructor
    arrayofCards()
    {
    }

    //methods
    void setBoardCard(Card card, int cardNum)
    {
        this.boardCards[cardNum] = card;
    }

    Card getBoardCard(int cardNum)
    {
        return this.boardCards[cardNum];
    }

    void setBurnCard(Card card, int cardNum)
    {
        this.burnCards[cardNum] = card;
    }

    private Card getBurnCard(int cardNum)
    {
        return this.burnCards[cardNum];
    }

    int boardSize()
    {
        return boardCards.length;
    }

    void printBoard()
    {
        System.out.println("The board contains the following cards:");
        for(int i = 0; i < boardCards.length; i++)
        {
            System.out.println(i + 1 + ": " + getBoardCard(i).printCard());
        }
        System.out.println("\n");
    }

    void printBurnCards()
    {
        System.out.println("The burn cards are:");
        for(int i = 0; i < burnCards.length; i++)
        {
            System.out.println(i + 1 + ": " + getBurnCard(i).printCard());
        }
        System.out.println("\n");
    }
}
