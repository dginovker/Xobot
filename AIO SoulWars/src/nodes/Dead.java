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
 * Created by Cyn on 1/23/2018.
 */
public class Dead extends Node {
    Helper_Functions help;
    @Override
    public boolean validate() {
        return BLUE_SPAWN_AREA.contains(Players.getMyPlayer().getLocation())
                || RED_SPAWN_AREA.contains(Players.getMyPlayer().getLocation())
                || WEST_GRAVEYARD_SPAWN.contains(Players.getMyPlayer().getLocation())
                || EAST_GRAVEYARD_SPAWN.contains(Players.getMyPlayer().getLocation());
    }

    @Override
    public void execute() {
        Variables.setCurrentNode(this);

        for (int i : EXIT_BARRIER)
        {
            GameObject barrier = GameObjects.getNearest(i);
            if (barrier != null && barrier.getDistance() < 15)
            {
                barrier.interact("Pass");//DOUBLE CHECK STRING
            }
        }
        Time.sleep(1200);
    }

    @Override
    public String toString() {
        return "Handling Death";
    }
}
