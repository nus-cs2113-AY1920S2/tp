package tasks;

import command.RepeatCommand;
import common.Messages;
import seedu.atas.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.StringJoiner;

public class RepeatEvent extends Event {
    public static final String REPEAT_ICON = "R";
    int numOfPeriod;
    String typeOfPeriod;
    int periodCounter;
    LocalDateTime originalDateAndTime;
    LocalDateTime nextDateAndTime;

    /**
     * Event object constructor.
     *
     * @param name          name of Event
     * @param location      location of Event
     * @param startDateTime starting date and time of Event
     * @param endDateTime   ending date and time of Event
     * @param comments      comments for the Event
     */
    public RepeatEvent(String name, String location, LocalDateTime startDateTime, LocalDateTime endDateTime,
                       String comments, int numOfPeriod, String typeOfPeriod, LocalDateTime originalDateAndTime,
                       int periodCounter) {
        super(name, location, startDateTime, endDateTime, comments);
        this.numOfPeriod = numOfPeriod;
        this.typeOfPeriod = typeOfPeriod;
        this.originalDateAndTime = originalDateAndTime;
        this.periodCounter = periodCounter;
    }

    public int getNumOfPeriod() {
        return numOfPeriod;
    }

    public String getTypeOfPeriod() {
        return typeOfPeriod;
    }

    public LocalDateTime getNextDateTime() {
        return nextDateAndTime;
    }

    public int getPeriodCounter() {
        return periodCounter;
    }

    public LocalDateTime getOriginalDateAndTime() {
        return originalDateAndTime;
    }

    /**
     * Update date of event to the next upcoming date (after today) if the recurring event
     * has already occurred.
     */
    public void updateDate() {
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

    @Override
    public String toString() {
        return "[" + REPEAT_ICON + "]"
                + String.format("%s %s", getStatusIcon(), name)
                + " (at: "
                + location
                + " | "
                + startDateAndTime.format(Parser.PRINT_DATE_FORMAT)
                + " - "
                + endDateAndTime.format(Parser.PRINT_TIME_FORMAT)
                + ")"
                + System.lineSeparator()
                + String.format(Messages.REPEAT_EVENT_WITH_COMMENTS_INDENT, numOfPeriod + typeOfPeriod)
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
        sj.add(Integer.toString(numOfPeriod));
        sj.add(typeOfPeriod);
        sj.add(Integer.toString(periodCounter));
        sj.add(originalDateAndTime.format(Parser.INPUT_DATE_FORMAT));
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
        int numOfPeriod = Integer.parseInt(tokens[7]);
        String typeOfPeriod = tokens[8];
        int periodCounter = Integer.parseInt(tokens[9]);
        LocalDateTime originalDateAndTime = Parser.parseDate(tokens[10]);
        String comments = tokens[11];
        assert tokens.length == 12;
        RepeatEvent repeatEvent = new RepeatEvent(name, location, startDateAndTime, endDateAndTime,
                comments, numOfPeriod, typeOfPeriod, originalDateAndTime, periodCounter);
        if (isDone) {
            repeatEvent.setDone();
        }
        return repeatEvent;
    }
}
