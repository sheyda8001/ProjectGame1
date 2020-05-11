package graphic;

import entities.cards.Card;
import entities.passives.Passive;
import entities.passives.PassiveType;
import game.Main;
import interfaces.DeckPlayer;
import interfaces.StorePlayer;
import logic.entrance.Entrance;
import logic.update.Updater;
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
import java.util.HashMap;
import java.util.Random;

public class PanelFactory {

    public JPanel entrancePage() {

        JTextField tname;
        JTextField tpass;
        JLabel name;
        JLabel pass;
        MainPanel panel = new MainPanel(Color.gray);

        name = new JLabel("Name :");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(((Constants.WIDTH - 200) / 2) - 100, 110);
        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(200, 40);
        tname.setLocation((Constants.WIDTH - 200) / 2, 100);

        pass = new JLabel("Password :");
        pass.setFont(new Font("Arial", Font.PLAIN, 20));
        pass.setSize(100, 20);
        pass.setLocation(((Constants.WIDTH - 200) / 2) - 110, 210);
        tpass = new JTextField();
        tpass.setFont(new Font("Arial", Font.PLAIN, 15));
        tpass.setSize(200, 40);
        tpass.setLocation((Constants.WIDTH - 200) / 2, 200);

        JButton signin = new JButton("enter");
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == signin) {
                    if (!Entrance.loginPermission(tname.getText().toString(), tpass.getText().toString(), "register")) {

                        Main.getWindow().setWindowPanel(entrancePage());
                        ErrorFrame errorFrame = new ErrorFrame(Error.REGISTER);
                    } else {
                        Log.makelog();
                        Main.getWindow().setWindowPanel(mainMenu());
                    }
                }
            }
        };

        signin.setFont(new Font("Arial", Font.PLAIN, 15));
        signin.setSize(100, 20);
        signin.setLocation((Constants.WIDTH - 200) / 2, 300);
        signin.addActionListener(listener);
        panel.add(signin);
        panel.add(name);
        panel.add(tname);
        panel.add(pass);
        panel.add(tpass);

        return panel;
    }

    public JPanel firstPage() {
        MainPanel panel = new MainPanel(Color.gray);


        JLabel name = new JLabel("Name :");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(((Constants.WIDTH - 200) / 2) - 100, 110);

        JTextField tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(200, 40);
        tname.setLocation((Constants.WIDTH - 200) / 2, 100);

        JLabel pass = new JLabel("Password :");
        pass.setFont(new Font("Arial", Font.PLAIN, 20));
        pass.setSize(100, 20);
        pass.setLocation(((Constants.WIDTH - 200) / 2) - 110, 210);

        JPasswordField tpass = new JPasswordField();
        tpass.setFont(new Font("Arial", Font.PLAIN, 15));
        tpass.setSize(200, 40);
        tpass.setLocation((Constants.WIDTH - 200) / 2, 200);

        JButton signin = new JButton("enter");
        JButton reg = new JButton("Register");
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == reg) {
                    Main.getWindow().setWindowPanel(entrancePage());
                } else if (e.getSource() == signin) {
                    if (!Entrance.loginPermission(tname.getText().toString(), tpass.getText().toString(), "signin")) {

                        //Main.window.setWindowPanel(panel);
                        System.out.print(tname.getText() + "   " + tpass.getText());

                        Main.getWindow().setWindowPanel(firstPage());
                        ErrorFrame errorFrame = new ErrorFrame(Error.SIGNIN);

                    } else {
                        Log.makelog();
                        Main.getWindow().setWindowPanel(mainMenu());
                    }
                }

            }
        };

        reg.setFont(new Font("Arial", Font.PLAIN, 15));
        reg.setSize(100, 20);
        reg.setLocation(10, 10);
        reg.addActionListener(listener);
        panel.add(reg);

        signin.setFont(new Font("Arial", Font.PLAIN, 15));
        signin.setSize(100, 20);
        signin.setLocation((Constants.WIDTH - 200) / 2, 300);
        signin.addActionListener(listener);
        panel.add(signin);
        panel.add(name);
        panel.add(tname);
        panel.add(pass);
        panel.add(tpass);
        return panel;


    }


    public JPanel mainMenu() {
        BufferedImage bgImage = null;
        try {
            bgImage = ImageIO.read(new File("sources/MainMenu.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            Log.LOGGER.finest("error  "+e.getStackTrace() );

        }
        MainPanel panel = new MainPanel(bgImage);

        JButton quit = mainMenuButton("QUIT", (Constants.WIDTH - 250) / 2, 400);
        JButton play = mainMenuButton("PLAY", (Constants.WIDTH - 250) / 2, 50);
        JButton store = mainMenuButton("STORE", (Constants.WIDTH - 250) / 2, 120);
        JButton status = mainMenuButton("STATUS", (Constants.WIDTH - 250) / 2, 190);
        JButton collection = mainMenuButton("COLLECTION", (Constants.WIDTH - 250) / 2, 260);
        JButton setting = mainMenuButton("SETTING", (Constants.WIDTH - 250) / 2, 330);

        ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == quit) {
                    Log.LOGGER.finest("exit all");
                    Updater updater =new Updater(Main.getPlayer());
                    updater.update();
                    System.exit(0);
                } else if (e.getSource() == store) {
                    Main.getWindow().setWindowPanel(shop());
                    Log.LOGGER.finest("navigate store");
                } else if (e.getSource() == collection) {
                    Main.getWindow().setWindowPanel(collectionPanel());
                    Log.LOGGER.finest("navigate collections");
                } else if (e.getSource() == setting) {
                    Log.LOGGER.finest("navigate setting");
                } else if (e.getSource() == play) {
                    DeckPlayer deckPlayer=DeckPlayer.getInstance();
                   if(deckPlayer.getSelectedDeck()!=null) {
                       if(Main.getPlayer().getPassive().size()!=0){
                       Log.LOGGER.finest("navigate play");
                       Main.getWindow().setWindowPanel(play());}
                       else{
                           JFrame frame=passiveChoose();
                           Log.LOGGER.finest("select Passive");
                       }
                   }
                   else{
                       Main.getWindow().setWindowPanel(collectionPanel());
                       Log.LOGGER.finest("navigate collections");
                   }
                } else if (e.getSource() == status) {
                    Main.getWindow().setWindowPanel(status());
                    Log.LOGGER.finest("navigate status");
                }
            }
        };

        quit.addActionListener(listener);
        play.addActionListener(listener);
        setting.addActionListener(listener);
        status.addActionListener(listener);
        store.addActionListener(listener);
        collection.addActionListener(listener);


        panel.add(quit);
        panel.add(play);
        panel.add(store);
        panel.add(status);
        panel.add(collection);
        panel.add(setting);


        return panel;
    }
    private JPanel status()
    {
        DeckPlayer player=DeckPlayer.getInstance();
        MainPanel panel=new MainPanel(Color.white);
        ConstButton button =new ConstButton();
        JPanel panel1=new JPanel(new GridLayout(10,1));
        panel1.setBounds(Constants.WIDTH-170,0,100,Constants.HEIGHT);
        JButton[] buttons=new JButton[10];
        JLabel label =new JLabel();
        label.setBounds(0,0,400,400);

        ActionListener listener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==button)
                {
                    ConstFrame constFrame=new ConstFrame();
                }
                for(int i=0;i<10&&i<player.getSortedDecks().size();i++)
                {
                    if(e.getSource()==buttons[i])
                    {
                        Log.LOGGER.finest("status"+buttons[i].getText());

                        label.setText(player.getSortedDecks().get(i).getDesc());
                        label.setVisible(true);
                    }
                }
            }
        };

        for(int i=0;i<10&&i<player.getSortedDecks().size();i++)
        {
            buttons[i]=new JButton(player.getSortedDecks().get(i).getName());
            buttons[i].setSize(100,70);
            panel1.add(buttons[i]);
            buttons[i].addActionListener(listener);
        }
        button.addActionListener(listener);
        panel.add(panel1);
        panel.add(button);
        panel.add(label);
        return panel;

    }
