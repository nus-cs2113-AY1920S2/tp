package seedu.nuke.command;

import seedu.nuke.exception.ModuleNotFoundException;
import seedu.nuke.module.Module;

import static seedu.nuke.util.Message.MESSAGE_NO_TASK_IN_LIST;
import static seedu.nuke.util.Message.MESSAGE_TASK_SUCCESSFULLY_LIST;

import java.util.ArrayList;

/**
 * sort all tasks of one module according to deadline of task and print it out to the user.
 */
public class CheckModuleTasksDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "lstm";
    public static final String MESSAGE_USAGE = COMMAND_WORD;
    public static final int EMPTY = 0;

    private ArrayList<String> deadlines;

    /**
     * Constructor method for CheckModuleTaskDeadlineCommand class.
     * @throws ModuleNotFoundException exception is thrown if the specified module is not found.
     */
    public CheckModuleTasksDeadlineCommand() {
    }

    @Override
    public CommandResult execute() {
        if (currentModule.countTasks() == EMPTY) {
            return new CommandResult(MESSAGE_NO_TASK_IN_LIST);
        }

        assert currentModule.countTasks() != EMPTY : "make sure the list is not empty";

        deadlines = currentModule.checkDeadline();
        return new CommandResult(String.format(MESSAGE_TASK_SUCCESSFULLY_LIST, currentModule.countTasks()),
                true, deadlines);
    }
}
