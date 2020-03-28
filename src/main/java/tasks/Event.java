package tasks;

import command.RepeatCommand;
import common.Messages;
import seedu.atas.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.StringJoiner;

public class Event extends Task {
    public static final String EVENT_ICON = "E";

    protected String location;
    protected LocalDateTime startDateAndTime;
    protected LocalDateTime endDateAndTime;
    protected boolean isRepeat;
    protected int numOfPeriod;
    protected String typeOfPeriod;
    protected LocalDateTime originalDateAndTime;
    protected int periodCounter;
    protected LocalDateTime nextDateAndTime;


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
        this.isRepeat = false;
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

    public int getNumOfPeriod() {
        return numOfPeriod;
    }

    public String getTypeOfPeriod() {
        return typeOfPeriod;
    }

    public boolean getIsRepeat() {
        return isRepeat;
    }

    public LocalDateTime getNextDateTime() {
        return nextDateAndTime;
    }

    public int getPeriodCounter() {
        return periodCounter;
    }

    /**
     * Set event as repeating event.
     * @param numOfPeriod number of periods before event repeats
     * @param typeOfPeriod type of period which event repeats - could be daily, weekly, monthly or yearly
     */
    public void setRepeat(int numOfPeriod, String typeOfPeriod, LocalDateTime originalDateAndTime, int periodCounter) {
        this.isRepeat = true;
        this.numOfPeriod = numOfPeriod;
        this.typeOfPeriod = typeOfPeriod;
        this.originalDateAndTime = originalDateAndTime;
        this.periodCounter = periodCounter;
    }

    /**
     * Set event to not repeating anymore.
     */
    public void setNoRepeat() {
        this.isRepeat = false;
        this.numOfPeriod = 0;
        this.typeOfPeriod = null;
        this.originalDateAndTime = null;
        this.periodCounter = -1;
    }

    /**
     * Update date of event to the next upcoming date (after today) if the recurring event
     * has already occurred.
     */
    public void updateDate() {
        if (this.isRepeat) {
            switch (typeOfPeriod) {
            case (RepeatCommand.DAILY_ICON):
                updateDateByDays(numOfPeriod);
                break;
            case (RepeatCommand.WEEKLY_ICON):
                updateDateByDays(numOfPeriod * 7);
                break;
            case (RepeatCommand.MONTHLY_ICON):
                updateDateByMonth(numOfPeriod);
                break;
            case (RepeatCommand.YEARLY_ICON):
                updateDateByYear(numOfPeriod);
                break;
            default:
                assert false;
            }
        }
    }

    /**
     * Update date of event if it is a daily recurring event.
     * @param numOfPeriod num of days before it recurs
     */
    private void updateDateByDays(int numOfPeriod) {
        LocalDate currDate = LocalDate.now();
        LocalDate startDate = startDateAndTime.toLocalDate();
        while (startDate.compareTo(currDate) < 0) {
            startDate = startDate.plusDays(numOfPeriod);
            periodCounter += 1;
        }
        startDateAndTime = startDateAndTime.plusDays(periodCounter * numOfPeriod);
        endDateAndTime = endDateAndTime.plusDays(periodCounter * numOfPeriod);

        if (startDateAndTime.compareTo(LocalDateTime.now()) < 0) {
            nextDateAndTime = startDateAndTime.plusDays(numOfPeriod);
        } else {
            nextDateAndTime = startDateAndTime;
        }
    }

    /**
     * Update date of event if it is a monthly recurring event.
     * @param numOfPeriod num of months before it recurs
     */
    private void updateDateByMonth(int numOfPeriod) {
        LocalDate currDate = LocalDate.now();
        LocalDate startDate = startDateAndTime.toLocalDate();
        while (startDate.compareTo(currDate) < 0) {
            startDate = startDate.plusMonths(numOfPeriod);
            periodCounter += 1;
        }
        startDateAndTime = startDateAndTime.plusMonths(periodCounter * numOfPeriod);
        endDateAndTime = endDateAndTime.plusMonths(periodCounter * numOfPeriod);

        if (startDateAndTime.compareTo(LocalDateTime.now()) <= 0) {
            nextDateAndTime = startDateAndTime.plusMonths(numOfPeriod);
        } else {
            nextDateAndTime = startDateAndTime;
        }
    }

    /**
     * Update date of event if it is a yearly recurring event.
     * @param numOfPeriod num of years before it recurs
     */
    private void updateDateByYear(int numOfPeriod) {
        LocalDate currDate = LocalDate.now();
        LocalDate startDate = startDateAndTime.toLocalDate();
        while (startDate.compareTo(currDate) < 0) {
            startDate = startDate.plusYears(numOfPeriod);
            periodCounter += 1;
        }
        startDateAndTime = startDateAndTime.plusYears(periodCounter * numOfPeriod);
        endDateAndTime = endDateAndTime.plusYears(periodCounter * numOfPeriod);

        if (startDateAndTime.compareTo(LocalDateTime.now()) <= 0) {
            nextDateAndTime = startDateAndTime.plusYears(numOfPeriod);
        } else {
            nextDateAndTime = startDateAndTime;
        }
    }

    private String repeatToStringAndComment() {
        if (isRepeat) {
            String repeatString = numOfPeriod + typeOfPeriod;
            return String.format(Messages.REPEAT_EVENT_WITH_COMMENTS_INDENT, repeatString);
        } else {
            return Messages.COMMENTS_INDENT;
        }
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
                + repeatToStringAndComment()
                + comments;
    }

    @Override
    public String encodeTask() {
        StringJoiner sj = new StringJoiner(STORAGE_DELIMITER);
        sj.add(EVENT_ICON);
        sj.add(isDone ? "true" : "false");
        sj.add(name);
        sj.add(location);
        sj.add(startDateAndTime.format(Parser.INPUT_DATE_FORMAT));
        sj.add(endDateAndTime.format(Parser.INPUT_DATE_FORMAT));
        sj.add(isRepeat ? "true" : "false");
        if (isRepeat) {
            sj.add(Integer.toString(numOfPeriod));
            sj.add(typeOfPeriod);
            sj.add(Integer.toString(periodCounter));
            sj.add(originalDateAndTime.format(Parser.INPUT_DATE_FORMAT));
        }
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
        boolean isRepeat = Boolean.parseBoolean(tokens[6]);
        int numOfPeriod;
        String typeOfPeriod;
        int periodCounter;
        LocalDateTime originalDateAndTime;
        String comments;
        Event event;
        if (isRepeat) {
            numOfPeriod = Integer.parseInt(tokens[7]);
            typeOfPeriod = tokens[8];
            periodCounter = Integer.parseInt(tokens[9]);
            originalDateAndTime = Parser.parseDate(tokens[10]);
            comments = tokens[11];
            assert tokens.length == 12;
            event = new Event(name, location, startDateAndTime, endDateAndTime, comments);
            event.setRepeat(numOfPeriod, typeOfPeriod, originalDateAndTime, periodCounter);
        } else {
            comments = tokens[7];
            event = new Event(name, location, startDateAndTime, endDateAndTime, comments);
        }
        if (isDone) {
            event.setDone();
        }
        return event;
    }
}
