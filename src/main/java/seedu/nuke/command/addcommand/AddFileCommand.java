package seedu.nuke.command.addcommand;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.CategoryManager;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.TaskFileManager;
import seedu.nuke.data.TaskManager;
import seedu.nuke.directory.DirectoryTraverser;
import seedu.nuke.directory.Task;
import seedu.nuke.directory.TaskFile;
import seedu.nuke.exception.IncorrectDirectoryLevelException;

import java.util.regex.Pattern;

import static seedu.nuke.parser.Parser.*;
import static seedu.nuke.util.ExceptionMessage.*;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_INCORRECT_DIRECTORY_LEVEL;
import static seedu.nuke.util.Message.messageAddFileSuccess;
import static seedu.nuke.util.Message.messageAddTaskSuccess;

/**
 * <h3>Add File Command</h3>
 * A <b>Command</b> to add a <b>File</b> to the <b>File List</b>.
 *
 * @see Command
 * @see TaskFile
 */
public class AddFileCommand extends AddCommand {
    public static final String COMMAND_WORD = "addf";
    public static final String FORMAT = COMMAND_WORD
            + " <file name> -m <module code> -c <category name> -t <task description> -f <file path>";
    public static final Pattern REGEX_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)+)"
            + "(?<moduleCode>(?:\\s+" + MODULE_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<categoryName>(?:\\s+" + CATEGORY_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<taskDescription>(?:\\s+" + TASK_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<filePath>(?:\\s+" + FILE_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<invalid>.*)"
    );

    private String moduleCode;
    private String categoryName;
    private String taskDescription;
    private String fileName;
    private String filePath;

    /**
     * Constructs the command to add a file.
     *
     * @param moduleCode
     *  The module code of the module that has the category to add the task
     * @param categoryName
     *  The name of the category to add the task
     * @param taskDescription
     *  The priority of the category
     * @param fileName
     *  The name of the file
     * @param filePath
     *  The path to the file
     */
    public AddFileCommand(String moduleCode, String categoryName, String taskDescription,
                          String fileName, String filePath) {
        this.moduleCode = moduleCode;
        this.categoryName = categoryName;
        this.taskDescription = taskDescription;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    /**
     * Constructs the command to add a file without a path.
     *
     * @param moduleCode
     *  The module code of the module that has the category to add the task
     * @param categoryName
     *  The name of the category to add the task
     * @param taskDescription
     *  The priority of the category
     * @param fileName
     *  The name of the file
     */
    public AddFileCommand(String moduleCode, String categoryName, String taskDescription, String fileName) {
        this(moduleCode, categoryName, taskDescription, fileName, null);
    }

    /**
     * Executes the <b>Add File Command</b> to add a <b>File</b> into the <b>File List</b>.
     *
     * @return The <b>Command Result</b> of the execution
     * @see TaskFile
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
        try {
            Task parentTask = DirectoryTraverser.getTaskDirectory(moduleCode, categoryName, taskDescription);
            TaskFile toAdd = new TaskFile(parentTask, fileName, filePath);
            parentTask.getFiles().add(toAdd);
            return new CommandResult(messageAddFileSuccess(fileName));
        } catch (ModuleManager.ModuleNotFoundException e) {
            return new CommandResult(MESSAGE_MODULE_NOT_FOUND);
        } catch (CategoryManager.CategoryNotFoundException e) {
            return new CommandResult(MESSAGE_CATEGORY_NOT_FOUND);
        } catch (TaskManager.TaskNotFoundException e) {
            return new CommandResult(MESSAGE_TASK_NOT_FOUND);
        } catch (TaskFileManager.DuplicateTaskFileException e) {
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        } catch (IncorrectDirectoryLevelException e) {
            return new CommandResult(MESSAGE_INCORRECT_DIRECTORY_LEVEL);
        }
    }
}
