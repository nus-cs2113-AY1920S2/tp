package seedu.techtoday.creator;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ManualNoteCreatorTest {


    @Test
    void getNote() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String input = "testNote";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String actualOutput = ManualNoteCreator.getNote();
        assertEquals("testNote", actualOutput);
    }

    @Test
    void getUrl() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String input = "testUrl";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String actualOutput = ManualNoteCreator.getUrl();
        assertEquals("testUrl", actualOutput);
    }

    @Test
    void getExtract() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String input = "testExtract";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String actualOutput = ManualNoteCreator.getExtract();
        assertEquals("testExtract", actualOutput);
    }

    @Test
    void getCategory() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String input = "testCategory";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String actualOutput = ManualNoteCreator.getCategory();
        assertEquals("testCategory", actualOutput);
    }
}