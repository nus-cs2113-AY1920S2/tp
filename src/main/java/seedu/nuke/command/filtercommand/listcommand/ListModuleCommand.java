package seedu.nuke.command.filtercommand.listcommand;

import seedu.nuke.command.CommandResult;
import seedu.nuke.directory.DirectoryLevel;
import seedu.nuke.directory.Module;

import java.util.ArrayList;

import static seedu.nuke.util.Message.MESSAGE_NO_MODULES_TO_SHOW;
import static seedu.nuke.util.Message.MESSAGE_SHOW_LIST;

public class ListModuleCommand extends ListCommand {
    public static final String COMMAND_WORD = "lsm";
    public static final String FORMAT = COMMAND_WORD + " [ <module keyword> -e -a ]";
    public static final String MESSAGE_USAGE = String.format(
            "%s - Filter and show module(s)\n"
            + "Note: -e to filter for exact keywords\n"
            + "Format: %s\n"
            + "Example: lsm CS -a\n",
            COMMAND_WORD, FORMAT);

    private String moduleKeyWord;
    private boolean isExact;

    /**
     * Constructs the command to list modules.
     *
     * @param moduleKeyWord
     *  The keyword to filter the modules
     * @param isExact
     *  Checks if modules are to be filtered exactly
     */
    public ListModuleCommand(String moduleKeyWord, boolean isExact) {
        this.moduleKeyWord = moduleKeyWord;
        this.isExact = isExact;
    }

    /**
     * Executes the <b>List Module Command</b> to show a filtered list of modules.
     *
     * @return The <b>Command Result</b> of the execution
     * @see Module
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
        ArrayList<Module> filteredModuleList = createFilteredModuleList(moduleKeyWord, isExact);
        if (filteredModuleList.isEmpty()) {
            return new CommandResult(MESSAGE_NO_MODULES_TO_SHOW);
        }
        sortModuleList(filteredModuleList);
        return new CommandResult(MESSAGE_SHOW_LIST, DirectoryLevel.MODULE, new ArrayList<>(filteredModuleList));
    }
}