private JPanel play()
{
    PlayPanel panel=new PlayPanel();
    ConstButton button =new ConstButton();
    ActionListener listener=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==button)
            {

                    ConstFrame constFrame=new ConstFrame();

            }
        }
    };
    button.addActionListener(listener);
    panel.add(button);
    return panel;
}
private JFrame passiveChoose()
{
JFrame frame =new JFrame();
frame.setSize(300,300);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    frame.setResizable(false);
    ArrayList<PassiveType> list= Passive.getAllPassives();
    JPanel panel=new JPanel(new GridLayout(1,3));
    JButton[] button=new JButton[3];
    JLabel label=new JLabel("passive selected");
    label.setBounds(0,0,300,300);
    ActionListener listener =new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            for(int i=0;i<3;i++)
            {
                if(e.getSource()==button[i])
                {
                    Main.getPlayer().setPassive(Passive.getPassive(PassiveType.valueOf(button[i].getText())));
                    frame.setContentPane(label);
                }
            }
        }
    };

    Random random=new Random();
    for(int i=0;i<3;i++)
    {

        int i1=Math.abs(random.nextInt(Passive.getAllPassives().size()-1));
        button[i]=new JButton(Passive.getAllPassives().get(i1)+"");
        button[i].setBounds(i*100,0,100,300);
        button[i].addActionListener(listener);
        panel.add(button[i]);
    }
    frame.setContentPane(panel);
    frame.setVisible(true);
    return frame;
}
    private JButton mainMenuButton(String title, int x, int y) {
        JButton button = new JButton(title);
        button.setBackground(Color.orange);
        button.setFont(new Font("Arial", Font.PLAIN, 15));
        button.setSize(250, 50);
        button.setLocation(x, y);
        return button;
    }

    public JPanel shop() {
        StorePlayer storePlayer=StorePlayer.getInstance();
        BufferedImage bgImage = null;
        try {
            bgImage = ImageIO.read(new File("sources/ShopBG.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            Log.LOGGER.finest("error  "+e.getStackTrace() );

        }
       MainPanel panel=new MainPanel(bgImage);

        ConstButton constButton=new ConstButton();



        JButton sell = new JButton("sell");
        sell.setBackground(Color.pink);
        sell.setFont(new Font("Arial", Font.PLAIN, 15));
        sell.setSize(250, 50);
        sell.setLocation((Constants.WIDTH-520)/2, (Constants.HEIGHT-50)/2);

        JButton buy = new JButton("buy");
        buy.setBackground(Color.cyan);
        buy.setFont(new Font("Arial", Font.PLAIN, 15));
        buy.setSize(250, 50);
        buy.setLocation((Constants.WIDTH-520)/2+250, (Constants.HEIGHT-50)/2);

        ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==sell)
                {
                    Main.getWindow().setWindowPanel(sellPanel(storePlayer));
                    Log.LOGGER.finest("navigate sell");

                }
                if(e.getSource()==buy)
                {
                    Log.LOGGER.finest("navigate buy");

                    Main.getWindow().setWindowPanel(buyPanel(storePlayer,0));
                }
                if(e.getSource()==constButton)
                {
                        ConstFrame constFrame=new ConstFrame();
                }
            }
        };

        sell.addActionListener(listener);
        buy.addActionListener(listener);
        constButton.addActionListener(listener);

        panel.add(sell);
        panel.add(buy);
        panel.add(constButton);
        return panel;
    }
