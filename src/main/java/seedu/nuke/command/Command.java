package seedu.nuke.command;

import seedu.nuke.data.DataManager;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.TaskManager;
import seedu.nuke.module.Module;
import seedu.nuke.task.Task;
import seedu.nuke.ui.Ui;

public abstract class Command {
    public static String COMMAND_WORD;
    protected ModuleManager moduleManager;
    protected DataManager dataManager;
    protected Module currentModule;

    public abstract CommandResult execute () ;
    public String toString(){
        return this.COMMAND_WORD;
    }

    public void setData(ModuleManager moduleManager, DataManager dataManager) {
        this.moduleManager = moduleManager;
        this.dataManager = dataManager;
    }
}
