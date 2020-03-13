package seedu.duke;

import tasks.Task;
import tasks.Assignment;
import tasks.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    /**
     * Getter for the current Local Date.
     * Formats Local Date into "dd/MM/yyyy" format.
     * @return LocalDate object of the formatted current Date
     */
    public LocalDate getCurrentDate() {
        LocalDate currentDateObj = LocalDate.now();
        DateTimeFormatter formattedDateObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String currentDate = currentDateObj.format(formattedDateObj);
        LocalDate formattedCurrDate = LocalDate.parse(currentDate, formattedDateObj);
        return formattedCurrDate;
    }

    /**
     * Getter method for tasks depending of days from today.
     * @param days Integer representing number of days from today
     * @return ArrayList object containing all tasks from indicated days from today
     */
    public ArrayList<Task> getTasksByDays(int days) {
        ArrayList<Task> taskList = new ArrayList<>();
        LocalDate currDate = getCurrentDate();
        LocalDate daysIndicated = currDate.plusDays(days);
        for (Task task : tasks) {
            LocalDate taskDate = task.getDate();
            if (currDate.compareTo(taskDate) <= 0 && taskDate.compareTo(daysIndicated) <= 0) {
                taskList.add(task);
            }
        }
        return taskList;
    }

    /**
     * Getter method for tasks that are events and in the future.
     * @return ArrayList object containing all future events.
     */
    public ArrayList<Task> getUpcomingEventArray() {
        ArrayList<Task> eventList = new ArrayList<>();
        LocalDateTime currDateTime = LocalDateTime.now();
        for (Task task : tasks) {
            LocalDateTime taskDateTime = task.getDateAndTime();
            if (task instanceof Event && taskDateTime.compareTo(currDateTime) > 0) {
                eventList.add(task);
            }
        }
        return eventList;
    }

    /**
     * Getter method for tasks that are assignments and not marked done.
     * @return ArrayList object containing all incomplete assignments
     */
    public ArrayList<Task> getIncompleteAssignArray() {
        ArrayList<Task> assignList = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Assignment && !task.getIsDone()) {
                assignList.add(task);
            }
        }
        return assignList;
    }

    /**
     * Getter method for Task with the provided index in TaskList.
     * @param index index of Task to return
     * @return Task object with corresponding index
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
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

    /**
     * Delete tasks according to the index specified by user.
     * @param deleteIndex index of task to be deleted
     * @throws IndexOutOfBoundsException throws when index is out of range of the size of current Tasklist
     */
    public void deleteTask(int deleteIndex) throws IndexOutOfBoundsException {
        tasks.remove(deleteIndex);
    }

    /**
     * Deletes all the tasks in the list.
     */
    public void clearList() {
        tasks.clear();
    }
}
