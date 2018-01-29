package aids;

import xobot.script.methods.Players;
import xobot.script.util.Time;
import xobot.script.wrappers.Area;
import xobot.script.wrappers.Tile;
import xobot.script.wrappers.interactive.Player;

/**
 * Created by SRH on 1/22/2018.
 */

public class Helper_Functions {
    public static boolean conditionalSleep(SleepCondition conn, int timeout) {
        long start = System.currentTimeMillis();
        while (!conn.isValid()) {
            if (start + timeout < System.currentTimeMillis()) {
                return false;
            }
            Time.sleep(50);
        }
        return true;
    }

    public boolean isEnemy(Player o) {
        return true;
    }

    public boolean isFrozen() {
        return false;
    }

    public interface SleepCondition {

        boolean isValid();
    }
}
