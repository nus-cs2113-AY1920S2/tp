public class Activity {
    protected String name;
    protected String startTime;
    protected String endTime;

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
