package tasks;

import common.Messages;
import seedu.duke.Parser;

import java.lang.reflect.Member;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Event extends Task {
    public static final String EVENT_ICON = "E";
    protected String location;
    protected LocalDateTime dateAndTime;

    /**
     * Event object.
     * @param name name of Event
     * @param location location of Event
     * @param dateTime date and time of Event
     * @param comments comments for the Event
     */
    public Event(String name, String location, LocalDateTime dateTime, String comments) {
        super(name, comments);
        this.location = location;
        this.dateAndTime = dateTime;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    @Override
    public LocalDate getDate() {
        return dateAndTime.toLocalDate();
    }

    @Override
    public LocalTime getTime() {
        return dateAndTime.toLocalTime();
    }

    @Override
    public String toString() {
        return "[" + EVENT_ICON + "]"
                + super.toString()
                + " (at: "
                + location
                + " | "
                + dateAndTime.format(Parser.PRINT_DATE_FORMAT)
                + ")"
                + System.lineSeparator()
                + Messages.COMMENTS_INDENT
                + comments;
    }
}
