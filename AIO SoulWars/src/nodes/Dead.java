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
        return BLUE_SPAWN_AREA.contains(Players.getMyPlayer().getLocation())
                || RED_SPAWN_AREA.contains(Players.getMyPlayer().getLocation())
                || WEST_GRAVEYARD_SPAWN.contains(Players.getMyPlayer().getLocation())
                || EAST_GRAVEYARD_SPAWN.contains(Players.getMyPlayer().getLocation());
    }

    @Override
    public void execute() {
        System.out.println("Start");
        Variables.setScriptNode("Handling Death");

        for (int i : EXIT_BARRIER)
        {
            GameObject barrier = GameObjects.getNearest(i);
            System.out.println("I'm here");
            if (barrier != null)
            {
                System.out.println("Pass barrier");
                barrier.interact("Pass");//DOUBLE CHECK STRING
                //barrier.interact(0);
            }
        }
        Time.sleep(1200);
        System.out.println("??");
    }

    @Override
    public String toString() {
        return "Handling Death";
    }
}
