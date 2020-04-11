package seedu.techtoday.articlelist;

import seedu.techtoday.objects.Article;

/** Represents command that is used to call a method to delete article from taskList. */
public class ArticleDeleter {
    /**
     * Deletes the task at index taskNumber from the tasklist.
     *
     * @param command Command inputted by the user.
     */
    public static void execute(String command) {
        String[] commandArray = command.split(" ");
        int articleNumber = Integer.parseInt(commandArray[2]) - 1;
        Article articleToDelete = SavedArticleList.savedArticleList.get(articleNumber);
        System.out.println("Deleting the following article:");
        ArticlePrinter.printIsolatedArticle(articleToDelete);
        SavedArticleList.savedArticleList.remove(articleNumber);
    }

}
