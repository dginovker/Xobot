package nodes;

import api.Data;
import xobot.script.ActiveScript;
import xobot.script.methods.*;
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
        if (Data.type == Data.JoinType.RANDOM)
        {
            joinGreenPortal();
        }
        else
        {
            joinWantedPortal();
        }

        return true;
    }

    private void joinGreenPortal() {
        GameObject greenPortal = GameObjects.getNearest(gameObject -> gameObject.getId() == Data.GREEN_PORTAL_ID && gameObject.isReachable());
        if (greenPortal != null)
        {
            Data.status = "Entering Green Portal";
            greenPortal.interact("Join-team");
            Time.sleep(10000); //because I can't find a sleepUntil function
        }
    }

    private void joinWantedPortal() {
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
    }
}
