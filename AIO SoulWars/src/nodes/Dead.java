package nodes;

import aids.Constants;
import aids.Helper_Functions;
import aids.Variables;
import xobot.script.methods.GameObjects;
import xobot.script.methods.Players;
import xobot.script.util.Time;
import xobot.script.wrappers.interactive.GameObject;

import static aids.Constants.*;

/**
 * Created by SRH on 1/23/2018.
 */
public class Dead extends Node {
    @Override
    public boolean validate() {
        System.out.println("Checking validation of Dead - currentCoords: " + Players.getMyPlayer().getLocation().toString());
        System.out.println("Blue spawn: " + BLUE_SPAWN_AREA.contains(Players.getMyPlayer().getLocation()) + "Red spawn: " + RED_SPAWN_AREA.contains(Players.getMyPlayer().getLocation()) + "West Graveyard: " + WEST_GRAVEYARD_SPAWN.contains(Players.getMyPlayer().getLocation()) + "East Graveyard: " + EAST_GRAVEYARD_SPAWN.contains(Players.getMyPlayer().getLocation()));
        return BLUE_SPAWN_AREA.contains(Players.getMyPlayer().getLocation())
                || RED_SPAWN_AREA.contains(Players.getMyPlayer().getLocation())
                || WEST_GRAVEYARD_SPAWN.contains(Players.getMyPlayer().getLocation())
                || EAST_GRAVEYARD_SPAWN.contains(Players.getMyPlayer().getLocation());
    }

    @Override
    public void execute() {
        Variables.setScriptNode("Handling Death");

        for (int i : EXIT_BARRIER)
        {
            GameObject barrier = GameObjects.getNearest(i);
            if (barrier != null)
            {
                System.out.println("Pass barrier");
                barrier.interact("Pass");
                Time.sleep(3000);
            }
        }
    }

    @Override
    public String toString() {
        return "Handling Death";
    }
}
