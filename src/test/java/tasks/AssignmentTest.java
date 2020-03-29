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

    //@@author joelczk
    @Test
    public void assignmentTest_getModule() {
        assertEquals(newAssignment.getModule(), "CS2113T");
        assertNotEquals(newAssignment.getModule(), null);
        assertNull(nullAssignment.getModule());
    }

    @Test
    public void assignmentTest_getDateAndTime() {
        LocalDateTime testDateAndTime = Parser.parseDate("20/03/20 0900");
        assertEquals(newAssignment.getDateAndTime(), testDateAndTime);
        assertNotEquals(newAssignment.getDateAndTime(), null);
        assertNull(nullAssignment.getDateAndTime());
    }

    @Test
    public void assignmentTest_getIsDone() {
        assertFalse(newAssignment.getIsDone());
        assertFalse(nullAssignment.getIsDone());
    }

    @Test
    public void assignmentTest_SetDone() {
        newAssignment.setDone();
        assertTrue(newAssignment.getIsDone());
    }

    @Test
    public void assignmentTest_GetComments() {
        assertEquals(newAssignment.getComments(), "My Very Long Long Long Comments");
        assertNull(nullAssignment.getComments());
    }

    @Test
    public void assignmentTest_GetStatusIcon() {
        assertEquals(newAssignment.getStatusIcon(), "[X]");
        newAssignment.setDone();
        assertEquals(newAssignment.getStatusIcon(),"[/]");
    }

    @Test
    public void assignmentTest_GetName() {
        assertEquals(newAssignment.getName(),"TP");
        assertNull(nullAssignment.getName());
    }

    //@@author
    @Test
    public void assignmentTest_ToString() {
        String printedString = "[A][X] project meeting (by: Fri 20 Mar 2020 09:00 | mod: CS2113T)"
                + System.lineSeparator() + Messages.NEWLINE_INDENT
                + "My Very Long Long Long Long Long Comment";
        assertNotEquals(newAssignment.toString(), printedString);
    }
}
