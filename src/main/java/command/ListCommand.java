package command;

import common.Messages;
import seedu.atas.Parser;
import seedu.atas.TaskList;
import seedu.atas.Ui;
import tasks.Task;

import java.util.ArrayList;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    private static final String LIST_ALL_COMMAND_USAGE = "- List All Tasks: list";
    public static final String LIST_TODAY_COMMAND_USAGE = "- List Today's Tasks: list today";
    private static final String LIST_WEEK_COMMAND_USAGE = "- List This Week's Tasks: list week";
    private static final String LIST_UPCOMING_EVENT_COMMAND_USAGE = "- List Upcoming Events: list upcoming events";
    private static final String LIST_INCOMPLETE_ASSIGN_COMMAND_USAGE = "- List Incomplete Assignments: "
            + "list incomplete assignments";
    public static final String COMMAND_USAGE = "List commands that are available:" + System.lineSeparator()
            + Messages.NEWLINE_INDENT + LIST_ALL_COMMAND_USAGE + System.lineSeparator()
            + Messages.NEWLINE_INDENT + LIST_TODAY_COMMAND_USAGE + System.lineSeparator()
            + Messages.NEWLINE_INDENT + LIST_WEEK_COMMAND_USAGE + System.lineSeparator()
            + Messages.NEWLINE_INDENT + LIST_UPCOMING_EVENT_COMMAND_USAGE + System.lineSeparator()
            + Messages.NEWLINE_INDENT + LIST_INCOMPLETE_ASSIGN_COMMAND_USAGE;

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
            return new CommandResult(String.format(Messages.INCORRECT_ARGUMENT_ERROR,
                    Parser.capitalize(COMMAND_WORD), COMMAND_USAGE));
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
        return (String.format(Messages.LIST_TASKLIST_MESSAGE, System.lineSeparator(), stringFromArrayList));
    }

    /**
     * Converts ArrayList object into string to be printed.
     * @param allTaskList Use ArrayList object with all tasks added in it to obtain its original index.
     * @param selectedTaskList ArrayList object with tasks to be printed.
     * @return Formatted string of tasks.
     */
    private String stringTaskList(ArrayList<Task> allTaskList, ArrayList<Task> selectedTaskList) {
        StringBuilder stringFromArrayList = new StringBuilder();
        assert selectedTaskList.size() > 0;
        assert allTaskList.size() > 0;
        Task lastTask = selectedTaskList.get(selectedTaskList.size() - 1);
        assert lastTask != null;
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
