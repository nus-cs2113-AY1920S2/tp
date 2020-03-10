import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MeetingTest {

    @Test
    public void testStringConversion() {
        assertEquals("CS2113T Meeting, 2020-03-10, 14:00, 15:00", new Meeting("CS2113T Meeting", LocalDate.parse("2020-03-10"), LocalTime.parse("14:00"), LocalTime.parse("15:00")).toString());
    }

}
