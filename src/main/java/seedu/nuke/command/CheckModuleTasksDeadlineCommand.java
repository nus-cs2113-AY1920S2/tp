package seedu.nuke.command;

import seedu.nuke.exception.ModuleNotFoundException;
import seedu.nuke.module.Module;

import static seedu.nuke.util.Message.MESSAGE_NO_TASK_IN_LIST;
import static seedu.nuke.util.Message.MESSAGE_TASK_SUCCESSFULY_LIST;

import java.util.ArrayList;

/**
 * sort all tasks of one module according to deadline of task and print it out to the user
 */
public class CheckModuleTasksDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "lstm";
    public static final String MESSAGE_USAGE = COMMAND_WORD;

    private int moduleIndex;
    private Module module;
    private ArrayList<String> deadlines;

    public CheckModuleTasksDeadlineCommand(int moduleIndex) throws ModuleNotFoundException {
        this.moduleIndex = moduleIndex;
        try {
            this.module = moduleManager.getModuleList().get(moduleIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new ModuleNotFoundException("Module index is out of bound");
        }
    }

    @Override
    public CommandResult execute() {
        if (module.countTasks() == 0) {
            return new CommandResult(MESSAGE_NO_TASK_IN_LIST);
        }
        deadlines = module.checkDeadline();
        return new CommandResult(String.format(MESSAGE_TASK_SUCCESSFULY_LIST, module.countTasks()), true, deadlines);
    }
}
