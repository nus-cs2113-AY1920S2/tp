package seedu.nuke.command;

import seedu.nuke.data.DataManager;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.TaskManager;
import seedu.nuke.task.Task;
import seedu.nuke.ui.TextUi;

import java.util.ArrayList;
import java.util.Collections;

public class CheckDeadlineCommand extends Command{
    protected DataManager dataManager;

    public static final String COMMAND_WORD = "ck";
    public static final String MESSAGE_USAGE = COMMAND_WORD;

    @Override
    /**
     * module level
     */
    public CommandResult execute() {
        //get the large task list
        dataManager = new DataManager(moduleManager);
        for (Task task: dataManager.getAllTasks()
             ) {
            System.out.println(String.format(task.getDescription()+" deadline:"+task.getDeadline()));
        }
        return new CommandResult("");
    }
}
