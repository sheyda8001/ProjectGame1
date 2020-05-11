package entities.cards;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import entities.CourseCreator;
import entities.Entity;
import entities.heros.HeroClass;
import logs.Log;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public abstract class Card  {
    private String description = "";
    private final CardType cardType;
    private final Rarity rarity;
    private HeroClass heroClass;
    private boolean collectible = true;

    private final String name;
    private int manaCost;
    private int cardCost;
    public Card(String name, String description, Rarity rarity, CardType type, HeroClass heroClass, int manaCost, int cardCost) {

        this.name=name;

        setDescription(description);
        setCollectible(collectible);
        cardType = type;
        this.rarity = rarity;
        this.heroClass = heroClass;
        setManaCost(manaCost);
        this.cardCost=cardCost;}



    public static Card getInstance(String name)  {
        Card c=null;
       // CardTypeToString cdf=new CardTypeToString();
        // Gson gson = new Gson();
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Card.class, new CourseCreator());
            Gson gson = gsonBuilder.create();
            Card object = gson.fromJson(new FileReader("cards\\" + name + ".json"), MinionCard.class);

            return object;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(Exception e)
        {
            Log.LOGGER.finest("error "+e.getStackTrace());
        }


        return c;
    }



    public int getCardCost(){return cardCost;}
    public void setManaCost(int manaCost)
    {
        this.manaCost=manaCost;
    }
    public CardType getCardType() {
        return cardType;
    }
public int getManaCost(){
        return this.manaCost;
}
    public HeroClass getHeroClass() {
        return heroClass;
    }
    public String getDescription() {
        return description;
    }
    public Rarity getRarity() {
        return rarity;
    }
    public boolean isCollectible() {
        return collectible;
    }
    public void setCollectible(boolean collectible) {
        this.collectible = collectible;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public  String getName()
    {
        return this.name;
    }
//public static source.Cards.Card getCard(String name)
//{
//    Gson gson = new Gson();
//    source.Cards.Card card;
//    try (Reader reader = new FileReader("cards\\"+name+".json")) {
//
//        // Convert JSON File to Java Object
//        card = gson.fromJson(reader, source.Cards.Card.class);
//        return card;
//
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//
//}
public String toString( )
    {  String dec="<html>";
        try (FileReader reader = new FileReader("cards\\"+this.name+".json"))
        {
            Object obj = new JSONParser().parse(reader);

            reader.close();
            JSONObject jo = (JSONObject) obj;
            if (((String) jo.get("cardType")).equals("MINION")) {
                dec = dec + "type:" + ((String) jo.get("cardType")) + "<br>";
                dec += "name:" + ((String) jo.get("name")) + "<br>";
                dec += "cardCost:" + (jo.get("cardCost")) + "<br>";
                dec += "manaCost:" + (jo.get("manaCost")) + "<br>";
                dec += "rarity:" + ((String) jo.get("rarity")) + "<br>";
                dec += "description:" + ((String) jo.get("description")) + "<br>";
                dec += "Attack:" + (jo.get("Attack")) + "<br>";
                dec += "HP:" + (jo.get("HP")) + "<br>";
            }
            if (((String) jo.get("cardType")).equals("SPELL")) {
                dec = dec + "name:" + ((String) jo.get("name")) +"<br>";
                dec = dec + "description:" + ((String) jo.get("description")) + "<br>";
                dec = dec + "cardType:" + ((String) jo.get("cardType")) + "<br>";
                dec = dec + "rarity:" + ((String) jo.get("rarity")) + "<br>";
                dec = dec + "heroClass:" + ((String) jo.get("heroClass")) + "<br>";
                dec = dec + "manaCost:" + (jo.get("manaCost")) + "<br>";
                dec = dec + "cardCost:" + (jo.get("cardCost")) + "<br>";

            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            Log.LOGGER.finest("error  "+e.getStackTrace() );

        }
        dec+="</html>";
        return dec;
    }
}
