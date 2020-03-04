package tasks;

import java.time.LocalDateTime;

public class Assignment extends Task {
    public Assignment(String name, String module, LocalDateTime deadline, String comments) {
        super(name, module, deadline, comments);
    }
}
