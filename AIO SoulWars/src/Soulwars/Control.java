package Soulwars;

import Soulwars.aids.Helper_Functions;
import Soulwars.nodes.Idle;
import Soulwars.nodes.JoinLobby;
import xobot.client.Player;
import xobot.client.callback.listeners.MessageListener;
import xobot.client.callback.listeners.PaintListener;
import xobot.script.ActiveScript;
import xobot.script.Manifest;
import xobot.script.methods.Players;

import java.awt.*;
import java.util.ArrayList;

import static Soulwars.aids.Variables.getLoopDelay;
import static Soulwars.aids.Variables.setLoopDelay;

/**
 * Created by SRH on 1/22/2018.
 */
@Manifest(authors = { "Jake, SRH, Skattle, Kappakek" }, name = "AIO SoulWars", version = 0.1, description = "")
public class Control extends ActiveScript implements MessageListener, PaintListener {

    private final ArrayList<Node> farmFragments = new ArrayList<>();
    private final ArrayList<Node> joinGame = new ArrayList<>();
    private final ArrayList<Node> conquorMap = new ArrayList<>();
    private ArrayList<Node> activeNode = null;

    private Helper_Functions helper;

    @Override
    public boolean onStart() {
        setLoopDelay(50);
        farmFragments.add(new Idle());
        joinGame.add(new JoinLobby());
        conquorMap.add(new Idle());
        return true;
    }

    @Override
    public int loop() {
        setActiveNode();

        for (Node node : activeNode) {
            if (node.validate()) {
                node.execute();
            }
        }
        return getLoopDelay();
    }

    private void setActiveNode() {
        if (!helper.isInGame())
        {
            activeNode = joinGame;
            return;
        }
        if (helper.isObliskMine())
        {
            activeNode = farmFragments;
            return;
        }
        activeNode = conquorMap;
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
