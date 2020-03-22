package seedu.event;

import seedu.ui.UI;

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
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            return localDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
        } catch (DateTimeParseException e) {
            UI.display("Unknown DateTime format provided. DateTime is set to yyyy-MM-dd HHmm.");
            return "yyyy-MM-dd HHmm";
        }
    }

    public String toString() {
        return this.dateTimeFormat;
    }
}