package seedu.nuke.command;

import seedu.nuke.data.DataManager;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.ScreenShotManager;
import seedu.nuke.module.Module;

public abstract class Command {
    public static String COMMAND_WORD;
    protected ModuleManager moduleManager;
    protected static Module currentModule;

    public abstract CommandResult execute();

    public String toString() {
        return this.COMMAND_WORD;
    }

    /**
     * initialize some attributes of the command if needed.
     * @param moduleManager current module manager that manages all modules.
     */
    public void setData(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }

    public static Module getCurrentModule() {
        return currentModule;
    }

    public static void setCurrentModule(Module currentModule) {
        Command.currentModule = currentModule;
    }
}
