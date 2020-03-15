package seedu.nuke.command;

import seedu.nuke.data.DataManager;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.TaskManager;
import seedu.nuke.task.Task;
import seedu.nuke.ui.TextUi;

import java.util.ArrayList;
import java.util.Collections;

import static seedu.nuke.util.Message.MESSAGE_NO_TASK_IN_LIST;
import static seedu.nuke.util.Message.MESSAGE_TASK_SUCCESSFULY_LIST;

/**
 * sort all tasks of all modules according to deadline of task and print it out to the user
 */
public class CheckAllTasksDeadlineCommand extends Command{

    protected DataManager dataManager;
    private ArrayList<String> deadlines;

    public static final String COMMAND_WORD = "lst";
    public static final String MESSAGE_USAGE = COMMAND_WORD;
    public static final int EMPTY = 0;


    @Override
    public CommandResult execute() {
        //get the large task list
        dataManager = new DataManager(moduleManager);
        if (dataManager.countAllTasks() == EMPTY) {
            return new CommandResult(MESSAGE_NO_TASK_IN_LIST);
        }
        deadlines = dataManager.checkDeadline();
        return new CommandResult(String.format(MESSAGE_TASK_SUCCESSFULY_LIST, dataManager.countAllTasks()), true, deadlines);
    }
}
