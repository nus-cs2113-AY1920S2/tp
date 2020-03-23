package seedu.event;

import org.junit.jupiter.api.Test;
import seedu.exception.DukeException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SeminarTest {

    @Test
    void getName() throws DukeException {
        Seminar seminar1 = new Seminar("1", "2", "3");
        assertEquals("1", seminar1.getName());
    }

    @Test
    void setName() throws DukeException {
        Seminar seminar1 = new Seminar("1", "2", "3");
        assertThrows(DukeException.class, () -> seminar1.setName(""));

        Seminar seminar2 = new Seminar("1", "2", "3");
        seminar2.setName("seminar2");
        assertEquals("seminar2", seminar2.getName());
    }

    @Test
    void getDatetime() throws DukeException {
        //Seminar seminar1 = new Seminar("1", "4", "3");
        //assertEquals("yyyy-MM-dd HHmm", seminar1.getDatetime());
        Seminar seminar2 = new Seminar("1", "2020-05-04 0130", "3");
        assertEquals("Mon, May 04 2020 0130", seminar2.getDatetime());
    }

    @Test
    void setDatetime() throws DukeException {
        Seminar seminar1 = new Seminar("1", "2", "3");
        seminar1.setDatetime("2020-05-04 0130");
        assertEquals("Mon, May 04 2020 0130", seminar1.getDatetime());
    }

    @Test
    void getVenue() throws DukeException {
        Seminar seminar2 = new Seminar("1", "2", "3");
        assertEquals("3", seminar2.getVenue());
    }

    @Test
    void setVenue() throws DukeException {
        Seminar seminar1 = new Seminar("1", "2", "3");
        seminar1.setVenue("sea");
        assertEquals("sea", seminar1.getVenue());
    }
}
