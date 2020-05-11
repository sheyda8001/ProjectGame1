package game;

import entities.Player;
import entities.decks.Deck;
import graphic.Window;

public class Main {
  private  static Window window;
    public static Player player;

    public static Window getWindow() {
        return window;
    }

    public static Player getPlayer() {
        return player;
    }
    public Main(String []args)
    {
 window= new Window();
    }
    public static void main(String[] args)
    {

        new Main(args);
    }

}
