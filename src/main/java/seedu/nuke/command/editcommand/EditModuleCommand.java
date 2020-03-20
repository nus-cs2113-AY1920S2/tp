package seedu.nuke.command.editcommand;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.Module;
import seedu.nuke.exception.ModuleNotProvidedException;

import java.util.regex.Pattern;

import static seedu.nuke.parser.Parser.MODULE_CODE_PREFIX;
import static seedu.nuke.util.ExceptionMessage.*;
import static seedu.nuke.util.Message.MESSAGE_EDIT_MODULE_SUCCESS;

/**
 * <h3>Edit Module Command</h3>
 * A <b>Command</b> to edit a <b>Module</b> in the <b>Module List</b>.
 * @see Command
 * @see Module
 */
public class EditModuleCommand extends EditCommand {
    public static final String COMMAND_WORD = "edm";
    public static final String FORMAT = COMMAND_WORD + " <new module code> -m <old module code>";
    public static final Pattern[] REGEX_FORMATS = {
            Pattern.compile("(?<identifier>^\\s*([^-]+))"),
            Pattern.compile("(?<moduleCode>(?:\\s+" + MODULE_CODE_PREFIX + " [^-]+))"),
            Pattern.compile("(?<invalid>(?:\\s+-(?:[^mp].*|[m]\\S+)))")
    };

    private String oldModuleCode;
    private String newModuleCode;

    public EditModuleCommand(String oldModuleCode, String newModuleCode) {
        this.oldModuleCode = oldModuleCode;
        this.newModuleCode = newModuleCode;
    }

    /**
     * Executes an edit on the module.
     *
     * @return
     *  The result of the execution
     */
    @Override
    protected CommandResult executeEdit() {
        try {
            Module toEdit = ModuleManager.getModule(oldModuleCode);
            ModuleManager.edit(toEdit, newModuleCode);

            return new CommandResult(MESSAGE_EDIT_MODULE_SUCCESS);
        }  catch (ModuleNotProvidedException e) {
            return new CommandResult(MESSAGE_MODULE_NOT_PROVIDED);
        } catch (ModuleManager.ModuleNotFoundException e) {
            return new CommandResult(MESSAGE_MODULE_NOT_FOUND);
        } catch (ModuleManager.DuplicateModuleException e) {
            return new CommandResult(MESSAGE_DUPLICATE_MODULE);
        }
    }

    /**
     * Executes the <b>Edit Module Command</b> to edit a <b>Module</b> with the <code>module code</code>
     * from the <b>Module List</b>.
     *
     * @return The <b>Command Result</b> of the execution
     * @see Module
     * @see ModuleManager
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
        return executeEdit();
    }
}
