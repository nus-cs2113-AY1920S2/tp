package seedu.event;

import seedu.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {
    private String dateTime;
    private String dateTimeFormat;

    public DateTime(String arguments) {
        this.dateTime = arguments;
        this.dateTimeFormat = getDateTimeFormat();
    }


    public String getDateTimeFormat() {
        return getDateTime().format(DateTimeFormatter.ofPattern("E, MMM dd yyyy HHmm"));
    }

    public LocalDateTime getDateTime() {
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            return localDateTime;
        } catch (DateTimeParseException e) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            return currentDateTime;
        }
    }

    public Integer getMonth() {
        LocalDateTime localDateTime = getDateTime();
        int month = localDateTime.getMonthValue();
        return month;
    }

    public Integer getYear() {
        LocalDateTime localDateTime = getDateTime();
        int year = localDateTime.getYear() - 2000;
        //only current century format in double digit
        return year;
    }

    public String toString() {
        return this.dateTimeFormat;
    }
}