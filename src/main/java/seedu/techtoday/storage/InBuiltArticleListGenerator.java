package seedu.techtoday.storage;

import seedu.techtoday.articlelist.SavedArticleList;
import seedu.techtoday.articlelist.ViewedArticleList;
import seedu.techtoday.objects.Article;
import seedu.techtoday.articlelist.ArticleAdder;

/**
 * Class representing method to generate an article list when there is no internet.
 */
public class InBuiltArticleListGenerator {
    /**
     * Executes the generation of articles from existing information.
     */
    public static void execute() {

        String timeStampToday = "1584768812";

        // article 1
        String title1 = "How Tailscale Works";
        String url1 = "https://tailscale.com/blog/how-tailscale-works/";
        Article article1 = new Article(title1, url1, "default");
        article1.setTime(timeStampToday);

        // article 2
        String title2 = "Tesla Model 3 Vulnerability – Disable Autopilot Notifications, Speedometer, etc.";
        String url2 = "https://safekeepsecurity.com/about/cve-2020-10558/";
        Article article2 = new Article(title2, url2, "default");
        article2.setTime(timeStampToday);

        // article 3
        String title3 = "It never makes sense to use foldl on lists";
        String url3 = "https://github.com/hasura/graphql-engine/pull/2933#discussion_r328821960";
        Article article3 = new Article(title3, url3, "default");
        article3.setTime(timeStampToday);

        // article 4
        String title4 = "George Gershwin, Then and Now";
        String url4 = "https://www.the-tls.co.uk/articles/summertime-crawford-cambridge-companion-to-gerschwin-calenza-review-russell-davies/";
        Article article4 = new Article(title4, url4, "default");
        article4.setTime(timeStampToday);

        // article 5
        String title5 = "AI for generative design: Plain text to 3D Designs";
        String url5 = "https://blog.insightdatascience.com/ai-for-3d-generative-design-17503d0b3943";
        Article article5 = new Article(title5, url5, "default");
        article5.setTime(timeStampToday);

        // article 6
        String title6 = "Furstenberg and Margulis awarded 2020 Abel Prize";
        String url6 = "https://www.nature.com/articles/d41586-020-00799-7";
        Article article6 = new Article(title6, url6, "default");
        article6.setTime(timeStampToday);

        ArticleAdder.execute(ViewedArticleList.viewedArticleList, article1);
        ArticleAdder.execute(ViewedArticleList.viewedArticleList, article2);
        ArticleAdder.execute(ViewedArticleList.viewedArticleList, article3);
        ArticleAdder.execute(ViewedArticleList.viewedArticleList, article4);
        ArticleAdder.execute(ViewedArticleList.viewedArticleList, article5);
        ArticleAdder.execute(ViewedArticleList.viewedArticleList, article6);
    }
}
