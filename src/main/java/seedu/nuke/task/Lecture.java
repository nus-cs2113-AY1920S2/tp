package seedu.nuke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Lecture extends Task {
    public Lecture(String description, ArrayList<String> files, LocalDateTime dateTime, int priority) {
        super(description, files, dateTime, priority);
    }
}
