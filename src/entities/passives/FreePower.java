package entities.passives;

import logic.PlayManager;

public class FreePower extends Passive
{
    private PlayManager playManager=PlayManager.getInstance();
    @Override
    public String getJob() {
        return "low one mana hero Power && set 2 permission";
    }

    @Override
 public   void doTheJob() {
playManager.getPlayer1().getSelectedHero().setHeroPowerNumPermission(2);
playManager.getPlayer1().getSelectedHero().lowHeroPowerCost(1);
    }
}
