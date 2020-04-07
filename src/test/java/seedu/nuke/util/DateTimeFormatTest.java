package seedu.nuke.util;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class DateTimeFormatTest {

    @Test
    void stringToDateTime_emptyString() throws DateTimeFormat.InvalidDateTimeException {
        DateTime emptyDateTime = DateTimeFormat.stringToDateTime("  ");
        assertFalse(emptyDateTime.isPresent());

        DateTime emptyDateTimeSecond = DateTimeFormat.stringToDateTime("");
        assertFalse(emptyDateTimeSecond.isPresent());
    }

    @Test
    void stringToDateTime_dateOnly() throws DateTimeFormat.InvalidDateTimeException {
        DateTime dateTime = DateTimeFormat.stringToDateTime("1/1/20");
        assertTrue(dateTime.isPresent());

        assertEquals("01/01/2020", dateTime.getDateString());
        assertEquals("11:59PM", dateTime.getTimeString());
    }

    @Test
    void stringToDateTime_timeOnly() throws DateTimeFormat.InvalidDateTimeException {
        DateTime dateTime = DateTimeFormat.stringToDateTime("11am");
        assertTrue(dateTime.isPresent());

        assertEquals(LocalDate.now(), dateTime.getDate());
        assertEquals("11:00AM", dateTime.getTimeString());
    }

    @Test
    void stringToDateTime_bothDateAndTime() throws DateTimeFormat.InvalidDateTimeException {
        DateTime dateTime = DateTimeFormat.stringToDateTime("monday 7.00");
        assertTrue(dateTime.isPresent());

        assertEquals(DateTimeFormat.getNextDateOfDay(DayOfWeek.MONDAY), dateTime.getDate());
        assertEquals("07:00AM", dateTime.getTimeString());
    }

    @Test
    void stringToDateTime_moreThanTwoParameters() throws DateTimeFormat.InvalidDateTimeException {
        try {
            DateTime dateTime = DateTimeFormat.stringToDateTime("wed 7.00 fri");
        } catch (DateTimeFormat.InvalidDateTimeException e) {
            assertTrue(true);
        }

        try {
            DateTime dateTime = DateTimeFormat.stringToDateTime("1 2 3 4 5");
        } catch (DateTimeFormat.InvalidDateTimeException e) {
            assertTrue(true);
        }
    }

    @Test
    void stringToDateTime_invalidDateTime() {
        try {
            DateTimeFormat.stringToDateTime("x_x 6am");
            fail("Should catch a datetime exception here");
        } catch (DateTimeFormat.InvalidDateTimeException e) {
            assertTrue(true);
        }

        try {
            DateTimeFormat.stringToDateTime("tue 123456");
            fail("Should catch a datetime exception here");
        } catch (DateTimeFormat.InvalidDateTimeException e) {
            assertTrue(true);
        }
    }

    @Test
    void stringToDate_emptyDate() throws DateTimeFormat.InvalidDateException {
        assertEquals(LocalDate.now(), DateTimeFormat.stringToDate(null));
    }

    @Test
    void stringToDate() throws DateTimeFormat.InvalidDateTimeException {
        assertEquals(LocalDate.now(), DateTimeFormat.stringToDate("today"));
        assertEquals(LocalDate.now(), DateTimeFormat.stringToDate("tdy"));

        assertEquals(LocalDate.now().plusDays(1), DateTimeFormat.stringToDate("tomorrow"));
        assertEquals(LocalDate.now().plusDays(1), DateTimeFormat.stringToDate("tmr"));

        assertEquals(LocalDate.now().minusDays(1), DateTimeFormat.stringToDate("yesTerDay"));
        assertEquals(LocalDate.now().minusDays(1), DateTimeFormat.stringToDate("YST"));

        assertEquals(DateTimeFormat.getNextDateOfDay(DayOfWeek.MONDAY), DateTimeFormat.stringToDate("Mon"));
        assertEquals(DateTimeFormat.getNextDateOfDay(DayOfWeek.TUESDAY), DateTimeFormat.stringToDate("TuEsDaY"));
        assertEquals(DateTimeFormat.getNextDateOfDay(DayOfWeek.WEDNESDAY), DateTimeFormat.stringToDate("wed"));
        assertEquals(DateTimeFormat.getNextDateOfDay(DayOfWeek.THURSDAY), DateTimeFormat.stringToDate("THU"));
        assertEquals(DateTimeFormat.getNextDateOfDay(DayOfWeek.FRIDAY), DateTimeFormat.stringToDate("Fri"));
        assertEquals(DateTimeFormat.getNextDateOfDay(DayOfWeek.SATURDAY), DateTimeFormat.stringToDate("sAT"));
        assertEquals(DateTimeFormat.getNextDateOfDay(DayOfWeek.SUNDAY), DateTimeFormat.stringToDate("sundaY"));

        assertEquals(LocalDate.of(2020, 10, 10), DateTimeFormat.stringToDate("101020"));
        assertEquals(LocalDate.of(2020, 12, 6), DateTimeFormat.stringToDate("6/12"));
        assertEquals(LocalDate.of(2006, 4, 3), DateTimeFormat.stringToDate("3-4-06"));
        assertEquals(LocalDate.of(2020, 4, 9), DateTimeFormat.stringToDate("9/04"));
    }

    @Test
    void stringToDate_invalidDate() {
        try {
            DateTimeFormat.stringToDate("0/1/20");
            fail("Should catch a datetime exception here");
        } catch (DateTimeFormat.InvalidDateTimeException e) {
            assertTrue(true);
        }

        try {
            DateTimeFormat.stringToDate("32/1/20");
            fail("Should catch a datetime exception here");
        } catch (DateTimeFormat.InvalidDateTimeException e) {
            assertTrue(true);
        }

        try {
            DateTimeFormat.stringToDate("1/0");
            fail("Should catch a datetime exception here");
        } catch (DateTimeFormat.InvalidDateTimeException e) {
            assertTrue(true);
        }

        try {
            DateTimeFormat.stringToDate("1/13");
            fail("Should catch a datetime exception here");
        } catch (DateTimeFormat.InvalidDateTimeException e) {
            assertTrue(true);
        }

        try {
            DateTimeFormat.stringToDate("abc");
            fail("Should catch a datetime exception here");
        } catch (DateTimeFormat.InvalidDateTimeException e) {
            assertTrue(true);
        }
    }

    @Test
    void stringToTime_emptyTime() throws DateTimeFormat.InvalidTimeException {
        assertNull(DateTimeFormat.stringToTime(null));
    }

    @Test
    void stringToTime() throws DateTimeFormat.InvalidTimeException {
        assertEquals(LocalTime.of(13, 10), DateTimeFormat.stringToTime("1:10pM"));
        assertEquals(LocalTime.of(3, 0), DateTimeFormat.stringToTime("3"));
        assertEquals(LocalTime.of(6, 1), DateTimeFormat.stringToTime("601"));
        assertEquals(LocalTime.of(19, 6), DateTimeFormat.stringToTime("7.06pm"));
    }

    @Test
    void stringToTime_invalidDate() {
        try {
            DateTimeFormat.stringToTime("1/1/20");
            fail("Should catch a datetime exception here");
        } catch (DateTimeFormat.InvalidDateTimeException e) {
            assertTrue(true);
        }

        try {
            DateTimeFormat.stringToTime("8:60pm");
            fail("Should catch a datetime exception here");
        } catch (DateTimeFormat.InvalidDateTimeException e) {
            assertTrue(true);
        }

        try {
            DateTimeFormat.stringToTime("25:30");
            fail("Should catch a datetime exception here");
        } catch (DateTimeFormat.InvalidDateTimeException e) {
            assertTrue(true);
        }

        try {
            DateTimeFormat.stringToTime("12p");
            fail("Should catch a datetime exception here");
        } catch (DateTimeFormat.InvalidDateTimeException e) {
            assertTrue(true);
        }

        try {
            DateTimeFormat.stringToTime("a");
            fail("Should catch a datetime exception here");
        } catch (DateTimeFormat.InvalidDateTimeException e) {
            assertTrue(true);
        }
    }
}