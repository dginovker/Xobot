package api.activities;

/**
 * Created by HP xw8400
 * Author: Jacob
 * Date: 8/3/2018.
 */
public abstract class Activity {

    public Activity() {

    }

    public abstract boolean requires();

    public abstract String perform();
}
