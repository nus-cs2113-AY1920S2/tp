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
     * Constructor to create activity when endTime is previously known
     * (i.e. when loading from jikan.storage)
     *
     * @param name Name of activity.
     * @param startTime Time at which activity started.
     * @param endTime Time at which activity ended.
     */
    public Activity(String name, String startTime, String endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
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

    /**
     * Converts the Activity object to data representation to be stored in a data file.
     * File format:
     * name, startTime, endTime
     *
     * @return String representing the Task object in comma-separated data format.
     */
    public String toData() {
        String dataLine = (this.name + "," + this.startTime + "," + this.endTime);
        return dataLine;
    }
}
