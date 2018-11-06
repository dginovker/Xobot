import api.Data;

import nodes.*;
import xobot.client.callback.listeners.MessageListener;
import xobot.client.callback.listeners.PaintListener;
import xobot.client.events.MessageEvent;
import xobot.script.ActiveScript;
import xobot.script.Manifest;

import xobot.script.util.Time;
import xobot.script.util.Timer;


import java.awt.*;

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

@Manifest(authors = { "Kumalo", "Skattle", "Chad" }, name = "Kumalo Soulwars", description = "Plays the SoulWars minigame [Updated!]", version = 1.3)

public class Soulwars extends ActiveScript implements PaintListener, MessageListener {

    private Timer startTime;
    private int zeal = 0, wins = 0, losses = 0, ties = 0;
    private final Color black = new Color(0, 0, 0, 127);
    public Data data;

    private final Node[] array = new Node[] {new EnterGame(data), new WaitInLobby(data), new LeaveSpawnOrGrave(data), new PlayGame(data)};

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


        for (final Node node : array)
        {
            if (node.activate())
            {
                node.execute(data);
            }
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

        g.drawString("KSoulWars V" + this.getClass().getAnnotation(Manifest.class).version() + ": " + Data.status, 20, 325);
        if (Data.actual != null) {
            g.drawString("Team: ", 420, 325);
            g.setColor(Data.actual.getColor());
            g.drawString(Data.actual.toString(), 460, 325);
        } else {
            g.drawString("Team: null", 420, 325);
        }
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
                Data.type = Data.JoinType.RANDOM;
            }
        }
    }
}
