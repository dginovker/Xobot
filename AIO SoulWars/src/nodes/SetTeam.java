package nodes;

import aids.Variables;
import xobot.script.methods.Players;

import static aids.Constants.BLUE_TEAM_LOBBY;
import static aids.Constants.RED_TEAM_LOBBY;

public class SetTeam extends Node {
    @Override
    public boolean validate() {
        return BLUE_TEAM_LOBBY.contains(Players.getMyPlayer().getLocation())
                || RED_TEAM_LOBBY.contains(Players.getMyPlayer().getLocation());
    }

    @Override
    public void execute() {
        Variables.setCurrentNode(this);
        if (BLUE_TEAM_LOBBY.contains(Players.getMyPlayer().getLocation()))
        {
            Variables.setCurrentTeam(Variables.Team.BLUE);
        }
        else
        {
            Variables.setCurrentTeam(Variables.Team.RED);
        }
        Variables.setScriptNode("Chilling in lobby");
    }

    @Override
    public String toString() {
        return "Chilling & Setting Team";
    }
}
