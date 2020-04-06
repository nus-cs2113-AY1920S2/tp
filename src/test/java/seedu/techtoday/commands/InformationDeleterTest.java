package seedu.techtoday.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.techtoday.articlelist.ArticleAdder;
import seedu.techtoday.articlelist.SavedArticleList;
import seedu.techtoday.joblist.JobAdder;
import seedu.techtoday.joblist.SavedJobList;
import seedu.techtoday.notelist.NoteAdder;
import seedu.techtoday.notelist.SavedNoteList;
import seedu.techtoday.objects.Article;
import seedu.techtoday.objects.Job;
import seedu.techtoday.objects.Note;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InformationDeleterTest {

    @BeforeEach
    void setUp() {
        // article
        SavedArticleList.savedArticleList = new ArrayList<>();
        ArticleAdder.execute(SavedArticleList.savedArticleList, new Article("Test1", "www.test1.com", "article"));
        ArticleAdder.execute(SavedArticleList.savedArticleList, new Article("Test2", "www.test2.com", "article"));
        ArticleAdder.execute(SavedArticleList.savedArticleList, new Article("Test3", "www.test3.com", "article"));

        // job
        SavedJobList.savedJobList = new ArrayList<>();
        JobAdder.execute(SavedJobList.savedJobList, new Job("Test1", "www.test1.com", "job"));
        JobAdder.execute(SavedJobList.savedJobList, new Job("Test2", "www.test2.com", "job"));
        JobAdder.execute(SavedJobList.savedJobList, new Job("Test3", "www.test3.com", "job"));

        // note
        SavedNoteList.savedNoteList = new ArrayList<>();
        NoteAdder.execute(SavedNoteList.savedNoteList, new Note("Test1"));
        NoteAdder.execute(SavedNoteList.savedNoteList, new Note("Test2"));
        NoteAdder.execute(SavedNoteList.savedNoteList, new Note("Test3"));
    }

    @Test
    void execute_article_success() {
        // All system.out.println statements will be in outContent
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        // execute command
        InformationDeleter.execute("delete article 1");

        String expectedOutput = "Deleting the following article:"
                + System.lineSeparator()
                + "   Title: Test1"
                + System.lineSeparator()
                + "   Date: "
                + System.lineSeparator()
                + "   Category: article"
                + System.lineSeparator()
                + "   Url: www.test1.com"
                + System.lineSeparator()
                + "   Extract: "
                + System.lineSeparator()
                + "__________________________________________________________________________________________"
                + System.lineSeparator()
                + ""
                + System.lineSeparator()
                + ""
                + System.lineSeparator()
                + ""
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void execute_job_success() {
        // All system.out.println statements will be in outContent
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        // execute command
        InformationDeleter.execute("delete job 1");

        String expectedOutput = "Deleting the following job:"
                + System.lineSeparator()
                + "   Title: Test1"
                + System.lineSeparator()
                + "   Date: "
                + System.lineSeparator()
                + "   Category: job"
                + System.lineSeparator()
                + "   Text: www.test1.com"
                + System.lineSeparator()
                + "   Extract: "
                + System.lineSeparator()
                + "__________________________________________________________________________________________"
                + System.lineSeparator()
                + ""
                + System.lineSeparator()
                + ""
                + System.lineSeparator()
                + ""
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void execute_note_success() {
        // All system.out.println statements will be in outContent
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        // execute command
        InformationDeleter.execute("delete note 1");

        String expectedOutput = "Deleting the following note:"
                + System.lineSeparator()
                + "   Title: Test1"
                + System.lineSeparator()
                + "   Date: "
                + System.lineSeparator()
                + "   Category: null"
                + System.lineSeparator()
                + "   URL: null"
                + System.lineSeparator()
                + "   Extract: "
                + System.lineSeparator()
                + "__________________________________________________________________________________________"
                + System.lineSeparator()
                + ""
                + System.lineSeparator()
                + ""
                + System.lineSeparator()
                + ""
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }
}