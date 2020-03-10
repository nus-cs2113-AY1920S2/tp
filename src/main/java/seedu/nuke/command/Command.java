package seedu.nuke.command;

import seedu.nuke.data.ModuleManager;
import seedu.nuke.task.Task;
import seedu.nuke.ui.Ui;

public abstract class Command {
    public static String COMMAND_WORD;
    public abstract CommandResult execute (ModuleManager moduleManager, Ui ui) ;

    public String toString(){
        return this.COMMAND_WORD;
    }
}
