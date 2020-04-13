package seedu.event;

import org.junit.jupiter.api.Test;
import seedu.exception.PacException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EventTest {

    @Test
    void getName() throws PacException {
        Event event1 = new Event("1", "2", "3");
        assertEquals("1", event1.getName());
    }

    @Test
    void setName() throws PacException {
        Event event1 = new Event("1", "2", "3");
        assertThrows(PacException.class, () -> event1.setName(""));

        Event event2 = new Event("1", "2", "3");
        event2.setName("event2");
        assertEquals("event2", event2.getName());
    }

    @Test
    void getDatetime() throws PacException {
        //Event event1 = new Event("1", "4", "3");
        //assertEquals("yyyy-MM-dd HHmm", event1.getDatetime());
        Event event2 = new Event("1", "2020-05-04 0130", "3");
        assertEquals("Mon, May 04 2020 0130", event2.getDatetime());
    }

    @Test
    void setDatetime() throws PacException {
        Event event1 = new Event("1", "2", "3");
        event1.setDatetime("2020-05-04 0130");
        assertEquals("Mon, May 04 2020 0130", event1.getDatetime());
    }

    @Test
    void getVenue() throws PacException {
        Event event2 = new Event("1", "2", "3");
        assertEquals("3", event2.getVenue());
    }

    @Test
    void setVenue() throws PacException {
        Event event1 = new Event("1", "2", "3");
        event1.setVenue("sea");
        assertEquals("sea", event1.getVenue());
    }
}