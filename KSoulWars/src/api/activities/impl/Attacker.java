package api.activities.impl;

import api.Data;
import api.activities.Activity;
import api.team.Team;
import api.walking.Walker;
import xobot.script.methods.Players;
import xobot.script.util.Filter;
import xobot.script.util.Time;
import xobot.script.wrappers.interactive.Player;

/**
 * Created by HP xw8400
 * Author: Jacob
 * Date: 8/3/2018.
 */
public class Attacker extends Activity {

    @Override
    public boolean requires() {
        return Players.getMyPlayer().getInteractingIndex() == -1;
    }

    @Override
    public String perform() {

        Player target = Players.getNearest(new Filter<Player>() {
            @Override
            public boolean accept(Player player) {
                return player.getEquipment()[1] != Data.actual.getIdentifier() && !player.isDead() && player.isReachable();
            }
        });

        if (target != null) {
            target.interact("attack");

            if (Time.sleep(() -> Players.getMyPlayer().getInteractingIndex() != -1, 5000)) {
                return "Success";
            }
            return "Timed-out";
        } else if (!Team.obelisk.getCentralTile().onMinimap()) {
            Walker.walkTowards(Team.obelisk.getCentralTile().randomize(3, 3));
            return "Walking";
        }

        return "Failure";
    }

}
