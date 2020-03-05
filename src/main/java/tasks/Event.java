package tasks;

import java.time.LocalDateTime;

public class Event extends Task {
    public Event(String name, String location, LocalDateTime dateTime, String comments) {
        super(name, location, dateTime, comments);
    }
}
