package seedu.nuke.command;

import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.TaskManager;
import seedu.nuke.task.Task;

public abstract class Command {
    public static String COMMAND_WORD;
    protected ModuleManager moduleManager;

    public abstract CommandResult execute () ;
    public String toString(){
        return this.COMMAND_WORD;
    }

    public void setData(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }
}
