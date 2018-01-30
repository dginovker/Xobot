package nodes;

import aids.Helper_Functions;
import xobot.script.methods.Players;
import xobot.script.wrappers.interactive.Player;

/**
 * Created by Cyn on 1/22/2018.
 */
public class Combat extends Node {
    Helper_Functions help;

    @Override
    public boolean validate() {
        System.out.println("Checking validation of Combat");
        return Players.getMyPlayer().isInCombat();
    }

    @Override
    public void execute() {
        if (!Player.getMyPlayer().isInCombat())
        {
            attack();
        }
        if (help.isFrozen())
        {
            useRanged();
        }
        else
        {
            useMelee();
        }
    }

    private void attack() {
        Player toAttack = Players.getNearest(o -> help.isEnemy(o));
        toAttack.interact("Attack");
    }

    private void useMelee() {
        //If wearing ranged
        //If has melee equipment
            //Switch to melee
    }

    private void useRanged() {
        //If wearing melee
        //If has ranged equipment
            //Switch to ranged
    }

    @Override
    public String toString() {
        return "Fighting Someone";
    }
}
