package seedu.ui;

import seedu.StudentList;
import seedu.event.Event;
import seedu.exception.DukeException;

import java.util.ArrayList;

import static seedu.duke.Duke.studentListCollection;

public class DisplayList extends UI {
    public void printEventList(ArrayList<Event> list, String type) {
        System.out.println("Here are all the " + type + "s in your list.");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
    }

    public void printStudentList(StudentList studentList, String listName) {
        System.out.println("Student List created, named : " + listName);
        studentList.showList();
    }

    public int getStudentListIndex() {
        assert !studentListCollection.isEmpty() : "studentListCollection should be empty.";
        int index = 1;
        display("Please choose one of the following list.");
        for (StudentList studentList: studentListCollection) {
            System.out.println(index + ". " + studentList.getListName());
        }
        return Integer.parseInt(getStringInput());
    }

    public void printSearchResults(ArrayList<StudentList> searchResults) throws DukeException {
        System.out.println("Here's the Search Results");
        try {
            int index = 1;
            for (StudentList studentList : searchResults) {
                System.out.println("\n[" + index + "]");
                studentList.showList();
                index++;
            }
        } catch (Exception e) {
            throw new DukeException("Search Failed");
        }
    }
}
