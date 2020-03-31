package seedu.command;

import seedu.exception.PACException;


public class Help extends Command {
    seedu.ui.Help help;

    public Help() {
        this.help = new seedu.ui.Help();
    }

    /**
     * This method allows the user to select the help message to
     * be displayed by type of command.
     *
     * @throws PACException Throws PACException when the user
     *                       selects any command out of the list.
     */
    public void selectHelpMessage() throws PACException {
        help.printGetHelp();
        String typeOfHelp = help.getStringInput();
        switch (typeOfHelp) {
        case "1":
            help.printEventHelp();
            break;
        case "2":
            help.printAttendanceHelp();
            break;
        case "3":
            help.printPerformanceHelp();
            break;
        case "4":
            help.printStudentListHelp();
            break;
        default: throw new PACException("Invalid help function selected.");
        }
    }

    public void execute() throws PACException {
        selectHelpMessage();
    }
}
