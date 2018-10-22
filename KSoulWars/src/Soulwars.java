import api.Data;
import api.activities.Activity;

import api.team.Team;

import xobot.bot.Context;
import xobot.client.callback.listeners.MessageListener;
import xobot.client.callback.listeners.PaintListener;
import xobot.client.events.MessageEvent;
import xobot.script.ActiveScript;
import xobot.script.Manifest;
import xobot.script.methods.*;
import xobot.script.methods.input.KeyBoard;
import xobot.script.methods.tabs.Inventory;
import xobot.script.methods.tabs.Skills;
import xobot.script.util.Filter;
import xobot.script.util.Random;
import xobot.script.util.Time;
import xobot.script.util.Timer;

import xobot.script.wrappers.interactive.GameObject;
import xobot.script.wrappers.interactive.Item;

import java.awt.*;
import java.util.concurrent.Callable;

import static api.Data.updateTeam;

/**
 * Created by HP xw8400
 * Author: Jacob
 * Date: 1/14/2018.
 *
 * Modified by Skattle
 * Author: SRH
 * Date: 10/16/2018.
 */

@Manifest(authors = { "Kumalo", "Skattle" }, name = "Kumalo Soulwars", description = "Plays the SoulWars minigame", version = 1.1)

public class Soulwars extends ActiveScript implements PaintListener, MessageListener {

    private Timer startTime;
    private int zeal = 0, wins = 0, losses = 0, ties = 0;
    private final Color black = new Color(0, 0, 0, 127);
    private Data data;

    @Override
    public boolean onStart() {
        startTime = new Timer();
        data = new Data();

        while (data.isVisible()) {
            Time.sleep(500);
        }

        return data.getController() != null;
    }

