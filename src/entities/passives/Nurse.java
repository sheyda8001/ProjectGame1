package entities.passives;

import entities.cards.Card;
import entities.cards.CardType;
import entities.cards.MinionCard;
import logic.PlayManager;

public class Nurse extends Passive {
    private PlayManager playManager =PlayManager.getInstance();
    @Override
    public String getJob() {
        return "reborn minion";
    }

    @Override
 public   void doTheJob() {
reborn(getTheCard());
    }

    private void reborn(Card card){};
    private Card getTheCard()
    {
        Card card=null;
        int min=50;
        for(int i=0;i<playManager.getPlayer1HandCards().size();i++)
        {
            if(Card.getInstance(playManager.getPlayer1HandCards().get(i)).getCardType().equals(CardType.MINION))
            {
                MinionCard minionCard =(MinionCard) MinionCard.getInstance(playManager.getPlayer1HandCards().get(i));
                if(minionCard.getHP()<min){
                    min=minionCard.getHP();
                card=minionCard;}
            }
        }
        return card;
    }
}
