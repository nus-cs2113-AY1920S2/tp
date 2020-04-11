package seedu.duke.converter;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TimeConverterTest {

    @Test
    void testOldDate() throws ParseException {
        String oldDate = "29/3/2020";
        assertEquals("Sun 29 Mar 2020", TimeConverter.oldDate(oldDate));
    }

    @Test
    void testOldTime() throws ParseException {
        String oldTime = "2315";
        assertEquals("11:15 PM", TimeConverter.oldTime(oldTime));
    }

    @Test
    void testOldDate_isWrongFormat() {
        final String testInput1 = "22 feb 2020";
        final String testInput2 = "31-02-2019";
        final String testInput3 = "2020/04/22";
        final String testInput4 = "05/22/2023";

        ArrayList<String> testInputs = new ArrayList<>();
        testInputs.add(testInput1);
        testInputs.add(testInput2);
        testInputs.add(testInput3);
        testInputs.add(testInput4);

        for (String wrongUserInput : testInputs) {
            try {
                String time = TimeConverter.oldTime(wrongUserInput);
            } catch (ParseException e) {
                assertTrue(true);
            }
        }
    }
    
    @Test
    void testConvertTime_isRightFormat() throws ParseException {
        final String testInput1 = "1:00 PM";
        final String testInput2 = "11:59 PM";
        final String testInput3 = "12:00 AM";
        assertEquals("1300", TimeConverter.convertTime(testInput1));
        assertEquals("2359", TimeConverter.convertTime(testInput2));
        assertEquals("0000", TimeConverter.convertTime(testInput3));
    }

    @Test
    void testConvertDate_isRightFormat() throws ParseException {
        final String testInput1 = "Sun 29 Mar 2020";
        final String testInput2 = "Tue 01 Jan 2019";
        assertEquals("29/03/2020", TimeConverter.convertDate(testInput1));
        assertEquals("01/01/2019", TimeConverter.convertDate(testInput2));
    }
}
