package logic;

import entities.Player;
import entities.cards.Card;
import entities.cards.CardType;
import entities.cards.Weapon;
import entities.heros.HeroClass;
import game.Main;
import logs.Log;

import java.util.ArrayList;
import java.util.Random;

public class PlayManager {
    private ArrayList<Card> weapons1=new ArrayList<>();
    private int howmanyCards=1,offPrice=0;
private Player player1,player2;
private static  PlayManager playManager;
private int turn=0,wholeTurn=0;
private int mana=1;
private ArrayList<String> events;
private ArrayList<String> newCard=new ArrayList<>();
private PlayManager ()
{
player1= Main.getPlayer();
events=new ArrayList<>();
for(int i=0;i<3;i++){
    String st=getRandomCard();
player1.getNowCards().add(st);
player1.getSelectedDeck().getCards().remove(player1.getSelectedDeck().getCards().indexOf(st));
}

}
public ArrayList<Card> getWeapons1()
{
    return weapons1;
}
public void setOffPrice(int num)
{
    this.offPrice=num;
}
public static PlayManager getInstance()
{
    if(playManager==null)
    {
        playManager=new PlayManager();
    }
    return playManager;
}
public void endTurn()
{
    wholeTurn++;
    for(int i=0;i<newCard.size();i++)
    {
        newCard.remove(0);
    }
    if(mana<10)
mana++;
    else{mana=10;}
player1.setExactMana(mana);if(player1.getSelectedDeck().getCards().size()>0){
    for(int i=0;i<howmanyCards;i++) {
        String st = getRandomCard();
        if (player1.getNowCards().size() < 12) {
            player1.getNowCards().add(st);
            if(Card.getInstance(st).getCardType().equals(CardType.WEAPON)) weapons1.add(Card.getInstance(st));
        }
        newCard.add(st);
        player1.getSelectedDeck().removeCard(player1.getSelectedDeck().getCards().indexOf(st));
    }
}

//turn =(turn%2);
}
public void setHowMany(int num)
{
    this.howmanyCards=num;
}
public ArrayList<String> getNewCard()
{
    return this.newCard;
}
public ArrayList<String> getEvents()
{
    return events;
}
public void addEvent(String event)
{
    events.add(event);
}
public void playCard(Card card)
{
    if(card.getCardType().equals(CardType.WEAPON))
    {
        Weapon card1= (Weapon) card;
        if(card1.canUse(wholeTurn))
        {
            weapons1.remove(weapons1.indexOf(card.getName()));
        }
    }
    if(player1.getPlayedCard().size()<7||(!card.getCardType().equals(CardType.MINION))){
    if(player1.getMana()>=card.getManaCost()) {
        events.add(card.getName() + " " + turn+1);
        int n=player1.getNowCards().indexOf(card.getName());
        player1.getNowCards().remove(n);
        if(card.getCardType().equals(CardType.MINION)){
        player1.setPlayedCard(card.getName());}
        player1.setMana(card.getManaCost()-offPrice);
    }}
}
public String getRandomCard()
{

    String name="";
    Random rand=new  Random();
    int i=Math.abs(rand.nextInt(player1.getSelectedDeck().getCards().size()));
    name+=player1.getSelectedDeck().getCards().get(i);
    Log.LOGGER.finest("get card"+ name);

    return name;
}
public ArrayList<String> getPlayer1HandCards()
{
    return player1.getNowCards();
}
public ArrayList<String> getPlayer1PlaayedCards()
{
    return  player1.getPlayedCard();
}
public void addPlayedCardPlayer1(String name)
{
    player1.setPlayedCard(name);
}
public int getMana()
{
    return mana;
}
public int getManaPlayer1()
{
    return player1.getMana();
}
public int getHPPlayer1()
{
    return player1.getSelectedHero().getHP();
}
public int getDeckNum1()
{
    return player1.getSelectedDeck().getCards().size();
}
public HeroClass getPlayer1HeroClass()
{
    return player1.getSelectedDeck().getHeroClass();
}
public Player getPlayer1()
{
    return player1;
}
}
