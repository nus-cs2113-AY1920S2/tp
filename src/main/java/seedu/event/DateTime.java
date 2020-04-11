package seedu.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {
    private String dateTime;
    private String dateTimeFormat;
    private boolean isInvalid = false;

    public DateTime(String arguments) {
        this.dateTime = arguments;
        this.dateTimeFormat = getDateTimeFormat();
    }

    /**
     * Formats datetime to the correct output for the user.
     * @return The parsed string of date and time of the event.
     */
    public String getDateTimeFormat() {
        if (getDateTime() != null) {
            return getDateTime().format(DateTimeFormatter.ofPattern("E, MMM dd yyyy HHmm"));
        } else {
            return "";
        }
    }

    /**
     * Parses datetime of pattern input by the user.
     * If no input by the user, it takes the current datetime.
     * @return Parsed datetime object
     */
    public LocalDateTime getDateTime() {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            isInvalid = true;
            return null;
        }
    }

    public boolean checkValidDateTime() {
        return !isInvalid;
    }

    /**
     * Gets the numerical value of the month of the event.
     * @return The value of the month
     */
    public int getMonth() {
        LocalDateTime localDateTime = getDateTime();
        return localDateTime.getMonthValue();
    }

    /**
     * Gets the year of the event as a single or double digit number, referring to the current century.
     * @return Year of the event.
     */
    public int getYear() {
        LocalDateTime localDateTime = getDateTime();
        //only current century format in double digit
        return localDateTime.getYear() - 2000;
    }

    public int getDate() {
        LocalDateTime localDateTime = getDateTime();
        return localDateTime.getDayOfMonth();
    }


    public String toString() {
        return this.dateTimeFormat;
    }

    public String toStorable() {
        return this.dateTime;
    }

    public static DateTime parseStorable(String representation) {
        return new DateTime(representation);
    }
}