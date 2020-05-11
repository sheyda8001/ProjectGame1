package logic.update;

import com.google.gson.Gson;
import entities.decks.Deck;
import entities.decks.DeckFactory;
import entities.heros.HeroClass;
import logs.Log;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DecksUpdater  {
    private ArrayList<Deck> decks = new ArrayList<>();
    private DeckFactory deckFactory = new DeckFactory();


    private void fillDecks() {
        try (FileReader reader = new FileReader("decks\\DecksToBuild.json")) {
            FileReader reade = new FileReader("decks\\DecksToBuild.json");
            int i1=reade.read();
            String st="";
            while(i1!=-1)
            {
                st+=(char)i1;
                i1=reade.read();
            }
            ArrayList<String> decksNames = new ArrayList<>();
            if(!st.equals("nothing")){
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(reade);
            Object obj = new JSONParser().parse(reader);
            reader.close();
            reade.close();
            JSONObject jo = (JSONObject) obj;
            JSONArray jsonArray = (JSONArray) jsonObject.get("decksname");
            Iterator<String> iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
               decksNames.add((iterator.next()));
            }
            for (int i = 0; i < decksNames.size(); i++) {
                Deck deck = null;
                int j = 0;
                jsonArray = (JSONArray) jsonObject.get(decksNames.get(i));
                iterator = jsonArray.iterator();
                while (iterator.hasNext()) {
                    if (j == 0) {
                       deck = new Deck(decksNames.get(i), HeroClass.valueOf(iterator.next()));
                    } else {
                        deck.addCard((iterator.next()));
                    }
                    j++;
                }
decks.add(deck);
            }}
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            Log.LOGGER.finest("error  " + e.getStackTrace());
        }

    }

    private void makeDecks() {
        for(int i=0;i<decks.size();i++) {
            if (deckFactory.makeDeck(decks.get(i))) {
                eraseFile();
            }
        }
    }

    private void eraseFile()
    {
        try {
            String st = "nothing";
            FileWriter fw = new FileWriter("decks\\DecksToBuild.json");

            fw.write(st);
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
            Log.LOGGER.finest("error " + e.getStackTrace());

        }
    }

    public void updateToMake() {
        makeDecks();
    }
    public void updateDeck(Deck deck)
    {

    }
}
