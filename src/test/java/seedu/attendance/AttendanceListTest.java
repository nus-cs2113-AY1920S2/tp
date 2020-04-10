package seedu.attendance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class AttendanceListTest {

    AttendanceList attendanceList = new AttendanceList();
    String testEventName = "Event1";

    @Test
    void sort() {
        attendanceList.addToList(new Attendance("John Doe", "Y"), testEventName);
        attendanceList.addToList(new Attendance("Amy Doe", "Y"), testEventName);
        attendanceList.addToList(new Attendance("Robert Doe", "N"), testEventName);
        attendanceList.addToList(new Attendance("James Doe", "N"), testEventName);
        assertEquals("[John Doe: Present, Amy Doe: Present, Robert Doe: Absent, James Doe: Absent]",
                attendanceList.getAttendanceList().toString());
        attendanceList.sortByName();
        assertEquals("[Amy Doe: Present, James Doe: Absent, John Doe: Present, Robert Doe: Absent]",
                attendanceList.getAttendanceList().toString());
        attendanceList.sortByStatus();
        assertEquals("[James Doe: Absent, Robert Doe: Absent, Amy Doe: Present, John Doe: Present]",
                attendanceList.getAttendanceList().toString());
    }

    @Test
    void find() {
        attendanceList.addToList(new Attendance("Boy", "Y"), testEventName);
        attendanceList.addToList(new Attendance("Girl", "Y"), testEventName);
        attendanceList.addToList(new Attendance("Big Boy", "N"), testEventName);
        assertEquals("[]", attendanceList.isMatch("Man").toString());
        assertEquals("[Boy: Present, Big Boy: Absent]", attendanceList.isMatch("Boy".toLowerCase()).toString());
    }


    @Test
    void isDuplicate() {
        attendanceList.addToList(new Attendance("John Doe", "Y"), testEventName);
        assertFalse(attendanceList.isDuplicate("Jerry"));
        assertTrue(attendanceList.isDuplicate("John Doe")); // Must match exactly
    }

    @Test
    void clearList() {
        attendanceList.addToList(new Attendance("John Doe", "Y"), testEventName);
        attendanceList.addToList(new Attendance("Doe John", "Y"), testEventName);
        assertFalse(attendanceList.isEmpty());
        attendanceList.clearList();
        assertTrue(attendanceList.isEmpty());
    }

    @Test
    void isEmpty() {
        assertTrue(attendanceList.isEmpty());
        attendanceList.addToList(new Attendance("John Doe", "Y"), testEventName);
        assertFalse(attendanceList.isEmpty());
    }

    @Test
    void getAttendanceList() {
        assertEquals("[]", attendanceList.getAttendanceList().toString());
        attendanceList.addToList(new Attendance("John Doe", "Y"), testEventName);
        assertEquals("[John Doe: Present]", attendanceList.getAttendanceList().toString());
    }
}
