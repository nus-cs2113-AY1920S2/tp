package seedu.command.student;

import seedu.command.Command;
import seedu.exception.DukeException;
import seedu.ui.DisplayList;
import seedu.ui.UI;

import static seedu.duke.Duke.studentListCollection;

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
     * @throws DukeException    DukeException is thrown when there is an out of bound index.
     */
    private void deleteFromExisting() throws DukeException {
        ui.readUserInput();
        line = ui.getUserInput();
        try {
            index = Integer.parseInt(line);
            studentListCollection.remove(index - 1);
        } catch (Exception e) {
            throw new DukeException("Deletion Failed, out of bound.");
        }
        ui.displayStudentMessage("Here is the updated Student List Collection");
        displayList.printStudentListCollection();
    }

    @Override
    public void execute() throws DukeException {
        deleteFromExisting();
    }
}
