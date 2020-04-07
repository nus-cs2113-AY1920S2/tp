package seedu.command;

import seedu.exception.PacException;


public class Help extends Command {
    seedu.ui.Help help;

    public Help() {
        this.help = new seedu.ui.Help();
    }

    /**
     * This method allows the user to select the help message to
     * be displayed by type of command.
     *
     * @throws PacException Throws PacException when the user
     *                       selects any command out of the list.
     */
    public void selectHelpMessage() throws PacException {
        help.printGetHelp();
        String typeOfHelp = help.getStringInput();
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
        default: throw new PacException("Invalid help function selected.");
        }
    }

    public void execute() throws PacException {
        selectHelpMessage();
    }
}
