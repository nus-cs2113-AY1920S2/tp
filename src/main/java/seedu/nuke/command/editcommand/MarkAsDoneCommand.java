package seedu.nuke.command.editcommand;

import seedu.nuke.command.CommandResult;
import seedu.nuke.data.CategoryManager;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.TaskManager;
import seedu.nuke.data.storage.StorageManager;
import seedu.nuke.directory.Directory;
import seedu.nuke.directory.DirectoryTraverser;
import seedu.nuke.directory.Task;
import seedu.nuke.exception.IncorrectDirectoryLevelException;

import java.util.regex.Pattern;

import static seedu.nuke.parser.Parser.CATEGORY_PREFIX;
import static seedu.nuke.parser.Parser.MODULE_PREFIX;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_CATEGORY_NOT_FOUND;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_INCORRECT_DIRECTORY_LEVEL;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_TASK_NOT_FOUND;
import static seedu.nuke.util.Message.MESSAGE_ALREADY_DONE_TASK;
import static seedu.nuke.util.Message.MESSAGE_DONE_TASK;

public class MarkAsDoneCommand extends EditCommand {
    public static final String COMMAND_WORD = "done";
    public static final String FORMAT = COMMAND_WORD + " <task description> -m <module code> -c <category name>";
    public static final String MESSAGE_USAGE = String.format(
            "%s - Mark a task as done\n"
            + "Format: %s\n"
            + "Example: done review PR -m CS2113T -c Project\n",
            COMMAND_WORD, FORMAT);
    public static final Pattern REGEX_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)*)"
            + "(?<moduleCode>(?:\\s+" + MODULE_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<categoryName>(?:\\s+" + CATEGORY_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<invalid>(?:\\s+-.*)*)"
    );

    private String moduleCode;
    private String categoryName;
    private String taskDescription;

    /**
     * Constructs the command to mark a task as done.
     *
     * @param moduleCode
     *  The module code of the module containing the task to be marked as done.
     * @param categoryName
     *  The name of the category containing the task to be marked as done.
     * @param taskDescription
     *  The description of the task to be marked as done.
     */
    public MarkAsDoneCommand(String moduleCode, String categoryName, String taskDescription) {
        this.moduleCode = moduleCode;
        this.categoryName = categoryName;
        this.taskDescription = taskDescription;
    }

    /**
     * Marks the task as done.
     *
     * @param toMarkAsDone
     *  The task to mark as done
     */
    @Override
    protected void edit(Directory toMarkAsDone) {
        ((Task) toMarkAsDone).setDone(true);
    }

    @Override
    public CommandResult execute() {
        try {
            Task toMarkAsDone = DirectoryTraverser.getTaskDirectory(moduleCode, categoryName, taskDescription);
            if (toMarkAsDone.isDone()) {
                return new CommandResult(MESSAGE_ALREADY_DONE_TASK);
            }
            edit(toMarkAsDone);
            assert toMarkAsDone.isDone() : "How can this be?";
            StorageManager.setIsSave();
            return new CommandResult(MESSAGE_DONE_TASK);
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
}
