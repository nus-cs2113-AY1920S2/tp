package seedu.nuke.format;

import org.junit.jupiter.api.Test;
import seedu.nuke.util.DateTime;
import seedu.nuke.util.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DateTimeTest {

    DateTimeTest() throws DateTimeFormat.InvalidTimeException, DateTimeFormat.InvalidDateException {

    }

    DateTime dateTime = new DateTime(DateTimeFormat.stringToDate("08082020"), DateTimeFormat.stringToTime("4:38PM"));

    @Test
    void getDate() {
        assertEquals("08/08/2020", dateTime.getDate());
    }

    @Test
    void getTime() {
        assertEquals("04:38PM", dateTime.getTime());
    }

    @Test
    void getDateInSortFormat() {
        assertEquals("20200808", dateTime.getDateInSortFormat());
    }

    @Test
    void getTimeInSortFormat() {
        assertEquals("1638", dateTime.getTimeInSortFormat());
    }

    @Test
    void isOn_onDate() throws DateTimeFormat.InvalidDateException {
        LocalDate toCompare = DateTimeFormat.stringToDate("8/8");
        assertTrue(dateTime.isOn(toCompare));
    }

    @Test
    void isOn_notOnDate() throws DateTimeFormat.InvalidDateException {
        LocalDate toCompare = DateTimeFormat.stringToDate("12-09-20");
        assertFalse(dateTime.isOn(toCompare));
    }

    @Test
    void isBefore_beforeDate() throws DateTimeFormat.InvalidDateException {
        LocalDate toCompare = DateTimeFormat.stringToDate("09/09");
        assertTrue(dateTime.isBefore(toCompare));
    }

    @Test
    void isBefore_notBeforeDate() throws DateTimeFormat.InvalidDateException {
        LocalDate toCompare = DateTimeFormat.stringToDate("3/6/1997");
        assertFalse(dateTime.isBefore(toCompare));
    }

    @Test
    void isAfter_afterDate() throws DateTimeFormat.InvalidDateException {
        LocalDate toCompare = DateTimeFormat.stringToDate("3/6/1997");
        assertTrue(dateTime.isAfter(toCompare));
    }

    @Test
    void isAfter_notAfterDate() throws DateTimeFormat.InvalidDateException {
        LocalDate toCompare = DateTimeFormat.stringToDate("09/09");
        assertFalse(dateTime.isAfter(toCompare));
    }

    @Test
    void testToString() {
        DateTime today = new DateTime(LocalDate.now(), LocalTime.now());
        assertEquals("today " + today.getTime() + " [OVER!!]", today.toShow());

        DateTime tomorrow = new DateTime(LocalDate.now().plusDays(1), LocalTime.now());
        assertEquals("tomorrow " + tomorrow.getTime(), tomorrow.toShow());

        DateTime yesterday = new DateTime(LocalDate.now().minusDays(1), LocalTime.now());
        assertEquals(yesterday.getDate() + " " + yesterday.getTime() + " [OVER!!]", yesterday.toShow());

        DateTime weekAfter = new DateTime(LocalDate.now().plusWeeks(1), LocalTime.now());
        assertEquals(weekAfter.getDate() + " " + weekAfter.getTime(), weekAfter.toShow());

        DateTime weekBefore = new DateTime(LocalDate.now().minusWeeks(1), LocalTime.now());
        assertEquals(weekBefore.getDate() + " " + weekBefore.getTime() + " [OVER!!]", weekBefore.toShow());
    }
}
