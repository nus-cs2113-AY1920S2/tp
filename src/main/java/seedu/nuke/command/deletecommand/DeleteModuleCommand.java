package seedu.nuke.command.deletecommand;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.exception.ModuleNotFoundException;
import seedu.nuke.directory.Module;

import java.util.regex.Pattern;

import static seedu.nuke.parser.Parser.EXACT_FLAG;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.nuke.util.Message.messageDeleteModuleSuccess;

public class DeleteModuleCommand extends Command {
    public static final String COMMAND_WORD = "delm";
    public static final String MESSAGE_USAGE = "delm <module code>";
    public static final Pattern[] REGEX_FORMATS = {
            Pattern.compile("(?<identifier>^\\s*([^-]+))"),
            Pattern.compile("(?<exact>(?:" + EXACT_FLAG + ")?)"),
            Pattern.compile("(?<invalid>(?:-(?:[^e].*|[e]\\S+)))")
    };

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
