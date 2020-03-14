package command;

import common.Messages;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import tasks.Task;

import java.util.ArrayList;

public class ListCommand extends Command {
    public static final String LIST_COMMAND_WORD = "list";

    private final String listParam;
    private static final String ALL_TASK_COMMAND = "";
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
        ArrayList<Task> allTaskList = taskList.getTaskArray();
        // to deal with null being passed as input
        switch (listParam == null ? ALL_TASK_COMMAND : listParam) {
        case (TODAY_COMMAND):
            return new CommandResult(showListTasks(allTaskList, taskList.getTasksByDays(0)));
        case (WEEK_COMMAND):
            return new CommandResult(showListTasks(allTaskList, taskList.getTasksByDays(7)));
        case (UPCOMING_EVENT_COMMAND):
            return new CommandResult(showListTasks(allTaskList, taskList.getUpcomingEventArray()));
        case (INCOMPLETE_ASSIGN_COMMAND):
            return new CommandResult(showListTasks(allTaskList, taskList.getIncompleteAssignArray()));
        case (ALL_TASK_COMMAND):
            return new CommandResult(showListTasks(allTaskList, taskList.getTaskArray()));
        default:
            return new CommandResult(Messages.LIST_INCORRECT_FORMAT_ERROR);
        }
    }

    /**
     * Formats String of tasks with standard task listing message.
     * @param taskList ArrayList object with tasks to be printed
     * @return Formatted string of tasks and printing message
     */
    public String showListTasks(ArrayList<Task> allTaskList, ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            //If there are no tasks found within the provided taskList
            return (Messages.EMPTY_TASKLIST_MESSAGE);
        }
        String stringFromArrayList = stringTaskList(allTaskList, taskList);
        return (String.format(Messages.SHOW_TASKLIST_MESSAGE, System.lineSeparator(), stringFromArrayList));
    }

    /**
     * Converts ArrayList object into string to be printed.
     * @param allTaskList Use ArrayList object with all tasks added in it to obtain its original index.
     * @param selectedTaskList ArrayList object with tasks to be printed.
     * @return Formatted string of tasks.
     */
    private String stringTaskList(ArrayList<Task> allTaskList, ArrayList<Task> selectedTaskList) {
        StringBuilder stringFromArrayList = new StringBuilder();
        Task lastTask = selectedTaskList.get(selectedTaskList.size() - 1);
        for (Task task : selectedTaskList) {
            stringFromArrayList.append(String.format("%3d. %s", allTaskList.indexOf(task) + 1, task.toString()));
            if (task.equals(lastTask)) {
                break;
            }
            stringFromArrayList.append(System.lineSeparator());
        }
        return stringFromArrayList.toString();
    }
}
