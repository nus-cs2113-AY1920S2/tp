package seedu.techtoday.articlelist;

import seedu.techtoday.objects.Article;

import java.util.ArrayList;

/** Represents the data structure, i.e a list of Tasks, that stores the tasks.*/
public class ViewedArticleList {

    /** Class variable that represents a the viewedArticle list. */
    public static ArrayList<Article> viewedArticleList;

    /** Initializes a new taskList.  */
    public ViewedArticleList() {
        viewedArticleList = new ArrayList<Article>();
    }

}