package seedu.ui;

import seedu.student.StudentList;
import seedu.event.Event;
import seedu.exception.PacException;

import java.util.ArrayList;

import static seedu.pac.Pac.studentListCollection;

public class DisplayList extends UI {
    public void printEventList(ArrayList<Event> list, String type) {
        UI.display("Here are all the " + type + "s in your list.");
        for (int i = 0; i < list.size(); i++) {
            UI.display(i + 1 + ". " + list.get(i));
        }
    }

    public void printStudentList(StudentList studentList, String listName) {
        UI.display("Student List created, named : " + listName);
        studentList.showList();
    }

    public int getStudentListIndex() {
        assert !studentListCollection.isEmpty() : "studentListCollection should be empty.";
        int index = 1;
        display("Please choose one of the following list.");
        for (StudentList studentList: studentListCollection) {
            UI.display(index + ". " + studentList.getListName());
            index++;
        }
        return Integer.parseInt(getStringInput());
    }

    public void printSearchResults(ArrayList<StudentList> searchResults) throws PacException {
        UI.display("Here's the Search Result(s)");
        try {
            int index = 1;
            for (StudentList studentList : searchResults) {
                UI.display("\n[" + index + "]");
                studentList.showList();
                index++;
            }
        } catch (Exception e) {
            throw new PacException("Search Failed");
        }
    }
}
