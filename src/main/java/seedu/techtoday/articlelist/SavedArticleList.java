package seedu.techtoday.articlelist;

import java.util.ArrayList;

import seedu.techtoday.objects.Article;

/** Represents the data structure, i.e a list of Tasks, that stores the tasks.*/
public class SavedArticleList {

    /** Class variable that represents a the savedArticle list. */
    public static ArrayList<Article> savedArticleList;

    /** Initializes a new taskList.  */
    public SavedArticleList() {
        savedArticleList = new ArrayList<>();
    }

    /**
     * Function that sorts article objects by title.
     */
    public static void sort() {
        savedArticleList.sort(new ArticleTitleSorter());
    }

}