private JPanel sellPanel(StorePlayer storePlayer)
{
    MainPanel panel =new MainPanel(Color.getHSBColor(20,70,60));

    ConstButton constButton=new ConstButton();

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

    JButton sell = new JButton("sell");
    sell.setFont(new Font("Arial", Font.PLAIN, 15));
    sell.setSize(100, 50);
    sell.setLocation((Constants.WIDTH-50)/2+100, Constants.HEIGHT-230);

    JLabel coins=new JLabel("your coins:"+ Main.getPlayer().getCoins());
    coins.setFont(new Font("Arial", Font.PLAIN, 20));
    coins.setLocation(Constants.WIDTH-200,0);
    coins.setSize(200,50);

    final int[] i = {0};
    ImageIcon image=null;
    image = new ImageIcon("sources/cards/"+ storePlayer.canBuy().get(i[0])+".png");
    JLabel label=new JLabel(image);
    label.setBounds(0,0,400,600);
    panel.add(label);
    final JLabel[] cardPrice = new JLabel[1];
    cardPrice[0] =new JLabel("Card Price:"+ Card.getInstance(storePlayer.canBuy().get(i[0])).getCardCost());
    cardPrice[0].setFont(new Font("Arial", Font.PLAIN, 20));
    cardPrice[0].setLocation((Constants.WIDTH-50)/2+100, Constants.HEIGHT-330);
    cardPrice[0].setSize(200,50);

    JLabel isSelled=new JLabel();
    isSelled.setFont(new Font("Arial", Font.PLAIN, 20));
    isSelled.setSize(400,50);
    isSelled.setLocation((Constants.WIDTH-50)/2+100, Constants.HEIGHT-180);


    ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String st="";
            if(e.getSource()==right)
            {
                isSelled.setText("");
                panel.add(isSelled);
                if(i[0] <(Main.getPlayer().getrWholeCards().size()-1))
                {
                    ++i[0];
                    st=Main.getPlayer().getrWholeCards().get(i[0]);
                    cardPrice[0].setText("Card Price:"+ Card.getInstance(Main.getPlayer().getrWholeCards().get(i[0])).getCardCost());
                    panel.add(cardPrice[0]);
                    ImageIcon image=null;
                    image = new ImageIcon("sources/cards/"+ Main.getPlayer().getrWholeCards().get(i[0])+".png");
                    label.setIcon(image);
                    panel.add(label);


                }
            }
            else if(e.getSource()==left)
            {
                isSelled.setText("");
                panel.add(isSelled);
                if(i[0]>0&&i[0]<Main.getPlayer().getrWholeCards().size())
                {
                    i[0]--;
                    st=storePlayer.canBuy().get(i[0]);
                    cardPrice[0].setText("Card Price:"+ Card.getInstance(Main.getPlayer().getrWholeCards().get(i[0])).getCardCost());
                    ImageIcon image=null;
                    image = new ImageIcon("sources/cards/"+ Main.getPlayer().getrWholeCards().get(i[0])+".png");
                    label.setIcon(image);
                    panel.add(cardPrice[0]);
                    panel.add(label);

                }
            }
            else if(e.getSource()==sell)
            {try{
                if(Main.getPlayer().getSt().sellCard(Main.getPlayer().getrWholeCards().get(i[0])))
                {
                    Log.LOGGER.finest("sell card:"+Main.getPlayer().getrWholeCards().get(i[0]));

                    coins.setText("your coins:"+ Main.getPlayer().getCoins());

                    isSelled.setText("You selled this card");
                    panel.add(isSelled);
                    panel.add(coins);
                }


                 else{
                     isSelled.setText("You can't sell this card");
                     panel.add(isSelled);

                 }

            }
            catch (IndexOutOfBoundsException ex1)
            {
                isSelled.setText("You can't buy any more");
                panel.add(isSelled);
                Log.LOGGER.finest("error  "+ex1.getStackTrace() );
            }if(i[0]>=0&&Main.getPlayer().getrWholeCards().size()!=0){
                ImageIcon image=null;
                image = new ImageIcon("sources/cards/"+ Main.getPlayer().getrWholeCards().get(i[0])+".png");
                label.setIcon(image);
                panel.add(cardPrice[0]);
                panel.add(label);
                if(st.equals(Main.getPlayer().getrWholeCards().get(i[0])))
                {
                    isSelled.setText("");
                    panel.add(isSelled);
                }}

            }
            else if(e.getSource()==constButton)
            {
                ConstFrame constFrame=new ConstFrame();
            }
        }
    };

    sell.addActionListener(listener);
    right.addActionListener(listener);
    left.addActionListener(listener);
