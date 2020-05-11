package interfaces;

import entities.Player;
import entities.cards.Card;
import entities.decks.Deck;
import entities.decks.DeckValidMaker;
import entities.heros.Hero;
import entities.heros.HeroClass;
import game.Main;
import logs.Log;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class DeckPlayer {
    private Player player ;
    private static DeckPlayer deckPlayer;
    private DeckPlayer()
    {
        this.player= Main.getPlayer();
    }
    public static DeckPlayer getInstance()
    {
        if(deckPlayer==null)
        {
            deckPlayer=new DeckPlayer();
            return deckPlayer;
        }
        else{
            return deckPlayer;
        }
    }
    public ArrayList<String> getPlayerDeck(String name)
    {
        ArrayList<String> cards=new ArrayList<>();
        try (FileReader reader = new FileReader("usersFile\\" + player.getUser() + ".json")) {
            FileReader reade = new FileReader("usersFile\\" + player.getUser() + ".json");
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(reade);
            reader.close();
            reade.close();
            JSONArray jsonArray = (JSONArray) jsonObject.get(name);
            Iterator<String> iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                cards.add((iterator.next()));
            }
            return cards;
        }
        catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            Log.LOGGER.finest("error  "+e.getStackTrace() );
        }
        return cards;
    }
    public ArrayList<Deck> getOpenedDecksName()
    {
        return player.getDecksName();
    }
    public int getIndexOf(String deckName)
    {
        boolean flag=false;
        int i1=0;
        for(;i1<player.getDecks().size();i1++) if(player.getDecks().get(i1).getName().equals(deckName)) {flag=true; break;}
        if(flag)
        return i1;
        else
            return -1;
    }
    public boolean addCard(String card,String deckName)
    {
System.out.println("deckn"+deckName);
System.out.println("gl"+ player.getDeck(deckName).getCards().toString());

ArrayList<String> cards=player.getDecks().get(getIndexOf(deckName)).getCards();

if(getNumCardInDeck(deckName,card)<2) return player.getDeck(deckName).addCard(card);
else{
        return false;
}
    }
    public int getNumCardInDeck(String deckName,String cardName)
    {
        int n=0;
        ArrayList<String> cards=player.getDecks().get(getIndexOf(deckName)).getCards();
for(int i=0;i<cards.size();i++)
{
    if(cards.get(i).equals(cardName)) n++;
}
return n;
    }
    public boolean removeCard(String card,String deckName)
    {
        ArrayList<String> cards=player.getDecks().get(getIndexOf(deckName)).getCards();
if(cards.contains(card))
{
    return player.getDeck(deckName).removeCard(cards.indexOf(card));
}
return false;
    }
    public ArrayList<String> getCardsCanAddTo(Deck deck)
    {
        ArrayList<String> list=new ArrayList<>();
        for(int i=0;i<player.getrWholeCards().size();i++)
        {
            HeroClass heroClass =Card.getInstance(player.getrWholeCards().get(i)).getHeroClass();
            if((heroClass.equals(deck.getHeroClass())||heroClass.equals(HeroClass.NEUTRAL))&&getNumCardInDeck(deck.getName(),player.getrWholeCards().get(i))<2)
            {
                list.add(player.getrWholeCards().get(i));
            }
        }
        return list;
    }
    public boolean removeDeck(String deckName)
    {
        if(player.getDecks().size()>1){
        player.getDecks().remove(player.getDeck(deckName));
        return true;}
        else{
            return false;
    }
    }
    public boolean changeName(String name,String deckName)
    {
        if(getIndexOf(name)==-1){
        player.getDecks().get(getIndexOf(deckName)).changeName(name);
        return true;}
        else{
            return false;
        }

    }
    public boolean changeHero(HeroClass heroClass,String deckName)
    {
        return player.getDecks().get(getIndexOf(deckName)).changeHero(heroClass);
    }
    public Deck getDeck(String deckName)
    {
        return player.getDeck(deckName);
    }
    public void setSelectedDeckPlayer(Deck deck)
    {
        player.setSelectedDeck(deck);
        player.setSelectedHero(deck.getHeroClass());
    }
    public Deck getSelectedDeck()
    {
        return player.getSelectedDeck();
    }
    public ArrayList<Deck> getSortedDecks()
    {
        DeckValidMaker deckValidMaker=new DeckValidMaker(player.getDecks());
        return deckValidMaker.getDecks();
    }
}
