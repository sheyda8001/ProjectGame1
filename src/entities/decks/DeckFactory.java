package entities.decks;

import com.google.gson.Gson;
import entities.cards.MinionCard;

import java.io.FileWriter;
import java.io.IOException;

public class DeckFactory {
    private Deck deck ;
    public  boolean makeDeck(Deck deck)
    {
this.deck=deck;
return makeFile();
    }

    private boolean makeFile()
    {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("decks\\"+deck.getName()+".json")) {
            System.out.println(deck.getCards().toString());
            gson.toJson(deck, writer);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
