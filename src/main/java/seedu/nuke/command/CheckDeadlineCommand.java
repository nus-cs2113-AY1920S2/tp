package seedu.nuke.command;

import seedu.nuke.data.DataManager;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.TaskManager;
import seedu.nuke.task.Task;
import seedu.nuke.ui.TextUi;

import java.util.ArrayList;
import java.util.Collections;

import static seedu.nuke.util.Message.MESSAGE_NO_TASK_IN_LIST;
import static seedu.nuke.util.Message.MESSAGE_TASK_SUCCESSFULY_LIST;

public class CheckDeadlineCommand extends Command{
    protected DataManager dataManager;

    public static final String COMMAND_WORD = "lst";
    public static final String MESSAGE_USAGE = COMMAND_WORD;

    @Override
    /**
     * module level
     */
    public CommandResult execute() {
        //get the large task list
        dataManager = new DataManager(moduleManager);
        if (dataManager.getAllTasks() == null){
            return new CommandResult(MESSAGE_NO_TASK_IN_LIST);
        }
        for (Task task: dataManager.getAllTasks()
             ) {
            System.out.println(String.format(task.getDescription()+" deadline:"+task.getDeadline()));
        }
        return new CommandResult(String.format(MESSAGE_TASK_SUCCESSFULY_LIST, dataManager.getAllTasks().size()));
    }
}
