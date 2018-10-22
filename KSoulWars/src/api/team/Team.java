package api.team;

import api.Data;
import xobot.script.methods.Players;
import xobot.script.methods.Widgets;
import xobot.script.wrappers.Area;
import xobot.script.wrappers.Tile;
import xobot.script.wrappers.WidgetChild;

import java.awt.*;


/**
 * Created by HP xw8400
 * Author: Jacob
 * Date: 1/25/2018.
 */
public final class Team {

    private final String name;
    private final int identifier;

    private Area lobby;
    private Area spawn;

    private Area eastGrave;
    private Area westGrave;

    private int spawnID;
    private int lobbyID;

    private Area pyrefiends;
    private Color color;

    public static Area obelisk = new Area(new Tile(1870, 3223), new Tile(1902, 3241));

    public Team(String name, int identifier, int lobbyID, int spawnID, Area lobby, Area spawn, Area pyrefiends, Color color) {
        this.name = name;
        this.identifier = identifier;

        this.lobbyID = lobbyID;
        this.spawnID = spawnID;

        this.lobby = lobby;
        this.spawn = spawn;
        this.pyrefiends = pyrefiends;

        this.eastGrave = new Area(new Tile(1932, 3244), new Tile(1934, 3246));
        this.westGrave = new Area(new Tile(1841, 3217), new Tile(1843, 3219));

        this.color = color;
    }

    public static Team randomize(Team arg1, Team arg2) {
        return (xobot.script.util.Random.nextBoolean() ? arg1 : arg2);
    }

    public static Team autoDetect() {
        //
        // if we are wearing a soulwars cape, then we have to be in a lobby, or in a game
        //
        // Player#getTeam() can sometimes be unreliable when not in a game
        /////////////////////////////////////////////////////
        int cape = Players.getMyPlayer().getEquipment()[1];
        if (cape == 14641) {
            return Data.red;
        } else if (cape == 14642) {
            return Data.blue;
        }
        return null;
    }

    public final boolean isInWestGrave() {
        return westGrave.contains(Players.getMyPlayer().getLocation());
    }

    public final boolean isInEastGrave() {
        return eastGrave.contains(Players.getMyPlayer().getLocation());
    }

    public final boolean isInSpawn() {
        return spawn.contains(Players.getMyPlayer().getLocation());
    }

    public final boolean isInLobby() {
        return lobby.contains(Players.getMyPlayer().getLocation());
    }

    public final boolean isControllingWestGrave() {
        WidgetChild child = Widgets.get(29266).getChild(1);
        return child != null && child.getColor() == (color.getRGB() & 0xffffff);
    }

    public final boolean isControllingObelisk() {
        WidgetChild child = Widgets.get(29266).getChild(0);
        return child != null && child.getColor() == (color.getRGB() & 0xffffff);
    }

    public final boolean isControllingEastGrave() {
        WidgetChild child = Widgets.get(29266).getChild(2);
        return child != null && child.getColor() == (color.getRGB() & 0xffffff);
    }
    ////////////// GETTERS \\\\\\\\\\\\\\

    public Area getLobbyArea()
    {
        return lobby;
    }

    public Area getDefaultSpawnArea()
    {
        return spawn;
    }

    public Area getPyrefiendArea()
    {
        return pyrefiends;
    }

    public int getSpawnID()
    {
        return spawnID;
    }

    public int getLobbyID()
    {
        return lobbyID;
    }

    public int getIdentifier() {
        return identifier;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return name;
    }
}
