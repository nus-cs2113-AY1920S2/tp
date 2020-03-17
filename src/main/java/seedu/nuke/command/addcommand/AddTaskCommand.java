package seedu.nuke.command.addcommand;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.command.TaskCommand;
import seedu.nuke.directory.Module;
import seedu.nuke.directory.Task;

import static seedu.nuke.util.Message.MESSAGE_TASK_ADDED;

public class AddTaskCommand extends TaskCommand {

    public static final String COMMAND_WORD = "addt";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " task description " + ": Add a task to the module.";

    private final Task taskToAdd;

    public AddTaskCommand(Task task) {
        this.taskToAdd = task;
    }

    @Override
    public CommandResult execute() {
        Module module = (Module)Command.getCurrentDirectory();
        moduleManager.addTaskToModule(module.getTaskManager(), taskToAdd);
        //dataManager.addTask(taskToAdd);
        return new CommandResult(MESSAGE_TASK_ADDED);
    }
}
