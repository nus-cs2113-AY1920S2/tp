package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Assignment extends Task{
    protected LocalDateTime deadline;
    protected String eventType;

    public Assignment(String description, LocalDateTime deadline) {
        super(description);
        this.eventType = "[Ass]";
        this.deadline = deadline;
    }

    public String getTaskType() {
        return eventType;
    }

    public String getTaskTime() {
        return deadline.format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm"));
    }

    @Override
    public String toString() {
        return String.format("%s%s %s (by: %s)", getTaskType(), super.getStatusIcon(),
                super.getDescription(), getTaskTime());
    }
}
