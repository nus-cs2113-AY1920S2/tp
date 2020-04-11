package seedu.duke.command;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.Person;
import seedu.duke.data.SemesterList;
import seedu.duke.ui.Ui;

public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";

    @Override
    public void execute(SemesterList semesterList, AvailableModulesList availableModulesList) {
        semesterList.clear();
        Person.clearTotalModuleCreditCompleted();
        Ui.showClearMessage();
    }

}
