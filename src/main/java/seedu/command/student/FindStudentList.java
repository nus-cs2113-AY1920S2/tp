package seedu.command.student;

import seedu.StudentList;
import seedu.command.Command;
import seedu.exception.DukeException;
import seedu.ui.DisplayList;
import seedu.ui.UI;

import java.util.ArrayList;

import static seedu.duke.Duke.studentListCollection;

/**
 * Class representing a student related command to find an existing studentList by list name.
 */
public class FindStudentList extends Command {

    private String name;
    protected ArrayList<StudentList> searchResults = new ArrayList<>();
    protected DisplayList displayList = new DisplayList();
    protected UI ui = new UI();


    /**
     * Method to find an existing student list from studentListCollection by list name.
     * @throws DukeException    DukeException is thrown when there is an out of bound index.
     */
    protected void find() throws DukeException {
        ui.displayStudentMessage("Please state the list name you are searching for");
        ui.readUserInput();
        name = ui.getUserInput();
        for (int i = 0; i < studentListCollection.size(); i++) {
            if (studentListCollection.get(i).getListName().contains(name)) {
                searchResults.add(studentListCollection.get(i));
            }
        }
        if (searchResults.isEmpty()) {
            ui.displayStudentMessage("Nothing match you description : " + name);
        } else {
            ui.displayStudentMessage("You have " + searchResults.size() + " matches:");
            displayList.printSearchResults(searchResults);
        }
    }


    @Override
    public void execute() throws DukeException {
        find();
    }
}
