package graphic;

import game.Main;
import interfaces.StorePlayer;
import logic.update.Updater;
import logs.Log;
import property.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ConstButton extends JButton {
    public ConstButton()
    {
        super();
        //setBounds(Constants.WIDTH-30,Constants.HEIGHT-30,30,30);
        setFont(new Font("Arial", Font.PLAIN, 15));
        setSize(30, 30);
       setLocation(Constants.WIDTH-80, Constants.HEIGHT-80);

    }
}
enum Job{
    QUIT,
    MENU;
}
class ConstFrame extends JFrame{
    public ConstFrame()
    {
        super();
//        super();
//        setPreferredSize(new Dimension(300,100));
//        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//        setResizable(false);
//        setLocationRelativeTo(null);
//        setVisible(true);
        JPanel panel=new JPanel();
        panel.setSize(300,100);
         PanelFactory panelFactory=new PanelFactory();

        Buttons quitButton=new Buttons(Job.QUIT);
        Buttons menuButton=new Buttons(Job.MENU);

        panel.add(quitButton);
        panel.add(menuButton);
//        setContentPane(panel);
//        invalidate();
//        validate();
        setPreferredSize(new Dimension(300,100));
        setMaximumSize(new Dimension(300,100));
        setMinimumSize(new Dimension(300,100));
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(panel);
        invalidate();
        validate();
        setVisible(true);
        ActionListener listener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==menuButton)
                {
                    Log.LOGGER.finest("move to menue");

                    Main.getWindow().setWindowPanel(panelFactory.mainMenu());
                }
                else if(e.getSource()==quitButton){
                    Log.LOGGER.finest("quit");

                    String ObjButtons[] = {"Yes","No"};
                    int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure you want to exit?","",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
                    if(PromptResult==JOptionPane.YES_OPTION)
                    {
                        Updater updater =new Updater(Main.getPlayer());
                        updater.update();
                        System.exit(0);
                    }
                }
            }
        };
       menuButton. addActionListener(listener);
       quitButton.addActionListener(listener);
    }
}
class Buttons extends JButton{
    public Buttons(Job job)
    {
        super();
        setSize(30,30);
        if(job.equals(Job.MENU)) {
            setText("M");
            setLocation(120, 35);
        }
        else{
            setText("Q");
            setLocation(150,35);
        }

    }
}