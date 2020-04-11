package seedu.techtoday.articlelist;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SavedArticleListTest {

    @Test
    public void constructorTest() {
        SavedArticleList saved = new SavedArticleList();
        assertEquals(saved.savedArticleList, new ArrayList<>());
    }

}