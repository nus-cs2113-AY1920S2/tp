package seedu.nuke.command.editcommand;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.CategoryManager;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.TaskManager;
import seedu.nuke.directory.Task;
import seedu.nuke.exception.IncorrectDirectoryLevelException;
import seedu.nuke.util.DateTime;

import java.util.regex.Pattern;

import static seedu.nuke.directory.DirectoryTraverser.getBaseCategory;
import static seedu.nuke.directory.DirectoryTraverser.getBaseModule;
import static seedu.nuke.directory.DirectoryTraverser.getBaseTask;
import static seedu.nuke.parser.Parser.CATEGORY_NAME_PREFIX;
import static seedu.nuke.parser.Parser.DEADLINE_PREFIX;
import static seedu.nuke.parser.Parser.MODULE_CODE_PREFIX;
import static seedu.nuke.parser.Parser.PRIORITY_PREFIX;
import static seedu.nuke.parser.Parser.TASK_DESCRIPTION_PREFIX;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_CATEGORY_NOT_FOUND;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_DUPLICATE_TASK;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_INCORRECT_DIRECTORY_LEVEL;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_TASK_NOT_FOUND;
import static seedu.nuke.util.Message.MESSAGE_EDIT_TASK_SUCCESS;

/**
 * <h3>Edit Task Command</h3>
 * A <b>Command</b> to edit a <b>Task</b> in the <b>Task List</b>.
 * @see Command
 * @see Task
 */
public class EditTaskCommand extends EditCommand {
    public static final String COMMAND_WORD = "edt";
    public static final String FORMAT = COMMAND_WORD
            + " <old task description> -m <module code> -c <category name>"
            + " -t <new task description> -d <new deadline> -p <new priority>";
    public static final Pattern REGEX_FORMAT = Pattern.compile(
            "(?<identifier>(?:(?:\\s+[^-\\s]\\S*)+|^[^-\\s]\\S*)?)"
            + "(?<moduleCode>(?:\\s+" + MODULE_CODE_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
            + "(?<categoryName>(?:\\s+" + CATEGORY_NAME_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
            + "(?<taskDescription>(?:\\s+" + TASK_DESCRIPTION_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
            + "(?<optional>(?:\\s+-[dp](?:\\s+[^-\\s]\\S*)+)*)"
            + "(?<invalid>(?:\\s+-.*)*)"
    );
    public static final Pattern REGEX_OPTIONAL_FORMAT = Pattern.compile(
            "(?<deadline>(?:\\s+" + DEADLINE_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
            + "(?<priority>(?:\\s+" + PRIORITY_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
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
        if (newDeadline == null) {
            newDeadline = toEdit.getDeadline();
        }
        if (newPriority < 0) {
            newPriority = toEdit.getPriority();
        }
    }

    /**
     * Returns the base category level directory of the current Directory.
     *
     * @return
     *  The base category level directory of the current Directory
     * @throws IncorrectDirectoryLevelException
     *  If the current directory is too low to obtain the category level directory
     * @throws ModuleManager.ModuleNotFoundException
     *  If the module with the module code is not found in the Module List
     * @throws CategoryManager.CategoryNotFoundException
     *  If the category with the category name is not found in the Category List
     * @throws TaskManager.TaskNotFoundException
     *  If teh task with the task description is not found in the Task List
     */
    protected Task getBaseTaskDirectory()
            throws IncorrectDirectoryLevelException, ModuleManager.ModuleNotFoundException,
            CategoryManager.CategoryNotFoundException, TaskManager.TaskNotFoundException {
        //if (moduleCode.isEmpty()) {
        //    if (categoryName.isEmpty()) {
        //        if (oldTaskDescription.isEmpty()) {
        //            return getBaseTask();
        //        }
        //        return getBaseCategory().getTasks().getTask(oldTaskDescription);
        //    }
        //    if (oldTaskDescription.isEmpty()) {
        //        if (!getBaseCategory().isSameCategory(categoryName)) {
        //            throw new IncorrectDirectoryLevelException();
        //        }
        //        return getBaseTask();
        //    }
        //    return getBaseModule().getCategories().getTask(categoryName, oldTaskDescription);
        //}
        //
        //if (categoryName.isEmpty()) {
        //    if (!getBaseCategory().isSameCategory(categoryName)) {
        //        throw new IncorrectDirectoryLevelException();
        //    }
        //    if (oldTaskDescription.isEmpty()) {
        //        if (!getBaseTask().isSameTask(oldTaskDescription)) {
        //            throw new IncorrectDirectoryLevelException();
        //        }
        //        return getBaseTask();
        //    }
        //    return getBaseCategory().getTasks().getTask(oldTaskDescription);
        //}
        //if (oldTaskDescription.isEmpty()) {
        //    if (!getBaseCategory().isSameCategory(categoryName) && !getBaseTask().isSameTask(oldTaskDescription)) {
        //        throw new IncorrectDirectoryLevelException();
        //    }
        //    return getBaseTask();
        //}
        if (moduleCode.isEmpty()) {
            moduleCode = getBaseModule().getModuleCode();
        }
        if (categoryName.isEmpty()) {
            if (!getBaseModule().isSameModule(moduleCode)) {
                throw new IncorrectDirectoryLevelException();
            }
            categoryName = getBaseCategory().getCategoryName();
        }
        if (oldTaskDescription.isEmpty()) {
            if (!getBaseModule().isSameModule(moduleCode) && !getBaseCategory().isSameCategory(categoryName)) {
                throw new IncorrectDirectoryLevelException();
            }
            oldTaskDescription = getBaseTask().getDescription();
        }
        return ModuleManager.getTask(moduleCode, categoryName, oldTaskDescription);
    }

    /**
     * Executes an edit on the task.
     *
     * @return The result of the execution
     */
    @Override
    protected CommandResult executeEdit() {
        try {
            Task toEdit = getBaseTaskDirectory();
            fillAllAttributes(toEdit);
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
        } catch (IncorrectDirectoryLevelException e) {
            return new CommandResult(MESSAGE_INCORRECT_DIRECTORY_LEVEL);
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
