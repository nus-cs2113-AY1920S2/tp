package seedu.nuke.command.task;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.category.CategoryList;
import seedu.nuke.data.module.ModuleList;
import seedu.nuke.data.task.Task;
import seedu.nuke.data.task.TaskList;

import static seedu.nuke.util.ExceptionMessage.*;
import static seedu.nuke.util.Message.MESSAGE_DELETE_CATEGORY_SUCCESS;

public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "delt";
    public static final String FORMAT = COMMAND_WORD + " <task description> -m <module code> -c <category name>";

    private String moduleCode;
    private String categoryName;
    private String description;

    public DeleteTaskCommand(String moduleCode, String categoryName, String description) {
        this.moduleCode = moduleCode;
        this.categoryName = categoryName;
        this.description = description;
    }

    /**
     * Executes the <b>Delete Task Command</b> to delete a <b>Task</b> with the <code>description/code> from the
     * <b>Task List</b>.
     *
     * @return The <b>Command Result</b> of the execution
     * @see Task
     * @see TaskList
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
        try {
            Task deletedTask = ModuleList.filterExact(moduleCode, categoryName).delete(description);
            return new CommandResult(MESSAGE_DELETE_CATEGORY_SUCCESS(deletedTask.getDescription()));
        } catch (ModuleList.ModuleNotFoundException e) {
            return new CommandResult(MESSAGE_MODULE_NOT_FOUND);
        } catch (CategoryList.CategoryNotFoundException e) {
            return new CommandResult(MESSAGE_CATEGORY_NOT_FOUND);
        } catch (TaskList.TaskNotFoundException e) {
            return new CommandResult(MESSAGE_TASK_NOT_FOUND);
        }
    }
}
