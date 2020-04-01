package seedu.nuke.command.filtercommand.listcommand;

import seedu.nuke.command.CommandResult;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.DirectoryLevel;
import seedu.nuke.directory.Task;

import java.util.ArrayList;

import static seedu.nuke.util.Message.MESSAGE_NO_TASK_IN_LIST;
import static seedu.nuke.util.Message.messageTaskSuccessfullyList;

/**
 * sort all tasks of all modules according to deadline of task and print it out to the user.
 */
public class ListAllTasksDeadlineCommand extends ListCommand {
    public static final String COMMAND_WORD = "lstd";
    public static final String FORMAT = COMMAND_WORD;
    public static final String MESSAGE_USAGE = COMMAND_WORD + System.lineSeparator()
            + "List all tasks in ascending order of deadline"
            + System.lineSeparator() + FORMAT + System.lineSeparator();

    @Override
    public CommandResult execute() {
        // Get all tasks
        ArrayList<Task> filteredTaskList = ModuleManager.getAllTasks();
        sortTaskList(filteredTaskList, true, false);
        if (filteredTaskList.isEmpty()) {
            return new CommandResult(MESSAGE_NO_TASK_IN_LIST);
        }
        assert filteredTaskList.isEmpty() : "make sure there are some tasks in the list";

        return new CommandResult(messageTaskSuccessfullyList(filteredTaskList.size()),
                DirectoryLevel.TASK, new ArrayList<>(filteredTaskList));
    }
}
