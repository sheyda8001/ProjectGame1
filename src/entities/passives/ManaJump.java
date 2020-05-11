package entities.passives;

import logic.PlayManager;

public class ManaJump extends Passive {
    private PlayManager playManager=PlayManager.getInstance();

    @Override
  public  String getJob() {
        return "add mana Permission";
    }

    @Override
    public void doTheJob() {
playManager.getPlayer1().changeManaPermission(1);
    }
}
