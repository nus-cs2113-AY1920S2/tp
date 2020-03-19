package jikan.activity;

import jikan.exception.InvalidTimeFrameException;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

/**
 * Represents an activity entry with a name and total time spent.
 */

public class Activity {
    private String name;
    private Set<String> tags;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Duration duration;
    private LocalDate date;

    /**
     * Constructor for a new activity entry.
     * @param name represents the name of the activity
     * @param startTime the time that the activity first started
     * @param tags activity tags
     * @param endTime the time that the activity ended
     */
    public Activity(String name, LocalDateTime startTime, LocalDateTime endTime, Set<String> tags)
            throws InvalidTimeFrameException {

        if (endTime.isBefore(startTime)) {
            throw new InvalidTimeFrameException();
        }

        this.name = name.strip();
        this.startTime = startTime;
        this.tags = tags;
        this.endTime = endTime;
        this.duration = Duration.between(startTime, endTime);
        this.date = endTime.toLocalDate();
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

    public Set<String> getTags() {
        return tags;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Converts the jikan.activity.Activity object to data representation to be stored in a data file.
     * File format:
     * name, startTime, endTime
     *
     * @return String representing the Task object in comma-separated data format.
     */
    public String toData() {

        // Convert tags to a single space-separated
        String tagString = "";
        tagString = tagsToString(tagString);

        String dataLine = (this.name + "," + this.startTime + "," + this.endTime + "," + tagString);
        return dataLine;
    }

    private String tagsToString(String tagString) {
        Iterator i = this.tags.iterator();

        while (i.hasNext()) {
            tagString += i.next() + " ";
        }
        return tagString;
    }
}