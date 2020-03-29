package seedu.attendance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttendanceTest {

    Attendance attendanceTestUnit = new Attendance("John Doe", "Yes");

    @Test
    void getStudentName() {
        assertEquals("John Doe", attendanceTestUnit.getStudentName());
    }

    @Test
    void getAttendanceStatus() {
        assertEquals("Yes", attendanceTestUnit.getAttendanceStatus());
    }

    @Test
    void getAttendance() {
        assertEquals("John Doe: Yes", attendanceTestUnit.toString());
    }
}
