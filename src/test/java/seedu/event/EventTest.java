package seedu.event;

import org.junit.jupiter.api.Test;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    }

    @Test
    void getDatetime() {
        Event event1 = new Event();
        assertEquals(null, event1.getDatetime());
        Event event2 = new Event("1", "2", "3");
        assertEquals("2", event2.getDatetime());
    }

    @Test
    void setDatetime() {
        Event event1 = new Event();
        event1.setDatetime("Sunday");
        assertEquals("Sunday", event1.getDatetime());
    }

    @Test
    void getVenue() {
        Event event1 = new Event();
        assertEquals(null, event1.getVenue());
        Event event2 = new Event("1", "2", "3");
        assertEquals("3", event2.getVenue());
    }

    @Test
    void setVenue() {
        Event event1 = new Event();
        event1.setVenue("sea");
        assertEquals("sea", event1.getVenue());
    }
}