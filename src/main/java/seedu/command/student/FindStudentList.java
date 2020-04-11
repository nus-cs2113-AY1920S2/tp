package seedu.command.student;

import seedu.student.StudentList;
import seedu.command.Command;
import seedu.exception.PacException;
import seedu.ui.DisplayList;
import seedu.ui.UI;

import java.util.ArrayList;

import static seedu.pac.Pac.studentListCollection;

/**
 * Class representing a student related command to find an existing studentList by list name.
 */
public class FindStudentList extends Command {

    protected ArrayList<StudentList> searchResults = new ArrayList<>();
    protected DisplayList displayList = new DisplayList();
    protected UI ui = new UI();


    /**
     * Method to find an existing student list from studentListCollection by list name.
     * @throws PacException    PacException is thrown when there is an out of bound index.
     */
    protected void find() throws PacException {
        if (studentListCollection.isEmpty()) {
            UI.displayStudentListCollectionEmpty();
        } else {
            displayStudentListCollection();
            UI.display("\nPlease state the list name you are searching for");
            ui.readUserInput();
            String name = ui.getUserInput();
            searchResults = studentListCollection.search(name);
            if (searchResults.isEmpty()) {
                UI.display("Nothing match your description : " + name);
            } else {
                UI.display("You have " + searchResults.size() + " matches:");
                displayList.printSearchResults(searchResults);
            }
        }
    }


    /**
     * Displays studentListCollection.
     */
    private void displayStudentListCollection() {
        UI.display("Displaying all student list: ");
        ui.printStudentListCollection();
    }

    @Override
    public void execute() throws PacException {
        find();
    }


}
