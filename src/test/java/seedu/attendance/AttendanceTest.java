package seedu.attendance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttendanceTest {

    @Test
    void getModuleName() {
        assertEquals("CS2040C", new Attendance("CS2040C","Richard", "Tutorial 1").getEvent());
    }

    @Test
    void getStudentName() {
        assertEquals("Richard", new Attendance("CS2040C","Richard", "Tutorial 1").getStudent());
    }

    @Test
    void getDescription() {
        assertEquals("Tutorial 1", new Attendance("CS2040C","Richard", "Tutorial 1").getDescription());
    }

    @Test
    void getAttendance() {
        assertEquals("false", new Attendance("CS2040C","Richard", "Tutorial 1").getAttendance());
        assertEquals("false", new Attendance("CS2040C","Richard", "Tutorial 1", "false").getAttendance());
        assertEquals("true", new Attendance("CS2040C","Richard", "Tutorial 1", "true").getAttendance());

    }
}
