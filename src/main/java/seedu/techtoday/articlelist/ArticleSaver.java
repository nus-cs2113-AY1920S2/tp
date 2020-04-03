package seedu.techtoday.articlelist;

import seedu.techtoday.objects.Article;

import java.util.ArrayList;

/** Class to save articles to SavedArticleList. */
public class ArticleSaver extends SavedArticleList {

    public ArticleSaver() {
        super();
    }

    /**
     * Function that executes the save features for articles.
     * @param viewedArticleList - list of articles to be saved to.
     * @param command - number of article to be saved.
     */
    public static void execute(ArrayList<Article> viewedArticleList, String command) {
        String[] commandArray = command.split(" ");
        int commandNumber = Integer.parseInt(commandArray[2]) - 1;
        Article articleToSave = ViewedArticleList.viewedArticleList.get(commandNumber);
        System.out.println("Done, saved the article with the following details:");
        ArticlePrinter.printIsolatedArticle(articleToSave);
        ArticleAdder.execute(SavedArticleList.savedArticleList, articleToSave);
    }
}
