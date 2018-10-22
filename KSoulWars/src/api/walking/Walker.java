package api.walking;


import xobot.script.methods.Calculations;
import xobot.script.methods.Players;
import xobot.script.methods.Walking;
import xobot.script.util.Random;
import xobot.script.util.Time;
import xobot.script.wrappers.Path;
import xobot.script.wrappers.Tile;

import java.util.ArrayList;

/**
 * Created by HP xw8400
 * Author: Jacob
 * Date: 1/17/2018.
 */
public class Walker {

    public static void walkTowards(Tile t) {
        if (t.isReachable()) {
            Walking.walkTo(t);
        } else {
            Path path = new Path(createSteppedPath(Players.getMyPlayer().getLocation(), t, 15));

            Tile furthest = path.getNextTile();
            if (furthest != null) {
                Walking.walkTo(furthest);
                Time.sleep(() -> furthest.getDistance() < 12, 5000);
            }
        }
    }

    private static synchronized Tile[] createSteppedPath(Tile a, Tile b, double stepsBetweenTiles) {
        return windPath(a.getX(), a.getY(), b.getX(), b.getY(), 9D, 3D, stepsBetweenTiles, 8D);
    }

    /**
     * This algorithm was ripped and translated from the SRL Reflection include
     * 100% credit to BenLand100
     * source: https://github.com/SRL/srl-reflection/blob/master/Core/MMWalk.simba#L438
     *
     * @return Tile[]
     */
    private static synchronized Tile[] windPath(double Xs, double Ys, double Xe, double Ye, double Gravity, double Wind, double MaxStep, double TargetArea) {
        double veloX = 0, veloY = 0, WindX = 0, WindY = 0, veloMag, Dist, randomDist, Sqrt2, Sqrt3, Sqrt5;

        final ArrayList<Tile> tiles = new ArrayList<>();

        Sqrt2 = Math.sqrt(2);
        Sqrt3 = Math.sqrt(3);
        Sqrt5 = Math.sqrt(5);

        while (Math.hypot(Xs - Xe, Ys - Ye) > 1) {

            Dist = Math.hypot(Xs - Xe, Ys - Ye);
            Wind = Math.min(Wind, Dist);
            if (Dist >= TargetArea) {
                WindX = WindX / Sqrt3 + (2D * Math.random() - 1D) * Wind / Sqrt5;
                WindY = WindY / Sqrt3 + (2D * Math.random() - 1D) * Wind / Sqrt5;
            } else {
                WindX = WindX / Sqrt2;
                WindY = WindY / Sqrt2;
                if (MaxStep < 3) {
                    MaxStep = Random.nextInt(0, 3) + 3.0;
                } else {
                    MaxStep = MaxStep / Sqrt5;
                }
            }
            veloX = veloX + WindX;
            veloY = veloY + WindY;
            veloX = veloX + Gravity * (Xe - Xs) / Dist;
            veloY = veloY + Gravity * (Ye - Ys) / Dist;

            if (Math.hypot(veloX, veloY) > MaxStep) {
                randomDist = MaxStep / 2D + Math.random() * MaxStep / 2D;
                veloMag = Math.sqrt(veloX * veloX + veloY * veloY);
                veloX = (veloX / veloMag) * randomDist;
                veloY = (veloY / veloMag) * randomDist;
            }

            Xs = Xs + veloX;
            Ys = Ys + veloY;

            tiles.add(new Tile((int) Math.round(Xs), (int) Math.round(Ys)));
        }

        return tiles.toArray(new Tile[tiles.size()]);
    }
}
