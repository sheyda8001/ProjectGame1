package graphic;

import property.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainPanel extends JPanel {
    private BufferedImage bgimage=null;
    public MainPanel(BufferedImage bgImage)
    {

super();
        this.bgimage=bgImage;

       setLayout(null);
        setBounds(0, 0, Constants.WIDTH, Constants.HEIGHT);

    }
    public MainPanel(Color bgcolor)
    {
       super();
        setLayout(null);
        setBackground(bgcolor);
        setBounds(0, 0, Constants.WIDTH, Constants.HEIGHT);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.bgimage, 0, 0, null);
    }

}
