package seedu;

import java.util.ArrayList;
import java.util.Collections;

public class StudentList {
    public static ArrayList<String> studentList;
    public String listName;
    public String studentName;

    public StudentList(String listName) {
        this.listName = listName;
        studentList = new ArrayList<String>();
    }

    public void addToList(String studentName) {
        studentList.add(studentName);
    }

    public ArrayList<String> getStudentList() {
        return studentList;
    }

    public void sortAscending() {
        Collections.sort(studentList);
    }

    public void showList() {
        System.out.println(studentList);
    }
}
