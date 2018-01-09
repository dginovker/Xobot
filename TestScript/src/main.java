import xobot.client.callback.listeners.PaintListener;
import xobot.script.ActiveScript;
import xobot.script.Manifest;
import xobot.script.methods.tabs.Skills;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by SRH on 1/9/2018.
 */

@Manifest(authors = { "Skattle, SRH" }, name = "TestScript")
public class main extends ActiveScript implements PaintListener {

    private Paint painter;

    @Override
    public int loop() {
        return 0;
    }

    public boolean onStart() {
        painter = new Paint();

        return true;
    }

    @Override
    public void repaint(Graphics arg0) {
        painter.handlePaint(arg0);
    }
}
