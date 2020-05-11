package entities.heros;
import java.util.ArrayList;

import entities.cards.Card;
import game.Main;
import logs.Log;

public class Warlock extends Hero {
    private int HP=30;
    private boolean isopen=false;
    private int heroPowerUsed=0,heroPowerNumPermission=1;
    private HeroPower heroPower=new HeroPower("HP",2," random(if minion exists +1/+1 or add one card)");
    private String[] cards=new String[15];
    private String[] specialCards=new String[2];
    private String heroName="WARLOCK";
    private SpecialPower specialPower=new SpecialPower("WARLOCK","HP=35");
    public Warlock()
    {
        fillCards();
    }
    public void setCard()
    {

    }
    @Override
    public int getHeroPowerNumPermission() {
       return heroPowerNumPermission;
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
    public String getHeroName()
    {
        return this.heroName;
    }
    public void fillCards()
    {
        cards[0]="Witchwood Imp";
        cards[1]="Fungal Fortunes";
        cards[2]="Silence";
        cards[3]="Inner Rage";
        cards[4]="Warpath";
        cards[5]="Baron Geddon";
    }
    public boolean isopen(){return isopen;}
    @Override
    public String[] getCards() {
        return cards;
    }

    @Override
    public String[] getSpecialCards() {
        return specialCards;
    }

    @Override
    public boolean setCards(String name) {
        try{
            int num=0;
            for(int i=0;i<15;i++)
            {
                if(this.cards[i].equals(name)) {
                    num++;
                }

            }
            for(int i=0;i<15;i++)
            {
                if(this.cards[i]==null&&((num==1)||notexistinnow().contains(name)))
                {
                    this.cards[i]=name;
                    return true;
                }
            }}
        catch (Exception e)
        {
            Log.LOGGER.finest("error  "+e.getStackTrace());

        }
        return false;
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
        Log.LOGGER.finest("error  "+e.getStackTrace() );
    }
        return false;
    }

    @Override
    public void setDefense(int num) {
        HP+=num;
    }

    public int getHP()
    {
        return HP;
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

            }}
        catch(Exception e)
        {
            Log.LOGGER.finest("error  "+e.getStackTrace() );

        }
        return ans;
    }
}
