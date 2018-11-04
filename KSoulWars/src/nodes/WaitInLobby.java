package nodes;

import api.Data;

public class WaitInLobby extends Node {
    public WaitInLobby(Data data) {
        super(data);
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
