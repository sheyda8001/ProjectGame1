package graphic;
import entities.cards.Card;
import entities.decks.Deck;
import entities.heros.HeroClass;
import game.Main;
import interfaces.DeckPlayer;
import interfaces.StorePlayer;
import logic.store.Store;
import logs.Log;
import property.Constants;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
public class CollectionPanels  {
    private StorePlayer storePlayer=StorePlayer.getInstance();
    private DeckPlayer deckPlayer =DeckPlayer.getInstance();
    private  JTabbedPane tp=new JTabbedPane();
    private int mana=-1;
    private String name="",have="";
public JTabbedPane  herosPanel ()
{

    tp.setBounds(0,0, Constants.WIDTH,Constants.HEIGHT);
    tp.setBackground(Color.BLUE);
    tp.add("filter",filterPanel());
    for(int i=0;i< HeroClass.values().length;i++)
    {
        HeroClass heroClass=HeroClass.values()[i];
        JPanel panel =new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, Constants.WIDTH-200, Constants.HEIGHT);
        tp.add(heroClass.toString(),setPanel(heroClass));
    }
         tp.add("Decks",deckPanel());

      return tp;
      }
      private JPanel deckPanel()
{
    Deck[] selectedDeck = {null};
    Panel mainPanel=new Panel();
    DeckPlayer deckPlayer=DeckPlayer.getInstance();
    JLabel label =new JLabel();
    label.setBounds(0,0,Constants.WIDTH-200,Constants.HEIGHT);
    Panel panel =new Panel(new GridLayout(100,1));
    //deckPlayer.getOpenedDecksName().add(Deck.getInstance("Classic Mage"));
    DecksButton[] decksButtons=new DecksButton[deckPlayer.getOpenedDecksName().size()];
    JButton select=new JButton("select");
    select.setBounds(400,Constants.HEIGHT-100,100,50);

    ActionListener listener=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            for(int i=0;i<decksButtons.length;i++)
            {
                if(e.getSource()==decksButtons[i])
                {
                    label.add(deckCardPanel(deckPlayer.getOpenedDecksName().get(i).getName()));
                    mainPanel.add(label);
                    selectedDeck[0] =deckPlayer.getOpenedDecksName().get(i);
                }
            }
            if(e.getSource()==select)
            {
                deckPlayer.setSelectedDeckPlayer(selectedDeck[0]);
            }
        }
    };
    for(int i=0;i<deckPlayer.getOpenedDecksName().size();i++)
    {
        decksButtons[i]=new DecksButton(deckPlayer.getOpenedDecksName().get(i).getName());
        panel.add(decksButtons[i]);
        decksButtons[i].addActionListener(listener);
    }
select.addActionListener(listener);
    mainPanel.add(select);
    JScrollPane scrolled = new JScrollPane(panel);
   scrolled.setBounds(Constants.WIDTH-250,0,200,Constants.HEIGHT);
    mainPanel.add(scrolled);
