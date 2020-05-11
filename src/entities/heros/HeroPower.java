package entities.heros;

public class HeroPower {
    private int Cost;
private String where;
private String job="";
    public HeroPower (String where,int manaCost,String job)
    {
        this.Cost=manaCost;
this.where=where;
this.job=job;
    }
public String getWhere()
{
    return  where;
}
    public int getCost()
    {
        return this.Cost;
    }
    public void setCost(int cost){
        this.Cost=cost;
    }
public void doTheJob()
{

}
}
