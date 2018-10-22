package api.activities.impl;

import api.Data;
import api.activities.Activity;
import api.team.Team;
import api.walking.Walker;
import xobot.script.methods.GameObjects;
import xobot.script.methods.tabs.Inventory;
import xobot.script.util.Time;
import xobot.script.wrappers.interactive.GameObject;
import xobot.script.wrappers.interactive.Item;

/**
 * Created by HP xw8400
 * Author: Jacob
 * Date: 8/3/2018.
 */
public class Fragmenter extends Activity {

    @Override
    public boolean requires() {
        Item frag = Inventory.getItem(14639);
        return frag != null && frag.getStack() > 9 && Data.actual.isControllingObelisk();
    }

    @Override
    public String perform() {

        GameObject obelisk = GameObjects.getNearest(42010);
        if (obelisk != null && obelisk.isReachable()) {
            Item frag = Inventory.getItem(14639);

            if (frag != null) {
                frag.interact("use");
                Time.sleep(250);
                obelisk.interact("use-with");
                if (Time.sleep(() -> !Inventory.Contains(14639), 10000)) {
                    return "Success";
                }
                return "Timed-out";
            }
        } else {
            Walker.walkTowards(Team.obelisk.getCentralTile().randomize(3, 3));
            return "Walking";
        }

        return "Failure";
    }

}
