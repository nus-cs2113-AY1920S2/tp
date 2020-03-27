package tasks;

import common.Messages;
import org.junit.jupiter.api.Test;
import seedu.atas.Parser;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssignmentTest {
    Assignment newAssignment = new Assignment("TP", "CS2113T",  Parser.parseDate("20/03/20 0900"),
            "My Very Long Long Long Comments");
    Assignment nullAssignment = new Assignment(null, null, null, null);

    @Test
    public void testGetModule() {
        assertEquals(newAssignment.getModule(), "CS2113T");
        assertNotEquals(newAssignment.getModule(), null);
        assertNull(nullAssignment.getModule());
    }

    @Test
    public void testGetDateAndTime() {
        LocalDateTime testDateAndTime = Parser.parseDate("20/03/20 0900");
        assertEquals(newAssignment.getDateAndTime(), testDateAndTime);
        assertNotEquals(newAssignment.getDateAndTime(), null);
        assertNull(nullAssignment.getDateAndTime());
    }

    @Test
    public void testGetIsDone() {
        assertFalse(newAssignment.getIsDone());
        assertFalse(nullAssignment.getIsDone());
    }

    @Test
    public void testSetDone() {
        newAssignment.setDone();
        assertTrue(newAssignment.getIsDone());
    }

    @Test
    public void testGetComments() {
        assertEquals(newAssignment.getComments(), "My Very Long Long Long Comments");
        assertNull(nullAssignment.getComments());
    }

    @Test
    public void testGetStatusIcon() {
        assertEquals(newAssignment.getStatusIcon(), "[X]");
        newAssignment.setDone();
        assertEquals(newAssignment.getStatusIcon(),"[/]");
    }

    @Test
    public void testGetName() {
        assertEquals(newAssignment.getName(),"TP");
        assertNull(nullAssignment.getName());
    }

    @Test
    public void testToString() {
        String printedString = "[A][X] project meeting (by: Fri 20 Mar 2020 09:00 | mod: CS2113T)"
                + System.lineSeparator() + Messages.NEWLINE_INDENT
                + "My Very Long Long Long Long Long Comment";
        assertNotEquals(newAssignment.toString(), printedString);
    }
}
