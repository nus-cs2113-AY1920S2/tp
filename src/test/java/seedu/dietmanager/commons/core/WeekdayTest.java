package seedu.dietmanager.commons.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeekdayTest {

    @Test
    void getIndex() {
        assertEquals(1, Weekday.MONDAY.getIndex());
        assertEquals(2, Weekday.TUESDAY.getIndex());
        assertEquals(3, Weekday.WEDNESDAY.getIndex());
        assertEquals(4, Weekday.THURSDAY.getIndex());
        assertEquals(5, Weekday.FRIDAY.getIndex());
        assertEquals(6, Weekday.SATURDAY.getIndex());
        assertEquals(7, Weekday.SUNDAY.getIndex());
    }

    @Test
    void getName() {
        assertEquals("MONDAY", Weekday.MONDAY.getName());
        assertEquals("TUESDAY", Weekday.TUESDAY.getName());
        assertEquals("WEDNESDAY", Weekday.WEDNESDAY.getName());
        assertEquals("THURSDAY", Weekday.THURSDAY.getName());
        assertEquals("FRIDAY", Weekday.FRIDAY.getName());
        assertEquals("SATURDAY", Weekday.SATURDAY.getName());
        assertEquals("SUNDAY", Weekday.SUNDAY.getName());
    }
}