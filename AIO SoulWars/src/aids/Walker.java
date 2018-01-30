package aids;

import xobot.script.methods.Players;
import xobot.script.methods.Walking;
import xobot.script.util.Random;
import xobot.script.util.Time;
import xobot.script.wrappers.Tile;

import static xobot.script.methods.Walking.walkTo;

/**
 * Created by SRH on 1/28/2018.
 */
public class Walker {
    public static void walkTowards(Tile tile)
    {
        /*
          If you're east of 1948 and north of 3214 then go south
          If you're east of 1948 and south of 3214 then go west
          If you're west of 1821 and south of 3246 then go north
          If you're west of 1821 and north of 3246 then go east
          Else get closer
        */

        if (Players.getMyPlayer().getLocation().getX() > 1948) {
            if (Players.getMyPlayer().getLocation().getY() > 3214) {
                walkSouth();
                return;
            }
            walkWest();
            return;
        }
        if (Players.getMyPlayer().getLocation().getX() < 1821) {
            if (Players.getMyPlayer().getLocation().getY() > 3246) {
                walkNorth();
                return;
            }
            walkEast();
            return;
        }

        getCloser(tile);
    }

    private static void walkEast() {
        getCloser(new Tile(Players.getMyPlayer().getLocation().getX() + 5, Players.getMyPlayer().getLocation().getY()));
    }

    private static void walkNorth() {
        getCloser(new Tile(Players.getMyPlayer().getLocation().getX(), Players.getMyPlayer().getLocation().getY() + 5));
    }

    private static void walkWest() {
        getCloser(new Tile(Players.getMyPlayer().getLocation().getX() - 5, Players.getMyPlayer().getLocation().getY()));
    }

    private static void walkSouth() {
        getCloser(new Tile(Players.getMyPlayer().getLocation().getX(), Players.getMyPlayer().getLocation().getY() - 5));
    }

    private static void getCloser(Tile tile) {
        int modifier = 5;
        tile = new Tile(Players.getMyPlayer().getLocation().getX() + modifier*tile.getX()/Math.abs(tile.getX()), Players.getMyPlayer().getLocation().getY() + modifier*tile.getY()/Math.abs(tile.getY()));
        while (!tile.isReachable())
        {
            if (modifier > 12)
            {
                System.out.println("I think we're stuck! Resetting modifier.. (Coords: " + Players.getMyPlayer().getLocation().toString() + ")");
                modifier = 0;
            }
            modifier++;
            tile = new Tile(Players.getMyPlayer().getLocation().getX() + modifier*tile.getX()/Math.abs(tile.getX()), Players.getMyPlayer().getLocation().getY() + modifier*tile.getY()/Math.abs(tile.getY()));
        }
        System.out.println("Calculated new tile: " + tile.toString());
        walkTo(tile);
        Time.sleep(2000);
    }

    public static int distance(Tile t1, Tile t2)
    {
        return (int) Math.floor(Math.sqrt(Math.pow(t1.getX() - t2.getX(), 2) + Math.pow(t1.getY() - t2.getY(), 2)));
    }
}