mainPanel.add(label);
    return mainPanel;
}
private JPanel deckCardPanel(String deckname)
{
    Deck deck =deckPlayer.getOpenedDecksName().get(deckPlayer.getIndexOf(deckname));
    Panel panel =new Panel();
    panel.setBounds(0,0,Constants.WIDTH-200,Constants.HEIGHT);
    JButton right = new JButton(">");
    right.setBackground(Color.pink);
    right.setFont(new Font("Arial", Font.PLAIN, 15));
    right.setSize(50, 50);
    right.setLocation((Constants.WIDTH-50)/2+170, Constants.HEIGHT-280);
    JButton left = new JButton("<");
    left.setBackground(Color.cyan);
    left.setFont(new Font("Arial", Font.PLAIN, 15));
    left.setSize(50, 50);
    left.setLocation((Constants.WIDTH-50)/2+100, Constants.HEIGHT-280);
    JButton add = new JButton("add");
    add.setBackground(Color.pink);
    add.setSize(100, 50);
    add.setLocation((Constants.WIDTH-50)/2, Constants.HEIGHT-330);
    JButton removeDeck = new JButton("remove deck");
    removeDeck.setBackground(Color.gray);
    removeDeck.setSize(100, 50);
    removeDeck.setLocation((Constants.WIDTH-50)/2, 20);
    JButton remove = new JButton("remove");
    remove.setBackground(Color.pink);
  //  remove.setFont(new Font("Arial", Font.PLAIN, 15));
    remove.setSize(100, 50);
    remove.setLocation((Constants.WIDTH-50)/2+110, Constants.HEIGHT-330);
    JTextField tname = new JTextField();
    tname.setFont(new Font("Arial", Font.PLAIN, 10));
    tname.setSize(200, 30);
    tname.setLocation(400, 190);
    JButton signin = new JButton("chane name");
    signin.setBounds(450,220,100,50);
    JButton chH = new JButton("change hero");
    chH.setBounds(450,270,100,50);
    JLabel num=new JLabel();
    num.setBounds(400,100,250,70);
    num.setFont(new Font("Arial", Font.PLAIN, 30));
    num.setForeground(Color.pink);
    final int[] i = {0};
    ImageIcon image=null;
    image = new ImageIcon("sources/cards/"+deck.getCards().get(i[0])+".png");
    num.setText(deckPlayer.getNumCardInDeck(deckname,deck.getCards().get(i[0]))+"");
    num.setVisible(true);
    String st=deck.getCards().get(i[0]);
    JButton card=new JButton(image);
    card.setBounds(0,0,400,600);
    panel.add(card);
    ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==right)
            {
                if(i[0] <(deck.getCards().size()-1)&&i[0]>=0)
                {
                    ++i[0];
                    String st=deck.getCards().get(i[0]);
                    ImageIcon image=null;
                    image = new ImageIcon("sources/cards/"+st +".png");
                    card.setIcon(image);
                    panel.add(card);
                    num.setText(deckPlayer.getNumCardInDeck(deckname,deck.getCards().get(i[0]))+"");
                    num.setVisible(true);
                }
            }
            else if(e.getSource()==left)
            {
                if(i[0]>0&&i[0]<deck.getCards().size())
                {
                    i[0]--;
                    String st=deck.getCards().get(i[0]);
                    ImageIcon image=null;
                    image = new ImageIcon("sources/cards/"+st +".png");
                    card.setIcon(image);
                    panel.add(card);
                    num.setText(deckPlayer.getNumCardInDeck(deckname,deck.getCards().get(i[0]))+"");
                    num.setVisible(true);
                }
            }
            else if(e.getSource()==add)
            {
                new ChangePanel(deck,"add");
                Log.LOGGER.finest("added card to deck:"+deckname);
            }
            else if(e.getSource()==remove)
            {
                new ChangePanel(deck,"remove");
                Log.LOGGER.finest("remove card from deck:"+deckname);
            }
            else if(e.getSource()==removeDeck)
            {
if(deckPlayer.removeDeck(deckname)){ num.setText("deck removed"); num.setVisible(true);tp.add("Decks",deckPanel()); Log.LOGGER.finest("remove deck:"+deckname);}
else{
    num.setText("cant be removed");
    num.setVisible(true);
            }
            }
            else if(e.getSource()==signin)
            {
                if(deckPlayer.changeName(tname.getText(),deckname)) {
                    num.setText("name changed");
                    Log.LOGGER.finest("change namr to deck:"+deckname+"to"+tname.getText());
                    num.setVisible(true);
                  //  tp.add("Decks",deckPanel());
                }
                else{
                    num.setText("name exists");
                    num.setVisible(true);
                }
            }
            else if(e.getSource()==chH)
            {try{
                if(deckPlayer.changeHero(HeroClass.valueOf(tname.getText()),deckname)) {
                    num.setText("hero changed");
                    Log.LOGGER.finest("change hero deck:"+deckname+"to"+tname.getText());
                    num.setVisible(true);
                   // tp.add("Decks",deckPanel());
                }
                else{
                    num.setText("cant change");
                    num.setVisible(true);
                }}
                catch (Exception e1)
                {
                    num.setText("unvalid hero");
                    num.setVisible(true);
                }
            }
        }
    };
    left.addActionListener(listener);
    right.addActionListener(listener);
    add.addActionListener(listener);
    remove.addActionListener(listener);
    removeDeck.addActionListener(listener);
    card.addActionListener(listener);
    signin.addActionListener(listener);
    chH.addActionListener(listener);
    panel.add(chH);
    panel.add(signin);
    panel.add(tname);
    panel.add(left);
    panel.add(right);
    panel.add(removeDeck);
    panel.add(remove);
    panel.add(add);
    panel.add(num);
    return panel;
}
private ArrayList<String> filterHeroClass(HeroClass heroClass)
{
    ArrayList<String> list= storePlayer.getCards();
if(have.equals("have"))
{
    for(int i=0;i<list.size();i++)
    {
        if(!storePlayer.getWholeCardsPlayer().contains(list.get(i)))
        {
            list.remove(i);
            i--;
        }
    }
}
if(have.equals("nothave"))
{
    for(int i=0;i<list.size();i++)
    {
        if(storePlayer.getWholeCardsPlayer().contains(list.get(i)))
        {
            list.remove(i);
            i--;
        }
    }
}
if(mana>=0)
{
    for(int i=0;i<list.size();i++)
    {
        if(!(Card.getInstance(list.get(i)).getManaCost()==mana))
        {
            list.remove(i);
            i--;
        }
    }
}
for(int i=0;i<list.size();i++)
{System.out.println(list.get(i));

    if(!Card.getInstance(list.get(i)).getHeroClass().equals(heroClass))
    {
       list.remove(i);
       i--;
    }
}
for(int i=0;i<list.size();i++)
{
    if(!list.get(i).contains(name))
    {
        list.remove(i);
        i--;
    }
}
    return list;
}

