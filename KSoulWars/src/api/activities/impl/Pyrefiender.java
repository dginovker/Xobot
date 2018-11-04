package api.activities.impl;

import api.Data;
import api.activities.Activity;
import api.walking.Walker;
import xobot.script.methods.*;
import xobot.script.methods.tabs.Inventory;
import xobot.script.util.Time;
import xobot.script.util.Timer;
import xobot.script.wrappers.Tile;
import xobot.script.wrappers.interactive.GroundItem;
import xobot.script.wrappers.interactive.Item;
import xobot.script.wrappers.interactive.NPC;

/**
 * Created by HP xw8400
 * Author: Jacob
 * Date: 8/3/2018.
 */
public class Pyrefiender extends Activity {

    @Override
    public boolean requires() {
        return !Players.getMyPlayer().isInCombat();
    }

    @Override
    public String perform() {

        NPC target = NPCs.getNearest(npc -> npc.getId() == 1636 && npc.isReachable() && !npc.isDead() && !npc.isInCombat());

        if (target != null) {
            target.interact("attack");

            if (Time.sleep(() -> Players.getMyPlayer().getInteractingIndex() != -1, 5000)) {

                Timer timer = new Timer(30000);
                while (!target.isDead() && Players.getMyPlayer().getInteractingIndex() != -1 && timer.isRunning() && Game.isLoggedIn()) {
                    Time.sleep(250);
                }

                Tile spot = target.getLocation();
                int regionX = spot.getRegionX();
                int regionY = spot.getRegionY();

                if (regionX < 104 && regionY < 104) {

                    GroundItem[] items;
                    timer = new Timer(4500);

                    while (((items = GroundItems.getGroundItemsAt(regionX, regionY)) == null || items.length < 2) && timer.isRunning()) {
                        Time.sleep(100);
                    }

                    if (items != null && items.length > 0) {
                        if (Inventory.isFull() && !Inventory.Contains(14639)) {
                            Item bandage = Inventory.getItem(4049);
                            if (bandage != null) {
                                bandage.interact("eat");
                                Time.sleep(250);
                            }
                        }
                        for (GroundItem item : items) {
                            if (item.getItem().getID() == 14639) {

                                int oldCount = Inventory.getRealCount();
                                item.getItem().interact("take");

                                if (Time.sleep(() -> Inventory.getRealCount() > oldCount, 6000)) {
                                    return "Success";
                                }
                            }
                        }
                    }
                }
            }
            return "Timed-out";

        } else if (Calculations.distanceTo(Data.actual.getPyrefiendArea().getCentralTile()) > 20) {
            Walker.walkTowards(Data.actual.getPyrefiendArea().getCentralTile().randomize(3, 3));
            return "Walking";
        }
        return "Failure";
    }
}
