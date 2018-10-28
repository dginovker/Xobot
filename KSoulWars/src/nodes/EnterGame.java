package nodes;

import api.Data;
import xobot.script.ActiveScript;
import xobot.script.methods.GameObjects;
import xobot.script.methods.Packets;
import xobot.script.methods.Walking;
import xobot.script.methods.Widgets;
import xobot.script.util.Random;
import xobot.script.util.Time;
import xobot.script.wrappers.interactive.GameObject;

public class EnterGame extends Node {
    public EnterGame(ActiveScript aS) {
        super(aS);
    }

    @Override
    public boolean activate() {
        return !Data.inGame();
    }

    @Override
    public boolean execute(Data data) {
        GameObject portal = GameObjects.getNearest(gameObject -> gameObject.getId() == Data.preference.getLobbyID() && gameObject.isReachable());
        if (portal != null) {
            Data.status = "Entering " + Data.preference.toString() + " portal";
            portal.interact("enter");
            Time.sleep(1000);
            if (Time.sleep(() -> (Widgets.getBackDialogId() == 968), 8000)) {
                Packets.sendAction(679, 0, 0, 972);
                if (Time.sleep(() -> (Widgets.getBackDialogId() == 2459), 3000)) {
                    Packets.sendAction(315, 0, 0, 2461);
                    Time.sleep(() -> Data.preference.isInSpawn(), 5000);
                }
            } else if (Data.preference.isInLobby()) {
                if (Random.nextBoolean()) {
                    Data.status = "Being human";
                    Time.sleep(Random.nextInt(50, 6000));
                    Walking.walkTo(Data.preference.getLobbyArea().getCentralTile().randomize(3, 3));
                }
            } else {
                // we failed to enter....? (or it took longer than 8 seconds)
            }
        }

        return true;
    }
}
