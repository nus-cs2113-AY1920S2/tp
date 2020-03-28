package modulelogic;

import exception.InvalidUrlException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static common.Messages.MESSAGE_MODULECODE_IN_BLACKLIST;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LessonsGeneratorTest {
    LessonsGenerator myLessonsGenerator;

    @Test
    public void generate_blacklistedModule() throws InvalidUrlException {
        myLessonsGenerator = new LessonsGenerator("https://nusmods.com/timetable/sem-2/share?CG2023=LAB:03,PLEC:03,PTUT:03&CG2027=LEC:01,TUT:01&CG2028=LAB:02,TUT:01,LEC:01&CS2101=&CS2107=TUT:09,LEC:1&CS2113T=LEC:C01&EG3301R=LEC:1,LAB:G15");
        String expected = myLessonsGenerator.generate();
        assertEquals(expected, MESSAGE_MODULECODE_IN_BLACKLIST);
    }

    @Test
    public void generate_correctLessonDetails() throws InvalidUrlException {
        myLessonsGenerator = new LessonsGenerator("https://nusmods.com/timetable/sem-1/share?CG1111=TUT:04,LAB:02");
        myLessonsGenerator.generate();
        ArrayList<String[]> expected = myLessonsGenerator.getLessonDetails();
        ArrayList<String[]> actual = new ArrayList<>();
        actual.add(new String[] {"1400", "1600", "Friday", "1:2:3:4:5:6:7:8:9:10:11:12:13"});
        actual.add(new String[] {"1400", "1700", "Wednesday", "1:2:3:4:5:6:7:8:9:10:11:12:13"});
        actual.add(new String[] {"1400", "1700", "Monday", "1:2:3:4:5:6:7:8:9:10:11:12:13"});
        for (int i = 0; i < expected.size(); i++){
            assertArrayEquals(expected.get(i), actual.get(i));
        }
    }
}
