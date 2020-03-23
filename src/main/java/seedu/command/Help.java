package seedu.command;

import seedu.exception.DukeException;
import seedu.ui.UI;

public class Help extends Command {
    UI ui;

    public Help() {
        this.ui = new UI();
    }

    public void selectHelpMessage() throws DukeException {
        ui.printGetHelp();
        String typeOfHelp = ui.getStringInput();
        switch (typeOfHelp) {
        case "1":
            ui.printEventHelp();
            break;
        case "2":
            ui.printAttendanceHelp();
            break;
        case "3":
            ui.printPerformanceHelp();
            break;
        case "4":
            ui.printStudentListHelp();
            break;
        default: throw new DukeException("Invalid help function selected.");
        }
    }

    public void execute() throws DukeException {
        selectHelpMessage();
    }
}
