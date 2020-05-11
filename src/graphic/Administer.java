package graphic;

import javax.swing.*;

public class Administer {
    private PanelFactory panelFactory;
    private JFrame frame;
    public Administer(JFrame frame)
    {

        this.frame=frame;
    panelFactory=new PanelFactory();

}
    public  JPanel entrance()
    {

        return panelFactory.entrancePage() ;
    }
    public JPanel firstPage() {

        return panelFactory.firstPage() ;    }
}
