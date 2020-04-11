package seedu.techtoday.articlelist;

import java.util.ArrayList;
import seedu.techtoday.objects.Article;

/** Represents a class that adds an article object to an article list. */
public class ArticleAdder extends ViewedArticleList {

    public ArticleAdder() {
        super();
    }

    /**
     * Adds an article to an article list.
     *
     * @param articleList Array list of articles.
     * @param article An article object representing the description of an article.
     */
    public static void execute(ArrayList<Article> articleList, Article article) {
        articleList.add(article);
    }
}
