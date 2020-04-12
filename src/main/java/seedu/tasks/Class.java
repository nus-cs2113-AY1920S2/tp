package seedu.tasks;

import seedu.common.Messages;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Class extends Task {

    /** Weekly task classification. */
    public static final char WEEK = 'W';

    /**
     * Constructor method for initialising new class object.
     *
     * @param title       title of class
     * @param description description of class
     * @param date        range of days the class will be on
     * @param time        range of time the class will be held
     * @param location    location of class
     * @param reminder    any additional reminder if any
     * @param category    categorise the class by grouping in needed
     */
    public Class(String title, String description, String date, String time, String location,
                 String reminder, String category) {
        super(title, description, time, location, reminder, category);
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
        String[] days = dateInput.split("\\s+");
        for (String day : days) {
            // Get day of week
            Integer dayOfWeekInt = Integer.parseInt(day);
            if (dayOfWeekInt > 7 | dayOfWeekInt < 1) {
                throw new NumberFormatException(Messages.MESSAGE_DAY_OR_WEEK);
            }
            Integer inputDayOfWeek = Integer.parseInt(day);
            //Transfer day of week to local date format
            LocalDate now = LocalDate.now();
            Integer nowDayOfWeek = now.getDayOfWeek().getValue();
            this.date.add(now.plusDays(inputDayOfWeek - nowDayOfWeek));
        }
    }


    /**
     * Return string of class in its specific format.
     *
     * @return string of class.
     */
    public String toString() {
        String formattedTask = super.toString();
        for (int i = 0; i < date.size(); i++) {
            if (time.size() != 0) {
                formattedTask = formattedTask + String.format(" | %s : %s - %s",
                        date.get(i).getDayOfWeek().name(), time.get(i * 2), time.get(i * 2 + 1));
            } else {
                formattedTask = formattedTask + String.format(" | %s", date.get(i).getDayOfWeek().name());
            }
            if (location.size() > i) {
                formattedTask = formattedTask + String.format(" ( %s )", location.get(i));
            }
        }
        return formattedTask;
    }

    /**
     * Returns symbol for status of task.
     *
     * @return tick for done, cross for not done
     */
    public char getStatusIcon() {
        return WEEK;
    }
}