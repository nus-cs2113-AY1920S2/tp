package seedu.nuke.command.module;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.module.Module;
import seedu.nuke.data.module.ModuleList;
import seedu.nuke.gui.controller.MainController;
import seedu.nuke.gui.io.Executor;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static seedu.nuke.parser.Parser.EXACT_FLAG;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_DELETE_ERROR;
import static seedu.nuke.util.Message.*;

public class DeleteModuleCommand extends Command {
    public static final String COMMAND_WORD = "delm";
    public static final String FORMAT = COMMAND_WORD + " <module code>";
    public static final Pattern[] REGEX_FORMATS = {
            Pattern.compile("(?<identifier>^\\s*([^-]+))"),
            Pattern.compile("(?<exact>(?:" + EXACT_FLAG + ")?)"),
            Pattern.compile("(?<invalid>(?:-(?:[^e].*|[e]\\S+)))")
    };

    private String moduleCode;
    private boolean isExact;

    public DeleteModuleCommand(String moduleCode, boolean isExact) {
        this.moduleCode = moduleCode;
        this.isExact = isExact;
    }

    private CommandResult executeDelete(ArrayList<Module> filteredModules) throws InterruptedException {
        final int filteredModulesCount = filteredModules.size();
        boolean isConfirmed;
        MainController controller = new MainController();
        switch (filteredModulesCount) {

        case 0:
            return new CommandResult(MESSAGE_NO_MODULES_FOUND);

        case 1:
            Module toDelete = filteredModules.get(0);
            // Prompt user to confirm deletion
            isConfirmed = new Executor(controller.getConsole(), controller.getConsoleScreen())
                    .promptUser(MESSAGE_CONFIRM_DELETE_MODULE(toDelete));

            if (isConfirmed) {
                ModuleList.delete(toDelete);
                return new CommandResult(MESSAGE_DELETE_MODULE_SUCCESS);
            } else {
                return new CommandResult(MESSAGE_DELETE_MODULE_ABORTED);
            }

        default:
            // Prompt user for which modules to delete
            ArrayList<Integer> toDeleteIndices =
                    new Executor(controller.getConsole(), controller.getConsoleScreen())
                            .getIndicesFromUser(MESSAGE_PROMPT_DELETE_MODULE_INDICES(filteredModules));
            if (toDeleteIndices == null) {
                return new CommandResult(MESSAGE_INVALID_DELETE_INDICES);
            }

            // Prompt user to confirm deletion
            isConfirmed = new Executor(controller.getConsole(), controller.getConsoleScreen())
                    .promptUser(MESSAGE_CONFIRM_DELETE_MODULE(filteredModules, toDeleteIndices));
            if (isConfirmed) {
                ModuleList.delete(filteredModules, toDeleteIndices);
                return new CommandResult(MESSAGE_DELETE_MODULE_SUCCESS);
            } else {
                return new CommandResult(MESSAGE_DELETE_MODULE_ABORTED);
            }
        }
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
        ArrayList<Module> filteredModules =
                isExact ? ModuleList.filterExact(moduleCode) : ModuleList.filter(moduleCode);
        try {
            return executeDelete(filteredModules);
        } catch (InterruptedException e) {
            return new CommandResult(MESSAGE_DELETE_ERROR);
        }
    }
}
