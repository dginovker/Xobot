import Actions.Statement;
import GUI.GUI;
import xobot.client.callback.listeners.PaintListener;
import xobot.script.ActiveScript;
import xobot.script.Manifest;
import xobot.script.methods.Game;
import xobot.script.methods.NPCs;
import xobot.script.wrappers.Tile;
import xobot.script.wrappers.interactive.NPC;

import java.awt.*;
import java.util.ArrayList;

import static xobot.script.methods.input.KeyBoard.typeWord;

/**
 * Created by SRH on 1/9/2018.
 */

public class Main {
    public static void main(String[] args) {
        new GUI(new ArrayList<Statement>());
    }
}
/*

@Manifest(authors = { "Skattle, SRH" }, name = "TestScript4")
public class Main extends ActiveScript implements PaintListener {

    private ArrayList<Statement> actions = new ArrayList<>();

    private Paint painter;
    private GUI gui;

    @Override
    public int loop() {

        typeWord("Fuck", false);

        return Util.avrgRand(300, 2);
    }

    public boolean onStart() {
        painter = new Paint();

        gui = new GUI(actions);

        gui.setVisible(true);

        while (gui.isShowing())
        {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public void repaint(Graphics arg0) {
        painter.handlePaint(arg0);
    }
}*/
