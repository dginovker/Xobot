package nodes;

import api.Data;
import xobot.script.ActiveScript;

public abstract class Node {
    protected ActiveScript aS;

    public Node (ActiveScript aS)
    {
        this.aS = aS;
    }

    public abstract boolean activate();

    public abstract boolean execute(Data data);
}
