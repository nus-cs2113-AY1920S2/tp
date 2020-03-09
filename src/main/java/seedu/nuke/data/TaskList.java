package seedu.nuke.data;

import seedu.nuke.task.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class TaskList {
    ArrayList<Task> internalList = new ArrayList<>();

    /**
     * Constructs empty task list.
     */
    public TaskList() {}

    /**
     * todo add check same function
     * Checks if the list contains an equivalent task as the given description.
     * @param toCheck the task to-check
     * @return true if the task exists
     */
    public boolean contains(Task toCheck) {
        return false;
    }
}
