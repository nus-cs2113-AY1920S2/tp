package seedu.studentlist;

import org.junit.jupiter.api.Test;
import seedu.student.StudentList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StudentListTest {

    StudentList studentList = new StudentList("Demo List");

    @Test
    void isEmpty() {
        assertTrue(studentList.isEmpty());
        studentList.addToList("Alice");
        assertFalse(studentList.isEmpty());
    }

    @Test
    void isDuplicate() {
        studentList.addToList("Alice");
        assertTrue(studentList.isDuplicate("Alice"));
        assertFalse(studentList.isDuplicate("Bobby"));
    }

    @Test
    void getListName() {
        assertEquals("Demo List", studentList.getListName());
    }

    @Test
    void add() {
        studentList.addToList("Alice");
        studentList.addToList("Charlie");
        studentList.addToList("Bobby");
        assertEquals("[Alice, Charlie, Bobby]", studentList.getStudentList().toString());
    }

    @Test
    void sort() {
        add();
        studentList.sortAscending();
        assertEquals("[Alice, Bobby, Charlie]",studentList.getStudentList().toString());
    }

    @Test
    void getString() {
        add();
        assertEquals("Demo List|Alice|Charlie|Bobby", studentList.toString());
    }
}
