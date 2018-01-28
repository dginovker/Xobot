import aids.Variables;

import java.awt.*;

/**
 * Created by SRH on 1/22/2018.
 */
public class Paint {
    public static void draw(Graphics g) {

        g.drawString("Script state: " + Variables.getScriptState(), 15, 280);
        g.drawString("In team: " + Variables.getCurrentTeam(), 15, 300);

    }
}
