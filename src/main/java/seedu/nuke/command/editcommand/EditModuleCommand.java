package seedu.nuke.command.editcommand;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.Module;
import seedu.nuke.directory.Root;
import seedu.nuke.exception.IncorrectDirectoryLevelException;
import seedu.nuke.exception.ModuleNotProvidedException;

import java.util.regex.Pattern;

import static seedu.nuke.directory.DirectoryTraverser.getBaseModule;
import static seedu.nuke.parser.Parser.MODULE_CODE_PREFIX;
import static seedu.nuke.parser.Parser.PRIORITY_PREFIX;
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
    public static final Pattern REGEX_FORMAT = Pattern.compile(
            "(?<identifier>(?:(?:\\s+[^-\\s]\\S*)+|^[^-\\s]\\S*)?)" +
            "(?<moduleCode>(?:\\s+" + MODULE_CODE_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)" +
            "(?<invalid>(?:\\s+-.*)*)"
    );
    public static final Pattern[] REGEX_FORMATS = {
            Pattern.compile("(?<identifier>^\\s*([^-]+))"),
            Pattern.compile("(?<moduleCode>(?:\\s+" + MODULE_CODE_PREFIX + " [^-]+))"),
            Pattern.compile("(?<invalid>(?:\\s+-(?:[^mp].*|[m]\\S+)))")
    };

    private String oldModuleCode;
    private String newModuleCode;

    public EditModuleCommand(String oldModuleCode, String newModuleCode) {
        this.oldModuleCode = oldModuleCode;
        this.newModuleCode = newModuleCode.toUpperCase();
    }

    /**
     * Returns the base module level directory of the current Directory
     *
     * @return
     *  The base module level directory of the current Directory
     * @throws IncorrectDirectoryLevelException
     *  If the current directory is too low to obtain the module level directory
     * @throws ModuleManager.ModuleNotFoundException
     *  If the module with the module code is not found in the Module List
     */
    protected Module getBaseModuleDirectory()
            throws IncorrectDirectoryLevelException, ModuleManager.ModuleNotFoundException {
        if (oldModuleCode.isEmpty()) {
            return getBaseModule();
        } else {
            return ModuleManager.getModule(oldModuleCode);
        }
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
            Module toEdit = getBaseModuleDirectory();
            ModuleManager.edit(toEdit, newModuleCode);

            return new CommandResult(MESSAGE_EDIT_MODULE_SUCCESS);
        }  catch (ModuleNotProvidedException e) {
            return new CommandResult(MESSAGE_MODULE_NOT_PROVIDED);
        } catch (ModuleManager.ModuleNotFoundException e) {
            return new CommandResult(MESSAGE_MODULE_NOT_FOUND);
        } catch (ModuleManager.DuplicateModuleException e) {
            return new CommandResult(MESSAGE_DUPLICATE_MODULE);
        } catch (IncorrectDirectoryLevelException e) {
            return new CommandResult(MESSAGE_INCORRECT_DIRECTORY_LEVEL);
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
