package graphic;

import entities.cards.Card;
import entities.cards.CardType;
import entities.cards.MinionCard;
import entities.cards.Weapon;
import logic.PlayManager;
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

public class PlayPanel extends JPanel {
    private int lastSizeHand=0;
    private ArrayList<String> events;
    private PlayManager playManager=PlayManager.getInstance();
    private JScrollPane scrollPane=event();
    private JButton endTurn=endTurn();
    private JScrollPane handCardPlayer1=handCardPlayer1();
    private JScrollPane groundPlayer1=groundPlayer1();
    private JLabel manaPlayer1=mana1();
    private JLabel mana=mana();
    private JLabel HP1=HP1();
    private JLabel deckNum=deckNum1();
    private JScrollPane weapons=weapons();
    public PlayPanel()
    {
        super();
        setBounds(0,0, Constants.WIDTH,Constants.HEIGHT);
        setBackground(Color.LIGHT_GRAY);
        setLayout(null);
        add(scrollPane);
        add(endTurn);
        add(handCardPlayer1);
        add(groundPlayer1);
        add(manaPlayer1);
        add(mana);
        add(HP1);
        add(heroPower1());
        add(deckNum);
        add(weapons);
    }
private JLabel heroPower1()
{
    JLabel label=new JLabel();
    label.setBounds(120,Constants.HEIGHT-300,100,100);
    BufferedImage img = null;
    try {
        img = ImageIO.read(new File("sources\\heropowers\\"+playManager.getPlayer1HeroClass().toString()+".png"));
    } catch (IOException e) {
        e.printStackTrace();
    }
    Image dimg = img.getScaledInstance(100, 100,
            Image.SCALE_SMOOTH);
    ImageIcon imageIcon = new ImageIcon(dimg);
    label.setIcon(imageIcon);
    return label;
}
    private JButton endTurn()
    {
        if(playManager.getNewCard().size()!=0)
        {
            for(int i=0;i<playManager.getNewCard().size();i++) {
                NewCardFrame frame = new NewCardFrame(playManager.getNewCard().get(i));
            }
        }
        JButton endTurn=new JButton("end turn");
        endTurn.setBounds(Constants.WIDTH-120,(Constants.HEIGHT-50)/2,100,50);
        ActionListener listener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==endTurn)
                {
                    Log.LOGGER.finest("end turn");

                    playManager.endTurn();
                    remove(mana);
                    remove(manaPlayer1);
                    mana=mana();
                    manaPlayer1=mana1();
                    add(mana);
                    revalidate();
                    repaint();
                    add(manaPlayer1);
                    revalidate();
                    repaint();
                    remove(handCardPlayer1);
                    handCardPlayer1=handCardPlayer1();
                    add(handCardPlayer1);
                    revalidate();
                    repaint();
                    remove(deckNum);
                    deckNum=deckNum1();
                    add(deckNum);
                    revalidate();
                    repaint();
                    remove(weapons);
                    weapons=weapons();
                    add(weapons);
                    revalidate();
                    repaint();
                    if(playManager.getPlayer1HandCards().size()!=lastSizeHand)
                    {
                        for(;lastSizeHand<playManager.getPlayer1HandCards().size();lastSizeHand++)
                        {
                            NewCardFrame frame=new NewCardFrame(playManager.getPlayer1HandCards().get(lastSizeHand));
                        }
                    }
                }
            }
        };
        endTurn.addActionListener(listener);
        return endTurn;
    }
    private JScrollPane event()
    {
      JPanel panel =new JPanel(new GridLayout(100,1));
      panel.setSize(100,1000);
      for(int i=0;i<playManager.getEvents().size();i++)
      {
          JLabel label=new JLabel(playManager.getEvents().get(i));
          label.setSize(100,50);
          panel.add(label);
      }
        JScrollPane scrolled = new JScrollPane(panel);
        scrolled.setBounds(0,0,100,Constants.HEIGHT);

        return scrolled;
    }
    private JScrollPane weapons()
    {
        JPanel panel=new JPanel(new GridLayout(1,12));
        panel.setBounds(200,Constants.HEIGHT-300,1000,100);
        JButton[] cardButton =new JButton[12];
        for(int i=0;i<12&&i<playManager.getWeapons1().size();i++)
        {
            cardButton[i]=cardButton(playManager.getWeapons1().get(i),"weapon");
            JLabel label=new JLabel();
            if(playManager.getWeapons1().get(i).getCardType().equals(CardType.WEAPON))
            {
                Weapon card= (Weapon) playManager.getWeapons1().get(i);
                label.setText(card.getNumberUsePermission()+"");
                cardButton[i].add(label);
            }
            panel.add(cardButton[i]);
        }
        JScrollPane scrolled = new JScrollPane(panel);
        scrolled.setBounds(300,Constants.HEIGHT-300,Constants.WIDTH-400,100);
        scrolled.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrolled.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        return scrolled;
    }
    private JPanel handCardPlayer2()
    {
        JPanel panel=new JPanel(new GridLayout(1,12));
        panel.setBounds(100,0,Constants.WIDTH-100,100);

        return panel;
    }
    private JScrollPane handCardPlayer1()
    {

        JPanel panel=new JPanel(new GridLayout(1,12));
        panel.setBounds(100,Constants.HEIGHT-170,1000,100);
        JButton[] cardButton =new JButton[12];
        for(int i=0;i<12&&i<playManager.getPlayer1HandCards().size();i++)
{
  cardButton[i]=cardButton(Card.getInstance(playManager.getPlayer1HandCards().get(i)),"hand");
  JLabel label=new JLabel();
  if(Card.getInstance(playManager.getPlayer1HandCards().get(i)).getCardType().equals(CardType.MINION))
  {
      MinionCard card= (MinionCard) MinionCard.getInstance(playManager.getPlayer1HandCards().get(i));
      label.setText(card.getHP()+"");
      cardButton[i].add(label);
  }
    panel.add(cardButton[i]);
}
        JScrollPane scrolled = new JScrollPane(panel);
        scrolled.setBounds(100,Constants.HEIGHT-170,Constants.WIDTH-200,100);
        scrolled.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrolled.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        return scrolled;
    }
    private JButton cardButton(Card card,String job)
    {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("sources\\cards\\"+card.getName()+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance((Constants.WIDTH-100)/12, 100,
                Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        JButton button=new JButton(imageIcon);
        button.setSize((Constants.WIDTH-100)/12, 100);
        ActionListener listener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==button)
                {
                    if(job.equals("hand")) {
                        playManager.playCard(card);
                        Log.LOGGER.finest("play"+ card.getName());

                    }
                    remove(scrollPane);
                    scrollPane=event();
                    add(scrollPane);
                    revalidate();
                    repaint();
                    remove(groundPlayer1);
                    groundPlayer1=groundPlayer1();
                    add(groundPlayer1);
                    revalidate();
                    repaint();
                    remove(manaPlayer1);
                    manaPlayer1=mana1();
                    add(manaPlayer1);
                    revalidate();
                    repaint();
                    remove(handCardPlayer1);
                    handCardPlayer1=handCardPlayer1();
                    add(handCardPlayer1);
                    revalidate();
                    repaint();
                    remove(deckNum);
                    deckNum=deckNum1();
                    add(deckNum);
                    revalidate();
                    repaint();
                    remove(weapons);
                    weapons=weapons();
                    add(weapons);
                    revalidate();
                    repaint();
                }
            }
        };

        button.addActionListener(listener);
        return button;
    }
    private JScrollPane groundPlayer1()
    {
        JPanel panel =new JPanel(new GridLayout(1,7));
        panel.setBounds(100,130,1000,100);
        ArrayList<JButton> cards=new ArrayList<>();
        for(int i=0;i<playManager.getPlayer1PlaayedCards().size();i++)
        {
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File("sources\\cards\\"+playManager.getPlayer1PlaayedCards().get(i)+".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image dimg = img.getScaledInstance(70,100,
                    Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(dimg);
           cards.add(cardButton(Card.getInstance(playManager.getPlayer1PlaayedCards().get(i)),"ground"));
            JLabel label1=new JLabel();

            if(Card.getInstance(playManager.getPlayer1PlaayedCards().get(i)).getCardType().equals(CardType.MINION))
            {

                MinionCard card= (MinionCard) MinionCard.getInstance(playManager.getPlayer1PlaayedCards().get(i));
                label1.setText(card.getHP()+"");
              cards.get(i).add(label1);
            }
            panel.add(cards.get(i));

        }
        JScrollPane scrolled = new JScrollPane(panel);
        scrolled.setBounds(100,130,Constants.WIDTH-220,100);
        scrolled.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrolled.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        return scrolled;
    }
    private JLabel mana1()
    {
        JLabel label=new JLabel();
        label.setBounds(Constants.WIDTH-100,Constants.HEIGHT-120,50,50);
        label.setText(playManager.getManaPlayer1()+"");
        return label;
    }
    private JLabel mana()
    {
        JLabel label =new JLabel();
        label.setBounds(Constants.WIDTH-50,Constants.HEIGHT-120,50,50);
        label.setText(playManager.getMana()+"");
        return label;
    }
    private JLabel HP1()
    {
        JLabel label =new JLabel();
        label.setBounds(Constants.WIDTH-50,Constants.HEIGHT-170,50,50);
        label.setText(playManager.getHPPlayer1()+"");
        return label;
    }
    private JLabel deckNum1()
    {
        JLabel label =new JLabel();
        label.setBounds(Constants.WIDTH-100,Constants.HEIGHT-200,50,50);
        label.setText(playManager.getDeckNum1()+"");
        return label;
    }
    private JFrame newCardFrame(String name)
    {
        JFrame frame =new JFrame();
        frame.setPreferredSize(new Dimension(200,300));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
       JPanel panel=new JPanel();
       panel.setBounds(0,0,200,300);
        JLabel label=new JLabel();
        label.setBounds(0,0,200,300);
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("sources\\cards\\"+name+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance((Constants.WIDTH-100)/12, 100,
                Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        label.setIcon(imageIcon);
        panel.add(label);
       frame.setContentPane(panel);
        invalidate();
        validate();
        setVisible(true);
        return frame;
    }
    class NewCardFrame extends JFrame{
        private String name="";
        NewCardFrame(String name)
        {
            super();
            this.name=name;
            setPreferredSize(new Dimension(300,400));
            setSize(300,400);
           setLocationRelativeTo(null);
          setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
           setResizable(false);
            JPanel panel=new JPanel();
            panel.setBounds(0,0,300,400);
            panel.add(getLabel());
            setVisible(true);
           setContentPane(panel);
            //add(panel);

        }
        private JLabel getLabel()
        {
            JLabel label=new JLabel();
            label.setBounds(0,0,300,400);
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File("sources\\cards\\"+name+".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image dimg = img.getScaledInstance(300, 400,
                    Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(dimg);
            label.setIcon(imageIcon);
            return label;
        }
    }
}
