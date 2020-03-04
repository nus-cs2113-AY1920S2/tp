package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime eventTime;
    protected String eventType;

    public Event(String description, LocalDateTime eventTime) {
        super(description);
        this.eventType = "[E]";
        this.eventTime = eventTime;
    }

    public String getTaskType() {
        return this.eventType;
    }

    public String getTaskTime() {
        return this.eventTime.format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm"));
    }

    @Override
    public String toString() {
        return String.format("%s%s %s (by: %s)", getTaskType(), super.getStatusIcon(),
                super.getDescription(), getTaskTime());
    }
}
