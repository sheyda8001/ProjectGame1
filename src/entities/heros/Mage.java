package entities.heros;
import entities.cards.Card;
import game.Main;
import logs.Log;

import java.util.ArrayList;

public class Mage extends Hero {
    private  int HP=30;
    private int heroPowerUsed=0,heroPowerNumPermission=1;
    private String[] cards=new String[15];
    private boolean isopen=true;
    private String[] specialCards=new String[2];
    private String heroName="MAGE";
    private HeroPower heroPower=new HeroPower("mana",2,"low one hp ");
    private SpecialPower specialPower= new SpecialPower("MAGE","low 2 mana in spells");
    public Mage()
    {
        fillCards();
    }
    public void setCard()
    {

    }
    @Override
    public int getHeroPowerNumPermission() {
      return   heroPowerNumPermission;
    }
    @Override
    public void setDefense(int num) {
        HP+=num;
    }
    public int getHP()
    {
        return HP;
    }
    private void fillCards()
    {
        cards[0]="The Dark Portal";
        cards[1]="Warpath";
        cards[2]="Inner Rage";
        cards[3]="Wisp";
        cards[4]="Vicious Scalehide";
        cards[5]="Polymorph";
    }
    public String getHeroName()
    {
        return this.heroName;
    }
    public boolean isopen()
    {
        return true;
    }
    public void useHeroPower()
    {
        Main.player.setMana(2);
        heroPowerUsed++;
    }
    @Override
    public String[] getSpecialCards() {
        return specialCards;
    }
    private ArrayList<String> notexistinnow()
    {
        ArrayList<String> ans =new ArrayList<>();
        try{
            for(int i = 0; i< Main.player.getrWholeCards().size(); i++)
            {
                if((Card.getInstance(Main.player.getrWholeCards().get(i)).getHeroClass().equals(Main.player.getSelectedHeroName())||Card.getInstance(Main.player.getrWholeCards().get(i)).getHeroClass().equals(HeroClass.NEUTRAL))&&!Main.player.getNowCards().contains(Main.player.getrWholeCards().get(i)))
                {
                    ans.add(Main.player.getrWholeCards().get(i));
                }

            }
            return ans;}
        catch(Exception e)
        {
            Log.LOGGER.finest("error "+e.getStackTrace());

        }
        return ans;
    }
    @Override
    public boolean setCards(String name) {
        try {
            int num = 0;
            for (int i = 0; i < 15; i++) {
                if (this.cards[i].equals(name)) {
                    num++;
                }

            }
            for (int i = 0; i < 15; i++) {
                if (this.cards[i] == null && ((num == 1) || notexistinnow().contains(name))) {
                    this.cards[i] = name;
                    return true;
                }
            }
        }
        catch (Exception e)
        {
            Log.LOGGER.finest("error "+e.toString());

        }
        return false;
    }

    @Override
    public String[] getCards() {
        return cards;
    }
    public boolean removeCards(String name)
    {try{
        for(int i=0;i<15;i++)
        {
            if(this.cards[i].equals(name)){
                this.cards[i]=null;return true;}
        }}
    catch(Exception e)
    {
        Log.LOGGER.finest("error "+e.getStackTrace());

    }
        return false;
    }
public HeroPower getHeroPower()
{
return heroPower;
}

    @Override
    public void lowHeroPowerCost(int cost) {
        heroPower.setCost(heroPower.getCost()-cost);
    }
    public void setHeroPowerNumPermission(int num){
        heroPowerNumPermission=num;
    }
}
