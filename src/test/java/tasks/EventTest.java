package tasks;

import org.junit.jupiter.api.Test;
import seedu.duke.Parser;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventTest {
    Event newEvent = new Event("project meeting", "NUS SOC", Parser.parseDate("20/03/20 0900"), "My Very Long Long "
            + "Long Long Long Comment");
    Event nullEvent = new Event(null,null,null,null);

    @Test
    public void testGetLocation() {
        assertEquals(newEvent.getLocation(), "NUS SOC");
        assertNotEquals(newEvent.getLocation(), null);
        assertNull(nullEvent.getLocation());
    }

    @Test
    public void testGetDateAndTime() {
        LocalDateTime testDateAndTime = Parser.parseDate("20/03/20 0900");
        assertEquals(newEvent.getDateAndTime(), testDateAndTime);
        assertNotEquals(newEvent.getDateAndTime(), null);
        assertNull(nullEvent.getDateAndTime());
    }

    @Test
    public void testGetIsDone() {
        assertFalse(newEvent.getIsDone());
        assertFalse(nullEvent.getIsDone());
    }

    @Test
    public void testSetDone() {
        newEvent.setDone();
        assertTrue(newEvent.getIsDone());
    }

    @Test
    public void testGetComments() {
        assertEquals(newEvent.getComments(), "My Very Long Long Long Long Long Comment");
        assertNull(nullEvent.getComments());
    }

    @Test
    public void testGetStatusIcon() {
        assertEquals(newEvent.getStatusIcon(), "[X]");
        newEvent.setDone();
        assertEquals(newEvent.getStatusIcon(),"[/]");
    }

    @Test
    public void testGetName() {
        assertEquals(newEvent.getName(),"project meeting");
        assertNull(nullEvent.getName());
    }

    @Test
    public void testToString() {
        String printedString = "[E][X] project meeting (at: Fri 20 Mar 2020 09:00)"
                + System.lineSeparator() + "   "
                + "My Very Long Long Long Long Long Comment";
        assertEquals(newEvent.toString(), printedString);
    }
}
