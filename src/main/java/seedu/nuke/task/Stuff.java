package seedu.nuke.task;

import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Stuff {
    protected ArrayList<Task> tasks;
    protected String description;

    public Stuff(String description) {
        this.tasks = new ArrayList<Task>();
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}
