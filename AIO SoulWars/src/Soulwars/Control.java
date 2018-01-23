package Soulwars;

import Soulwars.nodes.Idle;
import xobot.client.callback.listeners.MessageListener;
import xobot.client.callback.listeners.PaintListener;
import xobot.script.ActiveScript;

import java.awt.*;
import java.util.ArrayList;

import static Soulwars.Variables.getLoopDelay;
import static Soulwars.Variables.setLoopDelay;

/**
 * Created by SRH on 1/22/2018.
 */
public class Control extends ActiveScript implements MessageListener, PaintListener {

    private final ArrayList<Node> nodes = new ArrayList<>();

    @Override
    public boolean onStart() {
        setLoopDelay(50);
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
        return getLoopDelay();
    }

    public void onStop() {
        setLoopDelay(-1);
    }

    @Override
    public void MessageRecieved(String message, int type, String username) {
        MessageHandler.handle(message, type, username);
    }

    @Override
    public void repaint(Graphics g) {
        Paint.draw(g);
    }
}
