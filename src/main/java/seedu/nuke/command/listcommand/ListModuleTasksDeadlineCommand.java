package seedu.nuke.command.listCommand;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.directory.Module;

import java.util.ArrayList;

import static seedu.nuke.util.Message.MESSAGE_GO_INTO_MODULE;
import static seedu.nuke.util.Message.MESSAGE_NO_TASK_IN_LIST;
import static seedu.nuke.util.Message.MESSAGE_TASK_SUCCESSFULLY_LIST;


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
        Module module = (Module) Command.getCurrentDirectory();
        if ((module != null)) {
            if (module.countTasks() == EMPTY) {
                return new CommandResult(MESSAGE_NO_TASK_IN_LIST);
            }

            assert module.countTasks() != EMPTY : "make sure the list is not empty";

            deadlines = module.checkDeadline();
            return new CommandResult(String.format(MESSAGE_TASK_SUCCESSFULLY_LIST, module.countTasks()),
                    true, deadlines);
        } else {
            return new CommandResult(MESSAGE_GO_INTO_MODULE);
        }

    }
}
