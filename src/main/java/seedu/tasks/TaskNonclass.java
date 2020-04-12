package seedu.tasks;

import seedu.calendar.CalendarParser;
import seedu.common.Messages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class TaskNonclass extends Task {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final char TICK = 'Y'; //Yes
    public static final char CROSS = 'N'; //No
    private boolean isDone = false;

    /**
     * Initializes Task.
     *
     * @param title title of task if any.
     * @param description description of task if any.
     * @param date date in format:yyyy-mm-dd of task if any.
     * @param time time in format: hh:mm of task if any.
     * @param location location of task if any.
     * @param reminder reminder of task if any.
     * @param category category of task. If no input, default is TODO.
     */
    public TaskNonclass(String title, String description, String date, String time, String location,
                        String reminder, String category) {
        super(title,description,time,location,reminder,category);
        //set default date to date inserted
        if (!date.isEmpty()) {
            setDate(date);
        }

        if (!time.isEmpty()) {
            setTime(time);
        }
    }

    @Override
    public void setDate(String dateInput) throws DateTimeParseException, NumberFormatException {
        this.date.clear();
        isDateSetByUser = true;
        if (!dateInput.isEmpty()) {
            String[] dates = dateInput.split("\\s+");
            for (String date : dates) {
                LocalDate addedDate = CalendarParser.convertToDate(date);
                if (addedDate.compareTo(LocalDate.now()) < 0) {
                    throw new NumberFormatException(Messages.MESSAGE_PAST_DATE_Error);
                }
                this.date.add(addedDate);
                isDateSetByUser = true;
            }
        }
    }

    /**
     * Format the string to be correct output form.
     *
     * @return a string.
     */
    public String toString() {
        String formattedTask = super.toString();
        for (int i = 0; i < date.size(); i++) {
            if (time.size() != 0) {
                formattedTask = formattedTask + String.format(" | %s : %s - %s",
                        date.get(i), time.get(i * 2), time.get(i * 2 + 1));
            } else {
                formattedTask = formattedTask + String.format(" | %s", date.get(i));
            }
            if (location.size() > i) {
                formattedTask = formattedTask + String.format(" ( %s )",location.get(i));
            }
        }
        return formattedTask;
    }


    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns symbol for status of task.
     *
     * @return tick for done, cross for not done
     */
    public char getStatusIcon() {
        return (isDone ? TICK : CROSS); //return tick or X symbols
    }

    /**
     * Returns done status of task.
     * @return true or false whether completed
     */
    public boolean getDoneStatus() {
        return isDone;
    }
}
