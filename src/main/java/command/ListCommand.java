package command;

import seedu.duke.TaskList;
import seedu.duke.Ui;

public class ListCommand extends Command {

    private final String listParam;
    private static final String TODAY_COMMAND = "today";
    private static final String WEEK_COMMAND = "week";

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
        if (listParam.equals(TODAY_COMMAND)) {
            taskList.listTodayTasks();
        } else if (listParam.equals(WEEK_COMMAND)) {
            taskList.listWeekTasks();
        }
    }
}
