package seedu.attendance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttendanceTest {

    @Test
    void getStudentName() {
        Attendance attendanceTestUnit = new Attendance("John Doe", "Y");
        assertEquals("John Doe", attendanceTestUnit.getStudentName());
    }

    @Test
    void getStatus() {
        Attendance attendanceTestUnit = new Attendance("John Doe", "Y");
        assertEquals("Present", attendanceTestUnit.getStatus());
    }

    @Test
    void getAttendance() {
        Attendance attendanceTestUnit = new Attendance("John Doe", "Y");
        assertEquals("John Doe: Present", attendanceTestUnit.toString());
    }

    @Test
    void setStatus() {
        Attendance attendanceTestUnit = new Attendance("John Doe", "Y");
        attendanceTestUnit.setStatus("N"); // Set status as absent
        assertEquals("Absent", attendanceTestUnit.getStatus());
        attendanceTestUnit.setStatus("Y"); // With upper case y
        assertEquals("Present", attendanceTestUnit.getStatus());
        attendanceTestUnit.setStatus("unknown"); // With unknown input
        assertEquals("Absent", attendanceTestUnit.getStatus());
        attendanceTestUnit.setStatus("y"); // With lower case y
        assertEquals("Present", attendanceTestUnit.getStatus());
        attendanceTestUnit.setStatus("      y     "); // With additional white spaces
        assertEquals("Present", attendanceTestUnit.getStatus());
    }

    @Test
    void setName() {
        Attendance attendanceTestUnit = new Attendance("John Doe", "Y");
        assertEquals("John Doe", attendanceTestUnit.getStudentName());
        attendanceTestUnit.setName("David Chang"); // Set new name
        assertEquals("David Chang", attendanceTestUnit.getStudentName());
        attendanceTestUnit.setName(" Ryan   "); // Set new name with white spaces
        assertEquals("Ryan", attendanceTestUnit.getStudentName());
    }
}
