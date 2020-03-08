package tasks;

import java.time.LocalDateTime;

public class Event extends Task {
    protected String type;

    /**
     * Event object.
     * @param name name of Event
     * @param location location of Event
     * @param dateTime date and time of Event
     * @param comments comments for the Event
     */
    public Event(String name, String location, LocalDateTime dateTime, String comments) {
        super(name, location, dateTime, comments);
        this.type = "event";
    }

    @Override
    public String getType() {
        return type;
    }
}
