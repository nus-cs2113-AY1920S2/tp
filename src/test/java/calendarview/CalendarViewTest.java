package calendarview;

import exception.CalendarException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static ui.Constants.INVALID_MONTH;
import static ui.Constants.INVALID_MONTH_RANGE;
import static ui.Constants.INVALID_YEAR;
import static ui.Constants.NON_INTEGER_YEAR;
import static ui.Constants.ONLY_MONTH_AND_YEAR;

//@@author NizarMohd
class CalendarViewTest {

    /**
     *Raise an exception pertaining to an invalid lower bound range of month input.
     */
    @Test
    void setMonth_throwsException_InvalidMonthRange_lowerBound() {
        String in = "-1";
        try {
            CalendarView.setMonth(in);
            fail("Should have raised an exception");
        } catch (CalendarException e) {
            assertEquals(INVALID_MONTH_RANGE, e.getMessage());
        }
    }

    /**
     *Raise an exception pertaining to an invalid upper bound range of month input.
     */
    @Test
    void setMonth_throwsException_InvalidMonthRange_upperBound() {
        String in = "13";
        try {
            CalendarView.setMonth(in);
            fail("Should have raised an exception");
        } catch (CalendarException e) {
            assertEquals(INVALID_MONTH_RANGE, e.getMessage());
        }
    }

    /**
     * Raise an exception pertaining non-integer input of month value.
     */
    @Test
    void setMonth_throwsException_InvalidMonth() {
        String in = "may";
        try {
            CalendarView.setMonth(in);
            fail("Should have raised an exception");
        } catch (CalendarException e) {
            assertEquals(INVALID_MONTH, e.getMessage());
        }
    }

    /**
     * Throws an exception for year input that has an integer value lesser than the current year..
     */
    @Test
    void setYear_throwsException_InvalidYear() {
        String in = "1999";
        try {
            CalendarView.setYear(in);
            fail("Should have raised an exception");
        } catch (CalendarException e) {
            assertEquals(INVALID_YEAR, e.getMessage());
        }
    }

    /**
     * Throws an exception for year input that is not an integer value.
     */
    @Test
    void setYear_throwsException_nonIntegerYear() {
        String in = "twenty-twenty";
        try {
            CalendarView.setYear(in);
            fail("Should have raised an exception");
        } catch (CalendarException e) {
            assertEquals(NON_INTEGER_YEAR, e.getMessage());
        }
    }

    /**
     * Throws an exception pertaining to input length.
     */
    @Test
    void checkInputLength() {
        String in = "month and year";
        String[] input = in.split(" ");
        try {
            CalendarView.checkInputLength(input);
            fail("Should have raised an exception");
        } catch (CalendarException e) {
            assertEquals(ONLY_MONTH_AND_YEAR, e.getMessage());
        }
    }

    /**
     * Checks for concatenation of two error message.
     */
    @Test
    void setMonthAndYear_invalidMonth_invalidYear() {
        String[] input = {"may", "1999"};
        String expected = INVALID_MONTH + " Also, " + INVALID_YEAR;
        assertEquals(expected, CalendarView.setMonthAndYear(input));
    }

    /**
     * Checks for concatenation of two error message.
     */
    @Test
    void setMonthAndYear_invalidMonthRange_invalidYear() {
        String[] input = {"-1", "1999"};
        String expected = INVALID_MONTH_RANGE + " Also, " + INVALID_YEAR;
        assertEquals(expected, CalendarView.setMonthAndYear(input));
    }

    /**
     * Checks for concatenation of two error message.
     */
    @Test
    void setMonthAndYear_invalidMonth_nonIntegerYear() {
        String[] input = {"may", "oneNineNineNine"};
        String expected = INVALID_MONTH + " Also, " + NON_INTEGER_YEAR;
        assertEquals(expected, CalendarView.setMonthAndYear(input));
    }

}