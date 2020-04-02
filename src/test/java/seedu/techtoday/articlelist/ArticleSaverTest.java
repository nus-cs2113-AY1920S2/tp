package seedu.techtoday.articlelist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.techtoday.objects.Article;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArticleSaverTest {

    @BeforeEach
    void setUp() {
        SavedArticleList.savedArticleList = new ArrayList<>();
        ViewedArticleList.viewedArticleList = new ArrayList<>();
        ArticleAdder.execute(ViewedArticleList.viewedArticleList, new Article("Test1", "www.test1.com", "article"));
        ArticleAdder.execute(ViewedArticleList.viewedArticleList, new Article("Test2", "www.test2.com", "article"));
        ArticleAdder.execute(ViewedArticleList.viewedArticleList, new Article("Test3", "www.test3.com", "article"));
    }

    @Test
    void execute() {
        // All system.out.println statements will be in outContent
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        // execute command
        ArticleSaver.execute(SavedArticleList.savedArticleList, "save article 2");

        String expectedOutput = "Done, saved the article with the following details:"
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

    @Test
    public void constructorTest() {
        ArticleSaver saved = new ArticleSaver();
        assertEquals(saved.savedArticleList, new ArrayList<>());
    }
}