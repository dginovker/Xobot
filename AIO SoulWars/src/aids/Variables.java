package aids;

import xobot.script.wrappers.Tile;

/**
 * Created by Cyn on 1/23/2018.
 */
public class Variables {
    private Variables() {
        // NO INSTANTIATION
    }

    private static int loopDelay = -1;
    private static long nextGame;
    private static Team currentTeam;
    private static String scriptState;
    private static String scriptNode;


    public static int getLoopDelay() {
        return loopDelay;
    }

    public static void setLoopDelay(int loopDelay) {
        Variables.loopDelay = loopDelay;
    }

    public static long getNextGame() {
        return nextGame;
    }

    public static void setNextGame(long nextGame) {
        Variables.nextGame = nextGame;
    }

    public static Team getCurrentTeam() {
        return currentTeam;
    }

    public static void setCurrentTeam(Team currentTeam) {
        Variables.currentTeam = currentTeam;
    }

    public static String getScriptState() {
        return scriptState;
    }

    public static void setScriptState(String setter) {
        scriptState = setter;
    }

    public static String getScriptNode() {
        return scriptNode;
    }

    public static void setScriptNode(String node) {
        scriptNode = node;
    }

    public enum Team {

        BLUE(0, new Tile(1234, 1234), new Tile(1234, 1234)),
        RED(0, new Tile(1234, 1234), new Tile(1234, 1234));


        private int capeId;
        private Tile spawnTile;
        private Tile graveTile;

        Team(int capeId, Tile spawnTile, Tile graveTile) {
            this.capeId = capeId;
            this.spawnTile = spawnTile;
            this.graveTile = graveTile;
        }

        public Tile getGraveTile() {
            return graveTile;
        }

        public Tile getSpawnTile() {
            return spawnTile;
        }

        public int getCapeId() {
            return capeId;
        }
    }
}
