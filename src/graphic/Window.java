package graphic;

import game.Main;
import logic.update.Updater;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class Window extends JFrame {
private Container window=null;
private Updater updater=new Updater(Main.getPlayer());
    private static Administer admin;
   // private static Window window =new Window() ;
    public Window()
    {
   super("HEARTH STONE");
    admin=new Administer(this);


   setPreferredSize(new Dimension(property.Constants.WIDTH,property.Constants.HEIGHT));
    setMaximumSize(new Dimension(property.Constants.WIDTH,property.Constants.HEIGHT));
    setMinimumSize(new Dimension(property.Constants.WIDTH,property.Constants.HEIGHT));
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        updater.update();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we)
            {
                String ObjButtons[] = {"Yes","No"};
                int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure you want to exit?","",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
                if(PromptResult==JOptionPane.YES_OPTION)
                {
                    updater.update();
                    System.exit(0);
                }
            }
        });
   setResizable(false);
    setLocationRelativeTo(null);
    ImageIcon img = new ImageIcon("sources/icon1.jpg");
    setIconImage(img.getImage());

   setVisible(true);
        setWindowPanel(admin.firstPage());

window=admin.firstPage();

}
public void setWindowPanel(Container container)
{
   //remove(window);
   setContentPane(container);
    invalidate();
    validate();

   setVisible(true);
   window=container;

}
public Container getWindowPanel()
{
    return  this.window;
}
public void setBGImage(BufferedImage img)
{
    setContentPane(new JLabel(new ImageIcon(img)));
}

}
