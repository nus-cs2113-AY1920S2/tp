package seedu.event;

import org.junit.jupiter.api.Test;
import seedu.exception.DukeException;

import java.security.InvalidParameterException;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EventTest {

    @Test
    void getName() {
        Event event1 = new Event();
        long time = Instant.now().getEpochSecond();
        String expectedName = "event_" + time;
        // might throw error, if event1 and time are created at different seconds
        assertEquals(expectedName, event1.getName());

        Event event2 = new Event("1", "2", "3");
        assertEquals("1", event2.getName());
    }

    @Test
    void setName() {
        Event event1 = new Event();
        event1.setName("event1");
        assertEquals("event1", event1.getName());

        Event event2 = new Event();
        assertThrows(InvalidParameterException.class, () -> event2.setName(null));

        Event event3 = new Event("1", "2", "3");
        assertThrows(InvalidParameterException.class, () -> event3.setName(""));

        Event event4 = new Event("1", "2", "3");
        event4.setName("event4");
        assertEquals("event4", event4.getName());
    }

    @Test
    void getDatetime() {
        Event event1 = new Event("1", "4", "3");
        assertEquals("Please provide correct format", event1.getDatetime());
        Event event2 = new Event("1", "2020-05-04 0130", "3");
        assertEquals("May 04 2020 0130", event2.getDatetime());
    }

    @Test
    void setDatetime() {
        Event event1 = new Event();
        event1.setDatetime("2020-05-04 0130");
        assertEquals("May 04 2020 0130", event1.getDatetime());
    }

    @Test
    void getVenue() throws DukeException {
        Event event1 = new Event();
        assertEquals("", event1.getVenue());
        Event event2 = new Event("1", "2", "3");
        assertEquals("3", event2.getVenue());
    }

    @Test
    void setVenue() throws DukeException {
        Event event1 = new Event();
        event1.setVenue("sea");
        assertEquals("sea", event1.getVenue());
    }
}