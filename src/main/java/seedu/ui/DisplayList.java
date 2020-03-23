package seedu.ui;

import seedu.StudentList;
import seedu.event.Event;
import seedu.exception.DukeException;

import java.util.ArrayList;

import static seedu.duke.Duke.studentListCollection;

public class DisplayList extends UI {
    public void printEventList(ArrayList<Event> list) {
        System.out.println("Here are all the events in your list.");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
    }

    public void printSeminarList(ArrayList<Event> list) {
        System.out.println("Here are all the seminar events in your list.");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
    }

    public void printCalendar(ArrayList<Event> list, int semesterOneYear, int semesterTwoYear, int semester) {
        System.out.printf("Events of Semester %d of AY %d/%d\n",
                semester, semesterOneYear, semesterTwoYear);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " +  list.get(i));
        }
    }

    public void printStudentList(StudentList studentList, String listName) {
        System.out.println("Student List created, named : " + listName);
        studentList.showList();
    }

    public void printStudentListCollection() throws DukeException {
        int index = 1;
        try {
            for (StudentList studentList : studentListCollection) {
                System.out.print("[" + index + "] ");
                studentList.showList();
                System.out.println("--------------");
                index++;
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    public int getStudentListIndex() {
        assert !studentListCollection.isEmpty() : "studentListCollection should be empty.";
        int index = 1;
        for (StudentList studentList: studentListCollection) {
            System.out.println(index + ". " + studentList.getListName());
        }
        return Integer.parseInt(getUserInput());
    }
}
