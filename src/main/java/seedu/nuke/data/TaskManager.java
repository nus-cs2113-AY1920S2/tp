package seedu.nuke.data;

import seedu.nuke.task.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * every module has a task manager to manage tasks within module
 */
public class TaskManager {
    ArrayList<Task> allTasks = new ArrayList<>();

    /**
     * Constructs empty task list.
     */
    public TaskManager() {}

    /**
     * todo add check same function
     * Checks if the list contains an equivalent task as the given description.
     * @param toCheck the task to-check
     * @return true if the task exists
     */
    public boolean contains(Task toCheck) {
        return false;
    }

    /**
     * Parses the given arguments string as a single index number.
     *
     * @return the task list
     */
    public ArrayList<Task> getTaskList() {
        return allTasks;
    }

    /**
     * clears all tasks from the task list.
     * call by clear function
     */
    public void clear() {
        allTasks.clear();
    }

    /**
     * compare this task with other task
     *
     * @param other target task
     * @return true if tasks are equal
     */

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskManager // instanceof handles nulls
                && this.allTasks.equals(((TaskManager) other).allTasks));
    }
}
