package api.activities;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by HP xw8400
 * Author: Jacob
 * Date: 8/4/2018.
 */
public class Controller {

    private final ArrayList<Activity> activities = new ArrayList<>();

    public Controller(Activity... activities) {
        this.activities.addAll(Arrays.asList(activities));
    }

    public Activity getActivity() {
        for (Activity activity: activities) {
            if (activity.requires()) {
                return activity;
            }
        }
        return null;
    }
}
