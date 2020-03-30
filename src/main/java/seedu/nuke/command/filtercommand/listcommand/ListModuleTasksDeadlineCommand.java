package seedu.nuke.command.filtercommand.listcommand;

import seedu.nuke.command.CommandResult;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.DirectoryTraverser;
import seedu.nuke.directory.Module;
import seedu.nuke.directory.Task;
import seedu.nuke.exception.IncorrectDirectoryLevelException;
import seedu.nuke.util.ExceptionMessage;
import seedu.nuke.util.ListCreator;

import java.util.ArrayList;

import static seedu.nuke.util.Message.MESSAGE_NO_TASK_IN_LIST;
import static seedu.nuke.util.Message.messageTaskSuccessfullyList;


/**
 * sort all tasks of one module according to deadline of task and print it out to the user.
 */
public class ListModuleTasksDeadlineCommand extends ListCommand {

    public static final String COMMAND_WORD = "lsmtd";
    public static final String FORMAT = COMMAND_WORD + " <module code>";
    public static final String MESSAGE_USAGE = COMMAND_WORD + System.lineSeparator()
            + "List tasks of module in ascending order of deadline" + System.lineSeparator()
            + FORMAT + System.lineSeparator();
    public static final int EMPTY = 0;
    private String moduleCode;
    private String deadlines;

    public ListModuleTasksDeadlineCommand(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    @Override
    public CommandResult execute() {
        try {
            Module module = DirectoryTraverser.getModuleDirectory(moduleCode);
            ArrayList<Task> filteredTaskList = module.sortAllTasks();
            if (filteredTaskList.size() == EMPTY) {
                return new CommandResult(MESSAGE_NO_TASK_IN_LIST);
            }
            assert filteredTaskList.size() != EMPTY : "make sure there are some tasks in the list";
            deadlines = ListCreator.createTaskListTable(new ArrayList<>(filteredTaskList), true);
            return new CommandResult(messageTaskSuccessfullyList(ModuleManager.countAllTasks()) + deadlines);
        } catch (IncorrectDirectoryLevelException e) {
            return new CommandResult(ExceptionMessage.MESSAGE_INCORRECT_DIRECTORY_LEVEL);
        } catch (ModuleManager.ModuleNotFoundException e) {
            return new CommandResult(ExceptionMessage.MESSAGE_MODULE_NOT_FOUND);
        }
    }
}
