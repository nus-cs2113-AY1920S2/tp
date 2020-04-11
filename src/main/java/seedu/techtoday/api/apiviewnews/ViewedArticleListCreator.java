package seedu.techtoday.api.apiviewnews;

import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import seedu.techtoday.api.UrlJsonParser;
import seedu.techtoday.articlelist.ViewedArticleList;
import seedu.techtoday.objects.Article;

/** Represents a creates a list of news that can be viewed by the user. */
public class ViewedArticleListCreator {

    /**
     * Takes a url and pick 5 top stories to print.
     *
     * @param url URL  with a list of user stories identified by their story number(check url)
     *            (example: "https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty").
     * @throws IOException IO exception.
     * @throws JSONException JSON exception.
     */
    public static void execute(String url) throws IOException, JSONException {

        String indicesStr = UrlJsonParser.readJsonArrayfromUrl(url);
        int[] indicesArr = UrlJsonParser.stringToArray(indicesStr);
        for (int i = 0; i < indicesArr.length; i++) {
            String headOfUrl = "https://hacker-news.firebaseio.com/v0/item/";
            String midUrl = Integer.toString(indicesArr[i]);
            String tailUrl = ".json?print=pretty";
            String finalUrl = headOfUrl + midUrl + tailUrl;
            JSONObject rawNews = UrlJsonParser.readJsonFromUrl(finalUrl);
            Article processedArticle = NewsCreator.convertToProcessedNews(rawNews);
            ViewedArticleList.viewedArticleList.add(processedArticle);
        }
    }
}
