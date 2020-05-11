package logic.update;

import entities.Player;
import entities.decks.Deck;
import entities.heros.HeroClass;
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

public class PlayerUpdater implements Updatable {
    private Player player;
    public PlayerUpdater(Player player)
    {
        this.player=player;
    }
    public void update()
    {
updateUserFile();
updateDeckFile();
    }
    private void updateDeckFile()
    {
        try (FileReader reader = new FileReader("usersdeck\\" + player.getUser() + ".json")) {
            FileReader reade=   new FileReader("usersdeck\\" + player.getUser() + ".json");
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(reade);
            Object obj = new JSONParser().parse(reader);
            reader.close();
            reade.close();
            JSONObject jo = (JSONObject) obj;
            jo.remove("Decks");

            JSONArray decks =new JSONArray();
for(int i=0;i<player.getDecks().size();i++)
{
    decks.add(player.getDecks().get(i).getName());
}
jo.put("Decks",decks);
            for(int i=0;i<player.getDecks().size();i++)
            {
jo.remove(player.getDecks().get(i).getName());
                JSONArray cards =new JSONArray();
                for(int j=0;j<player.getDecks().get(i).getCards().size();j++)
                {
                    cards.add(player.getDecks().get(i).getCards().get(j));
                }
                jo.put(player.getDecks().get(i).getName(),cards);
                jo.remove(player.getDecks().get(i).getName()+"nums");
                JSONArray numCards=new JSONArray();
                for(int j=0;j<player.getDecks().get(i).getNumberCards().size();j++)
                {
                    numCards.add(player.getDecks().get(i).getNumberCards().get(j).toString());
                }
                jo.put(player.getDecks().get(i).getName()+"nums",numCards);

jo.remove(player.getDecks().get(i).getName()+"whole");
jo.put(player.getDecks().get(i).getName()+"whole",player.getDecks().get(i).getWholePlays());
               jo.remove(player.getDecks().get(i).getName()+"wins");
               jo.put(player.getDecks().get(i).getName()+"wins",player.getDecks().get(i).getWins());
               jo.remove(player.getDecks().get(i).getName()+"hero",player.getDecks().get(i).getHeroClass().toString());
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
    private void updateUserFile()
    {
        try (FileReader reader = new FileReader("usersFile\\" + player.getUser() + ".json")) {
            FileReader reade=   new FileReader("usersFile\\" + player.getUser() + ".json");
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(reade);
            Object obj = new JSONParser().parse(reader);
            reader.close();
            reade.close();
            JSONObject jo = (JSONObject) obj;
           jsonObject.remove("Coins");
           jsonObject.put("Coins",player.getCoins());
           JSONArray wholeCards=new JSONArray();
           for(int i=0;i<player.getrWholeCards().size();i++)
           {
               wholeCards.add(player.getrWholeCards().get(i));
           }
            jsonObject.remove("WholeCards");
           jsonObject.put("WholeCards",wholeCards);

           jsonObject.remove("OpenedHeros");
           JSONArray opend=new JSONArray();
           for(int i=0;i<player.getOpenedHeros().size();i++)
           {
               opend.add(player.getOpenedHeros().get(i));
           }
            jsonObject.put("OpenedHeros",opend);

             jo.remove("NowCards");
            JSONArray now=new JSONArray();
            for(int i=0;i<player.getNowCards().size();i++)
            {
                now.add(player.getNowCards().get(i));
            }
            jsonObject.put("NowCards",now);

            jo.remove("Decks");
            JSONArray deck=new JSONArray();
            for(int i=0;i<player.getDecks().size();i++)
            {
                deck.add(player.getDecks().get(i).getName());
            }
            jsonObject.put("Decks",deck);
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
