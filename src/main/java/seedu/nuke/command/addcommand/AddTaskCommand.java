package seedu.nuke.command.addcommand;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.command.TaskCommand;
import seedu.nuke.data.CategoryManager;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.TaskManager;
import seedu.nuke.directory.Category;
import seedu.nuke.directory.Module;
import seedu.nuke.directory.Task;
import seedu.nuke.util.DateTime;

import java.util.regex.Pattern;

import static seedu.nuke.parser.Parser.*;
import static seedu.nuke.util.ExceptionMessage.*;
import static seedu.nuke.util.Message.MESSAGE_TASK_ADDED;
import static seedu.nuke.util.Message.messageAddTaskSuccess;

/**
 * <h3>Add Task Command</h3>
 * A <b>Command</b> to add a <b>Task</b> to the <b>Task List</b>.
 *
 * @see Command
 * @see Task
 */
public class AddTaskCommand extends AddCommand {
    public static final String COMMAND_WORD = "addt";
    public static final String FORMAT = COMMAND_WORD +
            " <task description> -m <module code> -c <category name> -d <deadline> -p <priority>";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " task description " + ": Add a task to the module.";
    public static final Pattern[] REGEX_FORMATS = {
            Pattern.compile("(?<identifier>^\\s*([^-]+))"),
            Pattern.compile("(?<moduleCode>(?:\\s+" + MODULE_CODE_PREFIX + " [^-]+)?)"),
            Pattern.compile("(?<categoryName>(?:\\s+" + CATEGORY_NAME_PREFIX + " [^-]+)?)"),
            Pattern.compile("(?<deadline>(?:\\s+" + DEADLINE_PREFIX + " [^-]+)?)"),
            Pattern.compile("(?<priority>(?:\\s+" + PRIORITY_PREFIX + " [^-]+)?)"),
            Pattern.compile("(?<invalid>(?:\\s+-(?:[^mcdp].*|[mcdp]\\S+))*)")
    };

    private String moduleCode;
    private String categoryName;
    private String description;
    private DateTime deadline;
    private int priority;
    private Task taskToAdd;

    /**
     * Constructs the command to add a task.
     *
     * @param moduleCode
     *  The module code of the module that has the category to add the task
     * @param categoryName
     *  The name of the category to add the task
     * @param description
     *  The priority of the category
     * @param deadline
     *  The deadline of the task
     * @param priority
     *  The priority of the task
     */
    public AddTaskCommand(String moduleCode, String categoryName, String description, DateTime deadline, Integer priority) {
        this.moduleCode = moduleCode;
        this.categoryName = categoryName;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
    }

    /**
     * Constructs the command to add a task without a priority.
     *
     * @param moduleCode
     *  The module code of the module that has the category to add the task
     * @param categoryName
     *  The name of the category to add the task
     * @param description
     *  The priority of the category
     * @param deadline
     *  The deadline of the task
     */
    public AddTaskCommand(String moduleCode, String categoryName, String description, DateTime deadline) {
        // Dummy value for missing priority
        this(moduleCode, categoryName, description, deadline, -1);
    }

    public AddTaskCommand(Task task) {
        this.taskToAdd = task;
    }

    /**
     * Executes the <b>Add Task Command</b> to add a <b>Task</b> into the <b>Task List</b>.
     *
     * @return The <b>Command Result</b> of the execution
     * @see Task
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
//        Module module = (Module)Command.getCurrentDirectory();
//        ModuleManager.addTaskToModule(module.getTaskManager(), taskToAdd);
//        //dataManager.addTask(taskToAdd);
//        return new CommandResult(MESSAGE_TASK_ADDED);
        try {
            if (priority < 0) {
                priority = ModuleManager.getCategory(moduleCode, categoryName).getCategoryPriority();
            }
            Category parentCategory = ModuleManager.getCategory(moduleCode, categoryName);
            Task toAdd = new Task(parentCategory, description, deadline, priority);
            ModuleManager.retrieveList(moduleCode, categoryName).add(toAdd);
            return new CommandResult(messageAddTaskSuccess(description));
        } catch (ModuleManager.ModuleNotFoundException e) {
            return new CommandResult(MESSAGE_MODULE_NOT_FOUND);
        } catch (CategoryManager.CategoryNotFoundException e) {
            return new CommandResult(MESSAGE_CATEGORY_NOT_FOUND);
        } catch (TaskManager.DuplicateTaskException e) {
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        }
    }
}
