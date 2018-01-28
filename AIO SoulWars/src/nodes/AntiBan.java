package nodes;

import xobot.script.methods.Players;
import xobot.script.util.Time;
import xobot.script.wrappers.interactive.Player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Thanks to Scriptss for the code :)
 */
public class AntiBan extends Node {

    private static final Set<String> staff = new HashSet<String>(Arrays.asList(
            new String[] {"alec","aleksandr","ali","angrod", "aozaki", "bartra", "benhammer", "bullfrog", "cedric", "chuchuz", "danny", "ed", "eve", "fearless", "immortal", "ipso", "jagex", "jambita", "jombo", "king julius", "lush", "mark", "melo", "michael", "nexus", "seedo92", "shaun", "sophie", "tnuc", "trumps"}
    ));

    @Override
    public boolean validate() {
        return modsNearby();
    }

    @Override
    public void execute() {
        //Try to log out packet
        Time.sleep(500000);
    }

    @Override
    public String toString() {
        return null;
    }

    private boolean modsNearby() {
        try {
            for (Player p: Players.getAll()) {
                if (staff.contains(p.getName().toLowerCase())) {
                    System.out.println("SPOOKED: " + p.getName());
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println("modsNearby error");
            return false;
        }
    }
}
