package seedu.command;

import seedu.exception.PacException;
import seedu.ui.UI;


public class Help extends Command {
    seedu.ui.Help help;

    public Help() {
        this.help = new seedu.ui.Help();
    }

    /**
     * This method allows the user to select the help message to
     * be displayed by type of command.
     */
    public void selectHelpMessage() {
        help.printGetHelp();
        String typeOfHelp = help.getStringInput();
        while (!typeOfHelp.equals("back")) {
            switch (typeOfHelp) {
            case "1":
                help.printStudentListHelp();
                break;
            case "2":
                help.printEventHelp();
                break;
            case "3":
                help.printCalendarHelp();
                break;
            case "4":
                help.printAttendanceHelp();
                break;
            case "5":
                help.printPerformanceHelp();
                break;
            case "list":
                help.printGetHelp();
            default:
                help.printGetHelp();
            }
            typeOfHelp = help.getStringInput();
        }
    }

    public void execute() throws PacException {
        selectHelpMessage();
    }
}
