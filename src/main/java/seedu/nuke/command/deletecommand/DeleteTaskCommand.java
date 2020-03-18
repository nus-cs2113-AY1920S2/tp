package seedu.nuke.command.deletecommand;

import seedu.nuke.Executor;
import seedu.nuke.command.CommandResult;
import seedu.nuke.common.DataType;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.TaskManager;
import seedu.nuke.directory.Directory;
import seedu.nuke.directory.Task;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static seedu.nuke.parser.Parser.*;
import static seedu.nuke.parser.Parser.ALL_FLAG;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_TASK_NOT_FOUND;
import static seedu.nuke.util.Message.*;

public class DeleteTaskCommand extends DeleteCommand {
    public static final String COMMAND_WORD = "delt";
    public static final String FORMAT = COMMAND_WORD + " <category name>";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " task description " + ": Add a task to the module.";
    public static final Pattern[] REGEX_FORMATS = {
            Pattern.compile("(?<identifier>^\\s*([^-]+))"),
            Pattern.compile("(?<moduleCode>(?:" + MODULE_CODE_PREFIX + " [^-]+)?)"),
            Pattern.compile("(?<categoryName>(?:" + CATEGORY_NAME_PREFIX + " [^-]+)?)"),
            Pattern.compile("(?<exact>(?:" + EXACT_FLAG + ")?)"),
            Pattern.compile("(?<all>(?:" + ALL_FLAG + ")?)"),
            Pattern.compile("(?<invalid>(?:-(?:[^m].*|[m]\\S+)))")
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
        // Cast to Array List of Categories
        ArrayList<Task> tasks = filteredTasks.stream()
                .map(Task.class::cast)
                .collect(Collectors.toCollection(ArrayList::new));
        final int tasksCount = tasks.size();
        if (tasksCount == 0) {
            return new CommandResult(MESSAGE_NO_TASKS_FOUND);
        } else if (tasksCount == 1) {
            Executor.preparePromptConfirmation();
            Executor.setFilteredList(tasks, DataType.TASK);
            Task toDelete = (Task) tasks.get(0);
            return new CommandResult(messageConfirmDeleteTask(toDelete));
        } else {
            Executor.preparePromptIndices();
            Executor.setFilteredList(tasks, DataType.TASK);
            return new CommandResult(messagePromptDeleteTaskIndices(tasks));
        }
    }

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
