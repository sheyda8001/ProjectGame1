package entities.heros;


import entities.Entity;
import logs.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Hero  {
    public abstract String[] getCards();
    public abstract String[] getSpecialCards();
    public abstract boolean setCards(String name);
    public abstract boolean removeCards(String name);
    public abstract void setDefense(int num);
    public abstract boolean isopen();
    public abstract String getHeroName();
    public abstract int getHP();
    private static ArrayList<String> AllHeros =new ArrayList<String>();
    public abstract HeroPower getHeroPower();
    public abstract void lowHeroPowerCost(int cost);
    public abstract void setHeroPowerNumPermission(int num);
    public abstract int getHeroPowerNumPermission();
    public static ArrayList<String> getAllHero()
    {
        return AllHeros;
    }
    public static void showHeros()
    {
        try {
            File file = new File("heros\\heros.txt");    //creates a new file instance
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            String line;
            while ((line = br.readLine()) != null) {
                AllHeros.add(line);
                System.out.print(line+"   ");
            }
            fr.close();    //closes the stream and release the resources

        } catch (IOException e) {
            e.printStackTrace();
            Log.LOGGER.finest("error "+e.getStackTrace());

        }
    }
    public static ArrayList<String> getallHeros()
    {ArrayList<String> sttt=new ArrayList<>();
        try {
            File file = new File("heros\\heros.txt");    //creates a new file instance
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            String line;
            while ((line = br.readLine()) != null) {
                AllHeros.add(line);
                sttt.add(line);
            }
            fr.close();    //closes the stream and release the resources

        } catch (IOException e) {
            e.printStackTrace();
            Log.LOGGER.finest("error "+e.getStackTrace());

        }
        return sttt;
    }
    public static Hero getHero(HeroClass heroClass)
    {
        Hero h = null;
        if(heroClass.equals(HeroClass.MAGE))
        {
            h=new Mage();
            return h;
        }
        if(heroClass.equals(HeroClass.WARLOCK))
        {
            h=new Warlock();
            return h;
        }
        if(heroClass.equals(HeroClass.ROGUE))
        {
            h=new Rouge();
            return h;
        }
        if(heroClass.equals(HeroClass.PALADIN))
        {
            h=new Paladin();
            return h;
        }
        if(heroClass.equals(HeroClass.PRIEST))
        {
            h=new Priest();
            return h;
        }
        return h;
    }

}
