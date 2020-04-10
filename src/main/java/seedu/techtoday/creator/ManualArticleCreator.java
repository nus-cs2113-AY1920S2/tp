package seedu.techtoday.creator;

import seedu.techtoday.articlelist.SavedArticleList;
import seedu.techtoday.articlelist.ArticlePrinter;
import seedu.techtoday.objects.Article;
import seedu.techtoday.ui.Ui;

/** Class representing a method used to ceate an article based on user input. */
public class ManualArticleCreator {

    /** Executes the method used to create an article object using user inputs. */
    public static void execute() {
        String title = getArticleTitle();
        String url = getArticleUrl();
        String category = getArticleCategory();
        String extract = getArticleExtract();
        String epochSecond = CurrentTimeFetcher.execute();
        Article article = new Article(title, url, category);
        article.setTime(epochSecond);
        article.setExtract(extract);
        SavedArticleList.savedArticleList.add(article);
        System.out.println("Done, we have added the following article to your list of saved articles");
        ArticlePrinter.printIsolatedArticle(article);
    }

    /**
     * Function that gets article name from the user.
     * @return String representation of the title from user input.
     */
    public static String getArticleTitle() {
        System.out.println("Enter the title of the article?");
        return Ui.getCommand();
    }

    /**
     * Function that gets the URL from the user.
     * @return String representation of url from user input.
     */
    public static String getArticleUrl() {
        System.out.println("What is the URl of the article (type \"No URL\")?");
        return Ui.getCommand();
    }

    /**
     * Function that gets category from the user.
     * @return String representation of category from user input.
     */
    public static String getArticleCategory() {
        System.out.println("What is the category (type \"default\" if you don't know it)");
        return Ui.getCommand();
    }

    /**
     * Function that asks and gets extract from the user.
     * @return String representation of category from user input.
     */
    public static String getArticleExtract() {
        System.out.println("Would you like to add any extract?");
        return Ui.getCommand();
    }
}

