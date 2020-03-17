package seedu.nuke.command.listCommand;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.exception.ModuleNotFoundException;
import seedu.nuke.module.Module;

import java.util.ArrayList;

import static seedu.nuke.util.Message.*;

/**
 * sort all tasks of one module according to deadline of task and print it out to the user.
 */
public class ListModuleTasksDeadlineCommand extends ListCommand {

    public static final String COMMAND_WORD = "lstm";
    public static final String MESSAGE_USAGE = COMMAND_WORD;
    public static final int EMPTY = 0;

    private ArrayList<String> deadlines;

    /**
     * Constructor method for CheckModuleTaskDeadlineCommand class.
     * @throws ModuleNotFoundException exception is thrown if the specified module is not found.
     */
    public ListModuleTasksDeadlineCommand() {
    }

    @Override
    public CommandResult execute() {
        if ((currentModule != null)){
            if (currentModule.countTasks() == EMPTY) {
                return new CommandResult(MESSAGE_NO_TASK_IN_LIST);
            }

            assert currentModule.countTasks() != EMPTY : "make sure the list is not empty";

            deadlines = currentModule.checkDeadline();
            return new CommandResult(String.format(MESSAGE_TASK_SUCCESSFULLY_LIST, currentModule.countTasks()),
                    true, deadlines);
        } else {
            return new CommandResult(MESSAGE_GO_INTO_MODULE);
        }

    }
}
