package aids;

import xobot.script.methods.Players;
import xobot.script.methods.Walking;
import xobot.script.util.Random;
import xobot.script.util.Time;
import xobot.script.wrappers.Tile;

/**
 * Created by SRH on 1/28/2018.
 */
public class Walker {
    public static void walkTowards(Tile tile)
    {
        int counter = 0;
        if (distance(Players.getMyPlayer().getLocation(), tile) > 15)
        {
            tile = new Tile(Players.getMyPlayer().getLocation().getX() + 5*tile.getX()/Math.abs(tile.getX()), Players.getMyPlayer().getLocation().getY() + 5*tile.getY()/Math.abs(tile.getY()));
            System.out.println("Calculated new tile: " + tile.toString());
        }
        while (!tile.isReachable())
        {
            while (counter % 50 == 49)
            {
                System.out.println("This while loop is getting too much..");
            }
            tile = getNearbyTile(tile);
        }
        Walking.walkTo(tile);
        Time.sleep(400);
    }

    public static Tile getNearbyTile(Tile tile)
    {
        Tile n = new Tile(tile.getX() + Random.nextInt(0, 1) - 2, tile.getY() + Random.nextInt(0, 1) - 2);

        return n;
    }

    public static int distance(Tile t1, Tile t2)
    {
        return (int) Math.floor(Math.sqrt(Math.pow(t1.getX() - t2.getX(), 2) + Math.pow(t1.getY() - t2.getY(), 2)));
    }
}
