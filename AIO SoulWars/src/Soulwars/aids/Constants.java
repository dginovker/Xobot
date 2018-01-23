package Soulwars.aids;

import xobot.script.wrappers.Area;
import xobot.script.wrappers.Tile;

/**
 * Created by Cyn on 1/23/2018.
 */
public class Constants {
    private Constants() {
        //no fucking instantiation
    }


    public static final Area LOBBY_AREA = new Area(new Tile(1880, 3140), new Tile(1899, 3179));

    public static final Area GAME_AREA = new Area(new Tile(1800, 3200), new Tile(1980, 3260));

    public static final int BANDAGE_STALL = 42024;
    public static final int PYREFIEND = 1636;
    public static final int SHARD_FRAGMENT = 14639;


    public static final int BANDAGES = 4049;

    public static final int TIME_LEFT = 29287;

    // public static final WidgetChild TIME_LEFT = Widgets.get(29266).getChild(25);
}
