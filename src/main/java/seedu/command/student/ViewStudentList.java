package seedu.command.student;

import seedu.command.Command;
import seedu.exception.PacException;
import seedu.ui.UI;

import static seedu.pac.Pac.studentListCollection;

/**
 * Class representing a student related command to view all existing studentList in studentListCollection.
 */
public class ViewStudentList extends Command {

    protected UI ui = new UI();

    /**
     * Method to display all existing student list.
     */
    private void displayStudentList() {
        if (studentListCollection.isEmpty()) {
            UI.displayStudentListCollectionEmpty();
        } else {
            ui.printStudentListCollection();
        }
    }

    @Override
    public void execute() throws PacException {
        displayStudentList();
    }
}
