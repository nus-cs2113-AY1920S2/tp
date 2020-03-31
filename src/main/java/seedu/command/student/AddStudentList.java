package seedu.command.student;

import seedu.exception.PACException;
import seedu.student.StudentList;
import seedu.command.Command;
import seedu.ui.DisplayList;
import seedu.ui.UI;

import static seedu.pac.PAC.studentListCollection;

/**
 * Class representing a student related command to add a new studentList to studentListCollection.
 */
public class AddStudentList extends Command {

    protected UI ui;
    protected DisplayList displayList;

    public AddStudentList() {
        this.ui = new UI();
        this.displayList = new DisplayList();
    }

    /**
     * Method to add student names to a new list.
     * Once studentList is created, it will be appended to studentListCollection.
     */
    private void addToList() throws PACException {
        String listName = ui.getListName();
        if (listName.toLowerCase().equals("done")) {
            ui.displayMessage("Student Add cancelled.");
            throw new PACException("Student Add cancelled");
        }
        StudentList studentList = new StudentList(listName);
        ui.addStudent(studentList);
        if (studentList.isEmpty()) {
            ui.displayMessage("You cannot create an empty Student List.");
            throw new PACException("You cannot create an empty Student List");

        }
        displayList.printStudentList(studentList, listName);
        studentListCollection.add(studentList);
    }

    @Override
    public void execute() throws PACException {
        addToList();
    }
}
