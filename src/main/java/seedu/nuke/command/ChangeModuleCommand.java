package seedu.nuke.command;

import seedu.nuke.directory.Module;

import static seedu.nuke.util.Message.MESSAGE_MODULE_CHANGE_SUCCESSFUL;

public class ChangeModuleCommand extends Command {

    public static final String COMMAND_WORD = "cd";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " <module code>";
    private Module moduleToChange;

    public ChangeModuleCommand(Module destinatedModule) {
        this.moduleToChange = destinatedModule;
    }

    @Override
    public CommandResult execute() {
        //context switch
        setCurrentDirectory(this.moduleToChange);
        return new CommandResult(MESSAGE_MODULE_CHANGE_SUCCESSFUL);
    }
}
