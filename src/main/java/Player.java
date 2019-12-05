class Player
{
    private Card[] ArrayofCards = new Card[2];

    //constructor
    Player()
    {
    }

    //methods
    void setCard(Card card, int cardNum)
    {
        ArrayofCards[cardNum] = card;
    }

    Card getCard(int cardNum)
    {

        return ArrayofCards[cardNum];
    }

    int holeCardsSize()
    {

        return ArrayofCards.length;
    }

}
