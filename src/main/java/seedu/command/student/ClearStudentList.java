package seedu.command.student;

import seedu.command.Command;
import seedu.exception.PacException;
import seedu.ui.UI;

import static seedu.pac.Pac.studentListCollection;

/**
 * Class representing a student related command to clear all existing studentList in studentListCollection.
 */
public class ClearStudentList extends Command {

    private UI ui = new UI();

    /**
     * Method to clear the entire studentListCollection.
     */
    private void clear() {
        studentListCollection.clear();
        ui.displayMessage("The Student List Collection is cleared");
    }

    @Override
    public void execute() throws PacException {
        clear();
    }
}
