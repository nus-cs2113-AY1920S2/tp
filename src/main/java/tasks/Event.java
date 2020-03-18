package tasks;

import common.Messages;
import seedu.atas.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Event extends Task {
    public static final String EVENT_ICON = "E";
    protected String location;
    protected LocalDateTime startDateAndTime;
    protected LocalDateTime endDateAndTime;

    /**
     * Event object constructor.
     * @param name name of Event
     * @param location location of Event
     * @param startDateTime starting date and time of Event
     * @param endDateTime ending date and time of Event
     * @param comments comments for the Event
     */
    public Event(String name, String location, LocalDateTime startDateTime, LocalDateTime endDateTime,
                 String comments) {
        super(name, comments);
        this.location = location;
        this.startDateAndTime = startDateTime;
        this.endDateAndTime = endDateTime;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public LocalDateTime getDateAndTime() {
        return startDateAndTime;
    }

    @Override
    public LocalDate getDate() {
        return startDateAndTime.toLocalDate();
    }

    @Override
    public LocalTime getTime() {
        return startDateAndTime.toLocalTime();
    }

    /**
     * Gets the ending date and time of the event.
     * @return LocalDateTime object representing the end time and date
     */
    public LocalDateTime getEndDateAndTime() {
        return endDateAndTime;
    }

    /**
     * Gets the ending date of the event.
     * @return LocalDate object representing the end date
     */
    public LocalDate getEndDate() {
        return endDateAndTime.toLocalDate();
    }

    /**
     * Gets the ending time of the event.
     * @return LocalTime object representing the end time
     */
    public LocalTime getEndTime() {
        return endDateAndTime.toLocalTime();
    }

    @Override
    public String toString() {
        return "[" + EVENT_ICON + "]"
                + super.toString()
                + " (at: "
                + location
                + " | "
                + startDateAndTime.format(Parser.PRINT_DATE_FORMAT)
                + " - "
                + endDateAndTime.format(Parser.PRINT_TIME_FORMAT)
                + ")"
                + System.lineSeparator()
                + Messages.COMMENTS_INDENT
                + comments;
    }
}
