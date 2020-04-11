package seedu.techtoday.api.apiviewjobs;

import org.json.JSONObject;
import seedu.techtoday.common.CommonMethods;
import seedu.techtoday.objects.Job;

/** Create news creates a JSON object for 1 news article fetched through the API. */
public class JobsCreator {

    /**
     * Returns an article object from a JSONObject representing a news article from the internet.
     * @param json JSON object representing a news article.
     * @return Article - article representing jsonObject from internet.
     */
    public static Job convertToProcessedJob(JSONObject json) {
        String title = json.get("title").toString();
        String timeStamp = json.get("time").toString();
        assert (CommonMethods.checkIsLong(timeStamp)) : "Timestamp should be of long type";
        String text;
        try {
            text = json.get("text").toString();
        } catch (Exception e) {
            text = "text Not provided";
        }
        Job job = new Job(title, text, "default");
        job.setTime(timeStamp);
        return job;
    }
}
