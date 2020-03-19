package jikan.activity;

import jikan.exception.InvalidTimeFrameException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActivityTest {

    HashSet<String> tags = new HashSet<String>();
    //the tags is empty here as adding needs to be done in a method.
    Activity activity;

    {
        try {
            activity = new Activity("Activity", LocalDateTime.parse("2020-01-01T08:00:00"),
                    LocalDateTime.parse("2020-01-01T10:00:00"), tags);
        } catch (InvalidTimeFrameException e) {
            e.printStackTrace();
        }
    }

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