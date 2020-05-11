package entities.decks;

import java.util.ArrayList;

public class DeckValidMaker {
    private ArrayList<Deck> list=new ArrayList<>();
    private Deck[] decks;
    public DeckValidMaker(ArrayList<Deck> list)
    {
        this.list=list;
        sort();
    }
    public ArrayList<Deck> getDecks()
    {
        return list;
    }
    public void addDeck(Deck deck)
    {
        list.add(deck);
        sort();
    }
    public ArrayList<Deck> getList()
    {

return list;    }
private void sort(){
        ArrayList<Deck> list1=new ArrayList<>();
        if(list.size()>0)
        list1.add(list.get(0));
for(int i=1;i<list.size();i++)
{
    boolean flag=false;
    for(int j=0;j<list1.size();j++){
    if((list.get(i).getWins()/list.get(i).getWholePlays())>(list1.get(j).getWins()/list1.get(j).getWholePlays()))
    {
        list1.add(j,list.get(i));
        flag=true;
        break;
    }
    else if(list.get(i).getWins()>list1.get(j).getWins())
    {
        list1.add(j,list.get(i));
        flag=true;
        break;
    }
    else if(list.get(i).getWholePlays()>list1.get(j).getWholePlays())
    {
        list1.add(j,list.get(i));
        flag=true;
        break;
    }
    else if(list.get(i).getMddle()>list1.get(j).getMddle())
    {
        list1.add(j,list.get(i));
        flag=true;
        break;
    }
    }
    if(!flag)
    {
        list1.add(list.get(i));
    }
}
list=list1;
}

}
