package aids;

import xobot.script.wrappers.Area;
import xobot.script.wrappers.Tile;

/**
 * Created by Cyn on 1/23/2018.
 */
public class Constants {
    private Constants() {
        //no fucking instantiation
    }

    public static final Area BLUE_SPAWN_AREA = new Area(1823, 3230, 1816, 3220);
    public static final Area RED_SPAWN_AREA = new Area(1958, 3244, 1951, 3234);
    public static final Area EAST_GRAVEYARD_SPAWN = new Area(1932, 3246, 1934, 3244);
    public static final Area WEST_GRAVEYARD_SPAWN = new Area(1841, 3217, 1843, 3219);
    public static final Area EAST_GRAVEYARD_AREA = new Area(1938, 3240, 1928, 3250);
    public static final Area WEST_GRAVEYWARD_AREA = new Area(1837, 3223, 1847, 3213);
    public static final Area OBLISK_AREA = new Area(1894, 3239, 1879, 3224);

    public static final Tile OBLISK_CENTER_TILE = new Tile(1886, 1731);

    public static final Area LOBBY_AREA = new Area(new Tile(1872, 1904), new Tile(3140, 3186));
    public static final Area BLUE_TEAM_LOBBY = new Area(1879, 3166, 1870, 3158);
    public static final Area RED_TEAM_LOBBY = new Area(1900, 3166, 1909, 3157);

    public static final Area GAME_AREA = new Area(new Tile(1800, 3200), new Tile(1980, 3260));

    public static final int BANDAGE_STALL = 42024;
    public static final int PYREFIEND = 1636;
    public static final int SHARD_FRAGMENT = 14639;
    public static final int OBLISK = -1;

    public static final int[] EXIT_BARRIER = {9247};

    public static final int BANDAGES = 4049;

    public static final int TIME_LEFT = 29287;

    // public static final WidgetChild TIME_LEFT = Widgets.get(29266).getChild(25);
}
