package seedu.nuke.command.listcommand;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.directory.Directory;
import seedu.nuke.directory.DirectoryLevel;
import seedu.nuke.directory.DirectoryTraverser;
import seedu.nuke.directory.Module;

import java.util.ArrayList;

import static seedu.nuke.util.Message.MESSAGE_GO_INTO_MODULE;
import static seedu.nuke.util.Message.MESSAGE_NO_TASK_IN_LIST;
import static seedu.nuke.util.Message.MESSAGE_TASK_SUCCESSFULLY_LIST;


/**
 * sort all tasks of one module according to deadline of task and print it out to the user.
 */
public class ListModuleTasksDeadlineCommand extends ListCommand {

    public static final String COMMAND_WORD = "lsmtd";
    public static final String MESSAGE_USAGE = COMMAND_WORD;
    public static final int EMPTY = 0;

    private ArrayList<String> deadlines;

    /**
     * Constructor method for CheckModuleTaskDeadlineCommand class.
     */
    public ListModuleTasksDeadlineCommand() {
    }

    @Override
    protected ArrayList<Directory> createFilteredList() {
        return null;
    }

    @Override
    public CommandResult execute() {
        Module module = (Module) DirectoryTraverser.getCurrentDirectory();
        if ((module != null)) {
            if (module.countTasks() == EMPTY) {
                return new CommandResult(MESSAGE_NO_TASK_IN_LIST);
            }

            assert module.countTasks() != EMPTY : "make sure the list is not empty";

            deadlines = module.checkDeadline();
            return new CommandResult(MESSAGE_TASK_SUCCESSFULLY_LIST(module.countTasks()) + deadlines);
        } else {
            return new CommandResult(MESSAGE_GO_INTO_MODULE);
        }

    }
}
