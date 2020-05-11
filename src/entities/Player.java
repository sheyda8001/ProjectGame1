package entities;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import entities.decks.Deck;
import entities.heros.*;
import entities.passives.Passive;
import logic.store.Store;
import logs.Log;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public  class Player {
    private ArrayList<Passive> passive =new ArrayList<>();
    private long coins;
    private long id;
    private Store st=new Store(this);
    private ArrayList<String> wholeCard=new ArrayList<>();
    private ArrayList<String> nowCard=new ArrayList<>();
    private ArrayList<String> playedCard=new ArrayList<>();
    private int manaPermission=0;
    private String user,pass;
    private int mana=1;
    private Hero selectedHero;
    private Hero playerHero;
    private Deck selectedDeck;
    private ArrayList<String> openedHeros=new ArrayList<String>();
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
private ArrayList<Deck> decks=new ArrayList<>();
    public Player(String user,String pass)
    {
        this.user=user;
        this.pass=pass;
        reaMainFile();
        readDeckFile();
        LOGGER.setLevel(Level.FINEST);
        LOGGER.fine("source.SignIn:"+user);
    }
    public int getManaPermission()
    {
        return manaPermission;
    }
    public void changeManaPermission(int i)
    {
        mana+=i;
    }
    public void setPassive(Passive passive)
    {
        this.passive.add(passive);
    }
    public ArrayList<String> getPlayedCard() {
        return playedCard;
    }
public ArrayList<Passive> getPassive()
{
    return passive;
}
    public void setPlayedCard(String playedCard) {
        this.playedCard.add( playedCard);
    }
    public void setSelectedHero(HeroClass hero)
    {
        this.selectedHero=Hero.getHero(hero);
    }
    public void setSelectedDeck(Deck deck)
    {
        this.selectedDeck=deck;
    }
   public Deck getSelectedDeck()
   {
       return this.selectedDeck;
   }
    public Store getSt()
    {
        return this.st;
    }
    public void addWholeCards(String name)
    {
        this.wholeCard.add(name);
    }
    public void setCoins(int coin)
    {
        this.coins=coin;
    }
    public int getCoins()
    {
        return (int)this.coins;
    }
    //    public String getNowCardsToString()
//    {
//        String st="";
//for(int i=0;i<getNowCards().size();i++)
//{
//    st+=getNowCards().get(i)+"    ";
//}
//return st;
//    }
    public int getNowCardsSize()
    {int num=0;try{
        for(int i=0;i<getNowCards().size();i++)
        {
            if(getNowCards().get(i)!=null) num++;
        }}
    catch(Exception e)
    {
        Log.LOGGER.finest("error  "+e.getStackTrace() );

    }
        return num;
    }
    public ArrayList<String> getNowCards()
    {
        return this.nowCard;
    }
    public ArrayList<String> getOpenedHeros()
    {
        return  this.openedHeros;
    }
    public Hero getSelectedHero()
    {
        return this.selectedHero;
    }
    public boolean setHero(HeroClass heroClass)
    {
        if(heroClass.equals(HeroClass.MAGE)&&this.openedHeros.contains(heroClass))
        {try{
            this.playerHero=new Mage();
        }
        catch(Exception e)
        {
            Log.LOGGER.finest("error  "+e.getStackTrace() );
        }
            return true;
        }
        if(heroClass.equals(HeroClass.WARLOCK)&&this.openedHeros.contains(heroClass))
        {try{
            this.playerHero=new Warlock();
          }
        catch (Exception e)
        {
            Log.LOGGER.finest("error  "+e.getStackTrace() );

        }
            return true;
        }
        if(heroClass.equals(HeroClass.ROGUE)&&this.openedHeros.contains(heroClass))
        {try{
            this.playerHero=new Rouge();
           }
        catch (Exception e)
        {
            Log.LOGGER.finest("error  "+e.getStackTrace() );
        }
            return true;
        }
        return false;
    }

    public String getNowCardsToString()
    {String st="";
        try{
            for(int i=0;i<getNowCards().size();i++)
            {
                if(getNowCards().get(i)!=null)
                {
                    st+=getNowCards().get(i)+"    ";
                }
            }}
        catch(Exception e)
        {
            Log.LOGGER.finest("error  "+e.getStackTrace() );
        }
        return st;
    }
//    private void setNowCard() {
//        for(int i=0;i<15;i++)
//        {
//            this.nowCard.add(this.selectedHero.getCards()[i]);
//        }
//    }
    public String getSelectedHeroName()
    {
        if(selectedHero!=null)
            return selectedHero.getHeroName();
        else{
            return null;
        }
    }
    public ArrayList<String> getrWholeCards()
    {
        return this.wholeCard;
    }
    public String wholeCardsToString()
    {
        return "your wholecards are"+wholeCard.toString();
    }
    public String getUser()
    {
        return this.user;
    }
    public  Player getPlayer()
    {
        return this;
    }
    public void setMana(int y)
    {
        mana-=y;
    }
    public void setExactMana(int y)
    {
        mana=y;
    }
    public int getMana()
    {
        return mana;
    }
    public int getid()
    {
        return (int)this.id;
    }
    public ArrayList<Deck> getDecksName()
    {
        return this.decks;
    }
public ArrayList<Deck> getDecks()
{
    return this.decks;
}
public Deck getDeck(String deckName)
{
    int i1=0;
    for(;i1<this.decks.size();i1++) if(this.decks.get(i1).getName().equals(deckName)) break;
    return  this.decks.get(i1);
}
        private void reaMainFile() {
        try (FileReader reader = new FileReader("usersFile\\" + user + ".json")) {
            FileReader reade=   new FileReader("usersFile\\" + user + ".json");
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(reade);
            Object obj = new JSONParser().parse(reader);
            reader.close();
            reade.close();
            JSONObject jo = (JSONObject) obj;
            this.id=(long)jsonObject.get("ID");
            this.coins = (long) jsonObject.get("Coins");
            JSONArray jsonArray = (JSONArray) jsonObject.get("WholeCards");
            Iterator<String> iterator = jsonArray.iterator();
            while(iterator.hasNext()) {
                this.wholeCard.add((iterator.next()));
            }
            jsonArray = (JSONArray) jsonObject.get("OpenedHeros");
            iterator = jsonArray.iterator();
            while(iterator.hasNext()) {
                this.openedHeros.add((iterator.next()));
            }
            jsonArray=(JSONArray) jo.get("NowCards");
            for(int i=0;i<jsonArray.size();i++)
            {
                this.nowCard.add((String) jsonArray.get(i));
            }
            jsonArray=(JSONArray) jo.get("Decks");
            for(int i=0;i<jsonArray.size();i++)
            {
                this.decks.add(Deck.getInstance((String) jsonArray.get(i)));
            }
        } catch (ParseException e) {
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
    }
    private void readDeckFile()
    {
        try (FileReader reader = new FileReader("usersdeck\\" + user + ".json")) {
            FileReader reade=   new FileReader("usersdeck\\" + user + ".json");
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(reade);
            Object obj = new JSONParser().parse(reader);
            reader.close();
            reade.close();
            JSONObject jo = (JSONObject) obj;
            JSONArray jsonArray =(JSONArray) jo.get("Decks");

            for(int i=0;i<jsonArray.size();i++)
            {

                ArrayList<String> card=new ArrayList<>();
                ArrayList<Integer> num1=new ArrayList<>();
                JSONArray cards =(JSONArray) jo.get(jsonArray.get(i));
                JSONArray numCards=(JSONArray) jo.get((String)jsonArray.get(i)+"nums");
                Iterator<String> iterator = cards.iterator();
                Iterator<String> iterator1 = numCards.iterator();
                while (iterator.hasNext()) {
                    card.add(iterator.next());
                    num1.add(Integer.valueOf(iterator1.next()));
                }

//                for(int i1=0;i1<cards.size();i1++)
//                {
//                    card.add((String)cards.get(i1));
//                    numCards.add((Integer)numCards.get(i1));
//                }
System.out.println(card.toString());
                int whole=Integer.valueOf((String)jsonObject.get((String)jsonArray.get(i)+"whole"));

                int wins=Integer.valueOf((String)jsonObject.get((((String)jsonArray.get(i))+"wins")));
                System.out.println(wins+" "+whole+" "+(String)jsonArray.get(i));
                HeroClass hero=HeroClass.valueOf((String)jsonObject.get((String)jsonArray.get(i)+"hero"));
                Deck deck=new Deck((String)jsonArray.get(i),hero,wins,whole,card,num1);
                this.decks.add(deck);
            }
    } catch (ParseException e) {
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

    }

}

