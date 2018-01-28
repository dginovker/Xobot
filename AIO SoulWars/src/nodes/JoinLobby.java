package nodes;

import aids.Constants;
import aids.Helper_Functions;
import aids.Variables;
import aids.Walker;
import xobot.script.methods.GameObjects;
import xobot.script.methods.Players;
import xobot.script.wrappers.interactive.GameObject;

/**
 * Created by SRH on 1/23/2018.
 */
//Jakes code: https://images-ext-1.discordapp.net/external/u53jqCEmgjRcw642NAWGw9U4cHCaMee7DWkiTu6ADWY/https/image.prntscr.com/image/aUzCFqVbRN2m4HKdqfCXfg.png?width=805&height=610
public class JoinLobby extends Node {

    private GameObject portal;

    @Override
    public boolean validate() {
        System.out.println("Checking validation of JoinLobby");
        System.out.println(Constants.LOBBY_AREA.contains(Players.getMyPlayer().getLocation()));
        return Constants.LOBBY_AREA.contains(Players.getMyPlayer().getLocation());
    }

    @Override
    public void execute() {
        System.out.println("JoinLobby valid");
        portal = GameObjects.getNearest(42031);


        if (portal != null) {
            portal.interact("join-team");
            Helper_Functions.conditionalSleep(() -> Variables.getCurrentTeam() != null, 3500);
        }
        else
        {
            System.out.println("Portal is null!");
        }
    }


    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
