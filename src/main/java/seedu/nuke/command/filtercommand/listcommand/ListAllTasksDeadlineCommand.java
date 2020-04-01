package seedu.nuke.command.filtercommand.listcommand;

import seedu.nuke.command.CommandResult;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.Task;
import seedu.nuke.util.ListCreator;

import java.util.ArrayList;

import static seedu.nuke.util.Message.messageTaskSuccessfullyList;

/**
 * sort all tasks of all modules according to deadline of task and print it out to the user.
 */
public class ListAllTasksDeadlineCommand extends ListCommand {

    private String deadlines;

    public static final String COMMAND_WORD = "lstd";
    public static final String FORMAT = COMMAND_WORD;
    public static final String MESSAGE_USAGE = COMMAND_WORD + System.lineSeparator()
            + "List all tasks in ascending order of deadline"
            + System.lineSeparator() + FORMAT + System.lineSeparator();
    public static final int EMPTY = 0;

    @Override
    public CommandResult execute() {
        //get the large task list
        ArrayList<Task> filteredTaskList = ModuleManager.sortAllTasks();
        deadlines = ListCreator.createTaskListTable(new ArrayList<>(filteredTaskList), true);
        return new CommandResult(messageTaskSuccessfullyList(ModuleManager.countAllTasks())
                + System.lineSeparator() + deadlines);
    }
}
