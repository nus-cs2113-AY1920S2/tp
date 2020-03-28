package tasks;

import common.Messages;
import seedu.atas.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.StringJoiner;

//@@author lwxymere
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

    @Override
    public String encodeTask() {
        StringJoiner sj = new StringJoiner(STORAGE_DELIMITER);
        sj.add(EVENT_ICON);
        sj.add(isDone ? "true" : "false");
        sj.add(name);
        sj.add(location);
        sj.add(startDateAndTime.format(Parser.INPUT_DATE_TIME_FORMAT));
        sj.add(endDateAndTime.format(Parser.INPUT_DATE_TIME_FORMAT));
        sj.add(comments);
        return sj.toString();
    }

    /**
     * Converts an encoded Event back to an Event object.
     * @param encodedTask Event encoded using encodedTask()
     * @return Event with the correct attributes set
     * @throws DateTimeParseException if encoded startDateAndTime or endDateAndTime cannot be parsed
     * @throws IndexOutOfBoundsException if encodedTask is not a String returned by calling encodeTask() on
     *              an Event
     */
    public static Event decodeTask(String encodedTask)
            throws DateTimeParseException, IndexOutOfBoundsException {
        String[] tokens = encodedTask.split("\\" + STORAGE_DELIMITER);
        assert tokens[0].equals(EVENT_ICON);
        boolean isDone = Boolean.parseBoolean(tokens[1]);
        String name = tokens[2];
        String location = tokens[3];
        LocalDateTime startDateAndTime = Parser.parseDate(tokens[4]);
        LocalDateTime endDateAndTime = Parser.parseDate(tokens[5]);
        String comments = tokens[6];
        assert tokens.length == 7;
        Event event = new Event(name, location, startDateAndTime, endDateAndTime, comments);
        if (isDone) {
            event.setDone();
        }
        return event;
    }
}
