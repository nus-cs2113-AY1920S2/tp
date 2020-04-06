package seedu.techtoday.creator;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ManualArticleCreatorTest {

    @Test
    void getArticle_TestArticle_success() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String input = "TestArticle";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String actualOutput = ManualArticleCreator.getArticle();
        assertEquals("TestArticle", actualOutput);
    }

    @Test
    void getUrl_TestUrl_success() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String input = "www.testarticle.com";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String actualOutput = ManualArticleCreator.getUrl();
        assertEquals("www.testarticle.com", actualOutput);
    }

    @Test
    void getCategory_default_success() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String input = "default";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String actualOutput = ManualArticleCreator.getCategory();
        assertEquals("default", actualOutput);
    }

    @Test
    void getExtract_No_success() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String input = "no";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String actualOutput = ManualArticleCreator.getExtract();
        assertEquals("no", actualOutput);
    }
}