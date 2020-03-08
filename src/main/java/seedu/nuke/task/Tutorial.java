package seedu.nuke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Tutorial extends Task {
    public Tutorial(String description, ArrayList<String> files, LocalDateTime dateTime, int priority) {
        super(description, files, dateTime, priority);
    }
}
