package seedu.nuke.command;

import seedu.nuke.exception.ModuleNotFoundException;
import seedu.nuke.module.Module;

import static seedu.nuke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.nuke.util.Message.messageDeleteModuleSuccess;

public class DeleteModuleCommand extends Command {
    public static final String COMMAND_WORD = "delm";
    public static final String MESSAGE_USAGE = "delm <module code>";

    private String moduleCode;

    public DeleteModuleCommand(String moduleCode) {
        this.moduleCode = moduleCode.toUpperCase();
    }

    /**
     * Executes the <b>Delete Module Command</b> to delete a <b>Module</b> with the <code>module code</code> from the
     * <b>Module List</b>.
     *
     * @return The <b>Command Result</b> of the execution
     * @see Module
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
        try {
            Module deletedModule = moduleManager.delete(moduleCode);
            return new CommandResult(
                    messageDeleteModuleSuccess(deletedModule.getModuleCode(), deletedModule.getTitle()));
        } catch (ModuleNotFoundException e) {
            return new CommandResult(MESSAGE_MODULE_NOT_FOUND);
        }
    }
}
