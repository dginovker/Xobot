package Soulwars.nodes;

import Soulwars.aids.Helper_Functions;
import Soulwars.aids.Variables;
import xobot.bot.Context;
import xobot.script.methods.GameObjects;
import xobot.script.methods.Players;
import xobot.script.methods.input.KeyBoard;
import xobot.script.methods.tabs.Inventory;
import xobot.script.wrappers.interactive.GameObject;

import static Soulwars.aids.Constants.BANDAGES;
import static Soulwars.aids.Constants.BANDAGE_STALL;
import static Soulwars.aids.Constants.GAME_AREA;

/**
 * Created by Cyn on 1/23/2018.
 */
public class GrabBandages extends Soulwars.Node {

    private GameObject object;

    @Override
    public boolean validate() {
        return Variables.getCurrentTeam() != null
                && !Inventory.Contains(BANDAGES)
                && !Players.getMyPlayer().isInCombat()
                && GAME_AREA.contains(Players.getMyPlayer().getLocation());
    }

    @Override
    public void execute() {

        object = GameObjects.getNearest(BANDAGE_STALL);

        if (Context.client.getInputState() == 0 && object != null) {
            object.interact("take-x"); // SECOND FUCKING OPTION
            Helper_Functions.conditionalSleep(() -> Context.client.getInputState() == 3, 4500); //it better be fucking state 3
        }

        if (Context.client.getInputState() == 3) {
            Context.client.setInputText("25");
            KeyBoard.pressEnter();
            Helper_Functions.conditionalSleep(() -> Inventory.Contains(BANDAGES), 1200);
        }


    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
