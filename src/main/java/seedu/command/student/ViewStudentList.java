package seedu.command.student;

import seedu.command.Command;
import seedu.exception.PACException;
import seedu.ui.UI;

/**
 * Class representing a student related command to view all existing studentList in studentListCollection.
 */
public class ViewStudentList extends Command {

    protected UI ui = new UI();

    /**
     * Method to display all existing student list.
     */
    private void displayStudentList() {
        ui.printStudentListCollection();
    }

    @Override
    public void execute() throws PACException {
        displayStudentList();
    }
}
