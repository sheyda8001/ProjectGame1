package entities.passives;

import logic.PlayManager;

public class Warriors extends Passive {
   private PlayManager playManager=PlayManager.getInstance();

    @Override
   public String getJob() {
        return "when minion die set defense";
    }

    @Override
  public  void doTheJob() {
       playManager.getPlayer1().getSelectedHero().setDefense(2);
    }
}
