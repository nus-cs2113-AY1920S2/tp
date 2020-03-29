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


public class EventTest {
    Event newEvent = new Event("project meeting", "NUS SOC", Parser.parseDate("20/03/20 0900"),
            Parser.parseDate("20/03/20 1100"), "My Very Long Long Long Long Long Comment");
    Event nullEvent = new Event(null,null,null, null,null);

    //@@author joelczk
    @Test
    public void eventTest_getLocation() {
        assertEquals(newEvent.getLocation(), "NUS SOC");
        assertNotEquals(newEvent.getLocation(), null);
        assertNull(nullEvent.getLocation());
    }

    @Test
    public void eventTest_getDateAndTime() {
        LocalDateTime testDateAndTime = Parser.parseDate("20/03/20 0900");
        assertEquals(newEvent.getDateAndTime(), testDateAndTime);
        assertNotEquals(newEvent.getDateAndTime(), null);
        assertNull(nullEvent.getDateAndTime());
    }

    @Test
    public void getEndDateAndTime_expectedUsage_success() {
        LocalDateTime testDateAndTime = Parser.parseDate("20/03/20 1100");
        assertEquals(newEvent.getEndDateAndTime(), testDateAndTime);
        assertNotEquals(newEvent.getDateAndTime(), null);
        assertNull(nullEvent.getDateAndTime());
    }

    @Test
    public void eventTest_getIsDone() {
        assertFalse(newEvent.getIsDone());
        assertFalse(nullEvent.getIsDone());
    }

    @Test
    public void eventTest_setDone() {
        newEvent.setDone();
        assertTrue(newEvent.getIsDone());
    }

    @Test
    public void eventTest_getComments() {
        assertEquals(newEvent.getComments(), "My Very Long Long Long Long Long Comment");
        assertNull(nullEvent.getComments());
    }

    @Test
    public void eventTestGetStatusIcon() {
        assertEquals(newEvent.getStatusIcon(), "[X]");
        newEvent.setDone();
        assertEquals(newEvent.getStatusIcon(),"[/]");
    }

    @Test
    public void testGetName() {
        assertEquals(newEvent.getName(),"project meeting");
        assertNull(nullEvent.getName());
    }
    //@@author
    @Test
    public void testToString() {
        String printedString = "[E][X] project meeting (at: NUS SOC | Fri 20 Mar 2020 09:00 - 11:00)"
                + System.lineSeparator() + Messages.COMMENTS_INDENT
                + "My Very Long Long Long Long Long Comment";
        assertEquals(newEvent.toString(), printedString);
    }
}
