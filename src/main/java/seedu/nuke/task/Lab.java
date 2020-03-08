package seedu.nuke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Lab extends Task {
    public Lab(String description, ArrayList<String> files, LocalDateTime dateTime, int priority) {
        super(description, files, dateTime, priority);
    }
}
