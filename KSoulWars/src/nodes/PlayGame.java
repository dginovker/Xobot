package nodes;

import api.Data;
import api.activities.Activity;
import xobot.bot.Context;
import xobot.script.ActiveScript;
import xobot.script.methods.Game;
import xobot.script.methods.GameObjects;
import xobot.script.methods.input.KeyBoard;
import xobot.script.methods.tabs.Inventory;
import xobot.script.methods.tabs.Skills;
import xobot.script.util.Time;
import xobot.script.wrappers.interactive.GameObject;
import xobot.script.wrappers.interactive.Item;

public class PlayGame extends Node {
    private Data data;

    public PlayGame(ActiveScript aS) {
        super(aS);
    }

    @Override
    public boolean activate() {
        return Data.actual != null && Data.inGame() && !Data.isInSpawnOrGrave() && !Data.actual.isInLobby();
    }

    @Override
    public boolean execute(Data data) {
        this.data = data;

        Item bandage = Inventory.getItem(4049);

        if (bandage != null) {
            eatBandages(bandage);
        } else {
            //
            // we don't have bandages
            //
            // find the nearest reachable bandage table
            /////////////////////////////////////////////////////
            GameObject table = GameObjects.getNearest(gameObject -> (gameObject.getId() == 42023 || gameObject.getId() == 42024) && gameObject.isReachable());
            //
            // if the table exists, withdraw from it
            /////////////////////////////////////////////////////
            if (table != null) {
                Data.status = "Grabbing bandages";
                table.interact("take-x");
                //table.interact(3);
                if (Time.sleep(() -> Game.getInputState() == 1, 7000)) {
                    Context.client.setInputText("28");
                    if (!Context.client.getInputText().isEmpty()) {
                        KeyBoard.pressEnter();
                        //
                        // if we failed to withdraw bandages, and still can, then try again in 5 secs
                        //
                        /////////////////////////////////////////////////////
                        if (!Time.sleep(() -> Inventory.Contains(4049), 3000)) {
                            Time.sleep(500);
                            execute(data);
                            return true;
                        }
                    }
                }
            } else {
                Activity activity = data.getController().getActivity();
                if (activity != null) {
                    Data.status = "Performing " + activity.getClass().getSimpleName();
                    Data.status = activity.perform();
                }
            }
        }

        return true;
    }

    private void eatBandages(Item bandage) {
        if (getHealthPercent() < 50) {
            Data.status = "Healing";
            bandage.interact("eat");
        } else {
            //
            // try to perform some activity
            /////////////////////////////////////////////////////
            Activity activity = data.getController().getActivity();
            if (activity != null) {
                Data.status = "Performing " + activity.getClass().getSimpleName();
                Data.status = activity.perform();
            }
        }
    }

    public int getHealthPercent() {
        return 100 * Skills.CONSTITUTION.getCurrentLevel() / Skills.CONSTITUTION.getRealLevel();
    }
}
