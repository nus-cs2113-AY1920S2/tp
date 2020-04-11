package seedu.studentlist;

import org.junit.jupiter.api.Test;
import seedu.student.StudentList;
import seedu.student.StudentListCollection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.student.StudentList.listNameComparator;

public class StudentListCollectionTest {

    StudentListCollection studentListCollection = new StudentListCollection();
    String[] nameList =  {"Daniel", "Robert", "Max"};

    @Test
    void add() {
        StudentList studentList1 = new StudentList("Demo List 1");
        for (String name: nameList) {
            studentList1.addToList(name);
        }
        studentListCollection.push(studentList1);
        assertEquals(System.lineSeparator() + "Demo List 1|Daniel|Robert|Max|",
                studentListCollection.getListCollection());
        StudentList studentList2 = new StudentList("Demo List 2");
        for (String name: nameList) {
            studentList2.addToList(name);
        }
        studentListCollection.push(studentList2);
        assertEquals(System.lineSeparator() + "Demo List 1|Daniel|Robert|Max|"
                + System.lineSeparator() + "Demo List 2|Daniel|Robert|Max|",
                studentListCollection.getListCollection());
    }

    @Test
    void isDuplicated() {
        add();
        assertTrue(studentListCollection.isExistedListName("Demo List 2"));
        assertFalse(studentListCollection.isExistedListName("Demo List 3"));
    }

    @Test
    void sort() {
        add();
        StudentList studentList0 = new StudentList("Demo List 0");
        for (String name: nameList) {
            studentList0.addToList(name);
        }
        studentListCollection.push(studentList0);
        assertEquals(System.lineSeparator() + "Demo List 1|Daniel|Robert|Max|"
                        + System.lineSeparator() + "Demo List 2|Daniel|Robert|Max|"
                        + System.lineSeparator() + "Demo List 0|Daniel|Robert|Max|",
                studentListCollection.getListCollection());
        studentListCollection.sort(listNameComparator);
        assertEquals(System.lineSeparator() + "Demo List 0|Daniel|Robert|Max|"
                        + System.lineSeparator() + "Demo List 1|Daniel|Robert|Max|"
                        + System.lineSeparator() + "Demo List 2|Daniel|Robert|Max|",
                studentListCollection.getListCollection());
    }

}
