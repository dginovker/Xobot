package nodes;

import aids.Constants;
import aids.Helper_Functions;
import aids.Variables;
import aids.Walker;
import xobot.script.methods.GameObjects;
import xobot.script.methods.Players;
import xobot.script.wrappers.interactive.GameObject;

import static aids.Constants.BLUE_TEAM_LOBBY;
import static aids.Constants.RED_TEAM_LOBBY;

/**
 * Created by SRH on 1/23/2018.
 */
//Jakes code: https://images-ext-1.discordapp.net/external/u53jqCEmgjRcw642NAWGw9U4cHCaMee7DWkiTu6ADWY/https/image.prntscr.com/image/aUzCFqVbRN2m4HKdqfCXfg.png?width=805&height=610
public class JoinLobby extends Node {

    private GameObject portal;

    @Override
    public boolean validate() {
        return Constants.LOBBY_AREA.contains(Players.getMyPlayer().getLocation())
                && !BLUE_TEAM_LOBBY.contains(Players.getMyPlayer().getLocation())
                && !RED_TEAM_LOBBY.contains(Players.getMyPlayer().getLocation());
    }

    @Override
    public void execute() {
        Variables.setCurrentNode(this);
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
