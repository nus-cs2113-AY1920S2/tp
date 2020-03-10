package seedu.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {
    private String dateTime;

    public DateTime(String arguments) {
        this.dateTime = arguments;
    }

    public String getDateTimeFormat() {
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.dateTime =  localDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
        return dateTime;
    }

    public String toString() {
        return dateTime;
    }

}