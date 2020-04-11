package jikan.activity;

import jikan.exception.InvalidTimeFrameException;
import jikan.exception.NameTooLongException;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private Duration allocatedTime;
    private LocalDate date;

    public static final int MAX_PERCENT = 100;

    /**
     * Constructor for a new activity entry.
     * @param name represents the name of the activity
     * @param startTime the time that the activity first started
     * @param tags activity tags
     * @param endTime the time that the activity ended
     */
    public Activity(String name, LocalDateTime startTime, LocalDateTime endTime, Duration duration,
                    Set<String> tags, Duration allocatedTime) throws InvalidTimeFrameException, NameTooLongException {

        if (endTime.isBefore(startTime)) {
            throw new InvalidTimeFrameException();
        }

        if (name.strip().length() <= 25) {
            this.name = name.strip();
        } else {
            throw new NameTooLongException();
        }
        this.startTime = startTime;
        this.tags = tags;
        this.endTime = endTime;
        this.duration = duration;
        this.date = endTime.toLocalDate();
        this.allocatedTime = allocatedTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.strip();
    }

    public Set<String> getTags() {
        return tags;
    }

    public String getTagsAsString() {
        return String.join(",", tags);
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
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

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Duration getAllocatedTime() {
        return this.allocatedTime;
    }

    public void setAllocatedTime(Duration allocatedTime) {
        this.allocatedTime = allocatedTime;
    }

    /**
     * Gets the percentage completed of the allocated time.
     * @return percent completed
     */
    public double getProgressPercent() {
        double percent = ((double)this.duration.toMillis() / this.allocatedTime.toMillis()) * MAX_PERCENT;
        return Math.min(percent, 100);
    }

    /**
     * Returns true if the Activity's date is within the date range specified (inclusive).
     *
     * @param startDate Start date of range
     * @param endDate End date of range
     * @return True if Activity is within date range; false otherwise
     */
    public boolean isWithinDateFrame(LocalDate startDate, LocalDate endDate) {
        if (!this.date.isBefore(startDate) && !this.date.isAfter(endDate)) {
            return true;
        }
        return false;
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

        String dataLine = (this.name + "," + this.startTime + "," + this.endTime + ","
                + this.duration.toString() + "," + this.allocatedTime + "," + tagString);
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