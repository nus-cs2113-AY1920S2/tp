package seedu;

import java.util.ArrayList;
import java.util.Collections;

public class StudentList {
    public ArrayList<String> studentList;
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

    public String getListName() {
        return listName;
    }


    public void sortAscending() {
        Collections.sort(studentList);
    }

    public void showList() {
        System.out.println(listName);
        int index = 1;
        for (String name : studentList) {
            System.out.print(index + ". ");
            System.out.println(name);
            index++;
        }
    }
}
