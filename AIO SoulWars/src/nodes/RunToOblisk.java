package nodes;

import aids.Walker;
import xobot.script.methods.Players;
import xobot.script.util.Time;

import static aids.Constants.OBLISK_AREA;
import static aids.Constants.OBLISK_CENTER_TILE;

/**
 * Created by Skattle on 1/29/2018.
 */
public class RunToOblisk extends Node {
    @Override
    public boolean validate() {
        return !OBLISK_AREA.contains(Players.getMyPlayer().getLocation());
    }

    @Override
    public void execute() {
        Walker.walkTowards(OBLISK_CENTER_TILE);
        Time.sleep(600);
    }

    @Override
    public String toString() {
        return null;
    }
}
