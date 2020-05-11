package entities.passives;

import logic.PlayManager;

public class OffCard extends Passive {
  private  PlayManager playManager=PlayManager.getInstance();
    @Override
    public String getJob() {
        return "low one mana";
    }

    @Override
    public void doTheJob() {
playManager.setOffPrice(1);
    }
}
