package tasks;

import org.junit.jupiter.api.Test;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TaskTest {

    @Test
    public void testGetName() {
        assertEquals(new Task("Assignment 1", null, null, null).getName(), "Assignment 1");
        assertNull(new Task(null, null, null, null).getName());
    }

    @Test
    public void testGetDetails() {
        assertEquals(new Task(null, "IS1103", null, null).getDetails(), "IS1103");
        assertNull(new Task(null, null, null, null).getDetails());
    }

    @Test
    public void testGetDateAndTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String dateTime = "03/07/2020 16:00";
        LocalDateTime testDateTime = LocalDateTime.parse(dateTime, formatter);
        assertEquals(new Task(null, null, testDateTime, null).getDateAndTime(), testDateTime);
    }

    @Test
    public void testComments() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String dateTime = "03/07/2020 16:00";
        LocalDateTime testDateTime = LocalDateTime.parse(dateTime, formatter);
        assertEquals(new Task("Assignment 1", "IS1103", testDateTime, "Easy to do").getComments(), "Easy to do");
        assertNull(new Task("Assignment 1", "IS1103", testDateTime, null).getComments());
    }

    @Test
    public void testGetStatusIcon() {
        assertEquals(new Task(null, null, null, null).getStatusIcon(true), "[/]");
        assertEquals(new Task(null, null, null, null).getStatusIcon(false), "[X]");
    }

    @Test
    public void testGetIsDone() {
        assertFalse(new Task(null, null, null, null).getIsDone());
    }
}