constButton.addActionListener(listener);

    panel.add(sell);
    panel.add(right);
    panel.add(left);
    panel.add(coins);
    panel.add(isSelled);
    panel.add(cardPrice[0]);
    panel.add(constButton);

    return panel;
}
public JPanel buyPanel(StorePlayer storePlayer,int i1)
{

    MainPanel panel =new MainPanel(Color.getHSBColor(66,16,18));

    ConstButton constButton=new ConstButton();

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

    JButton buy = new JButton("buy");
    buy.setFont(new Font("Arial", Font.PLAIN, 15));
    buy.setSize(100, 50);
    buy.setLocation((Constants.WIDTH-50)/2+100, Constants.HEIGHT-230);

JLabel coins=new JLabel("your coins:"+ Main.getPlayer().getCoins());
    coins.setFont(new Font("Arial", Font.PLAIN, 20));
    coins.setLocation(Constants.WIDTH-200,0);
    coins.setSize(200,50);

    final int[] i = {i1};
    ImageIcon image=null;
    image = new ImageIcon("sources/cards/"+ storePlayer.canBuy().get(i[0])+".png");
    JLabel label=new JLabel(image);
    label.setBounds(0,0,400,600);
    panel.add(label);
    final JLabel[] cardPrice = new JLabel[1];
    cardPrice[0] =new JLabel("Card Price:"+ Card.getInstance(storePlayer.canBuy().get(i[0])).getCardCost());
    cardPrice[0].setFont(new Font("Arial", Font.PLAIN, 20));
    cardPrice[0].setLocation((Constants.WIDTH-50)/2+100, Constants.HEIGHT-330);
    cardPrice[0].setSize(200,50);

    JLabel isBuyed=new JLabel();
    isBuyed.setFont(new Font("Arial", Font.PLAIN, 20));
    isBuyed.setSize(400,50);
    isBuyed.setLocation((Constants.WIDTH-50)/2+100, Constants.HEIGHT-180);


    ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String st="";
            if(e.getSource()==right)
            {
                isBuyed.setText("");
                panel.add(isBuyed);
                if(i[0] <(storePlayer.canBuy().size()-1))
                {
                    ++i[0];
                    st=storePlayer.canBuy().get(i[0]);
                    cardPrice[0].setText("Card Price:"+ Card.getInstance(storePlayer.canBuy().get(i[0])).getCardCost());
                    panel.add(cardPrice[0]);
                    ImageIcon image=null;
                    image = new ImageIcon("sources/cards/"+ storePlayer.canBuy().get(i[0])+".png");
                    label.setIcon(image);
                    panel.add(label);


                }
            }
           else if(e.getSource()==left)
            {
                isBuyed.setText("");
                panel.add(isBuyed);
                if(i[0]>0&&i[0]<storePlayer.canBuy().size())
                {
                    i[0]--;
                    st=storePlayer.canBuy().get(i[0]);
                    cardPrice[0].setText("Card Price:"+ Card.getInstance(storePlayer.canBuy().get(i[0])).getCardCost());
                    ImageIcon image=null;
                    image = new ImageIcon("sources/cards/"+ storePlayer.canBuy().get(i[0])+".png");
                    label.setIcon(image);
                    panel.add(cardPrice[0]);
                    panel.add(label);

                }
            }
           else if(e.getSource()==buy)
            {try{
                if(Main.getPlayer().getSt().buyCard(storePlayer.canBuy().get(i[0])))
                {                    Log.LOGGER.finest("buy card:"+storePlayer.canBuy().get(i[0]));

                    coins.setText("your coins:"+ Main.getPlayer().getCoins());
                    isBuyed.setText("You buyed this card");
                    panel.add(isBuyed);
                    panel.add(coins);
                }
                else{
                    isBuyed.setText("You can't buy this card");
                    panel.add(isBuyed);

                }



            }
                catch (IndexOutOfBoundsException ex1)
                {
                    Log.LOGGER.finest("error  "+ex1.getStackTrace() );
                    isBuyed.setText("You can't buy any more");
                    panel.add(isBuyed);
                }if(i[0]>=0&&storePlayer.canBuy().size()!=0){
                ImageIcon image=null;
                image = new ImageIcon("sources/cards/"+ storePlayer.canBuy().get(i[0])+".png");
                label.setIcon(image);
                panel.add(cardPrice[0]);
                panel.add(label);
if(st.equals(storePlayer.canBuy().get(i[0])))
{
    isBuyed.setText("");
    panel.add(isBuyed);
}}

            }
            else if(e.getSource()==constButton)
            {
                ConstFrame constFrame=new ConstFrame();
            }
        }
    };

    buy.addActionListener(listener);
    right.addActionListener(listener);
    left.addActionListener(listener);
