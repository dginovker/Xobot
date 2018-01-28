import aids.Constants;
import aids.Variables;
import nodes.Node;
import xobot.client.callback.listeners.MessageListener;
import xobot.client.callback.listeners.PaintListener;
import xobot.script.ActiveScript;
import xobot.script.Manifest;
import xobot.script.methods.Players;

import java.awt.*;
import java.util.ArrayList;

import static aids.Variables.setLoopDelay;

/**
 * Created by SRH on 1/22/2018.
 */
@Manifest(authors = { "Jake, SRH, Skattle, Kappakek" }, name = "AIO SoulWars", version = 0.1, description = "")
public class Control extends ActiveScript implements MessageListener, PaintListener {
    private final ArrayList<nodes.Node> farmFragments = new ArrayList<>();
    private final ArrayList<nodes.Node> joinGame = new ArrayList<>();
    private final ArrayList<nodes.Node> conquorMap = new ArrayList<>();
    private ArrayList<Node> activeNode = null;

    private aids.Helper_Functions helper;

    @Override
    public boolean onStart() {
        setLoopDelay(300);
        farmFragments.add(new nodes.Dead());
        joinGame.add(new nodes.JoinLobby());
        conquorMap.add(new nodes.Idle());
        return true;
    }

    @Override
    public int loop() {
        setActiveNode();

        for (nodes.Node node : activeNode) {
            if (node.validate()) {
                node.execute();
            }
        }
        return Variables.getLoopDelay();
    }

    private void setActiveNode() {
        if (!Constants.GAME_AREA.contains(Players.getMyPlayer().getLocation()))
        {
            activeNode = joinGame;
            Variables.setScriptState("Joining Game");
            return;
        }
/*        if (helper.isObliskMine())
        {
            activeNode = farmFragments;
            Variables.setScriptState("Farming Fragments");
            return;
        }*/
        Variables.setScriptState("Conquering Map");
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
    public void repaint(Graphics g)
    {
        Paint.draw(g);
    }
}
