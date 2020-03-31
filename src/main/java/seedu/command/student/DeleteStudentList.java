package seedu.command.student;

import seedu.command.Command;
import seedu.exception.PACException;
import seedu.ui.DisplayList;
import seedu.ui.UI;

import static seedu.pac.PAC.studentListCollection;

/**
 * Class representing a student related command to delete an existing studentList from studentListCollection.
 */
public class DeleteStudentList extends Command {

    protected int index;
    protected String line;
    protected DisplayList displayList = new DisplayList();
    protected UI ui = new UI();

    /**
     * Method to delete an existing student list from studentListCollection by its index.
     * @throws PACException    PACException is thrown when there is an out of bound index.
     */
    private void deleteFromExisting() throws PACException {
        ui.displayMessage("Please state the index of the student list you wish to delete.");
        ui.readUserInput();
        line = ui.getUserInput();
        try {
            index = Integer.parseInt(line);
            studentListCollection.remove(index - 1);
        } catch (NullPointerException e1) {
            ui.displayMessage("Deletion Failed, out of bound");
            throw new PACException("Out of bound.");
        } catch (Exception e2) {
            ui.displayMessage("Deletion Failed.");
            throw new PACException("Deletion Failed, out of bound.");
        }
        displayList.printStudentListCollection();
    }

    @Override
    public void execute() throws PACException {
        deleteFromExisting();
    }
}
