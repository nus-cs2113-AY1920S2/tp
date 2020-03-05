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



}
