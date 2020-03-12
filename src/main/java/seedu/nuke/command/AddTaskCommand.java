package seedu.nuke.command;

import seedu.nuke.data.ModuleManager;
import seedu.nuke.task.Task;
import seedu.nuke.module.Module;

import static seedu.nuke.util.Message.MESSAGE_TASK_ADDED;

public class AddTaskCommand extends TaskCommand{

    public static final String COMMAND_WORD = "addt";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " task description "+": Add a task to the module.";

    private final Task taskToAdd;
    private final Module moduleToAdd;

    public AddTaskCommand(Module module, Task task) {
        this.taskToAdd = task;
        this.moduleToAdd = module;
    }

    @Override
    public CommandResult execute() {
        //add the task to the module's task manager
        //todo add duplicated task check
        for (Module module: ModuleManager.getModuleList()
             ) {
            if (module.isSameModule(module)){
                module.getTaskManager().getTaskList().add(taskToAdd);
            }
        }
        //add the task to the data manager
        dataManager.getAllTasks().add(taskToAdd);
        return new CommandResult(MESSAGE_TASK_ADDED);
    }
}
