package seedu.duke.command;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.SelectedModulesList;
import seedu.duke.ui.Ui;

public class HelpingCommand extends Command {

    public static final String COMMAND_WORD = "help";

    @Override
    public void execute(SelectedModulesList selectedModulesList, AvailableModulesList availableModulesList) {
        Ui.showHelpMessage();
    }

}
