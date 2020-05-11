package entities.passives;

import logic.PlayManager;

public class TwiceDraw extends Passive {
    private PlayManager playManager=PlayManager.getInstance();
    public String getJob()
    {
        return "Draw Two Card";
    }
    public void doTheJob()
    {
    playManager.setHowMany(2);
    }
}
