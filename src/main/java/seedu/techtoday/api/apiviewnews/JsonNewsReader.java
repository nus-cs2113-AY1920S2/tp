package seedu.techtoday.api.apiviewnews;

import java.io.IOException;
import org.json.JSONException;
import seedu.techtoday.articlelist.ArticleListPrinter;
import seedu.techtoday.articlelist.ViewedArticleList;

/** Represents a class that reads a URL as a JSON objecs. */
public class JsonNewsReader {

    /**
     * Takes a url with a list of user stories identified by their story number(check url) and picks 5
     * top stories for the user to view.
     *
     */
    public static void viewNewNews() throws IOException, JSONException {
        ViewedArticleListCreator.execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty");
        ArticleListPrinter.execute(ViewedArticleList.viewedArticleList);
    }
}

