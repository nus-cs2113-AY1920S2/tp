package seedu.techtoday.api.apiviewnews;

import org.json.JSONException;
import org.json.JSONObject;
import seedu.techtoday.common.CommonMethods;
import seedu.techtoday.objects.Article;

/** Create news creates a JSON object for 1 news article fetched through the API. */
public class NewsCreator {

    /**
     * Returns an article object from a JSONObject representing a news article from the internet.
     *
     * @param json JSON object representing a news article.
     * @return Article - article representing jsonObject from internet.
     */
    public static Article convertToProcessedNews(JSONObject json) throws JSONException {
        String title = json.get("title").toString();
        String timeStamp = json.get("time").toString();
        assert (CommonMethods.checkIsLong(timeStamp)) : "Timestamp should be of long type";
        String url;
        try {
            url = json.get("url").toString();
        } catch (Exception e) {
            url = "URL Not provided";
        }
        Article article = new Article(title, url, "default");
        article.setTime(timeStamp);
        return article;
    }
}
