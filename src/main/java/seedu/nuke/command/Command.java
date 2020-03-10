package seedu.nuke.command;

import seedu.nuke.task.Task;

public abstract class Command {
    public static String COMMAND_WORD;
    public abstract CommandResult execute () ;

    public String toString(){
        return this.COMMAND_WORD;
    }
}
