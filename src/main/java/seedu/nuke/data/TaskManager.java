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

    private static final String NO_KEYWORD = "";

    /**
     * Initiates an empty Task List.
     */
    public TaskManager() {
        taskList = new ArrayList<>();
    }

    /**
     * Returns the entire Task List.
     *
     * @return
     *  The Task List
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Sets the entire Task List to a new list.
     *
     * @param taskList
     *  The new Task List to be set
     */
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
     *  If the task is not found in the Task List
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
     * Deletes a <b>Task</b> with the specified <code>description/code> from the <b>Task List</b>.
     *
     * @param description The description of the <b>Task</b> to be deleted
     * @throws TaskNotFoundException  If the task with the specified description is not found in the <b>Task List</b>
     * @return  The deleted task
     * @see Task
     */
    public Task delete(String description) throws TaskNotFoundException {
        Task toDelete = getTask(description);
        taskList.remove(toDelete);
        return toDelete;
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

    /**
     * Retrieves the File List of the category with the specified description
     *
     * @param description
     *  The description of the task to retrieve the File List from
     * @return
     *  The File List of the found task
     * @throws TaskNotFoundException
     *  If the task with the specified description is not found in the Task List
     */
    public TaskFileManager retrieveList(String description) throws TaskNotFoundException {
        return getTask(description).getFiles();
    }

    /**
     * Filter for tasks in the Task List with description that contains the specified keyword.
     * Filtering is done in a case-insensitive manner.
     *
     * @param taskKeyword
     *  The keyword to filter the tasks
     * @return
     *  The list of filtered tasks
     */
    public ArrayList<Task> filter(String taskKeyword) {
        ArrayList<Task> filteredTaskList = new ArrayList<>();
        for (Task category : taskList) {
            if (category.getDescription().toLowerCase().contains(taskKeyword.toLowerCase())) {
                filteredTaskList.add(category);
            }
        }
        return filteredTaskList;
    }

    /**
     * Filter for tasks in the Task List with description that matches <b>exactly</b> the specified keyword.
     * Filtering is done in a case-insensitive manner.
     *
     * @param taskKeyword
     *  The keyword to filter the tasks
     * @return
     *  The list of filtered tasks
     */
    public ArrayList<Task> filterExact(String taskKeyword) {
        // Returns all tasks in the Task List if no keyword is provided.
        if (taskKeyword.equals(NO_KEYWORD)) {
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
