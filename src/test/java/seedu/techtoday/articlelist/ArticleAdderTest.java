package seedu.techtoday.articlelist;


import org.junit.jupiter.api.Test;
import seedu.techtoday.objects.Article;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArticleAdderTest {

    @Test
    void execute() {
        ArrayList<Article> articleList = new ArrayList<Article>();
        ArticleAdder.execute(articleList, new Article("Test", "www.test.com", "article"));
        assertEquals(1, articleList.size());
    }

    @Test
    public void constructorTest() {
        ArticleAdder adder = new ArticleAdder();
        assertEquals(adder.viewedArticleList, new ArrayList<Article>());
    }
}