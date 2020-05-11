package interfaces;

import entities.Player;
import entities.cards.Card;
import entities.heros.HeroClass;
import game.Main;

import java.util.ArrayList;

public class StorePlayer {
private static StorePlayer instance=null;
private Player player=null;
private StorePlayer()
{
player= Main.getPlayer();
}

public static StorePlayer getInstance()
{
    if(instance==null)
    {
        instance=new StorePlayer();
    }
    return instance;
}
public ArrayList<String> canBuy()
{
    return this.player.getSt().canBuy();
}
public ArrayList<String> getCards()
{
    return this.player.getSt().getCards();
}
public ArrayList<String> getCardsWithType(HeroClass heroClass)
{
    return this.player.getSt().getCardsWithType(heroClass);
}
public ArrayList<String> getWholeCardsPlayer()
{
    return this.player.getrWholeCards();
}
}
