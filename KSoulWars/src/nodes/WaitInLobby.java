package nodes;

import api.Data;
import xobot.script.ActiveScript;

public class WaitInLobby extends Node {
    public WaitInLobby(ActiveScript aS) {
        super(aS);
    }

    @Override
    public boolean activate() {
        return Data.actual != null && Data.actual.isInLobby();
    }

    @Override
    public boolean execute(Data data) {
        Data.status = "Waiting for game to start" + getClass().getSimpleName();

        return true;
    }
}
