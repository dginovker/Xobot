package nodes;

import api.Data;
import xobot.script.ActiveScript;
import xobot.script.methods.GameObjects;
import xobot.script.util.Time;
import xobot.script.wrappers.interactive.GameObject;

public class LeaveSpawnOrGrave extends Node {
    public LeaveSpawnOrGrave(ActiveScript aS) {
        super(aS);
    }

    @Override
    public boolean activate() {
        return Data.actual != null && Data.isInSpawnOrGrave();
    }

    @Override
    public boolean execute(Data data) {
        if (Data.actual.isInSpawn()) {
            leaveSpawn();
        } else if (Data.actual.isInEastGrave()) {
            leaveEastGrave();
        } else if (Data.actual.isInWestGrave()) {
            leaveWestGrave();
        }
        Data.status = "Leaving Spawn/Grave" + getClass().getSimpleName();

        return true;
    }


    private void leaveWestGrave() {
        Data.status = "Leaving west grave";
        Time.sleep(15000, 20000);
        GameObject grave = GameObjects.getNearest(gameObject -> gameObject.getId() == 42020 && gameObject.isReachable());
        //
        // if it exists (it should always be there SINCE we are in spawn)
        //
        // pass through it, sleep until we are out, or until 5 seconds have passed
        /////////////////////////////////////////////////////
        if (grave != null) {
            // grave.interact(4);
            grave.interact("pass");
            Time.sleep(() -> !Data.actual.isInWestGrave(), 5000);
        }
    }

    private void leaveEastGrave() {
        Data.status = "Leaving east grave";
        Time.sleep(15000, 20000);
        GameObject grave = GameObjects.getNearest(gameObject -> gameObject.getId() == 42019 && gameObject.isReachable());
        //
        // if it exists (it should always be there SINCE we are in spawn)
        //
        // pass through it, sleep until we are out, or until 5 seconds have passed
        /////////////////////////////////////////////////////
        if (grave != null) {
            // grave.interact(4);
            grave.interact("pass");
            Time.sleep(() -> !Data.actual.isInEastGrave(), 5000);
        }
    }

    private void leaveSpawn() {
        Data.status = "Leaving spawn";
        //
        // we are in spawn, find the nearest portal
        /////////////////////////////////////////////////////
        GameObject spawn = GameObjects.getNearest(gameObject -> gameObject.getId() == Data.actual.getSpawnID() && gameObject.isReachable());
        //
        // if it exists (it should always be there SINCE we are in spawn)
        //
        // pass through it, sleep until we are out, or until 5 seconds have passed
        /////////////////////////////////////////////////////
        if (spawn != null) {
            //spawn.interact(4);
            spawn.interact("pass");
            Time.sleep(() -> !Data.actual.isInSpawn(), 5000);
        }
    }
}
