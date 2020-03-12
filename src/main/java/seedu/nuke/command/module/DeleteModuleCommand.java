package seedu.nuke.command.module;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.module.Module;
import seedu.nuke.data.module.ModuleList;

import static seedu.nuke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.nuke.util.Message.MESSAGE_DELETE_MODULE_SUCCESS;

public class DeleteModuleCommand extends Command {
    public static final String COMMAND_WORD = "delm";
    public static final String FORMAT = COMMAND_WORD + " <module code>";

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
     * @see ModuleList
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
        try {
            Module deletedModule = ModuleList.delete(moduleCode);
            return new CommandResult(MESSAGE_DELETE_MODULE_SUCCESS(deletedModule.getModuleCode(), deletedModule.getTitle()));
        } catch (ModuleList.ModuleNotFoundException e) {
            return new CommandResult(MESSAGE_MODULE_NOT_FOUND);
        }
    }
}
