package seedu.duke;

import tasks.Task;
import tasks.Assignment;
import tasks.Event;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Default constructor for TaskList class.
     * Instantiate a new ArrayList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Getter for size of ArrayList.
     * @return ArrayList size
     */
    public int getListSize() {
        return tasks.size();
    }

    /**
     * Getter for ArrayList of tasks.
     * @return ArrayList of tasks
     */
    public ArrayList<Task> getTaskArray() {
        return this.tasks;
    }


    public void listTodayTasks() {


    }


    /**
     * Getter for Task with the provided index in TaskList.
     * @param index index of Task to return
     * @return Task object with corresponding index
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds a task to TaskList.
     * @param task task object to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Set the Task corresponding to index specified as done.
     * @param doneIndex index of Task to be marked done
     * @throws IndexOutOfBoundsException throws when index is out of range of size of current TaskList
     */
    public void markTaskAsDone(int doneIndex) throws IndexOutOfBoundsException {
        tasks.get(doneIndex).setDone();
    }
}
