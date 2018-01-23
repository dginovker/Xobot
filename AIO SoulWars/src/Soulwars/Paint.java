package Soulwars;

import java.awt.*;

/**
 * Created by SRH on 1/22/2018.
 */
public class Paint {
    public static void draw(Graphics g) {

        if(Variables.getCurrentTeam() != null) {
            g.drawString("In team: " + Variables.getCurrentTeam(), 15, 300);
        }

    }
}
