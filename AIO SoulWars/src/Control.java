import xobot.client.callback.listeners.MessageListener;
import xobot.script.ActiveScript;

/**
 * Created by SRH on 1/22/2018.
 */
public class Control extends ActiveScript implements MessageListener {

    public Helper_Functions.State scriptState;

    @Override
    public int loop() {
        updateState();
        handleState();

        return 300;
    }

    /*
        Needs to be coded
        updateState() will look at the state of the player and update scriptState variable accordingly.
     */
    private void updateState() {

    }

    /*
        HandleState will look at scriptState and run the function that handles that state.
        Coded by: Skattle,
     */
    private void handleState() {
        switch(scriptState)
        {
            case GHOST:
                leaveSpawnRoom();
                break;
            case WALKING_TO_AVATAR:
                walkToAvatar();
                break;
            case WALKING_TO_WEST_GRAVEYARD:
                walkToWestGrave();
                break;
            case WALKING_TO_EAST_GRAVEYARD:
                walkToEastGrave();
                break;
            case WALKING_TO_MIDDLE:
                walkToMiddle();
                break;
            case FIGHTING:
                handleCombat();
                break;
            case WALKING_TO_JELLIES:
                walkToJellies();
                break;
            case WALKING_TO_PYREFIENDS:
                walkToPyrefiends();
                break;
            case COLLECTING_SHARDS:
                collectShards();
                break;
            case USING_SHARDS_ON_OBLISK:
                useShards();
                break;
        }
    }

    private void useShards() {
    }

    private void collectShards() {
    }

    private void walkToPyrefiends() {
    }

    private void walkToJellies() {
    }

    private void handleCombat() {
    }

    private void walkToMiddle() {
    }

    private void walkToEastGrave() {
    }

    private void walkToWestGrave() {
    }

    private void walkToAvatar() {
    }

    private void leaveSpawnRoom() {
    }

    @Override
    public void MessageRecieved(String s, int i, String s1) {

    }
}
