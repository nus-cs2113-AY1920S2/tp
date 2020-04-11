package seedu.techtoday.articlelist;

import seedu.techtoday.objects.Article;

import java.util.ArrayList;

/** Represents the data structure, i.e a list of Tasks, that stores the tasks.*/
public class SavedArticleList {

    /** Class variable that represents a the savedArticle list. */
    public static ArrayList<Article> savedArticleList;

    /** Initializes a new taskList.  */
    public SavedArticleList() {
        savedArticleList = new ArrayList<>();
    }

}
