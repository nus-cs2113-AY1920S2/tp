package seedu.nuke.command.editcommand;

import seedu.nuke.command.CommandResult;
import seedu.nuke.data.CategoryManager;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.TaskManager;
import seedu.nuke.directory.Task;
import seedu.nuke.exception.IncorrectDirectoryLevelException;

import java.util.regex.Pattern;

import static seedu.nuke.directory.DirectoryTraverser.getBaseCategory;
import static seedu.nuke.directory.DirectoryTraverser.getBaseModule;
import static seedu.nuke.directory.DirectoryTraverser.getBaseTask;
import static seedu.nuke.parser.Parser.CATEGORY_NAME_PREFIX;
import static seedu.nuke.parser.Parser.MODULE_CODE_PREFIX;
import static seedu.nuke.parser.Parser.TASK_DESCRIPTION_PREFIX;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_CATEGORY_NOT_FOUND;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_DUPLICATE_TASK;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_INCORRECT_DIRECTORY_LEVEL;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_TASK_NOT_FOUND;
import static seedu.nuke.util.Message.MESSAGE_EDIT_TASK_SUCCESS;

public class MarkAsDoneCommand extends EditCommand {
    public static final String COMMAND_WORD = "done";
    public static final String FORMAT = COMMAND_WORD
            + " -m <module code> -c <category name>"
            + " -t <task description>";
    public static final Pattern REGEX_FORMAT = Pattern.compile(
            "(?<moduleCode>(?:\\s+" + MODULE_CODE_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
                    + "(?<categoryName>(?:\\s+" + CATEGORY_NAME_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
                    + "(?<taskDescription>(?:\\s+" + TASK_DESCRIPTION_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
                    + "(?<invalid>(?:\\s+-.*)*)"
    );
    private String taskDescription;
    private String moduleCode;
    private String categoryName;

    /**
     * Constructs the command to mark a task as done.
     *
     * @param taskDescription The description of the task to be marked as done.
     * @param moduleCode The module code of the module containing the task to be marked as done.
     * @param categoryName The name of the category containing the task to be marked as done.
     */
    public MarkAsDoneCommand(String taskDescription, String moduleCode, String categoryName) {
        this.taskDescription = taskDescription;
        this.moduleCode = moduleCode;
        this.categoryName = categoryName;
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
        if (moduleCode.isEmpty()) {
            if (categoryName.isEmpty()) {
                if (taskDescription.isEmpty()) {
                    return getBaseTask();
                }
                return getBaseCategory().getTasks().getTask(taskDescription);
            }
            if (taskDescription.isEmpty()) {
                if (!getBaseCategory().isSameCategory(categoryName)) {
                    throw new IncorrectDirectoryLevelException();
                }
                return getBaseTask();
            }
            return getBaseModule().getCategories().getTask(categoryName, taskDescription);
        }

        if (categoryName.isEmpty()) {
            if (!getBaseCategory().isSameCategory(categoryName)) {
                throw new IncorrectDirectoryLevelException();
            }
            if (taskDescription.isEmpty()) {
                if (!getBaseTask().isSameTask(taskDescription)) {
                    throw new IncorrectDirectoryLevelException();
                }
                return getBaseTask();
            }
            return getBaseCategory().getTasks().getTask(taskDescription);
        }
        if (taskDescription.isEmpty()) {
            if (!getBaseCategory().isSameCategory(categoryName) && !getBaseTask().isSameTask(taskDescription)) {
                throw new IncorrectDirectoryLevelException();
            }
            return getBaseTask();
        }
        return ModuleManager.getTask(moduleCode, categoryName, taskDescription);
    }

    @Override
    protected CommandResult executeEdit() {
        try {
            Task toMarkAsDone = getBaseTaskDirectory();
            toMarkAsDone.setDone(true);
            return new CommandResult(MESSAGE_EDIT_TASK_SUCCESS);
        } catch (ModuleManager.ModuleNotFoundException e) {
            return new CommandResult(MESSAGE_MODULE_NOT_FOUND);
        } catch (CategoryManager.CategoryNotFoundException e) {
            return new CommandResult(MESSAGE_CATEGORY_NOT_FOUND);
        } catch (TaskManager.TaskNotFoundException e) {
            return new CommandResult(MESSAGE_TASK_NOT_FOUND);
        } catch (IncorrectDirectoryLevelException e) {
            return new CommandResult(MESSAGE_INCORRECT_DIRECTORY_LEVEL);
        }
    }

    @Override
    public CommandResult execute() {
        return executeEdit();
    }
}
