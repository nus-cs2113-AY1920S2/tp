package seedu.parser;

import seedu.student.StudentList;
import seedu.student.StudentListCollection;

import static seedu.duke.Duke.studentListCollection;

public class StudentListStorageParser {

    public StudentListCollection loadCollectionFromStorage(StudentList studentList) {
        StudentListCollection studentListCollection = new StudentListCollection();
        studentListCollection.add(studentList);
        return studentListCollection;
    }

    public StudentList loadListFromStorage(String line) {
        String[] token = line.split("|");
        String listName = token[0];
        StudentList studentList = new StudentList(listName);
        int listSize = token.length;
        for (int i = 1; i < listSize; i++) {
            studentList.addToList(token[i]);
        }
        return studentList;
    }

    public String toStorage() {
        return studentListCollection.toString();
    }
}
