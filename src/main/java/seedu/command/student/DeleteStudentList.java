package seedu.command.student;

import seedu.command.Command;
import seedu.exception.PacException;
import seedu.ui.DisplayList;
import seedu.ui.UI;

import static seedu.pac.Pac.studentListCollection;

/**
 * Class representing a student related command to delete an existing studentList from studentListCollection.
 */
public class DeleteStudentList extends Command {

    protected DisplayList displayList = new DisplayList();
    protected UI ui = new UI();

    /**
     * Method to delete an existing student list from studentListCollection by its index.
     * @throws PacException    PacException is thrown when there is an out of bound index.
     */
    private void deleteFromExisting() throws PacException {
        UI.display("Please state the index of the student list you wish to delete.");
        ui.readUserInput();
        String line = ui.getUserInput();
        try {
            int index = Integer.parseInt(line);
            studentListCollection.remove(index - 1);
        } catch (NullPointerException e1) {
            throw new PacException("Out of bound.");
        } catch (Exception e2) {
            throw new PacException("Deletion Failed, out of bound.");
        }
        if (studentListCollection.isEmpty()) {
            UI.displayStudentListCollectionEmpty();
        } else {
            displayList.printStudentListCollection();
        }
    }

    @Override
    public void execute() throws PacException {
        if (studentListCollection.isEmpty()) {
            UI.displayStudentListCollectionEmpty();
        } else {
            deleteFromExisting();
        }
    }
}
