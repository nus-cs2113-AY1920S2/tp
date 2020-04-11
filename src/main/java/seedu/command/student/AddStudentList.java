package seedu.command.student;

import seedu.exception.PacException;
import seedu.student.StudentList;
import seedu.command.Command;
import seedu.ui.DisplayList;
import seedu.ui.UI;

import static seedu.pac.Pac.studentListCollection;

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
    private void addToList() throws PacException {
        String listName = ui.getListName().trim();
        if (listName.isEmpty()) {
            throw new PacException("The list name is empty!");
        }
        if (studentListCollection.isExistedListName(listName)) {
            throw new PacException("There is already an existing list name!");
        }
        if (listName.toLowerCase().equals("done")) {
            throw new PacException("Student Add cancelled.");
        }
        StudentList studentList = new StudentList(listName);
        ui.addStudent(studentList);
        if (studentList.isEmpty()) {
            throw new PacException("You cannot create an empty Student List.");

        }
        displayList.printStudentList(studentList, listName);
        studentListCollection.add(studentList);
    }

    @Override
    public void execute() throws PacException {
        addToList();
    }
}
