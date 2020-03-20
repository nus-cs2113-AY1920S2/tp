package seedu.duke.command;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.SemesterList;
import seedu.duke.ui.Ui;

public class HelpingCommand extends Command {

    public static final String COMMAND_WORD = "help";

    @Override
    public void execute(SemesterList semesterList, AvailableModulesList availableModulesList) {
        Ui.showHelpMessage();
    }

}
