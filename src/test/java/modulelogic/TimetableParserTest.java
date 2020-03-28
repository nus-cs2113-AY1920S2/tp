package modulelogic;

import exception.InvalidUrlException;
import org.junit.jupiter.api.Test;

import static common.Messages.MESSAGE_RETURN_SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TimetableParserTest {
    TimetableParser myTimetableParser;

    @Test
    public void parse_wrongLink() {
        InvalidUrlException e = null;
        try {
            myTimetableParser = new TimetableParser("https://facebook.com");
        } catch (InvalidUrlException ex) {
            e = ex;
        }
        assertNotNull(e);
    }

    @Test
    public void parse_garbageLink() {
        InvalidUrlException e = null;
        try {
            myTimetableParser = new TimetableParser("https://www.nusmods.com/timetable/abcddddd");
        } catch (InvalidUrlException ex) {
            e = ex;
        }
        assertNotNull(e);
    }

    @Test
    public void parse_correctLink() throws InvalidUrlException {
        myTimetableParser = new TimetableParser("https://nusmods.com/timetable/sem-2/share?CG2023=LAB:03,PLEC:03,PTUT:03&CG2027=LEC:01,TUT:01&CG2028=LAB:02,TUT:01,LEC:01&CS2101=&CS2107=TUT:09,LEC:1&CS2113T=LEC:C01");
        String expected = myTimetableParser.parse();
        assertEquals(expected, MESSAGE_RETURN_SUCCESS);

    }
}
