package seedu.techtoday.creator;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ManualJobCreatorTest {

    @Test
    void getTitle() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String input = "testJob";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String actualOutput = ManualJobCreator.getJobTitle();
        assertEquals("testJob", actualOutput);
    }

    @Test
    void getText() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String input = "Testing text";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String actualOutput = ManualJobCreator.getJobText();
        assertEquals("Testing text", actualOutput);
    }

    @Test
    void getCategory() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String input = "testCategory";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String actualOutput = ManualJobCreator.getJobCategory();
        assertEquals("testCategory", actualOutput);
    }

    @Test
    void getExtract() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String input = "testExtract";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String actualOutput = ManualJobCreator.getJobExtract();
        assertEquals("testExtract", actualOutput);
    }

}