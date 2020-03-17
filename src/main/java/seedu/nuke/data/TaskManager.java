package seedu.nuke.data;

import seedu.nuke.directory.Task;
import seedu.nuke.exception.DataNotFoundException;
import seedu.nuke.exception.DuplicateDataException;

import java.util.ArrayList;

/**
 * every module has a task manager to manage tasks within module.
 */
public class TaskManager {
    private ArrayList<Task> taskList;

    /**
     * Initiates an empty Task List.
     */
    public TaskManager() {
        taskList = new ArrayList<>();
    }

    /**
     * Returns the entire Task List
     * @return
     *  The Task List
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Finds a task with the specified task description in the Task List.
     *
     * @param description
     *  The description of the task
     * @return
     *  The task with the specified description if found
     * @throws TaskNotFoundException
     *  If the task cannot be found in the Task List
     */
    public Task getTask(String description) throws TaskNotFoundException {
        for (Task task : taskList) {
            if (task.getDescription().equals(description)) {
                return task;
            }
        }
        throw new TaskNotFoundException();
    }

    /**
     * Checks for duplicates of the specified task in the Task List.
     *
     * @param toCheck
     *  The task to check
     * @return
     *  <code>TRUE</code> if there exists a duplicate, and <code>FALSE</code> otherwise
     */
    private boolean contains(Task toCheck) {
        for (Task task : taskList) {
            if (task.isSameTask(toCheck)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add a task to the Task List.
     *
     * @param toAdd
     *  The task to be added
     * @throws DuplicateTaskException
     *  If there exists a duplicate task in the Task List with the same task description
     */
    public void add(Task toAdd) throws DuplicateTaskException {
        if (contains(toAdd)) {
            throw new DuplicateTaskException();
        } else {
            taskList.add(toAdd);
        }
    }

    /**
     * Deletes the specified task from the Task List.
     *
     * @param toDelete
     *  The task to be deleted
     */
    public void delete(Task toDelete) {
        taskList.remove(toDelete);
    }

    /**
     * Clears all tasks from the task list.
     */
    public void clear() {
        taskList.clear();
    }

    /**
     * Counts the total number of tasks in the Task List.
     *
     * @return
     *  The total number of tasks in the Task List
     */
    public int countTotalTasks() {
        return taskList.size();
    }

    public TaskFileManager retrieve(String description) throws TaskNotFoundException {
        return getTask(description).getFiles();
    }

    public ArrayList<Task> filter(String taskKeyword) {
        ArrayList<Task> filteredTaskList = new ArrayList<>();
        for (Task category : taskList) {
            if (category.getDescription().toLowerCase().contains(taskKeyword.toLowerCase())) {
                filteredTaskList.add(category);
            }
        }
        return filteredTaskList;
    }

    public ArrayList<Task> filterExact(String taskKeyword) {
        // Returns all tasks in the Task List if no keyword is provided.
        if (taskKeyword.isEmpty()) {
            return this.getTaskList();
        }

        ArrayList<Task> filteredTaskList = new ArrayList<>();
        for (Task category : taskList) {
            if (category.getDescription().toLowerCase().equals(taskKeyword.toLowerCase())) {
                filteredTaskList.add(category);
            }
        }
        return filteredTaskList;
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
                && this.taskList.equals(((TaskManager) other).taskList));
    }

    public static class TaskNotFoundException extends DataNotFoundException {}
    public static class DuplicateTaskException extends DuplicateDataException {}
}
