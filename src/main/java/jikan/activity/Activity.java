package jikan.activity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Represents an activity entry with a name and total time spent.
 */

public class Activity {
    protected String name;
    protected String[] tags;
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    protected Duration duration;

    /*
    protected String name;
    protected String startTime;
    protected String endTime;
    */

    /**
     * Constructor for a new activity entry.
     * @param name represents the name of the activity
     * @param startTime the time that the activity first started
     * @param tags activity tags
     * @param endTime the time that the activity ended
     */
    public Activity(String name, LocalDateTime startTime, LocalDateTime endTime, String[] tags) {
        this.name = name;
        this.startTime = startTime;
        this.tags = tags;
        this.endTime = endTime;
        this.duration = Duration.between(startTime, endTime);
    }

    /**
     * Constructor to create activity when endTime is previously known
     * (i.e. when loading from jikan.storage)
     *
     * @param name Name of activity.
     * @param startTime Time at which activity started.
     * @param endTime Time at which activity ended.
     */
    public Activity(String name, LocalDateTime startTime, LocalDateTime endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Ends an ongoing activity and updates the total time spent on the activity.
     * @param endTime date and time when the activity ended
     * @return a confirmation message that the tracking of the activity has ended
     */
    public String endActivity(LocalDateTime endTime) {
        this.endTime = endTime;
        return ("You have ended " + this.name + " . Great job !\n");
    }

    public Duration getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }

    public String[] getTags() {
        return tags;
    }

    /**
     * Converts the jikan.activity.Activity object to data representation to be stored in a data file.
     * File format:
     * name, startTime, endTime
     *
     * @return String representing the Task object in comma-separated data format.
     */
    public String toData() {
        String dataLine = (this.name + "," + this.startTime + "," + this.endTime + "," + Arrays.toString(this.tags));
        return dataLine;
    }
}