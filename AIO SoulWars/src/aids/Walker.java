package aids;

import xobot.script.methods.Walking;
import xobot.script.util.Random;
import xobot.script.util.Time;
import xobot.script.wrappers.Tile;

/**
 * Created by Cyn on 1/28/2018.
 */
public class Walker {
    public static void walkTowards(Tile tile)
    {
        while (!tile.isReachable())
        {
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
}
