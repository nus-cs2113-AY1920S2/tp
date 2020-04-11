package seedu.command.student;

import seedu.student.StudentList;
import seedu.command.Command;
import seedu.exception.PacException;
import seedu.ui.UI;

import static seedu.pac.Pac.studentListCollection;

/**
 * Class representing a student related command to sort all studentList by alphabetical order.
 */
public class SortStudentListByList extends Command {

    protected StudentList studentList;

    /**
     * Method to sort all student list in studentListCollection alphabetically.
     */
    private void sort() {
        if (studentListCollection.isEmpty()) {
            UI.displayStudentListCollectionEmpty();
        } else {
            studentListCollection.sortByList();
            UI.display("StudentList sorted by name within the lists.");
        }
    }

    @Override
    public void execute() throws PacException {
        sort();
    }
}
