package seedu.nuke.command.deletecommand;

import seedu.nuke.Executor;
import seedu.nuke.command.CommandResult;
import seedu.nuke.common.DataType;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.Directory;
import seedu.nuke.directory.Module;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static seedu.nuke.parser.Parser.ALL_FLAG;
import static seedu.nuke.parser.Parser.EXACT_FLAG;
import static seedu.nuke.util.Message.*;

public class DeleteModuleCommand extends DeleteCommand {
    public static final String COMMAND_WORD = "delm";
    public static final String MESSAGE_USAGE = "delm <module code>";
    public static final Pattern[] REGEX_FORMATS = {
            Pattern.compile("(?<identifier>^\\s*([^-]+))"),
            Pattern.compile("(?<exact>(?:\\s+" + EXACT_FLAG + ")?)"),
            Pattern.compile("(?<all>(?:\\s+" + ALL_FLAG + ")?)"),
            Pattern.compile("(?<invalid>(?:\\s+-(?:[^e].*|[e]\\S+))*)")
    };

    private String moduleCode;
    private boolean isExact;

    /**
     * Constructs the command to delete a module.
     *
     * @param moduleCode
     *  The module code of the module
     * @param isExact
     *  Checks if modules are to be filtered exactly
     */
    public DeleteModuleCommand(String moduleCode, boolean isExact) {
        this.moduleCode = moduleCode;
        this.isExact = isExact;
    }

    /**
     *  Executes the initial phase of the module deletion process depending on the number of modules filtered.
     *  If there are no modules in the filtered list, then the deletion ends.
     *  Otherwise, if there is one, there will be a prompt to request confirmation.
     *  If there are multiple modules instead, there will be a prompt to choose which modules to delete.
     *
     * @param filteredModules
     *  The filtered list of modules containing possibly the modules to be deleted
     * @return
     *  The result of the execution
     */
    @Override
    protected CommandResult executeInitialDelete(ArrayList<Directory> filteredModules) {
        final int modulesCount = filteredModules.size();
        if (modulesCount == 0) {
            return new CommandResult(MESSAGE_NO_MODULES_FOUND);
        } else if (modulesCount == 1) {
            Executor.preparePromptConfirmation();
            Executor.setFilteredList(filteredModules, DataType.MODULE);
            Module toDelete = (Module) filteredModules.get(0);
            return new CommandResult(messageConfirmDeleteModule(toDelete));
        } else {
            Executor.preparePromptIndices();
            Executor.setFilteredList(filteredModules, DataType.MODULE);
            return new CommandResult(messagePromptDeleteModuleIndices(filteredModules));
        }
    }

    /**
     * Executes the <b>Delete Module Command</b> to delete a <b>Module</b> with the <code>module code</code> from the
     * <b>Module List</b>.
     *
     * @return The <b>Command Result</b> of the execution
     * @see Module
     * @see ModuleManager
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
        ArrayList<Module> filteredModules =
                isExact ? ModuleManager.filterExact(moduleCode) : ModuleManager.filter(moduleCode);
        return executeInitialDelete(new ArrayList<>(filteredModules));
    }
}
