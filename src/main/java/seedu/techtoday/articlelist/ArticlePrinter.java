package seedu.techtoday.articlelist;

import seedu.techtoday.common.CommonMethods;
import seedu.techtoday.common.TimeStampToDateConverter;
import seedu.techtoday.objects.Article;

/** Class that represents a command used to print an article. */
public class ArticlePrinter {

    /**
     * Executes the printing of an article.
     *
     * @param taskCounter - Index of the information in the information list.
     * @param article - Article representing an article object.
     */
    public static void execute(int taskCounter, Article article) {
        String articleToPrint = taskCounter + ". " + returnArticleString(article);
        System.out.println("   " + articleToPrint);
    }

    /**
     * Prints an article outside the setting of a article list.
     *
     * @param article - Article representing an article object.
     */
    public static void printIsolatedArticle(Article article) {
        String articleToPrint = returnArticleString(article);
        System.out.println("   " + articleToPrint);
    }

    /**
     * Retuns a string with the details of the article to be printed.
     *
     * @param article - Article under consideration.
     * @return String representing article.
     */
    private static String returnArticleString(Article article) {
        String timeStamp = article.getTimeStamp();
        String date = TimeStampToDateConverter.execute(timeStamp);
        String title = article.getTitle();
        String url = article.getUrl();
        String category = article.getCategory();
        String extract = article.getExtract();
        String upToNCharacters = CommonMethods.returnUptoNcharacters(extract);
        String articleToPrint = "Title: " + title + System.lineSeparator()
                + "   Date: " + date + System.lineSeparator()
                + "   Category: " + category + System.lineSeparator()
                + "   Url: " + url + System.lineSeparator()
                + "   Extract: " + upToNCharacters;
        return articleToPrint;
    }

}
