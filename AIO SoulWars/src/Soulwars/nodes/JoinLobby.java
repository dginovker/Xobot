package Soulwars.nodes;

import Soulwars.Helper_Functions;
import Soulwars.Node;
import Soulwars.Variables;
import xobot.script.methods.GameObjects;
import xobot.script.methods.Players;
import xobot.script.wrappers.interactive.GameObject;

import static Soulwars.Constants.LOBBY_AREA;

/**
 * Created by Cyn on 1/23/2018.
 */
public class JoinLobby extends Node {

    private GameObject portal;

    @Override
    public boolean validate() {
        return Variables.getCurrentTeam() != null && LOBBY_AREA.contains(Players.getMyPlayer().getLocation());
    }

    @Override
    public void execute() {
        portal = GameObjects.getNearest(42031);


        if (portal != null) {
            portal.interact("join-team");
            Helper_Functions.conditionalSleep(() -> Variables.getCurrentTeam() != null, 3500);
        }


    }


    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
