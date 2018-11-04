package api;

import api.activities.Controller;
import api.activities.impl.Attacker;
import api.activities.impl.Fragmenter;
import api.activities.impl.Pyrefiender;
import api.team.Team;
import xobot.script.wrappers.Area;
import xobot.script.wrappers.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by HP xw8400
 * Author: Jacob
 * Date: 8/16/2018.
 */
public class Data extends JFrame {

    public static final int GREEN_PORTAL_ID = 42031;

    public enum JoinType {
        RANDOM, ALWAYS_RED, ALWAYS_BLUE;
    }
    private Controller controller;


    public static Team red, blue, preference, actual;
    public static String status;
    public static JoinType type;
    static {
        status = "Setting up";
        red = new Team(
                "Red",
                14641,
                42030,
                42018,
                new Area(new Tile(1900, 3157), new Tile(1909, 3166)),
                new Area(new Tile(1951, 3234), new Tile(1958, 3244)),
                new Area(new Tile(1916, 3200), new Tile(1938, 3221)),
                new Color(11468800)
        );

        blue = new Team(
                "Blue",
                14642,
                42029,
                42015,
                new Area(new Tile(1870, 3158), new Tile(1879, 3166)),
                new Area(new Tile(1816, 3220), new Tile(1823, 3230)),
                new Area(new Tile(1835, 3236), new Tile(1859, 3259)),
                new Color(175)
        );
    }

    public Data() {
        super("Soulwars");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.setResizable(false);
        this.setPreferredSize(new Dimension(200, 100));

        this.addComponents();
        this.pack();
        this.setLocationRelativeTo(getOwner());
        this.setVisible(true);
    }

    public static boolean inGame()
    {

        return (Data.actual = Team.autoDetect()) != null;
    }

    public static boolean isInSpawnOrGrave() {
        return Data.actual.isInSpawn() || Data.actual.isInEastGrave() || Data.actual.isInWestGrave();
    }

    public static void updateTeam() {
        switch (Data.type) {
            case ALWAYS_BLUE:
                preference = Data.blue;
                break;
            case ALWAYS_RED:
                preference = Data.red;
                break;
            case RANDOM:
            default:
                preference = Team.randomize(Data.red, Data.blue);
        }
    }

    public void addComponents() {
        final JPanel content = new JPanel();

        final JPanel activities = new JPanel();

        JComboBox<String> types = new JComboBox<>(new String[] {"Pyrefiends", "Attacker"});
        activities.add("Activity", types);

        JComboBox<String> teams = new JComboBox<>(new String[] {"Red", "Blue", "Random"});
        activities.add("Team", teams);

        final JButton start = new JButton("Start Script");
        start.addActionListener((ActionEvent e) -> {
            if (types.getSelectedItem().equals("Pyrefiends")) {
                controller = new Controller(new Fragmenter(), new Pyrefiender());
            } else {
                controller = new Controller(new Attacker());
            }

            if (teams.getSelectedItem().equals("Red")) {
                type = JoinType.ALWAYS_RED;
            } else if (teams.getSelectedItem().equals("Blue")) {
                type = JoinType.ALWAYS_BLUE;
            } else {
                type = JoinType.RANDOM;
            }
            updateTeam();
            actual = Team.autoDetect();

            Data.this.dispose();
            Data.this.setVisible(false);
        });

        content.add(teams);
        content.add(Box.createRigidArea(new Dimension(0, 10)));
        content.add(activities);

        this.add(content);
        this.add(start, BorderLayout.SOUTH);
    }

    public Controller getController() {
        return controller;
    }
}
