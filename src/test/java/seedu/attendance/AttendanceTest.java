package seedu.attendance;

import org.junit.jupiter.api.Test;
import seedu.student.Student;
import seedu.student.attendance.Attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttendanceTest {

    @Test
    void testAttendance() {
        assertEquals("Richard CS2040C false", new Attendance("Richard", "CS2040C").toString());
    }

    @Test
    void getStudentName() {
        assertEquals("Richard",  new Student("Richard").getStudentName());
        assertEquals("Richard",  new Attendance("Richard", "CS2040C").getStudentName());
    }

    @Test
    void getDescription() {
        assertEquals("CS2040C",  new Attendance("Richard", "CS2040C").getDescription());
    }

    @Test
    void getAttendance() {
        assertEquals(false,  new Attendance("Richard", "CS2040C").getAttendance());
        assertEquals(false,  new Attendance("Richard", "CS2040C", false).getAttendance());
        assertEquals(true,  new Attendance("Richard", "CS2040C", true).getAttendance());

    }
}