private JPanel filterPanel()
{
    Panel panel=new Panel();

    JTextField searchBar=new JTextField();
    searchBar.setBounds(0,0,200,50);
    searchBar.setFont(new Font("Arial", Font.PLAIN, 15));
    JButton search = new JButton("search");
    search.setBounds(210,0,50,50);
    JRadioButton [] manas=new JRadioButton[12];
    JRadioButton doHave=new JRadioButton("have");
    doHave.setBounds(300,300,100,50);
    JRadioButton notHave=new JRadioButton("not have");
    notHave.setBounds(300,420,100,50);
    JRadioButton nothing=new JRadioButton("nothing");
    nothing.setBounds(300,560,100,50);
    for(int i=0;i<12;i++)
    { if(i<11){
        manas[i]=new JRadioButton(i+"");}
        else if(i==11)
    {
        manas[i]=new JRadioButton("nothing");
    }
        manas[i].setBounds(100,50+30*i,100,50);
    }
    ActionListener listener=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==search)
            {
name=searchBar.getText();
            }
            for(int i=0;i<12;i++)
            {
                if(i==12)
                {
                    mana=-1;
                }
               else if(manas[i].isSelected())
                {
                    mana=i;
                }
            }
            if(doHave.isSelected())
            {
                have="have";
            }
            if(notHave.isSelected())
            {
                have="nothave";
            }
            if(nothing.isSelected())
            {
                have="nothing";
            }

        }
    };
    notHave.addActionListener(listener);
    nothing.addActionListener(listener);
    doHave.addActionListener(listener);
    panel.add(doHave);
    panel.add(notHave);
    panel.add(nothing);
    for(int i=0;i<12;i++)
    {
        manas[i].addActionListener(listener);
        panel.add(manas[i]);
    }