constButton.addActionListener(listener);

    panel.add(buy);
    panel.add(right);
    panel.add(left);
    panel.add(coins);
    panel.add(isBuyed);
panel.add(cardPrice[0]);
    panel.add(constButton);

    return panel;
}
public JPanel collectionPanel()
{
    BufferedImage bgImage = null;
    try {
        bgImage = ImageIO.read(new File("sources/Collection1.jpg"));
    } catch (IOException e) {
        e.printStackTrace();
        Log.LOGGER.finest("error  "+e.getStackTrace() );

    }
DecksPanel decksPanel=new DecksPanel();
    MainPanel panel =new MainPanel(bgImage);

    ConstButton constButton=new ConstButton();



CollectionPanels panels=new CollectionPanels();
    ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==constButton)
            {
                ConstFrame constFrame=new ConstFrame();
            }
        }};
    constButton.addActionListener(listener);
    panel.add(constButton);
 panel.add(panels.herosPanel());
    return panel;

}

class DecksPanel extends JPanel{
      public DecksPanel()
      {
super();
setBounds(0,0,200,1000);
setLayout(null);

      }
}

    class ErrorFrame extends JFrame {
        protected ErrorFrame(Error job) {
            super(job.toString() + " ERROR");
            setSize(new Dimension(400, 200));
            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            setResizable(false);
            setLocationRelativeTo(null);

            job.setErrors();
            setContentPane(makeRegisterError(job));
            invalidate();
            validate();
            setVisible(true);
        }

        private JPanel makeRegisterError(Error error) {

            JPanel panel = new JPanel();
            panel.setSize(400, 200);
            JLabel lable = new JLabel(error.getError(error));
            System.out.print(error.getError(error));
            lable.setFont(new Font("Arial", Font.PLAIN, 20));
            lable.setLocation((400 - lable.getWidth()) / 2, (200 - lable.getHeight()) / 2);
            panel.add(lable);
            return panel;
        }

    }

}


 enum Error{
    REGISTER,
     SIGNIN;
    HashMap<Error,String> errors=new HashMap<>();
public void setErrors()
{
   errors. put(REGISTER, "this username has been made");
   errors. put(SIGNIN, "invalid password or username");
}
    public String toString()
    {
        return super.toString();
    }
    public String getError(Error error)
    {
        return errors.get(error);
    }

}