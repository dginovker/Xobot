package nodes;

import aids.Constants;
import aids.Helper_Functions;
import aids.Variables;
import xobot.script.methods.GameObjects;
import xobot.script.util.Time;
import xobot.script.wrappers.interactive.GameObject;

/**
 * Created by Cyn on 1/23/2018.
 */
public class Dead extends Node {
    Helper_Functions help;
    @Override
    public boolean validate() {
        return help.inEastGraveyardArea()
                || help.inWestGraveyardArea()
                || help.inSpawnArea();
    }

    @Override
    public void execute() {
        Variables.setCurrentNode(this);

        GameObject barrier = GameObjects.getNearest(Constants.EXIT_BARRIER);//GET EXIT_BARRIER VALUE
        barrier.interact("Pass");//DOUBLE CHECK STRING
        Time.sleep(1200);
    }

    @Override
    public String toString() {
        return "Handling Death";
    }
}
