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
    void execute_articleSizeList_success() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        InformationDeleter.execute("delete article 1");
        assertEquals(2,SavedArticleList.savedArticleList.size());
;
    }

    @Test
    void execute_jobSizeList_success() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        InformationDeleter.execute("delete job 1");
        assertEquals(2, SavedJobList.savedJobList.size());
    }

    @Test
    void execute_noteSizeList_success() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        InformationDeleter.execute("delete note 1");
        assertEquals(2,SavedNoteList.savedNoteList.size());
    }
}