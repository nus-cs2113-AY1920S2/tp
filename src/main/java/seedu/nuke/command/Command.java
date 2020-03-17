package seedu.nuke.command;

import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.Directory;
import seedu.nuke.directory.Module;

public abstract class Command {
    public static String COMMAND_WORD;
//    protected ModuleManager moduleManager;
    protected static Directory currentDirectory;

    public abstract CommandResult execute();

    public String toString() {
        return this.COMMAND_WORD;
    }

//    /**
//     * initialize some attributes of the command if needed.
//     * @param moduleManager current module manager that manages all modules.
//     */
//    public void setData(ModuleManager moduleManager) {
//        this.moduleManager = moduleManager;
//    }

    public static Directory getCurrentDirectory() {
        return currentDirectory;
    }

    public static void setCurrentDirectory(Directory currentDirectory) {
        Command.currentDirectory = currentDirectory;
    }
}

