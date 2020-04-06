package seedu.techtoday.articlelist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.techtoday.objects.Article;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArticleDeleterTest {

    @BeforeEach
    void setUp() {
        SavedArticleList.savedArticleList = new ArrayList<>();
        ArticleAdder.execute(SavedArticleList.savedArticleList, new Article("Test1", "www.test1.com", "article"));
        ArticleAdder.execute(SavedArticleList.savedArticleList, new Article("Test2", "www.test2.com", "article"));
        ArticleAdder.execute(SavedArticleList.savedArticleList, new Article("Test3", "www.test3.com", "article"));

    }

    @Test
    void execute() {
        // All system.out.println statements will be in outContent
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        // execute command
        ArticleDeleter.execute("delete article 2");

        String expectedOutput = "Deleting the following article:"
                + System.lineSeparator()
                + "   Title: Test2"
                + System.lineSeparator()
                + "   Date: "
                + System.lineSeparator()
                + "   Category: article"
                + System.lineSeparator()
                + "   Url: www.test2.com"
                + System.lineSeparator()
                + "   Extract: "
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }
}