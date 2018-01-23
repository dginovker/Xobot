import java.awt.*;

import xobot.client.callback.listeners.MessageListener;
import xobot.client.callback.listeners.PaintListener;
import xobot.script.ActiveScript;
import xobot.script.Manifest;
import xobot.script.methods.GameObjects;
import xobot.script.methods.Packets;
import xobot.script.methods.Players;
import xobot.script.methods.Walking;
import xobot.script.methods.tabs.Skills;
import xobot.script.util.Time;
import xobot.script.util.Timer;
import xobot.script.wrappers.Area;
import xobot.script.wrappers.Tile;
import xobot.script.wrappers.interactive.GameObject;


@Manifest(authors = { "pepsip77, Skattle, SRH" }, name = "Wildy Agility Course", version = 0.1, description = "")
public class WildyCourse extends ActiveScript implements PaintListener, MessageListener {

    private Obstacle currObstacle;
    private int startxp;
    private Timer t;
    private int completed = 0;
    private int deaths;
    private int nowExp;

    @Override
    public boolean onStart() {
        startxp = Skills.getCurrentExp(Skills.AGILITY);
        t = new Timer(System.currentTimeMillis());
        nowExp = Skills.getCurrentExp(Skills.AGILITY);

        currObstacle = null;
        return true;
    }

    @Override
    public void onStop() {

    }

    @Override
    public int loop() {
        if (Skills.getCurrentExp(Skills.AGILITY) - nowExp > 20000)
        {
            completed++;
        }
        System.out.println(":Exp:" + (Skills.getCurrentExp(Skills.AGILITY) - nowExp));
        nowExp = Skills.getCurrentExp(Skills.AGILITY);

        if (isAtCourse()) {
            if (!Players.getMyPlayer().isMoving() && Players.getMyPlayer().getAnimation() == -1) {
                currObstacle = getNextObstacle();
                if(currObstacle != null) {
                    if(Players.getMyPlayer().getLocation().getX() == currObstacle.beginTile.getX() &&
                            Players.getMyPlayer().getLocation().getY() == currObstacle.beginTile.getY()) {
                        GameObject o = GameObjects.getNearest(currObstacle.getId());
                        if( o != null && o.isReachable()) {
                            Packets.sendAction(502, o.uid, o.getX(), o.getY(), o.getId(), 1);
                            Time.sleep(950, 1050);
                        }
                    }else {
                        Walking.walkTo(currObstacle.beginTile);
                        Time.sleep(950, 1050);
                    }
                }
            }
        }
        else
        {
            goToCourse();

            GameObject o = GameObjects.getNearest(Obstacle.DOOR.getId());
            if( o != null && o.isReachable()) {
                o.interact("Open");
                Time.sleep(5000);
            }
        }

        return 300;
    }

    private void goToCourse() {
        Packets.sendAction(315, 0, 0, 1540);
        Time.sleep(500);
        Packets.sendAction(315, 0, 0, 2462);
        Time.sleep(500);
        Packets.sendAction(315, 0, 0, 2498);
        Time.sleep(500);
        Packets.sendAction(315, 0, 0, 2496);
        Time.sleep(500);
        Packets.sendAction(315, 0, 0, 2484);
        Time.sleep(500);
    }

    public boolean isAtCourse() {
        Area courseArea = new Area(new Tile(2987, 3931), new Tile(3009, 3980));

        if (courseArea.contains(Players.getMyPlayer().getLocation()))
        {
            return true;
        }
        System.out.println("Not at course");
        return false;
    }

    public Obstacle getNextObstacle() {
        if ((Players.getMyPlayer().getLocation().getY() <= Obstacle.PIPE.getBeginTile().getY()
                && Players.getMyPlayer().getLocation().getX() >= 2997)
                ||Players.getMyPlayer().getLocation().getY() <= Obstacle.WALL.getEndTile().getY()) {
            return Obstacle.PIPE;
        } else {
            boolean thisOne = false;
            for (Obstacle o : Obstacle.values()) {
                if (thisOne)
                    return o;
                if (Players.getMyPlayer().getLocation().getX() == o.getEndTile().getX() &&
                        Players.getMyPlayer().getLocation().getY() == o.getEndTile().getY())
                    thisOne = true;
                if (Players.getMyPlayer().getLocation().getX() == o.getBeginTile().getX() &&
                        Players.getMyPlayer().getLocation().getY() == o.getBeginTile().getY())
                    return o;
            }
        }
        return Obstacle.DOOR;
    }

    @Override
    public void MessageRecieved(String s, int i, String s1) {
        if (s.contains("You can't teleport"))
        {
            System.out.println("Fuck");
            Time.sleep(500000);
        }
        if (s.contains("you are dead"))
        {
            deaths++;
        }
    }

    public enum Obstacle {
        PIPE(2288, new Tile(3004, 3937), new Tile(3004, 3950)),
        SWING(2283, new Tile(3005, 3953), new Tile(3005, 3958)),
        STONES(2311, new Tile(3002, 3960), new Tile(2996, 3960)),
        LOG(2297, new Tile(3002, 3945),	new Tile(2994, 3945)),
        WALL(2328, new Tile(2995, 3937), new Tile(2995, 3933)),
        DOOR(2309, new Tile(2998, 3916), new Tile(2998, 3920));

        private int id;
        private Tile beginTile;
        private Tile endTile;

        Obstacle(int id, Tile beginTile, Tile endTile) {
            this.id = id;
            this.beginTile = beginTile;
            this.endTile = endTile;
        }

        public int getId() {
            return id;
        }

        public Tile getBeginTile() {
            return beginTile;
        }

        public Tile getEndTile() {
            return endTile;
        }
    }



    private final Color color1 = Color.WHITE;

    private final Font font1 = new Font("Arial", 0, 17);

    private final RenderingHints antialiasing = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    @Override
    public void repaint(Graphics render) {

        int xp = Skills.getCurrentExp(Skills.AGILITY) - startxp;
        int xph = (int) ((xp) * 3600000D / (t.getElapsed()));
        int ph = (int) ((completed) * 3600000D / (t.getElapsed()));

        Graphics2D g = (Graphics2D)render;
        g.setRenderingHints(antialiasing);

        g.setFont(font1);
        g.setColor(color1);
        g.drawString(("Time ran: " + t.toElapsedString()), 158, 248);
        g.drawString("Deaths: " + deaths, 325, 248);
        g.drawString(("Exp gained: " + String.valueOf(xp)), 325, 296);
        g.drawString(("Exp p/h: " + String.valueOf(xph)), 158, 296);
        g.drawString(("Runs p/h: " + String.valueOf(ph)), 158, 272);
        g.drawString("Agility runs: " + (String.valueOf(completed)), 325, 272);

    }

}