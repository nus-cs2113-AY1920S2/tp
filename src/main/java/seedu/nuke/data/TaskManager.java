package seedu.nuke.data;

import seedu.nuke.directory.Module;
import seedu.nuke.directory.Task;
import java.util.ArrayList;

/**
 * every module has a task manager to manage tasks within module.
 */
public class TaskManager {
    ArrayList<Task> allTasks = new ArrayList<>();

    /**
     * Constructs empty task list.
     */
    public TaskManager() {
    }

    public void setAllTasks(ArrayList<Task> allTasks) {
        this.allTasks = allTasks;
    }

    /**
     * todo add check same function
     * todo handle toRemove does not exist exception
     * Checks if the list contains an equivalent task as the given description.
     * @param toCheck the task to-check
     * @return true if the task exists
     */
    /*
    public boolean contains(Task toCheck) {
        return false;
    }
    */

    public void addTask(Task toAdd) {
        this.allTasks.add(toAdd);
    }

    public void removeTask(Task toRemove) {
        this.allTasks.remove(toRemove);
    }

    /**
     * Try to delete a task within a module and return the deleted task.
     * @param parameter the description of the task
     * @return the deleted task
     */
    public Task delete(String parameter) {
        for (Task task : allTasks) {
            if (task.getModuleCode().equals(parameter)) {
                allTasks.remove(task);
                return task;
            }
        }
        return null;
    }

    /**
     * Parses the given arguments string as a single index number.
     * @return the task list
     */
    public ArrayList<Task> getAllTasks() {
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
     * return total number of tasks in the task list.
     */
    public int countTotalTasks() {
        return allTasks.size();
    }

    /**
     * compare this task with other task.
     * @param other target task
     * @return true if tasks are equal
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskManager // instanceof handles nulls
                && this.allTasks.equals(((TaskManager) other).allTasks));
    }

    /**
     * Checks if the list contains an equivalent task as the given description.
     * @param description the module code to check if provided by NUS currently
     * @return true if NUS is providing the module currently
     */
    public boolean contains(String description) {
        for (Task t : allTasks) {
            if (t.getDescription().equals(description)) {
                return true;
            }
        }
        return false;
    }

    /**
     * find a task given the description of the task.
     * @param description the description of the task
     * @return a task of the given description or null if not found
     */
    public Task findTask(String description) {
        for (Task t : allTasks) {
            if (t.getDescription().equals(description)) {
                return t;
            }
        }
        return null;
    }
}