search.addActionListener(listener);
    panel.add(search);
    panel.add(searchBar);
    return panel;
}
private JPanel setPanel(HeroClass heroClass)
{
    Panel panel =new Panel();
    JButton right = new JButton(">");
    right.setBackground(Color.pink);
    right.setFont(new Font("Arial", Font.PLAIN, 15));
    right.setSize(50, 50);
    right.setLocation((Constants.WIDTH-50)/2+170, Constants.HEIGHT-280);

    JButton left = new JButton("<");
    left.setBackground(Color.cyan);
    left.setFont(new Font("Arial", Font.PLAIN, 15));
    left.setSize(50, 50);
    left.setLocation((Constants.WIDTH-50)/2+100, Constants.HEIGHT-280);
    JLabel label2=new JLabel();
label2.setBounds(400,30,300,250);
    label2.setFont(new Font("Arial", Font.PLAIN, 20));
label2.setForeground(Color.white);
    JLabel error=new JLabel("you can't buy this");
    error.setBounds(400,350,300,250);
    error.setFont(new Font("Arial", Font.PLAIN, 40));
    error.setForeground(Color.RED);

    final int[] i = {0};
    ImageIcon image=null;
    image = new ImageIcon("sources/cards/"+filterHeroClass(heroClass).get(i[0])+".png");
    label2.setText(Card.getInstance(filterHeroClass(heroClass).get(i[0])).toString());
    String st=filterHeroClass(heroClass).get(i[0]);
    ImageIcon image1=null;
    image1 = new ImageIcon("sources/Lock.png");
    JLabel label1=new JLabel(image1);
    label1.setBounds((Constants.WIDTH-50)/2+120,Constants.HEIGHT-380,100,100);


    if(!filterHeroClass(heroClass).contains(st))
    {
panel.add(label1);
    }
    JButton card=new JButton(image);
    card.setBounds(0,0,400,600);
    panel.add(card);
    error.setVisible(false);

//    panel.add(label);

    ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
if(e.getSource()==right)
{
    error.setVisible(false);

    if(i[0] <(filterHeroClass(heroClass).size()-1)&&i[0]>=0)
    {
        label1.setVisible(false);    //panel.remove(label1);
        ++i[0];
        String st=filterHeroClass(heroClass).get(i[0]);
//        st=storePlayer.canBuy().get(i[0]);
        ImageIcon image=null;
        image = new ImageIcon("sources/cards/"+st +".png");
        label2.setText(Card.getInstance(st).toString());
        panel.add(label1);
        label1.setVisible(false);
        if(!filterHeroClass(heroClass).contains(st))
        {
            label1.setVisible(true);
        }
        card.setIcon(image);
        panel.add(card);
    }
}
else if(e.getSource()==left)
{
    error.setVisible(false);
    if(i[0]>0&&i[0]<filterHeroClass(heroClass).size())
    {
        label1.setVisible(false);
        i[0]--;
        String st=filterHeroClass(heroClass).get(i[0]);
        ImageIcon image=null;
        image = new ImageIcon("sources/cards/"+st +".png");
        if(!filterHeroClass(heroClass).contains(st))
        {
            label1.setVisible(true);
        }
        label2.setText(Card.getInstance(st).toString());
        card.setIcon(image);
        panel.add(card);
    }
}
else if(e.getSource()==card)
{
    String st=filterHeroClass(heroClass).get(i[0]);
    PanelFactory panelFactory=new PanelFactory();
    if(storePlayer.canBuy().contains(st))
    {
        Main.getWindow().setWindowPanel(panelFactory.buyPanel(storePlayer,storePlayer.canBuy().indexOf(st)));
    }
    else{
     error.setVisible(true);
    }
}
        }
    };
left.addActionListener(listener);
right.addActionListener(listener);
card.addActionListener(listener);
 panel.add(left);
 panel.add(right);
