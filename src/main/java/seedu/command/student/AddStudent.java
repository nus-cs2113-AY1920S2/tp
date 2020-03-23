package seedu.command.student;

import seedu.StudentList;
import seedu.command.Command;
import seedu.duke.Duke;
import seedu.exception.DukeException;
import seedu.ui.UI;

import java.util.ArrayList;

import static seedu.duke.Duke.studentListCollection;

public class AddStudent extends Command {

    static UI ui = new UI();
    StudentList newStudentList;
    private String studentName;
    private String listName;
    private StudentList studentList;

    public AddStudent(StudentList newStudentList) {
        this.newStudentList = newStudentList;
    }

    protected void addToList() throws DukeException {
        listName = ui.getListName();
        studentList = new StudentList(listName);
        ui.addStudent(studentList);
        ui.displayStudentList(studentList,listName);
        studentListCollection.add(studentList);
    }

    @Override
    public void execute() throws DukeException {
        addToList();
    }
}
