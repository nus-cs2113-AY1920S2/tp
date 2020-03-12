package seedu.nuke.command;

import seedu.nuke.module.Module;

import static seedu.nuke.util.Message.MESSAGE_MODULE_CHANGE_SUCCESSFUL;

public class ChangeModuleCommand extends Command {

    public static final String COMMAND_WORD = "cd";
    public static final String MESSAGE_USAGE = COMMAND_WORD+ " <module code>";

    public ChangeModuleCommand(Module destinatedModule) {
        currentModule = destinatedModule;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_MODULE_CHANGE_SUCCESSFUL);
    }
}
