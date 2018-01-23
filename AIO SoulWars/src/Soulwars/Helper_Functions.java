package Soulwars;

import xobot.script.methods.Players;
import xobot.script.wrappers.Area;
import xobot.script.wrappers.Tile;
import xobot.script.wrappers.interactive.Player;

/**
 * Created by SRH on 1/22/2018.
 */

public class Helper_Functions {
    public enum State {
        GHOST,
        WALKING_TO_AVATAR,
        WALKING_TO_WEST_GRAVEYARD,
        WALKING_TO_EAST_GRAVEYARD,
        WALKING_TO_MIDDLE,
        FIGHTING,
        WALKING_TO_JELLIES,
        WALKING_TO_PYREFIENDS,
        COLLECTING_SHARDS,
        USING_SHARDS_ON_OBLISK
        //More states will probably need to be added.
        //All of these have to be nodes!
    }

    public Area blueSpawnArea = new Area(0, 0, 0, 0);
    public Area redSpawnArea = new Area(0, 0, 0, 0);
    public Area eastGraveyardArea = new Area(0, 0, 0, 0);
    public Area westGraveyardArea = new Area(0, 0, 0, 0);
    public Area obliskArea = new Area(0, 0, 0, 0);


    public double distance(Tile location, Tile tile) {
        return Math.sqrt(Math.pow(location.x - tile.x, 2) + Math.pow(location.y - tile.y, 2));
    }

    /*
        I have no idea how to code this.
     */
    public boolean isEnemy(Player o) {
        return false;
    }

    /*
        Needs to be coded.. Check cape color somehow(?), or set variable when you enter?
     */
    public String getTeam()
    {
        return "Red";
    }

    /*
        Find frozen widget?
     */
    public boolean isFrozen()
    {
        return false;
    }

    public boolean inSpawnArea()
    {
        return blueSpawnArea.contains(playerLocation()) || redSpawnArea.contains(playerLocation());
    }

    public boolean inEastGraveyardArea()
    {
        return eastGraveyardArea.contains(playerLocation());
    }

    public boolean inWestGraveyardArea()
    {
        return westGraveyardArea.contains(playerLocation());
    }

    public boolean inObliskArea()
    {
        return obliskArea.contains(playerLocation());
    }

    public boolean isObliskMine()
    {
        return getTeam().equals(obliskTeam());
    }

    public boolean isWestGraveyardMine()
    {
        return getTeam().equals(westGraveyardTeam());
    }

    public boolean isEastGraveyardMine()
    {
        return getTeam().equals(eastGraveyardTeam());
    }

    /*
        We need a way of finding out who owns what
     */
    public String obliskTeam()
    {
        return "Red";
    }

    /*
        We need a way of finding out who owns what
     */
    public String westGraveyardTeam()
    {
        return "Red";
    }

    /*
        We need a way of finding out who owns what
    */
    public String eastGraveyardTeam()
    {
        return "Red";
    }

    public Tile playerLocation()
    {
        return Players.getMyPlayer().getLocation();
    }
}
