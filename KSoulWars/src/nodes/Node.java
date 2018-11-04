package nodes;

import api.Data;

public abstract class Node {
    protected Data data;

    Node(Data data)
    {
        this.data = data;
    }

    public abstract boolean activate();

    public abstract boolean execute(Data data);
}
