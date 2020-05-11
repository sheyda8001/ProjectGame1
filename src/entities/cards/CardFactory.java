package entities.cards;

import com.google.gson.Gson;
import entities.heros.HeroClass;

import java.io.FileWriter;
import java.io.IOException;

public class CardFactory {


    public static void makeMinion(String name, String description, Rarity rarity, CardType type, HeroClass heroClass, int manaCost, int HP, int Attack, int cardCost)
    {
        MinionCard minioncard=new MinionCard(name,description,rarity,type,heroClass,manaCost,HP,Attack,cardCost);
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("cards\\"+name+".json")) {
            gson.toJson(minioncard, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void makespell(String name, String description, Rarity rarity, CardType type, HeroClass heroClass, int manaCost, int cardCost)
    {

        SpellCard spellcard=new SpellCard(name,description,rarity,type,heroClass,manaCost,cardCost);
        Gson gson = new Gson();




        try (FileWriter writer = new FileWriter("cards\\"+name+".json")) {
            gson.toJson(spellcard, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
