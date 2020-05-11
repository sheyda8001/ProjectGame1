package interfaces;

public class CardPlayer {
    private static CardPlayer cardPlayer;
    private CardPlayer()
    {

    }
    public static CardPlayer getInstance()
    {
        if(cardPlayer==null)
        {
            cardPlayer=new CardPlayer();
        }
       return  cardPlayer;
    }
}