    @Override
    public int loop() {
        try {
            if (Game.isLoggedIn()) {
                //
                // if we are in a SoulWars game
                /////////////////////////////////////////////////////
                if ((Data.actual = Team.autoDetect()) != null) {
                    //
                    // check if we are in lobby wait area
                    /////////////////////////////////////////////////////
                    if (Data.actual.isInLobby()) {
                        Data.status = "Waiting for game to start";
                        //
                        // not much we can do besides waiting for the game to start
                        /////////////////////////////////////////////////////
                    } else if (Data.actual.isInSpawn()) {
                        Data.status = "Leaving spawn";
                        //
                        // we are in spawn, find the nearest portal
                        /////////////////////////////////////////////////////
                        GameObject spawn = GameObjects.getNearest(new Filter<GameObject>() {
                            @Override
                            public boolean accept(GameObject gameObject) {
                                return gameObject.getId() == Data.actual.getSpawnID() && gameObject.isReachable();
                            }
                        });
                        //
                        // if it exists (it should always be there SINCE we are in spawn)
                        //
                        // pass through it, sleep until we are out, or until 5 seconds have passed
                        /////////////////////////////////////////////////////
                        if (spawn != null) {
                            //spawn.interact(4);
                            spawn.interact("pass");
                            Time.sleep(new Callable<Boolean>() {
                                @Override
                                public Boolean call() throws Exception {
                                    return !Data.actual.isInSpawn();
                                }
                            }, 5000);
                        }
                    } else if (Data.actual.isInEastGrave()) {
                        Data.status = "Leaving east grave";
                        Time.sleep(15000, 20000);
                        GameObject grave = GameObjects.getNearest(new Filter<GameObject>() {
                            @Override
                            public boolean accept(GameObject gameObject) {
                                return gameObject.getId() == 42019 && gameObject.isReachable();
                            }
                        });
                        //
                        // if it exists (it should always be there SINCE we are in spawn)
                        //
                        // pass through it, sleep until we are out, or until 5 seconds have passed
                        /////////////////////////////////////////////////////
                        if (grave != null) {
                            // grave.interact(4);
                            grave.interact("pass");
                            Time.sleep(new Callable<Boolean>() {
                                @Override
                                public Boolean call() throws Exception {
                                    return !Data.actual.isInEastGrave();
                                }
                            }, 5000);
                        }
                    } else if (Data.actual.isInWestGrave()) {
                        Data.status = "Leaving west grave";
                        Time.sleep(15000, 20000);
                        GameObject grave = GameObjects.getNearest(new Filter<GameObject>() {
                            @Override
                            public boolean accept(GameObject gameObject) {
                                return gameObject.getId() == 42020 && gameObject.isReachable();
                            }
                        });
                        //
                        // if it exists (it should always be there SINCE we are in spawn)
                        //
                        // pass through it, sleep until we are out, or until 5 seconds have passed
                        /////////////////////////////////////////////////////
                        if (grave != null) {
                            // grave.interact(4);
                            grave.interact("pass");
                            Time.sleep(new Callable<Boolean>() {
                                @Override
                                public Boolean call() throws Exception {
                                    return !Data.actual.isInWestGrave();
                                }
                            }, 5000);
                        }
                    } else {
                        Item bandage = Inventory.getItem(4049);
                        //
                        // if we have bandages in inventory
                        //
                        // eat them
                        /////////////////////////////////////////////////////
                        if (bandage != null) {
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
                        } else {
                            //
                            // we don't have bandages
                            //
                            // find the nearest reachable bandage table
                            /////////////////////////////////////////////////////
                            GameObject table = GameObjects.getNearest(new Filter<GameObject>() {
                                @Override
                                public boolean accept(GameObject gameObject) {
                                    return (gameObject.getId() == 42023 || gameObject.getId() == 42024) && gameObject.isReachable();
                                }
                            });
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
                                            return 500;
                                        }
                                    }
                                }
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
                    }
                } else {
                    //
                    // we aren't in a SoulWars game
                    //
                    // find the closest lobby portal
                    /////////////////////////////////////////////////////
                    GameObject portal = GameObjects.getNearest(new Filter<GameObject>() {
                        @Override
                        public boolean accept(GameObject gameObject) {
                            return gameObject.getId() == Data.preference.getLobbyID() && gameObject.isReachable();
                        }
                    });
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1200;
    }

    public void repaint(Graphics g) {
        g.setColor(black);
        g.fillRect(0, 270, 516, 69);

        g.setColor(Color.WHITE);

        g.drawString("Run time: " + startTime.toElapsedString(), 20, 295);
        g.drawString("Wins/Losses/Ties: " + wins + "/" + losses + "/" + ties, 200, 295);
        g.drawString("Zeal (p/h): " + zeal + " (" + Math.round(zeal * (3600.0 / (startTime.getElapsed() / 1000.0))) + ")", 420, 295);

        g.drawString("Auto SoulWars V" + this.getClass().getAnnotation(Manifest.class).version() + ": " + Data.status, 20, 325);
        if (Data.actual != null) {
            g.drawString("Team: ", 420, 325);
            g.setColor(Data.actual.getColor());
            g.drawString(Data.actual.toString(), 460, 325);
        } else {
            g.drawString("Team: null", 420, 325);
        }
    }

    public int getHealthPercent() {
        return 100 * Skills.CONSTITUTION.getCurrentLevel() / Skills.CONSTITUTION.getRealLevel();
    }

    public void MessageRecieved(MessageEvent event) {
        if (event.getType() == 0) {
            if (event.getMessage().contains("won the Soulwars")) {
                zeal += 3;
                wins++;
                updateTeam();
            } else if (event.getMessage().contains("lost the Soulwars")) {
                zeal++;
                losses++;
                updateTeam();
            } else if (event.getMessage().contains("Tie game!")) {
                zeal += 2;
                ties++;
                updateTeam();
            } else if (event.getMessage().contains("recieved extra zeal")) {
                zeal++;
                updateTeam();
            } else if (event.getMessage().contains("loyalty") || event.getMessage().contains("imbalanced")) {
                if (Data.preference == Data.red) {
                    Data.preference = Data.blue;
                } else {
                    Data.preference = Data.red;
                }
            } else if (event.getMessage().contains("the green")) {
                updateTeam();
            }
        }
    }
}
