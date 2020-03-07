package command;

import seedu.duke.TaskList;
import seedu.duke.Ui;

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

    /**
     * Processes user input and lists tasks accordingly.
     * @param taskList TaskList class which handles operation involving ArrayList of tasks
     * @param ui Ui class for displaying output of task scheduler
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        // to deal with null being passed as input
        switch (listParam == null ? "" : listParam) {
        case (TODAY_COMMAND):
            taskList.listTodayTasks();
            break;
        case (WEEK_COMMAND):
            taskList.listWeekTasks();
            break;
        case (UPCOMING_EVENT_COMMAND):
            ui.showListTasks(taskList.getUpcomingEventArray());
            break;
        case (INCOMPLETE_ASSIGN_COMMAND):
            ui.showListTasks(taskList.getIncompleteAssignArray());
            break;
        default:
            ui.showListTasks(taskList.getTaskArray());
        }
    }
}
