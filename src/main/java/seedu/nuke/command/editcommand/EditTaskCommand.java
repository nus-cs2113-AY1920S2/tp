package seedu.nuke.command.editcommand;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.CategoryManager;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.TaskManager;
import seedu.nuke.data.storage.StorageManager;
import seedu.nuke.directory.Directory;
import seedu.nuke.directory.DirectoryTraverser;
import seedu.nuke.directory.Task;
import seedu.nuke.exception.IncorrectDirectoryLevelException;
import seedu.nuke.util.DateTime;

import java.util.regex.Pattern;

import static seedu.nuke.parser.Parser.CATEGORY_PREFIX;
import static seedu.nuke.parser.Parser.DEADLINE_PREFIX;
import static seedu.nuke.parser.Parser.MODULE_PREFIX;
import static seedu.nuke.parser.Parser.PRIORITY_PREFIX;
import static seedu.nuke.parser.Parser.TASK_PREFIX;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_CATEGORY_NOT_FOUND;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_DUPLICATE_TASK;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_INCORRECT_DIRECTORY_LEVEL;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_TASK_NOT_FOUND;
import static seedu.nuke.util.Message.MESSAGE_EDIT_TASK_SUCCESS;
import static seedu.nuke.util.Message.MESSAGE_TASK_EXCEED_LIMIT;

/**
 * <h3>Edit Task Command</h3>
 * A <b>Command</b> to edit a <b>Task</b> in the <b>Task List</b>.
 * @see Command
 * @see Task
 */
public class EditTaskCommand extends EditCommand {
    public static final String COMMAND_WORD = "edt";
    public static final String FORMAT = COMMAND_WORD
            + " <task description> -m <module code> -c <category name>\n\t"
            + " { -t <new task description> -d <new deadline> -p <new priority> }";
    public static final String MESSAGE_USAGE = String.format(
            "%s - Edit the description, deadline and priority of a task\n"
            + "Format: %s\n"
            + "Example: edt read week 6 nites -m CS2113T -c Lecture -t read week 8 notes -d sun -p 1\n",
            COMMAND_WORD, FORMAT);
    public static final Pattern REGEX_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)*)"
            + "(?<moduleCode>(?:\\s+" + MODULE_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<categoryName>(?:\\s+" + CATEGORY_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<taskDescription>(?:\\s+" + TASK_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<priority>(?:\\s+" + PRIORITY_PREFIX + "\\s+\\S+)?)"
            + "(?<deadline>(?:\\s+" + DEADLINE_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<prioritySecond>(?:\\s+" + PRIORITY_PREFIX + "\\s+\\S+)?)"
            + "(?<invalid>.*)"
    );

    private String oldTaskDescription;
    private String moduleCode;
    private String categoryName;
    private String newTaskDescription;
    private DateTime newDeadline;
    private int newPriority;

    /**
     * Constructs the command to edit a task.
     *
     * @param oldTaskDescription
     *  The description of the task to be edited
     * @param moduleCode
     *  The module code of the module containing the task to be edited
     * @param categoryName
     *  The name of the category containing the task to be edited
     * @param newTaskDescription
     *  The new description of the task if any
     * @param newDeadline
     *  The new deadline of the task if any
     * @param newPriority
     *  The new priority of the task if any
     */
    public EditTaskCommand(String oldTaskDescription, String moduleCode, String categoryName,
                           String newTaskDescription, DateTime newDeadline, int newPriority) {
        this.oldTaskDescription = oldTaskDescription;
        this.moduleCode = moduleCode;
        this.categoryName = categoryName;
        this.newTaskDescription = newTaskDescription;
        this.newDeadline = newDeadline;
        this.newPriority = newPriority;
    }

    /**
     * Constructs the command to edit a task without a priority.
     *
     * @param oldTaskDescription
     *  The description of the task to be edited
     * @param moduleCode
     *  The module code of the module containing the task to be edited
     * @param categoryName
     *  The name of the category containing the task to be edited
     * @param newTaskDescription
     *  The new description of the task if any
     * @param newDeadline
     *  The new deadline of the task if any
     */
    public EditTaskCommand(String oldTaskDescription, String moduleCode, String categoryName,
                           String newTaskDescription, DateTime newDeadline) {
        this(oldTaskDescription, moduleCode, categoryName, newTaskDescription, newDeadline, -1);
    }

    /**
     * Fills in all missing attributes needed to edit the task.
     *
     * @param toEdit
     *  The task to be edited
     */
    private void fillAllAttributes(Task toEdit) {
        moduleCode = toEdit.getParent().getParent().getModuleCode();
        categoryName = toEdit.getParent().getCategoryName();
        if (newTaskDescription.isEmpty()) {
            newTaskDescription = toEdit.getDescription();
        }
        if (!newDeadline.isPresent()) {
            newDeadline = toEdit.getDeadline();
        }
        if (newPriority < 0) {
            newPriority = toEdit.getPriority();
        }
    }

    private boolean isExceedLengthLimit() {
        return newTaskDescription.length() > 25;
    }

    /**
     * Edits the task.
     *
     * @param toEdit
     *  The task to edit
     * @throws TaskManager.DuplicateTaskException
     *  If the new task description is duplicated
     */
    @Override
    protected void edit(Directory toEdit) throws TaskManager.DuplicateTaskException {
        ((Task) toEdit).getParent().getTasks().edit((Task) toEdit, newTaskDescription, newDeadline, newPriority);
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
        if (isExceedLengthLimit()) {
            return new CommandResult(MESSAGE_TASK_EXCEED_LIMIT);
        }
        try {
            Task toEdit = DirectoryTraverser.getTaskDirectory(moduleCode, categoryName, oldTaskDescription);
            fillAllAttributes(toEdit);
            edit(toEdit);
            StorageManager.setIsSave();
            return new CommandResult(MESSAGE_EDIT_TASK_SUCCESS);
        } catch (ModuleManager.ModuleNotFoundException e) {
            return new CommandResult(MESSAGE_MODULE_NOT_FOUND);
        } catch (CategoryManager.CategoryNotFoundException e) {
            return new CommandResult(MESSAGE_CATEGORY_NOT_FOUND);
        } catch (TaskManager.TaskNotFoundException e) {
            return new CommandResult(MESSAGE_TASK_NOT_FOUND);
        } catch (TaskManager.DuplicateTaskException e) {
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        } catch (IncorrectDirectoryLevelException e) {
            return new CommandResult(MESSAGE_INCORRECT_DIRECTORY_LEVEL);
        }
    }
}
