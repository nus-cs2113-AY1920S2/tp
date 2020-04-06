package seedu.techtoday.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.techtoday.articlelist.ViewedArticleList;
import seedu.techtoday.objects.Article;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InBuiltArticleListGeneratorTest {

    @BeforeEach
    void setUp() {
        ViewedArticleList.viewedArticleList = new ArrayList<Article>();
        InBuiltArticleListGenerator.execute();
    }

    @Test
    void execute_SizeofArray_6() {
        assertEquals(6, ViewedArticleList.viewedArticleList.size());
    }

    @Test
    public void testExecute_articleTitles() {
        assertEquals(ViewedArticleList.viewedArticleList.get(0).getTitle(), "How Tailscale Works");
        assertEquals(ViewedArticleList.viewedArticleList.get(1).getTitle(), "Tesla Model 3 Vulnerability – "
                + "Disable Autopilot Notifications, Speedometer, etc.");
        assertEquals(ViewedArticleList.viewedArticleList.get(2).getTitle(), "It never makes "
                 + "sense to use foldl on lists");
        assertEquals(ViewedArticleList.viewedArticleList.get(3).getTitle(), "George Gershwin, Then and Now");
        assertEquals(ViewedArticleList.viewedArticleList.get(4).getTitle(), "AI for generative "
                + "design: Plain text to 3D Designs");
        assertEquals(ViewedArticleList.viewedArticleList.get(5).getTitle(), "Furstenberg and "
                + "Margulis awarded 2020 Abel Prize");
    }

    @Test
    public void testExecute_articleUrl() {
        assertEquals(ViewedArticleList.viewedArticleList.get(0).getUrl(), "https://tailscale.com/blog/how-tailscale-works/");
        assertEquals(ViewedArticleList.viewedArticleList.get(1).getUrl(), "https://safekeepsecurity.com/about/cve-2020-10558/");
        assertEquals(ViewedArticleList.viewedArticleList.get(2).getUrl(), "https://github.com/hasura/graphql-engine/pull/2933#discussion_r328821960");
        assertEquals(ViewedArticleList.viewedArticleList.get(3).getUrl(), "https://www.the-tls.co.uk/articles/summertime-crawford-cambridge-companion-to-gerschwin-calenza-review-russell-davies/");
        assertEquals(ViewedArticleList.viewedArticleList.get(4).getUrl(), "https://blog.insightdatascience.com/ai-for-3d-generative-design-17503d0b3943");
        assertEquals(ViewedArticleList.viewedArticleList.get(5).getUrl(), "https://www.nature.com/articles/d41586-020-00799-7");
    }
}