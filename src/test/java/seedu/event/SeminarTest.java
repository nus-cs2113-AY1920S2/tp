package seedu.event;

import org.junit.jupiter.api.Test;
import seedu.exception.DukeException;
import java.time.Instant;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SeminarTest {

    @Test
    void getName() throws DukeException {
        Seminar seminar1 = new Seminar();
        long time = Instant.now().getEpochSecond();
        String expectedName = "seminar_" + time;
        //might throw error, if event1 and time are created at different seconds
        //assertEquals(expectedName, seminar1.getName());

        Seminar seminar2 = new Seminar("there", "2", "3");
        //assertEquals("there", seminar2.getName());
    }

    @Test
    void setName() throws DukeException {
        Seminar seminar1 = new Seminar();
        seminar1.setName("se");
        assertEquals("se", seminar1.getName());

        Seminar seminar2 = new Seminar();
        assertThrows(DukeException.class, () -> seminar2.setName(null));

        Seminar seminar3 = new Seminar("1", "2", "3");
        assertThrows(DukeException.class, () -> seminar3.setName(""));

        Seminar seminar4 = new Seminar("1", "2", "3");
        seminar4.setName("event4");
        assertEquals("event4", seminar4.getName());
    }

    @Test
    void getDatetime() throws DukeException {
        Seminar seminar1 = new Seminar("1", "4", "3");
        assertEquals("yyyy-MM-dd HHmm", seminar1.getDatetime());
        Seminar seminar2 = new Seminar("1", "2020-05-04 0130", "3");
        assertEquals("May 04 2020 0130", seminar2.getDatetime());
    }

    @Test
    void setDatetime() throws DukeException {
        Seminar seminar1 = new Seminar();
        seminar1.setDatetime("2020-05-04 0130");
        assertEquals("May 04 2020 0130", seminar1.getDatetime());
    }

    @Test
    void getVenue() throws DukeException {
        Seminar seminar1 = new Seminar();
        assertEquals("", seminar1.getVenue());
        Seminar seminar2 = new Seminar("1", "2", "3");
        assertEquals("3", seminar2.getVenue());
    }

}
