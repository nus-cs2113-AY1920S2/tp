package seedu.techtoday.articlelist;

import org.junit.jupiter.api.Test;
import seedu.techtoday.objects.Article;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ViewedArticleListTest {

    @Test
    public void constructorTest() {
        ViewedArticleList viewed = new ViewedArticleList();
        assertEquals(viewed.viewedArticleList, new ArrayList<Article>());
    }

}