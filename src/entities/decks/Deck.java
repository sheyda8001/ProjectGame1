package entities.decks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import entities.CourseCreator;
import entities.Entity;
import entities.cards.Card;
import entities.cards.CardType;
import entities.cards.MinionCard;
import entities.cards.Rarity;
import entities.heros.HeroClass;
import logs.Log;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

public class Deck  {
    private String name="";
    private HeroClass heroClass;
    private int wholePlays=0;
    private ArrayList<Integer> numberCards=new ArrayList<>();
private ArrayList<String> cards=new ArrayList<>();
private int wins=0;
    public Deck(String name , HeroClass heroClass) {
        this.name=name;
        this.heroClass=heroClass;
    }
public Deck(String name,HeroClass heroClass,int wins,int wholePlays,ArrayList<String> cards,ArrayList<Integer> numberCards)
{
    this.name=name;
    this.heroClass=heroClass;
    this.cards=cards;
    this.wholePlays=wholePlays;
    this.wins=wins;
    this.numberCards=numberCards;
}
public int getWins()
{
    return wins;
}
public int getWholePlays()
{
    return wholePlays;
}
public ArrayList<Integer> getNumberCards()
{
    return numberCards;
}
public float getMddle()
{
    int sum=0;
    for(int i=0;i<cards.size();i++)
    {
        sum+=Card.getInstance(cards.get(i)).getManaCost();
    }
    return sum/cards.size();
}
public String getDesc()
{
    String st="<html>";
    st+=name+"<br>";
    st+=wins/wholePlays+""+"<br>";
    st+=wins+""+"<br>";
    st+=wholePlays+""+"<br>";
    st+=getMddle()+""+"<br>";
    st+=heroClass.toString()+""+"<br>";
st+=getBestCard()+"<br>";
    return st;
}
public String getBestCard()
{
ArrayList<String> list=new ArrayList<>();
int max=0;
for(int i=0;i<numberCards.size();i++)
    {
        if(max<numberCards.get(i))
            max=numberCards.get(i);
    }
for(int i=0;i<numberCards.size();i++)
{
    if(max==numberCards.get(i))
    {
        list.add(cards.get(i));
    }
}
if(list.size()==1)
{
    return list.get(0);
}
else{
    ArrayList<String> list1=new ArrayList<>();
   Rarity.setValues();
    max=Rarity.value.get(Card.getInstance(list.get(0)).getRarity());
    for(int i=0;i<list.size();i++)
    {
        if(max<Rarity.value.get(Card.getInstance(list.get(i)).getRarity()))
            max=Rarity.value.get(Card.getInstance(list.get(i)).getRarity());
    }
    for(int i=0;i<list.size();i++)
    {
        if(max==Rarity.value.get(Card.getInstance(list.get(i)).getRarity()))
        {
            list1.add(list.get(i));
        }
    }
    if(list1.size()==1)return list1.get(0);
    else{
        ArrayList<String> list2=new ArrayList<>();
        max=Card.getInstance(list1.get(0)).getManaCost();
        for(int i=0;i<list1.size();i++)
        {
            if(max<Card.getInstance(list1.get(i)).getManaCost()) max=Card.getInstance(list1.get(i)).getManaCost();
        }
        for(int i=0;i<list1.size();i++)
        {
            if(max==Card.getInstance(list1.get(i)).getManaCost()) list2.add(list1.get(i));
        }
        if(list2.size()==1) return list2.get(0);
        else{
            for(int i=0;i<list2.size();i++)
            {
                if(Card.getInstance(list2.get(i)).getCardType().equals(CardType.MINION)) return list2.get(i);
            }
            return list2.get(0);
        }
    }

}
}
    public  static Deck getInstance(String name)
    {
        Deck d=null;
        // Deck d=new Deck(name.substring(name.indexOf(" ")+1), HeroClass.valueOf(name.substring(0,name.indexOf(" "))));
        try (FileReader reader = new FileReader("decks\\" + name + ".json")) {
            FileReader reade = new FileReader("decks\\" + name + ".json");
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(reade);
            Object obj = new JSONParser().parse(reader);
            reader.close();
            reade.close();
            JSONObject jo = (JSONObject) obj;
            String n=(String) jsonObject.get("name");
            HeroClass hero=HeroClass.valueOf((String)jsonObject.get("heroClass"));

            d=new Deck(n,hero);

            JSONArray jsonArray = (JSONArray) jsonObject.get("cards");
            Iterator<String> iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                d.addCard(iterator.next());
            }
            return d;
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Log.LOGGER.finest("error  " + e.getStackTrace());
        }
        return d;
    }


    public boolean removeCard(int cardPlace)
    {
        if( cards.size()<=cardPlace)
        {
           return false;
        }
            else {
            cards.remove(cardPlace);
            return true;
        }
    }
    public String getName(){return this.name;}
    public boolean addCard(String card)
    {
        if(cards.size()>=15)
        {
            return false;
        }
        else{
            cards.add(card);
            return true;
        }
    }
    public HeroClass getHeroClass()
    {
        return this.heroClass;
    }
    public ArrayList<String> getCards()
    {
        return this.cards;
    }
    public void changeName(String name)
    {
        this.name=name;
    }
    public boolean changeHero(HeroClass heroClass)
    {
        for(int i=0;i<cards.size();i++)
        {
            if(Card.getInstance(cards.get(i)).getHeroClass().equals(this.heroClass)) return false;
        }
        this.heroClass=heroClass;
        return true;
    }
}
