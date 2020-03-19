package jikan.activity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActivityTest {

    HashSet<String> tags = new HashSet<String>();
    Activity activity = new Activity("Activity", LocalDateTime.parse("2020-01-01T08:00:00"),
            LocalDateTime.parse("2020-01-01T10:00:00"), tags);

    @Test
    void getDuration() {
        assertEquals("PT2H", activity.getDuration().toString());
    }

    @Test
    void getName() {
        assertEquals("Activity", activity.getName());
    }

    @Test
    void getTags() {
        assertEquals(tags, activity.getTags());
    }
}