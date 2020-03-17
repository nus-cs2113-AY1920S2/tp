package seedu.nuke.command;

import seedu.nuke.data.DataManager;

import java.util.ArrayList;

import static seedu.nuke.util.Message.MESSAGE_NO_TASK_IN_LIST;
import static seedu.nuke.util.Message.MESSAGE_TASK_SUCCESSFULLY_LIST;

/**
 * sort all tasks of all modules according to deadline of task and print it out to the user.
 */
public class CheckAllTasksDeadlineCommand extends Command {

    private ArrayList<String> deadlines;

    public static final String COMMAND_WORD = "lst";
    public static final String MESSAGE_USAGE = COMMAND_WORD;
    public static final int EMPTY = 0;


    @Override
    public CommandResult execute() {
        //get the large task list
        if (moduleManager.countAllTasks() == EMPTY) {
            return new CommandResult(MESSAGE_NO_TASK_IN_LIST);
        }
        assert moduleManager.countAllTasks() == EMPTY : "make sure there are some tasks in the list";
        deadlines = moduleManager.checkDeadline();
        return new CommandResult(String.format(MESSAGE_TASK_SUCCESSFULLY_LIST, moduleManager.countAllTasks()),
                true, deadlines);
    }
}
