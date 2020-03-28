package seedu.nuke.command.addcommand;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.command.TaskCommand;
import seedu.nuke.data.CategoryManager;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.TaskManager;
import seedu.nuke.directory.Category;
import seedu.nuke.directory.Task;
import seedu.nuke.exception.IncorrectDirectoryLevelException;
import seedu.nuke.util.DateTime;

import java.util.regex.Pattern;

import static seedu.nuke.directory.DirectoryTraverser.getBaseCategory;
import static seedu.nuke.directory.DirectoryTraverser.getBaseModule;
import static seedu.nuke.parser.Parser.CATEGORY_NAME_PREFIX;
import static seedu.nuke.parser.Parser.DEADLINE_PREFIX;
import static seedu.nuke.parser.Parser.MODULE_CODE_PREFIX;
import static seedu.nuke.parser.Parser.PRIORITY_PREFIX;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_CATEGORY_NOT_FOUND;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_DUPLICATE_TASK;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_INCORRECT_DIRECTORY_LEVEL;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
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
    public static final String FORMAT = COMMAND_WORD
            + " <task description> -m <module code> -c <category name> [ -d <deadline> -p <priority> ]";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " task description " + ": Add a task to the module.";
    public static final Pattern REGEX_FORMAT = Pattern.compile(
            "(?<identifier>(?:(?:\\s+[^-\\s]\\S*)+|^[^-\\s]\\S*)+)"
            + "(?<moduleCode>(?:\\s+" + MODULE_CODE_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
            + "(?<categoryName>(?:\\s+" + CATEGORY_NAME_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
            + "(?<optional>(?:\\s+-[dp](?:\\s+[^-\\s]\\S*)+)*)"
            + "(?<invalid>(?:\\s+-.*)*)"
    );
    public static final Pattern REGEX_OPTIONAL_FORMAT = Pattern.compile(
            "(?<deadline>(?:\\s+" + DEADLINE_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
            + "(?<priority>(?:\\s+" + PRIORITY_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
    );

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
    public AddTaskCommand(String moduleCode, String categoryName, String description, DateTime deadline, int priority) {
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

    /**
     * Returns the parent category level directory of the Directory to be added.
     *
     * @return
     *  The parent category level directory of the Directory to be added
     * @throws IncorrectDirectoryLevelException
     *  If the current directory is too low to obtain the parent category level directory
     * @throws ModuleManager.ModuleNotFoundException
     *  If the module with the module code is not found in the Module List
     * @throws CategoryManager.CategoryNotFoundException
     *  If the category with the category name is not found in the Category List
     */
    protected Category getParentDirectory()
            throws IncorrectDirectoryLevelException, ModuleManager.ModuleNotFoundException,
            CategoryManager.CategoryNotFoundException {
        if (moduleCode.isEmpty()) {
            if (categoryName.isEmpty()) {
                return getBaseCategory();
            } else {
                return getBaseModule().getCategories().getCategory(categoryName);
            }
        } else {
            if (categoryName.isEmpty()) {
                if (!getBaseModule().isSameModule(moduleCode)) {
                    throw new IncorrectDirectoryLevelException();
                }
                return getBaseCategory();
            } else {
                return ModuleManager.getCategory(moduleCode, categoryName);
            }
        }
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
        //Module module = (Module)Command.getCurrentDirectory();
        //ModuleManager.addTaskToModule(module.getTaskManager(), taskToAdd);
        ////dataManager.addTask(taskToAdd);
        //return new CommandResult(MESSAGE_TASK_ADDED);
        try {
            Category parentCategory = getParentDirectory();
            if (priority < 0) {
                priority = parentCategory.getCategoryPriority();
            }
            Task toAdd = new Task(parentCategory, description, deadline, priority);
            parentCategory.getTasks().add(toAdd);
            return new CommandResult(messageAddTaskSuccess(description));
        } catch (ModuleManager.ModuleNotFoundException e) {
            return new CommandResult(MESSAGE_MODULE_NOT_FOUND);
        } catch (CategoryManager.CategoryNotFoundException e) {
            return new CommandResult(MESSAGE_CATEGORY_NOT_FOUND);
        } catch (TaskManager.DuplicateTaskException e) {
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        } catch (IncorrectDirectoryLevelException e) {
            return new CommandResult(MESSAGE_INCORRECT_DIRECTORY_LEVEL);
        }
    }
}
