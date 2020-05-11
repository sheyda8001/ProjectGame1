package entities.cards;

import entities.heros.HeroClass;

import java.util.ArrayList;

public class Weapon extends Card {
    private int HP=0,numberUsed=0;
    private int attack=0;
    private ArrayList<Integer> numTurns=new ArrayList<>();
    public Weapon(String name, String description, Rarity rarity, CardType type, HeroClass heroClass, int manaCost, int cardCost,int HP,int attack) {
        super(name, description, rarity, type, heroClass, manaCost, cardCost);
        this.HP=HP;
        this.attack=attack;
    }
    public int getAttack()
    {
        return attack;
    }
    public void setNumberUsed(int num,int numTurn)
    {
        if(canUse(numTurn)){
        numberUsed+=num;
        numTurns.add(numTurn);}
    }
    public int getNumberUsePermission()
    {
        return HP;
    }
    public int getNumberUsed()
    {
        return numberUsed;
    }
    public boolean canUse(int numTurn)
    {
        return numberUsed<HP&&numTurns.get(numTurns.size()-1)!=numTurn;
    }

}
