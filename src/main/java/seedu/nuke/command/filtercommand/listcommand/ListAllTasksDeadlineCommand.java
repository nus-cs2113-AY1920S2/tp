package seedu.nuke.command.filtercommand.listcommand;

import seedu.nuke.command.CommandResult;
import seedu.nuke.data.ModuleManager;

import java.util.ArrayList;

import static seedu.nuke.util.Message.MESSAGE_NO_TASK_IN_LIST;
import static seedu.nuke.util.Message.messageTaskSuccessfullyList;

/**
 * sort all tasks of all modules according to deadline of task and print it out to the user.
 */
public class ListAllTasksDeadlineCommand extends ListCommand {

    private ArrayList<String> deadlines;

    public static final String COMMAND_WORD = "lstd";
    public static final String MESSAGE_USAGE = COMMAND_WORD;
    public static final int EMPTY = 0;

    @Override
    public CommandResult execute() {
        //get the large task list
        if (ModuleManager.countAllTasks() == EMPTY) {
            return new CommandResult(MESSAGE_NO_TASK_IN_LIST);
        }
        assert ModuleManager.countAllTasks() != EMPTY : "make sure there are some tasks in the list";
        deadlines = ModuleManager.checkDeadline();
        return new CommandResult(messageTaskSuccessfullyList(ModuleManager.countAllTasks()) + deadlines);
    }
}
