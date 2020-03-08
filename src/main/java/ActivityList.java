import java.util.ArrayList;

/**
 * Represents the list of activities.
 */
public class ActivityList {
    ArrayList<Activity> activities;
    int size;

    /**
     * Constructor for a new activity list.
     */
    public ActivityList() {
        this.activities = new ArrayList<>();
        size = 0;
    }
}
