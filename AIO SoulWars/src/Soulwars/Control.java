package Soulwars;

import Soulwars.nodes.Idle;
import xobot.client.callback.listeners.MessageListener;
import xobot.script.ActiveScript;

import java.util.ArrayList;

/**
 * Created by SRH on 1/22/2018.
 */
public class Control extends ActiveScript implements MessageListener {

    private int loopDelay = -1
    private final ArrayList<Node> nodes = new ArrayList<>();

    @Override
    public boolean onStart() {
        loopDelay = 50;
        nodes.add(new Idle());
        return true;
    }

    @Override
    public int loop() {
        for (Node node : nodes) {
            if (node.validate()) {
                node.execute();
            }
        }
        return loopDelay;
    }

    public void onStop() {
        loopDelay = -1;
    }
}
