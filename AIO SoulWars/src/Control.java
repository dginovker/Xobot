import aids.Constants;
import aids.Variables;
import nodes.*;
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
@Manifest(authors = { "Jake, SRH, Skattle, Kappakek" }, name = "AIO SoulWars", version = 0.1, description = "Plays Soul Wars for that sweet sweet Zeal")
public class Control extends ActiveScript implements MessageListener, PaintListener {
    private final ArrayList<nodes.Node> farmFragments = new ArrayList<>();
    private final ArrayList<nodes.Node> joinGame = new ArrayList<>();
    private final ArrayList<nodes.Node> conquorMap = new ArrayList<>();
    private ArrayList<Node> activeNode = null;


    @Override
    public boolean onStart() {
        setLoopDelay(300);
        addJoinLobbyNodes();
        addConquorMapNodes();
        addFarmFragmentsNodes();
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

    private void addJoinLobbyNodes() {
        joinGame.add(new JoinLobby());
        joinGame.add(new SetTeam());
    }

    private void addFarmFragmentsNodes() {
        conquorMap.add(new Dead());
        conquorMap.add(new GrabBandages());
        conquorMap.add(new Combat());
    }

    private void addConquorMapNodes() {
        conquorMap.add(new Dead());
        conquorMap.add(new GrabBandages());
        conquorMap.add(new Combat());
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
