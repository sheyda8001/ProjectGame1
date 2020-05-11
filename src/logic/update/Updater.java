package logic.update;

import entities.Player;

public class Updater implements Updatable {
private DecksUpdater decksUpdater ;
private PlayerUpdater playerUpdater;
public Updater(Player player)
{
    decksUpdater =new DecksUpdater();
playerUpdater=new PlayerUpdater(player);
}
    @Override
    public void update() {
    decksUpdater.updateToMake();
    }
}
