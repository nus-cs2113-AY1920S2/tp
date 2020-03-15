package seedu.nuke.command;

import seedu.nuke.module.Module;

import java.util.ArrayList;

import static seedu.nuke.util.Message.MESSAGE_SHOW_MODULES;

public class ListModuleCommand extends Command {
    public static final String COMMAND_WORD = "lsm";
    public static final String MESSAGE_USAGE = COMMAND_WORD;

    @Override
    public CommandResult execute() {
        ArrayList<String> modules = new ArrayList<>();
        for(Module module: moduleManager.getModuleList()) {
            modules.add(module.getModuleCode() + " " + module.getTitle() + " " + module.getDescription());
        }
        return new CommandResult(MESSAGE_SHOW_MODULES, true, modules);
    }
}
