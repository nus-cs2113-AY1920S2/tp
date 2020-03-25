package meeting;

import meeting.Meeting;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MeetingTest {

    @Test
    public void testStringConversion() {
        Meeting m = new Meeting("CS2113T Meeting.Meeting", 1, LocalTime.parse("14:00"), 1, LocalTime.parse("15:00"));
        assertEquals("CS2113T Meeting.Meeting, 1, 14:00, 1, 15:00", m.toString());
    }

    @Test
    public void testStartEndTime_validTime_success() {
        Meeting m = new Meeting("CS2113T Meeting.Meeting", 2, LocalTime.parse("10:00"), 2, LocalTime.parse("12:30"));
        assertEquals(LocalTime.parse("10:00"), m.getStartTime());
        assertEquals(LocalTime.parse("12:30"), m.getEndTime());
    }

}
