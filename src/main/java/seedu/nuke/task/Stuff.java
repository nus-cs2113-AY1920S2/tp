package seedu.nuke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Stuff {
    ArrayList<Task> tasks;

    public Stuff() {
        tasks = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

}
