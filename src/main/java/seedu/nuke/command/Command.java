package seedu.nuke.command;

public abstract class Command {
    // public static String COMMAND_WORD;
    //protected ModuleManager moduleManager;
    //protected static Directory currentDirectory;

    /**
     * Executes the command.
     *
     * @return
     *  The result of the execution
     */
    public abstract CommandResult execute();


    //public String toString() {
    //    return this.COMMAND_WORD;
    //}
    //
    ///**
    // * initialize some attributes of the command if needed.
    // * @param moduleManager current module manager that manages all modules.
    // */
    //public void setData(ModuleManager moduleManager) {
    //    this.moduleManager = moduleManager;
    //}
    //
    //public static Directory getCurrentDirectory() {
    //    return currentDirectory;
    //}
    //
    //public static void setCurrentDirectory(Directory currentDirectory) {
    //    Command.currentDirectory = currentDirectory;
    //}
}

