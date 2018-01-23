import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import xobot.client.callback.listeners.MessageListener;
import xobot.client.callback.listeners.PaintListener;
import xobot.script.ActiveScript;
import xobot.script.Manifest;
import xobot.script.methods.Bank;
import xobot.script.methods.GameObjects;
import xobot.script.methods.NPCs;
import xobot.script.methods.Packets;
import xobot.script.methods.Walking;
import xobot.script.methods.Widgets;
import xobot.script.methods.tabs.Inventory;
import xobot.script.methods.tabs.Skills;
import xobot.script.util.Time;
import xobot.script.util.Timer;
import xobot.script.wrappers.Tile;
import xobot.script.wrappers.interactive.GameObject;
import xobot.script.wrappers.interactive.Item;
import xobot.script.wrappers.interactive.NPC;

@Manifest(authors = { "Neo, Skattle, SRH" }, name = "BestRuneCrafter")
public class BestRuneCrafter extends ActiveScript implements PaintListener, MessageListener{

    public Timer t = null;

    int crafted = 0;

    int altarid = 0;
    int portid = 0;

    int startxp = 0;

    String status = "Loading...";

    public boolean onStart() {
        t = new Timer(System.currentTimeMillis());

        startxp = Skills.getCurrentExp(Skills.RUNECRAFTING);
        return true;
    }

    @Override
    public int loop() {
        setIDs();
        try {
            Item i = Inventory.getItem(1436);
            if(i != null) {
                status = "Crafting..";
                if(Widgets.getBackDialogId() == 2459) {
                    Packets.sendAction(315, 1436, 13, 2461, 0, 0);
                    return 1000;
                }
                GameObject altar = GameObjects.getNearest(altarid);
                if(altar != null) {
                    altar.interact("craft");
                    return 450;
                }
                GameObject port = GameObjects.getNearest(portid);
                if(port != null && port.getLocation().isReachable()) {
                    port.interact("pass");
                    return 3000;
                }
                GameObject port1 = GameObjects.getNearest(7148, 7143, 7145, 7150, 7153);
                if(port1 != null) {
                    port1.interact("pass");
                    return 1500;
                }
                NPC n = NPCs.getNearest(2258);
                if(n != null) {
                    n.interact("talk-to");
                    return 500;
                }else {
                    Walking.walkTo(new Tile(3119, 3519));
                    return 1000;
                }
            }else {
                GameObject o = GameObjects.getNearest(26972);
                if(o != null) {
                    status = "Banking..";
                    if(Bank.isOpen()) {
                        Item ess = Bank.getItem(1436);
                        if(ess != null) {
                            if(!Inventory.isEmpty()) {
                                Bank.depositAll();
                                Time.sleep(250);
                            }
                            ess.interact("withdraw all");
                            return 1000;
                        }

                        return -1;
                    }else {
                        o.interact(4);
                        return 2000;
                    }

                }else {
                    status = "Teleporting home..";
                    Packets.sendAction(315, 449, 3, 1195, 0, 1);
                    return 5000;
                }
            }
        }catch(Exception e) {}
        return 0;
    }

    private void setIDs()
    {
        int level = Skills.getCurrentLevel(Skills.RUNECRAFTING);
        System.out.println(level);
        switch(getRune(level)) {
            case "Cosmic":
                portid = 7132;
                altarid = 2484;
                break;
            case "Blood":
                portid = 7141;
                altarid = 30624;
                break;
            case "Fire":
                portid = 7129;
                altarid = 2482;
                break;
            case "Earth":
                portid = 7130;
                altarid = 2481;
                break;
            case "Body":
                portid = 7131;
                altarid = 2483;
                break;
            case "Mind":
                portid = 7140;
                altarid = 2479;
                break;
            case "Air":
                portid = 7139;
                altarid = 2478;
                break;
            case "Soul":
                portid = 7138;
                altarid = 9999999;
                break;
            case "Water":
                portid = 7137;
                altarid = 2480;
                break;
            case "Death":
                portid = 7136;
                altarid = 2488;
                break;
            case "Law":
                portid = 7135;
                altarid = 2485;
                break;
            case "Chaos":
                portid = 7134;
                altarid = 2487;
                break;
        }
    }

    private String getRune(int level) {
        if (level >= 90)
        {
            return "Soul";
        }
        if (level >= 65)
        {
            return "Death";
        }
        if (level >= 54)
        {
            return "Law";
        }
        if (level >= 35)
        {
            return "Chaos";
        }
        if (level >= 27)
        {
            return "Cosmic";
        }
        if (level >= 20)
        {
            return "Body";
        }
        if (level >= 14)
        {
            return "Fire";
        }
        if (level >= 9)
        {
            return "Earth";
        }
        if (level >= 5)
        {
            return "Water";
        }
        if (level >= 2)
        {
            return "Mind";
        }

        return "Air";
    }

    private Image getImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch(IOException e) {
            return null;
        }
    }

    private final Color color1 = new Color(0, 0, 0);

    private final Font font1 = new Font("Arial", 0, 17);

    private final Image img1 = getImage("https://i.imgur.com/lqYcpKw.png");
    private final RenderingHints antialiasing = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


    @Override
    public void repaint(Graphics render) {

        int xp = Skills.getCurrentExp(Skills.RUNECRAFTING) - startxp;
        int xph = (int) ((xp) * 3600000D / (t.getElapsed()));
        int ph = (int) ((crafted) * 3600000D / (t.getElapsed()));

        Graphics2D g = (Graphics2D)render;
        g.setRenderingHints(antialiasing);

        g.drawImage(img1, 0, 162, null);
        g.setFont(font1);
        g.setColor(color1);
        g.drawString(t.toElapsedString(), 158, 248);
        g.drawString(status, 325, 248);
        g.drawString(String.valueOf(xp), 325, 296);
        g.drawString(String.valueOf(xph), 158, 296);
        g.drawString(String.valueOf(ph), 158, 272);
        g.drawString(String.valueOf(crafted), 325, 272);

    }

    @Override
    public void MessageRecieved(String s, int i, String arg2) {
        String text = s;
        if(i == 0 && text.toLowerCase().startsWith("you bind the temple's power into")) {
            text = text.toLowerCase().replace("you bind the temple's power into ", "");
            crafted += Integer.valueOf(text.split(" ")[0]);
        }
    }

}