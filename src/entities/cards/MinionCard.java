package entities.cards;


import entities.heros.HeroClass;

public  class MinionCard extends Card {
    private int HP=0;
    private int Attack=0;
    public MinionCard (String name, String description, Rarity rarity, CardType type, HeroClass heroClass, int manaCost, int HP, int Attack, int cardCost)
    {
        super( name,description,rarity, type, heroClass,manaCost,cardCost);
this.HP=HP;
this.Attack=Attack;

    }
    public int getHP()
    {
        return HP;
    }
public void setHP(int HP)
{
    this.HP=HP;
}
}