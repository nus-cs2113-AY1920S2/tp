package logic.modulelogic;

import common.exception.InvalidUrlException;
import common.exception.WfException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static common.Messages.MESSAGE_MODULECODE_IN_BLACKLIST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LessonsGeneratorTest {
    LessonsGenerator myLessonsGenerator;

    @Test
    public void generate_blacklistedModule() throws InvalidUrlException, WfException {
        // Will only throw exception if nusmods link is broken/invalid
        myLessonsGenerator = new LessonsGenerator("https://nusmods.com/timetable/sem-2/share?CG2023=LAB:03,PLEC:03,PTUT:03&CG2027=LEC:01,TUT:01&CG2028=LAB:02,TUT:01,LEC:01&CS2101=&CS2107=TUT:09,LEC:1&CS2113T=LEC:C01&EG3301R=LEC:1,LAB:G15");
        String expected = myLessonsGenerator.generate();
        assertEquals(expected, MESSAGE_MODULECODE_IN_BLACKLIST);
    }

    @Test
    public void generate_correctLessonDetails() throws InvalidUrlException, WfException {
        // Will only throw exception if nusmods link is broken/invalid
        myLessonsGenerator = new LessonsGenerator("https://nusmods.com/timetable/sem-1/share?CG1111=TUT:04,LAB:02");
        myLessonsGenerator.generate();
        Set<String> actual = new HashSet<>();
        actual.add("14001600Friday1:2:3:4:5:6:7:8:9:10:11:12:13");
        actual.add("14001700Wednesday1:2:3:4:5:6:7:8:9:10:11:12:13");
        actual.add("14001700Monday1:2:3:4:5:6:7:8:9:10:11:12:13");
        ArrayList<String[]> expectedArray = myLessonsGenerator.getLessonDetails();
        ArrayList<String> expected = new ArrayList<>();
        for (String[] strings : expectedArray) {
            expected.add(String.join("", strings));
        }
        for (String string : expected) {
            assertTrue(actual.contains(string));
        }
    }
}
