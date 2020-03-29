package seedu;

import java.util.ArrayList;

public class StudentListCollection extends ArrayList<StudentList> {

    public ArrayList<StudentList> studentListCollection;

    public StudentListCollection() {
        studentListCollection = new ArrayList<>();
    }

    @Override
    public String toString() {
        String data = "";
        for (StudentList studentList: this.studentListCollection) {
            data = data.concat(studentList.toString() + System.lineSeparator());
        }
        return data;
    }
}
