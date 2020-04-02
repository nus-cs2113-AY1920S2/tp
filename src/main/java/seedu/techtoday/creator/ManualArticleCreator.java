package seedu.techtoday.creator;

import seedu.techtoday.articlelist.SavedArticleList;
import seedu.techtoday.articlelist.ArticlePrinter;
import seedu.techtoday.objects.Article;
import seedu.techtoday.ui.Ui;

/** Class representing a method used to ceate an article based on user input. */
public class ManualArticleCreator {

    /** Executes the method used to create an article object using user inputs. */
    public static void execute() {
        System.out.println("Enter the title of the article?");
        String title = Ui.getCommand();
        System.out.println("What is the URl of the article (type \"No URL\")?");
        String url = Ui.getCommand();
        System.out.println("What is the category (type \"default\" if you don't know it)");
        String category = Ui.getCommand();
        System.out.println("Would you like to add any extract?");
        String extract = Ui.getCommand();
        String epochSecond = CurrentTimeFetcher.execute();
        Article article = new Article(title, url, category);
        article.setTime(epochSecond);
        article.setExtract(extract);
        SavedArticleList.savedArticleList.add(article);
        System.out.println("Done, we have added the following article to your list of saved articles");
        ArticlePrinter.printIsolatedArticle(article);
    }
}

