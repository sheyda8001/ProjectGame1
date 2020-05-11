package entities.heros;




import entities.cards.Card;
import game.Main;
import logs.Log;

import java.util.ArrayList;


public class Rouge extends Hero {
    private int HP=30;
    private int heroPowerUsed=0,heroPowerNumPermission=1;
    private boolean isopen=false;
    private String[] cards=new String[15];
private HeroPower heroPower=new HeroPower("mana",3,"steal a card from enemy deck if update heroPower steal from deck and hand");
    private String[] specialCards=new String[2];
    private String heroName="ROUGE";
    private SpecialPower specialPower=new SpecialPower("ROUGE","has weapon heropower high,when card from itself or neutral low 2 mana");
    public boolean isopen()
    {
        return isopen;
    }
    public int getHP()
    {
        return HP;
    }
    public HeroPower getHeroPower()
    {
        return this.heroPower;
    }

    @Override
    public void lowHeroPowerCost(int cost) {
        heroPower.setCost(heroPower.getCost()-cost);
    }
public void setHeroPowerNumPermission(int num){
        heroPowerNumPermission=num;
}

    @Override
    public int getHeroPowerNumPermission() {
        return heroPowerNumPermission;
    }

    public boolean setCards(String name)
    {
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
        }
        return false;
    }
    @Override
    public void setDefense(int num) {
        HP+=num;
    }
    private void fillCards()
    {
        cards[0]="Friendly Smith";
        cards[1]="Inner Rage";
        cards[2]="Baron Geddon";
        cards[3]="The Dark Portal";
        cards[4]="Fungal Fortunes";
        cards[5]="Serpent War";

    }
    public String getHeroName()
    {
        return this.heroName;
    }
    @Override
    public String[] getCards() {
        return cards;
    }

    @Override
    public String[] getSpecialCards() {
        return specialCards;
    }
    public void useHeroPower()
    {
        Main.player.setMana(3);
        heroPowerUsed++;
    }
    public boolean removeCards(String name)
    {try{
        for(int i=0;i<15;i++)
        {
            if(this.cards[i].equals(name)){
                this.cards[i]=null;return true;}
        }}
    catch (Exception e)
    {
        Log.LOGGER.finest("error  "+e.getStackTrace() );

    }
        return false;
    }
    private ArrayList<String> notexistinnow()
    {
        ArrayList<String> ans =new ArrayList<>();
        try{
            for(int i=0;i<Main.player.getrWholeCards().size();i++)
            {
                if((Card.getInstance(Main.player.getrWholeCards().get(i)).getHeroClass().equals(Main.player.getSelectedHeroName())||Card.getInstance(Main.player.getrWholeCards().get(i)).getHeroClass().equals(HeroClass.NEUTRAL))&&!Main.player.getNowCards().contains(Main.player.getrWholeCards().get(i)))
                {
                    ans.add(Main.player.getrWholeCards().get(i));
                }

            }}
        catch (Exception e)
        {
            Log.LOGGER.finest("error  "+e.getStackTrace() );

        }
        return ans;
    }
}
