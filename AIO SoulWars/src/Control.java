import xobot.client.callback.listeners.MessageListener;
import xobot.client.callback.listeners.PaintListener;
import xobot.script.ActiveScript;

import java.awt.*;

/**
 * Created by SRH on 1/22/2018.
 */
public class Control extends ActiveScript implements PaintListener, MessageListener {
    @Override
    public int loop() {
        return 0;
    }

    @Override
    public void MessageRecieved(String s, int i, String s1) {

    }

    @Override
    public void repaint(Graphics graphics) {

    }

}
