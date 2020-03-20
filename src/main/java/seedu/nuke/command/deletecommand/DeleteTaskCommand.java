package seedu.nuke.command.deletecommand;

import seedu.nuke.Executor;
import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.CategoryManager;
import seedu.nuke.data.TaskManager;
import seedu.nuke.directory.Category;
import seedu.nuke.directory.DirectoryLevel;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.Directory;
import seedu.nuke.directory.Task;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static seedu.nuke.parser.Parser.*;
import static seedu.nuke.parser.Parser.ALL_FLAG;
import static seedu.nuke.util.Message.*;

/**
 * <h3>Delete Task Command</h3>
 * A <b>Command</b> to delete <b>Task(s)</b> from the <b>Task List</b>.
 * @see Command
 * @see Task
 */
public class DeleteTaskCommand extends DeleteCommand {
    public static final String COMMAND_WORD = "delt";
    public static final String FORMAT = COMMAND_WORD + " <category name>";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " task description " + ": Add a task to the module.";
    public static final Pattern[] REGEX_FORMATS = {
            Pattern.compile("(?<identifier>^\\s*([^-]+))"),
            Pattern.compile("(?<moduleCode>(?:\\s+" + MODULE_CODE_PREFIX + " [^-]+)?)"),
            Pattern.compile("(?<categoryName>(?:\\s+" + CATEGORY_NAME_PREFIX + " [^-]+)?)"),
            Pattern.compile("(?<exact>(?:\\s+" + EXACT_FLAG + ")?)"),
            Pattern.compile("(?<all>(?:\\s+" + ALL_FLAG + ")?)"),
            Pattern.compile("(?<invalid>(?:\\s+-(?:[^m].*|[m]\\S+))*)")
    };

    private String moduleCode;
    private String categoryName;
    private String taskDescription;
    private boolean isExact;

    /**
     * Constructs the command to delete a task.
     *
     * @param moduleCode
     *  The module code of the module that has the category to delete the task
     * @param categoryName
     *  The name of the category to delete the task
     * @param taskDescription
     *  The description of the task
     * @param isExact
     *  Checks if categories are to be filtered exactly
     */
    public DeleteTaskCommand(String moduleCode, String categoryName, String taskDescription, boolean isExact) {
        this.moduleCode = moduleCode;
        this.categoryName = categoryName;
        this.taskDescription = taskDescription;
        this.isExact = isExact;
    }

    public DeleteTaskCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     *  Executes the initial phase of the task deletion process depending on the number of tasks filtered.
     *  If there are no tasks in the filtered list, then the deletion ends.
     *  Otherwise, if there is one, there will be a prompt to request confirmation.
     *  If there are multiple tasks instead, there will be a prompt to choose which tasks to delete.
     *
     * @param filteredTasks
     *  The filtered list of tasks containing possibly the tasks to be deleted
     * @return
     *  The result of the execution
     */
    @Override
    protected CommandResult executeInitialDelete(ArrayList<Directory> filteredTasks) {
        final int TASK_COUNT = filteredTasks.size();
        if (TASK_COUNT == 0) {
            return new CommandResult(MESSAGE_NO_TASKS_FOUND);
        } else if (TASK_COUNT == 1) {
            Executor.preparePromptConfirmation();
            Executor.setFilteredList(filteredTasks, DirectoryLevel.TASK);
            Task toDelete = (Task) filteredTasks.get(0);
            return new CommandResult(messageConfirmDeleteTask(toDelete));
        } else {
            Executor.preparePromptIndices();
            Executor.setFilteredList(filteredTasks, DirectoryLevel.TASK);
            return new CommandResult(messagePromptDeleteTaskIndices(filteredTasks));
        }
    }

    /**
     * Executes the <b>Delete Task Command</b> to delete <b>Task<(s)/b> with the <code>task description</code>
     * from the <b>Task List</b>.
     *
     * @return The <b>Command Result</b> of the execution
     * @see Task
     * @see TaskManager
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
//        try {
//            Module currentModule = (Module) Command.getCurrentDirectory();
//            Task toDelete = currentModule.getTaskManager().delete(taskDescription);
//
//            //add the task to the data manager
//            moduleManager.removeTask(currentModule.getTaskManager(), toDelete);
//            return new CommandResult(MESSAGE_TASK_REMOVED);
//        } catch (TaskManager.TaskNotFoundException e) {
//            return new CommandResult(MESSAGE_TASK_NOT_FOUND);
//        }
        ArrayList<Task> filteredTasks =
                isExact ? ModuleManager.filterExact(moduleCode, categoryName, taskDescription) :
                        ModuleManager.filter(moduleCode, categoryName, taskDescription);
        return executeInitialDelete(new ArrayList<>(filteredTasks));
    }
}
