package seedu.nuke.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DateTimeTest {

    DateTime dateTime = new DateTime(DateTimeFormat.stringToDate("08082020"), DateTimeFormat.stringToTime("4:38PM"));
    DateTime dateTimeSecond = new DateTime(DateTimeFormat.stringToTime("1800"));
    DateTime emptyDateTime = new DateTime();


    DateTimeTest() throws DateTimeFormat.InvalidTimeException, DateTimeFormat.InvalidDateException {
    }

    @Test
    void isPresent() {
        assertTrue(dateTime.isPresent());
        assertFalse(emptyDateTime.isPresent());
    }

    @Test
    void getDate() throws DateTimeFormat.InvalidDateException {
        assertEquals(DateTimeFormat.stringToDate("08/08/2020"), dateTime.getDate());
        assertEquals(LocalDate.now(), dateTimeSecond.getDate());
    }

    @Test
    void getTime() throws DateTimeFormat.InvalidTimeException {
        assertEquals(DateTimeFormat.stringToTime("04:38PM"), dateTime.getTime());
    }

    @Test
    void getDateTimeInSortFormat() throws DateTimeFormat.InvalidDateTimeException {
        assertEquals("202008081638", dateTime.getDateTimeInSortFormat());
        assertEquals("__", emptyDateTime.getDateTimeInSortFormat());
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
    void toShow() throws DateTimeFormat.InvalidTimeException {
        DateTime today = new DateTime(LocalDate.now(), DateTimeFormat.stringToTime("2359"));
        assertEquals("today " + today.getTimeString(), today.toShow());

        DateTime tomorrow = new DateTime(LocalDate.now().plusDays(1), LocalTime.now());
        assertEquals("tomorrow " + tomorrow.getTimeString(), tomorrow.toShow());

        DateTime yesterday = new DateTime(LocalDate.now().minusDays(1), LocalTime.now());
        assertEquals(yesterday.getDateString() + " " + yesterday.getTimeString() + " [OVER!]", yesterday.toShow());

        DateTime weekAfter = new DateTime(LocalDate.now().plusWeeks(1), LocalTime.now());
        assertEquals(weekAfter.getDateString() + " " + weekAfter.getTimeString(), weekAfter.toShow());

        DateTime weekBefore = new DateTime(LocalDate.now().minusWeeks(1), LocalTime.now());
        assertEquals(weekBefore.getDateString() + " " + weekBefore.getTimeString() + " [OVER!]", weekBefore.toShow());
    }

    @Test
    void testToString() {
        DateTime today = new DateTime(LocalDate.now(), LocalTime.now());
        assertEquals(today.getDateString() + " " + today.getTimeString(), today.toString());

        DateTime tomorrow = new DateTime(LocalDate.now().plusDays(1), LocalTime.now());
        assertEquals(tomorrow.getDateString() + " " + tomorrow.getTimeString(), tomorrow.toString());

        DateTime yesterday = new DateTime(LocalDate.now().minusDays(1), LocalTime.now());
        assertEquals(yesterday.getDateString() + " " + yesterday.getTimeString(), yesterday.toString());

        DateTime weekAfter = new DateTime(LocalDate.now().plusWeeks(1), LocalTime.now());
        assertEquals(weekAfter.getDateString() + " " + weekAfter.getTimeString(), weekAfter.toString());

        DateTime weekBefore = new DateTime(LocalDate.now().minusWeeks(1), LocalTime.now());
        assertEquals(weekBefore.getDateString() + " " + weekBefore.getTimeString(), weekBefore.toString());
    }
}
