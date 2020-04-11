package seedu.techtoday.articlelist;

import seedu.techtoday.common.Messages;
import seedu.techtoday.objects.Article;
import java.util.ArrayList;

/** Represents class that is used to call a method to delete article from taskList. */
public class ArticleListPrinter {
    /**
     * Prints all available tasks.
     *
     * @param articleList List that stores the tasks mentioned until now.
     */
    public static void execute(ArrayList<Article> articleList) {
        if (articleList.size() == 0) {
            Messages.printInCenter("There is nothing in the list currently.");
        }
        int taskCounter = 1;
        for (Article article : articleList) {
            ArticlePrinter.execute(taskCounter, article);
            taskCounter += 1;
            System.out.println("\n");
        }
    }
}
