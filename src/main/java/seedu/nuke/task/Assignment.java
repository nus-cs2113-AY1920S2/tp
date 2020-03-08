package seedu.nuke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Assignment extends Task {
    public Assignment(String description, ArrayList<String> files, LocalDateTime dateTime, int priority) {
        super(description, files, dateTime, priority);
    }
}
