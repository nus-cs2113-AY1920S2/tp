package seedu.nuke.command;

import seedu.nuke.data.DataManager;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.ScreenShotManager;
import seedu.nuke.module.Module;

public abstract class Command {
    public static String COMMAND_WORD;
    protected ModuleManager moduleManager;
    protected DataManager dataManager;
    protected ScreenShotManager screenShotManager;
    protected static Module currentModule;

    public abstract CommandResult execute();

    public String toString() {
        return this.COMMAND_WORD;
    }

    /**
     * initialize some attributes of the command if needed.
     * @param moduleManager current module manager that manages all modules.
     * @param dataManager current data managers that manage all tasks.
     * @param screenShotManager screen shot manager that manages all screen shots.
     */
    public void setData(ModuleManager moduleManager, DataManager dataManager, ScreenShotManager screenShotManager) {
        this.moduleManager = moduleManager;
        this.dataManager = dataManager;
        this.screenShotManager = screenShotManager;
    }

    public static Module getCurrentModule() {
        return currentModule;
    }

    public static void setCurrentModule(Module currentModule) {
        Command.currentModule = currentModule;
    }

}
