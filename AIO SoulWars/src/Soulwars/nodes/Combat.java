package Soulwars.nodes;

import Soulwars.Helper_Functions;
import Soulwars.Node;
import xobot.script.methods.Players;
import xobot.script.wrappers.interactive.Player;

/**
 * Created by Cyn on 1/22/2018.
 */
public class Combat extends Node {
    Helper_Functions help;

    @Override
    public boolean validate() {
        return Players.getMyPlayer().isInCombat() || Players.getNearest(o -> help.isEnemy(o) && help.distance(o.getLocation(), help.playerLocation()) < 12) != null;
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
        attack();
    }

    private void useRanged() {
        //If wearing melee
        //If has ranged equipment
            //Switch to ranged
        attack();
    }

    @Override
    public String toString() {
        return null;
    }
}
