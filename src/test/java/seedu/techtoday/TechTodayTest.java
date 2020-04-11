package seedu.techtoday;

import org.junit.jupiter.api.Test;
import seedu.techtoday.articlelist.SavedArticleList;
import seedu.techtoday.articlelist.ViewedArticleList;
import seedu.techtoday.joblist.SavedJobList;
import seedu.techtoday.joblist.ViewedJobList;
import seedu.techtoday.notelist.SavedNoteList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TechTodayTest {
    @Test
    public void constructorTest() {
        TechToday techtoday = new TechToday();
        assertEquals(techtoday.savedJobList.savedJobList, SavedJobList.savedJobList);
        assertEquals(techtoday.savedNoteList.savedNoteList, SavedNoteList.savedNoteList);
        assertEquals(techtoday.savedArticleList.savedArticleList, SavedArticleList.savedArticleList);
        assertEquals(techtoday.viewedArticleList.viewedArticleList, ViewedArticleList.viewedArticleList);
        assertEquals(techtoday.viewedJobList.viewedJobList, ViewedJobList.viewedJobList);
    }

    @Test
    void main() {
    }
}
