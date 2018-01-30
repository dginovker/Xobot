package nodes;

import xobot.script.methods.Players;
import xobot.script.util.Time;
import xobot.script.wrappers.interactive.Player;

import static aids.Constants.OBLISK_AREA;
import static aids.Variables.setScriptNode;

/**
 * Created by Cyn on 1/29/2018.
 */
public class AttackOblisk extends Node {
    @Override
    public boolean validate() {
        System.out.println("Checking validation of Attackoblisk");
        return OBLISK_AREA.contains(Players.getMyPlayer().getLocation());
    }

    @Override
    public void execute() {
        setScriptNode("Fighting for Oblisk");
        if (!Players.getMyPlayer().isInCombat() && !Players.getMyPlayer().isMoving())
        {
            for (Player p : Players.getAll())
            {
                p.interact("Attack");
            }
            Time.sleep(500);
            if (Players.getMyPlayer().isInCombat() || Players.getMyPlayer().isMoving())
            {
                return;
            }
        }
    }

    @Override
    public String toString() {
        return "Fighting for Oblisk";
    }
}
