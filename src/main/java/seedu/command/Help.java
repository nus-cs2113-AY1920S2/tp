package seedu.command;

import seedu.exception.PacException;
import seedu.ui.HelpUI;

public class Help extends Command {
    HelpUI helpUI;

    public Help() {
        this.helpUI = new HelpUI();
    }

    /**
     * This method allows the user to select the help message to
     * be displayed by type of command.
     */
    public void selectHelpMessage() {
        helpUI.printGetHelp();
        String typeOfHelp = helpUI.getStringInput();
        while (!typeOfHelp.equals("back")) {
            switch (typeOfHelp) {
            case "1":
                helpUI.printStudentListHelp();
                break;
            case "2":
                helpUI.printEventHelp();
                break;
            case "3":
                helpUI.printCalendarHelp();
                break;
            case "4":
                helpUI.printAttendanceHelp();
                break;
            case "5":
                helpUI.printPerformanceHelp();
                break;
            default:
                helpUI.printGetHelp();
            }
            typeOfHelp = helpUI.getStringInput();
        }
    }

    public void execute() throws PacException {
        selectHelpMessage();
    }
}
