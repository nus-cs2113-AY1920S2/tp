package seedu.atas;

import command.RepeatCommand;
import common.Messages;
import tasks.Assignment;
import tasks.Event;
import tasks.RepeatEvent;
import tasks.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

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

    //@@author e0309556
    /**
     * Obtain a range of numbers that is valid for usage on taskList.
     * @param taskList The interested list in TaskManager to find the range of values
     * @return A string with the range of valid numbers.
     */
    public String getRangeOfValidIndex(TaskList taskList) {
        int maxTasks = taskList.getListSize();
        return String.format(Messages.RANGE_OF_VALID_TASK_INDEX_MSG, maxTasks);
    }

    /**
     * Checks for duplicate task within tasklist.
     * @param tasklist TaskList to be checked against
     * @param addedTask new Task that needs to be checked
     * @return True if there already exists a task within tasklist. Otherwise, false.
     */
    public Boolean isRepeatTask(TaskList tasklist, Task addedTask) {
        for (Task task : tasklist.getTaskArray()) {
            if (task.equals(addedTask)) {
                return true;
            }
        }
        return false;
    }

    //@@author jichngan
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

    //@@author jichngan
    /**
     * Getter method for tasks depending of days from today.
     * @param days Integer representing number of days from today
     * @return ArrayList object containing all tasks from indicated days from today
     */
    public ArrayList<Task> getTasksByDays(int days) {
        assert days >= 0;
        ArrayList<Task> taskList = new ArrayList<>();
        LocalDate currDate = getCurrentDate();
        LocalDate daysIndicated = currDate.plusDays(days);
        for (Task task : tasks) {
            LocalDate taskDate = task.getDate();
            assert taskList.size() <= tasks.size();
            if (currDate.compareTo(taskDate) <= 0 && taskDate.compareTo(daysIndicated) <= 0) {
                taskList.add(task);
            }
        }
        return taskList;
    }

    //@@author e0309556
    /**
     * Getter method for tasks that are events and in the future.
     * @return ArrayList object containing all future events.
     */
    public ArrayList<Task> getUpcomingEventArray() {
        ArrayList<Task> eventList = new ArrayList<>();
        LocalDateTime currDateTime = LocalDateTime.now();
        for (Task task : tasks) {
            LocalDateTime taskDateTime = task.getDateAndTime();
            if ((task instanceof Event && taskDateTime.compareTo(currDateTime) > 0)
                    || (task instanceof RepeatEvent
                    && taskDateTime.toLocalDate().compareTo(currDateTime.toLocalDate()) == 0
                    && ((RepeatEvent) task).getNextDateTime().compareTo(currDateTime) > 0)) {
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

    //@@author
    /**
     * Getter for all events tasks.
     * @return ArrayList object containing all events
     */
    public ArrayList<Task> getEventsArray() {
        ArrayList<Task> eventList = new ArrayList<>();
        for (Task task: tasks) {
            if (task instanceof Event) {
                eventList.add(task);
            }
        }
        return eventList;
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
     * Getter for all assignment tasks.
     * @return ArrayList object containing all assignments
     */
    public ArrayList<Task> getAssignmentsArray() {
        ArrayList<Task> assignmentList = new ArrayList<>();
        for (Task task: tasks) {
            if (task instanceof Assignment) {
                assignmentList.add(task);
            }
        }
        return assignmentList;
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
        assert tasks.get(doneIndex).getIsDone() == true;
    }

    /**
     * Delete tasks according to the index specified by user.
     * @param deleteIndex index of task to be deleted
     * @throws IndexOutOfBoundsException throws when index is out of range of the size of current Tasklist
     */
    public void deleteTask(int deleteIndex) throws IndexOutOfBoundsException {
        int size = tasks.size();
        tasks.remove(deleteIndex);
        assert tasks.size() == size - 1;
    }

    /**
     * Edits task according to the index specified by user.
     * Edited task replaces the index of the old task.
     * @param editIndex Integer of index of task to be edited
     * @param editedTask Edited task object to be replaced in ArrayList
     * @throws IndexOutOfBoundsException Thrown when index is out of range of the current TaskList
     */
    public void editTask(int editIndex, Task editedTask) throws IndexOutOfBoundsException {
        tasks.set(editIndex, editedTask);
    }

    /**
     * Deletes all the tasks in the list.
     */
    public void clearList() {
        tasks.clear();
        assert tasks.size() == 0;
    }

    /**
     * Deletes the all tasks specified by doneIndex.
     * @param doneIndex ArrayList of indexes to be removed
     */
    public void deleteAllDoneTask(ArrayList<Integer> doneIndex) {
        doneIndex.sort(Comparator.reverseOrder());
        for (int index : doneIndex) {
            deleteTask(index);
        }
    }

    /**
     * Getter for tasks that falls within the provided time period.
     * @param startOfRange LocalDate representing start of time period
     * @param endOfRange LocalDate representing end of time period
     * @return ArrayList of tasks that falls withing time period
     */
    public ArrayList<Task> getTasksByRange(LocalDate startOfRange, LocalDate endOfRange) {
        ArrayList<Task> taskArrayList = new ArrayList<>();

        for (Task task : tasks) {
            LocalDate taskDate = task.getDate();
            assert taskArrayList.size() <= tasks.size();

            if (isWithinRange(startOfRange, endOfRange, taskDate)) {
                taskArrayList.add(task);
                continue;
            }

            // Add repeat Events that is before startOfRange but will spillover to provided time period
            if (task instanceof RepeatEvent && startOfRange.compareTo(taskDate) >= 0) {
                addFirstRepeatedEventWithinRange(startOfRange, endOfRange, task, taskDate, taskArrayList);
            }
        }
        return taskArrayList;
    }

    /**
     * Adds the first instance of the repeated event that falls within given time period if exists.
     * @param startOfRange LocalDate representing start of time period
     * @param endOfRange LocalDate representing end of time period
     * @param task repeating Event to be checked
     * @param taskDate Event date
     * @param taskArrayList ArrayList of Task that is to be returned
     */
    private void addFirstRepeatedEventWithinRange(LocalDate startOfRange, LocalDate endOfRange,
                                               Task task, LocalDate taskDate, ArrayList<Task> taskArrayList) {
        RepeatEvent event = (RepeatEvent) task;
        int numOfPeriod = event.getNumOfPeriod();
        String typeOfPeriod = event.getTypeOfPeriod();

        switch (typeOfPeriod) {
        case RepeatCommand.YEARLY_ICON:
            for (int timesRepeated = 1; taskDate.plusYears(numOfPeriod * timesRepeated)
                    .compareTo(endOfRange) <= 0; timesRepeated++) {
                if (isWithinRange(startOfRange, endOfRange,
                        taskDate.plusYears(numOfPeriod * timesRepeated))) {
                    RepeatEvent yearlyEventToAdd = new RepeatEvent(event.getName(),
                            event.getLocation(),
                            event.getDateAndTime().plusYears(timesRepeated * numOfPeriod),
                            event.getEndDateAndTime().plusYears(timesRepeated * numOfPeriod), event.getComments(),
                            numOfPeriod, typeOfPeriod, event.getOriginalDateAndTime(), event.getPeriodCounter());
                    taskArrayList.add(yearlyEventToAdd);
                    break;
                }
            }
            break;
        case RepeatCommand.MONTHLY_ICON:
            for (int timesRepeated = 1; taskDate.plusMonths(numOfPeriod * timesRepeated)
                    .compareTo(endOfRange) <= 0; timesRepeated++) {
                if (isWithinRange(startOfRange, endOfRange,
                        taskDate.plusMonths(numOfPeriod * timesRepeated))) {
                    Event monthlyEventToAdd = new RepeatEvent(event.getName(),
                            event.getLocation(),
                            event.getDateAndTime().plusMonths(timesRepeated * numOfPeriod),
                            event.getEndDateAndTime().plusMonths(timesRepeated * numOfPeriod), event.getComments(),
                            numOfPeriod, typeOfPeriod, event.getOriginalDateAndTime(), event.getPeriodCounter());
                    taskArrayList.add(monthlyEventToAdd);
                    break;
                }
            }
            break;
        case RepeatCommand.WEEKLY_ICON:
            for (int timesRepeated = 1; taskDate.plusWeeks(numOfPeriod * timesRepeated)
                    .compareTo(endOfRange) <= 0; timesRepeated++) {
                if (isWithinRange(startOfRange, endOfRange,
                        taskDate.plusWeeks(numOfPeriod * timesRepeated))) {
                    RepeatEvent weeklyEventToAdd = new RepeatEvent(event.getName(),
                            event.getLocation(),
                            event.getDateAndTime().plusWeeks(timesRepeated * numOfPeriod),
                            event.getEndDateAndTime().plusWeeks(timesRepeated * numOfPeriod), event.getComments(),
                            numOfPeriod, typeOfPeriod, event.getOriginalDateAndTime(), event.getPeriodCounter());
                    taskArrayList.add(weeklyEventToAdd);
                    break;
                }
            }
            break;
        case RepeatCommand.DAILY_ICON:
            for (int timesRepeated = 1; taskDate.plusDays(numOfPeriod * timesRepeated)
                 .compareTo(endOfRange) <= 0; timesRepeated++) {
                if (isWithinRange(startOfRange, endOfRange,
                        taskDate.plusDays(numOfPeriod * timesRepeated))) {
                    RepeatEvent dailyEventToAdd = new RepeatEvent(event.getName(),
                            event.getLocation(),
                            event.getDateAndTime().plusDays(timesRepeated * numOfPeriod),
                            event.getEndDateAndTime().plusDays(timesRepeated * numOfPeriod), event.getComments(),
                            numOfPeriod, typeOfPeriod, event.getOriginalDateAndTime(), event.getPeriodCounter());
                    taskArrayList.add(dailyEventToAdd);
                    break;
                }
            }
            break;
        default:
            assert false;
        }
    }

    /**
     * Checks if taskDate is within the range of time period provided.
     * @param startOfRange LocalDate representing start of time period
     * @param endOfRange LocalDate representing end of time period
     * @param taskDate LocalDate to be checked against the time period
     * @return true if taskDate is within the time period, false if otherwise
     */
    private boolean isWithinRange(LocalDate startOfRange, LocalDate endOfRange, LocalDate taskDate) {
        return startOfRange.compareTo(taskDate) <= 0 && endOfRange.compareTo(taskDate) >= 0;
    }
}
