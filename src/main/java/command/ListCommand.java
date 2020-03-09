package command;

import common.Messages;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import tasks.Task;

import java.util.ArrayList;

public class ListCommand extends Command {

    private final String listParam;
    private static final String TODAY_COMMAND = "today";
    private static final String WEEK_COMMAND = "week";
    private static final String UPCOMING_EVENT_COMMAND = "upcoming events";
    private static final String INCOMPLETE_ASSIGN_COMMAND = "incomplete assignments";

    /**
     * Default constructor for ListCommand Class.
     * @param listParam String containing user input on type of listing
     */
    public ListCommand(String listParam) {
        this.listParam = listParam;
    }

    @Override
    public CommandResult execute(TaskList taskList, Ui ui) {
        // to deal with null being passed as input
        switch (listParam == null ? "" : listParam) {
        case (TODAY_COMMAND):
            return new CommandResult(showListTasks(taskList.getTodayTasks()));
        case (WEEK_COMMAND):
            return new CommandResult(showListTasks(taskList.getWeekTasks()));
        case (UPCOMING_EVENT_COMMAND):
            return new CommandResult(showListTasks(taskList.getUpcomingEventArray()));
        case (INCOMPLETE_ASSIGN_COMMAND):
            return new CommandResult(showListTasks(taskList.getIncompleteAssignArray()));
        default:
            return new CommandResult(showListTasks(taskList.getTaskArray()));
        }
    }

    /**
     * Converts an ArrayList object to text for printing.
     * @param taskList ArrayList object to be converted to string.
     */
    public String showListTasks(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            //If there are no tasks found within the provided taskList
            return (Messages.EMPTY_TASKLIST_MESSAGE);
        }
        String stringFromArrayList = stringTaskList(taskList);
        return (String.format(Messages.SHOW_TASKLIST_MESSAGE, System.lineSeparator(), stringFromArrayList));
    }

    private String stringTaskList(ArrayList<Task> taskList) {
        StringBuilder stringFromArrayList = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            stringFromArrayList.append((i + 1) + "." + taskList.get(i).toString());
            if (i != taskList.size() - 1) {
                stringFromArrayList.append(System.lineSeparator());
            }
        }
        return stringFromArrayList.toString();
    }
}