panel.add(label2);
panel.add(label1);
panel.add(error);
    return panel;
}
class ChangePanel extends JFrame{
    private DeckPlayer deckPlayer=DeckPlayer.getInstance();
    private Deck deck;
    private String job;
    private ArrayList<String> list=new ArrayList<>();
    public ChangePanel(Deck deck,String job)
    {
        super(job +" card");
        this.deck=deck;
        System.out.println("ssss "+deck.getCards().toString());
        this.job=job;
        fillList();
        setPreferredSize(new Dimension(property.Constants.WIDTH,property.Constants.HEIGHT));
        setSize(property.Constants.WIDTH,property.Constants.HEIGHT);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(jTabbedPane());
    }
    private void fillList()
    {
        if(job.equals("remove")) {
            for (int i = 0; i < deck.getCards().size(); i++) {
                list.add(deck.getCards().get(i));
            }
        }
        if(job.equals("add"))
        {
            for(int i=0;i<deckPlayer.getCardsCanAddTo(deck).size();i++)
            {
                list.add(deckPlayer.getCardsCanAddTo(deck).get(i));
            }
        }
    }
    private JTabbedPane jTabbedPane()
    {
        JTabbedPane tp=new JTabbedPane();
        tp.setBounds(0,0, Constants.WIDTH,Constants.HEIGHT);
        tp.setBackground(Color.BLUE);
        tp.add("netural",heroPanel(HeroClass.NEUTRAL));
        tp.add(deck.getName(),heroPanel(deck.getHeroClass()));
        return tp;
    }
    private JPanel heroPanel(HeroClass heroClass)
    {
        ArrayList<String> list1=new ArrayList<>();
       JPanel panel =new JPanel();
       panel.setBounds(0,0,Constants.WIDTH,Constants.HEIGHT);
       panel.setLayout(null);
        JButton right = new JButton(">");
        right.setBackground(Color.pink);
        right.setFont(new Font("Arial", Font.PLAIN, 15));
        right.setSize(50, 50);
        right.setLocation((Constants.WIDTH-50)/2+170, Constants.HEIGHT-280);
        JButton left = new JButton("<");
        left.setBackground(Color.cyan);
        left.setFont(new Font("Arial", Font.PLAIN, 15));
        left.setSize(50, 50);
        left.setLocation((Constants.WIDTH-50)/2+100, Constants.HEIGHT-280);
        JLabel error=new JLabel("you can't "+job+" this");
        error.setBounds(400,350,300,300);
        error.setFont(new Font("Arial", Font.PLAIN, 40));
        error.setForeground(Color.RED);
        JLabel didJob=new JLabel(job+"ed");
        didJob.setBounds(400,350,300,250);
        didJob.setFont(new Font("Arial", Font.PLAIN, 40));
        didJob.setForeground(Color.black);
        final int[] i = {0};
   for(int i1=0;i1<list.size();i1++) if(Card.getInstance(list.get(i1)).getHeroClass().equals(heroClass)) list1.add(list.get(i1));
   if(list1.size()>0){
        ImageIcon image=null;
        image = new ImageIcon("sources/cards/"+list1.get(i[0])+".png");
        String st=list1.get(i[0]);
        JButton card=new JButton(image);
        card.setBounds(0,0,400,600);
        panel.add(card);
        error.setVisible(false);
        didJob.setVisible(false);
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==right)
                {
                    error.setVisible(false);
                    didJob.setVisible(false);

                    if(i[0] <(list1.size()-1)&&i[0]>=0)
                    {
                        ++i[0];
                        String st=list1.get(i[0]);
                        ImageIcon image=null;
                        image = new ImageIcon("sources/cards/"+st +".png");
                        card.setIcon(image);
                        panel.add(card);
                    }
                }
                else if(e.getSource()==left)
                {
                    error.setVisible(false);
                    didJob.setVisible(false);

                    if(i[0]>0&&i[0]<list1.size())
                    {
                        i[0]--;
                        String st=list1.get(i[0]);
                        ImageIcon image=null;
                        image = new ImageIcon("sources/cards/"+st +".png");
                        card.setIcon(image);
                        panel.add(card);
                    }
                }
                else if(e.getSource()==card)
                {
                    if (job.equals("remove"))
                     {
                        if(! deckPlayer.removeCard(list1.get(i[0]),deck.getName())){
                            didJob.setVisible(false);
                            error.setVisible(true);}
                        else{
                            didJob.setVisible(true);
                         error.setVisible(false);

                     }
                    }
                    if(job.equals("add"))
                    {
                        if(!deckPlayer.addCard(list1.get(i[0]),deck.getName()))
                        {
                            didJob.setVisible(false);
                            error.setVisible(true);}
                        else{
                            didJob.setVisible(true);
                            error.setVisible(false);
                        }
                    }

                }

            }
        };
        left.addActionListener(listener);
        right.addActionListener(listener);
        card.addActionListener(listener);
        panel.add(left);
        panel.add(right);
        panel.add(error);
        panel.add(didJob);}
return panel;
    }
}

class Panel extends JPanel {
    private BufferedImage bgimage = null;

    public Panel() {

        super();


        try {
            this.bgimage = ImageIO.read(new File("sources/Collection1.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            Log.LOGGER.finest("error  " + e.getStackTrace());

        }

        setLayout(null);
        setBounds(0, 0, Constants.WIDTH - 200, Constants.HEIGHT);
    }
public Panel(int width ,int height)
{
    super();
    setBounds(0,0,width,height);
}
    public Panel(GridLayout layout) {
        super(layout);
        setLocation(Constants.WIDTH - 250, 50);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.bgimage, 0, 0, null);
    }
}

class DecksButton extends JButton{
    private String deckName;
    public DecksButton(String deckName)
    {
        super(deckName);
        this.deckName=deckName;
        setSize(200,50);
      // setIcon(getImage());
       setText(deckName+"("+deckPlayer.getDeck(deckName).getHeroClass().toString()+")");



    }
    private ImageIcon getImage(){

//                try {
                    System.out.println(Deck.getInstance(deckName).getHeroClass().toString());
                    System.out.println("sdfadsf");
                //    Image img = ImageIO.read(getClass().getResource("sources//heros//"+ Deck.getInstance(deckName).getHeroClass().toString()+".jpg"));
                    ImageIcon image=null;
                    image = new ImageIcon("sources//heros//"+ Deck.getInstance(deckName).getHeroClass().toString()+".jpg");
                  return image;
//                } catch (Exception ex) {
//                    Log.LOGGER.finest("error  "+ex.getStackTrace() );
//                }
            }


        }}
