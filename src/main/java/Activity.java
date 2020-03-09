/**
 * Represents an activity entry with a name and total time spent.
 */

public class Activity {
    protected String name;
    protected String startTime;
    protected String endTime;

    /**
     * Constructor for a new activity entry.
     * @param name represents the name of the activity
     * @param startTime the time that the activity first started
     */
    public Activity(String name, String startTime) {
        this.name = name;
        this.startTime = startTime;
    }

    /**
     * Ends an ongoing activity and updates the total time spent on the activity.
     * @param endTime date and time when the activity ended
     * @return a confirmation message that the tracking of the activity has ended
     */
    public String endActivity(String endTime) {
        this.endTime = endTime;
        return ("You have ended " + this.name + " . Great job !\n");
    }
}
