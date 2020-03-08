package tasks;

import java.time.LocalDateTime;

public class Assignment extends Task {
    protected String type;

    /**
     * Assignment object.
     * @param name name of Assignment
     * @param module module for Assignment
     * @param deadline deadline of Assignment
     * @param comments comments for Assignment
     */
    public Assignment(String name, String module, LocalDateTime deadline, String comments) {
        super(name, module, deadline, comments);
        this.type = "assignment";
    }

    @Override
    public String getType() {
        return type;
    }
}
