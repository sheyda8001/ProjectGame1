package entities.cards;

import java.util.HashMap;

public enum Rarity {
    COMMON,
    RARE,
    EPIC,
    FREE,
    LEGENDARY;
  public static HashMap<Rarity,Integer> value= new HashMap<>();
     public static void setValues()
    {
        value.put(FREE,0);
        value.put(COMMON,1);
        value.put(RARE,2);
        value.put(EPIC,3);
        value.put(LEGENDARY,4);
    }


    @Override
    public String toString() {
        return super.toString();
    }}
