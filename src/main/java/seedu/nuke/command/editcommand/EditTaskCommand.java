package seedu.nuke.command.editcommand;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.CategoryManager;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.TaskManager;
import seedu.nuke.directory.Category;
import seedu.nuke.directory.Task;
import seedu.nuke.util.DateTime;

import java.util.regex.Pattern;

import static seedu.nuke.parser.Parser.*;
import static seedu.nuke.util.ExceptionMessage.*;
import static seedu.nuke.util.Message.MESSAGE_EDIT_CATEGORY_SUCCESS;
import static seedu.nuke.util.Message.MESSAGE_EDIT_TASK_SUCCESS;

/**
 * <h3>Edit Task Command</h3>
 * A <b>Command</b> to edit a <b>Task</b> in the <b>Task List</b>.
 * @see Command
 * @see Task
 */
public class EditTaskCommand extends EditCommand {
    public static final String COMMAND_WORD = "edt";
    public static final String FORMAT = COMMAND_WORD +
            " <old task description> -m <module code> -c <category name>" +
            " -t <new task description> -d <new deadline> -p <new priority>";
    public static final Pattern[] REGEX_FORMATS = {
            Pattern.compile("(?<identifier>^\\s*([^-]+)?)"),
            Pattern.compile("(?<moduleCode>(?:\\s+" + MODULE_CODE_PREFIX + " [^-]+))"),
            Pattern.compile("(?<categoryName>(?:\\s+" + CATEGORY_NAME_PREFIX + " [^-]+))"),
            Pattern.compile("(?<taskDescription>(?:\\s+" + TASK_DESCRIPTION_PREFIX + " [^-]+))"),
            Pattern.compile("(?<deadline>(?:\\s+" + DEADLINE_PREFIX + " [^-]+)?)"),
            Pattern.compile("(?<priority>(?:\\s+" + PRIORITY_PREFIX + " [^-]+)?)"),
            Pattern.compile("(?<invalid>(?:\\s+-(?:[^mctdp].*|[mctdp]\\S+)))")
    };

    private String oldTaskDescription;
    private String moduleCode;
    private String categoryName;
    private String newTaskDescription;
    private DateTime newDeadline;
    private int newPriority;

    public EditTaskCommand(String oldTaskDescription, String moduleCode, String categoryName,
                           String newTaskDescription, DateTime newDeadline, int newPriority) {
        this.oldTaskDescription = oldTaskDescription;
        this.moduleCode = moduleCode;
        this.categoryName = categoryName;
        this.newTaskDescription = newTaskDescription;
        this.newDeadline = newDeadline;
        this.newPriority = newPriority;
    }

    public EditTaskCommand(String oldTaskDescription, String moduleCode, String categoryName,
                           String newTaskDescription, DateTime newDeadline) {
        this(oldTaskDescription, moduleCode, categoryName, newTaskDescription, newDeadline, -1);
    }

    /**
     * Executes an edit on the task.
     *
     * @return The result of the execution
     */
    @Override
    protected CommandResult executeEdit() {
        try {
            Task toEdit = ModuleManager.getTask(moduleCode, categoryName, oldTaskDescription);
            if (newPriority < 0) {
                newPriority = toEdit.getPriority();
            }
            ModuleManager.retrieveList(moduleCode, categoryName)
                    .edit(toEdit, newTaskDescription, newDeadline, newPriority);
            return new CommandResult(MESSAGE_EDIT_TASK_SUCCESS);
        } catch (ModuleManager.ModuleNotFoundException e) {
            return new CommandResult(MESSAGE_MODULE_NOT_FOUND);
        } catch (CategoryManager.CategoryNotFoundException e) {
            return new CommandResult(MESSAGE_CATEGORY_NOT_FOUND);
        } catch (TaskManager.TaskNotFoundException e) {
            return new CommandResult(MESSAGE_TASK_NOT_FOUND);
        } catch (TaskManager.DuplicateTaskException e) {
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        }
    }

    /**
     * Executes the <b>Edit Task Command</b> to edit a <b>Task</b> with the <code>task description</code>
     * from the <b>Task List</b>.
     *
     * @return The <b>Command Result</b> of the execution
     * @see Task
     * @see TaskManager
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
        return executeEdit();
    }
}
