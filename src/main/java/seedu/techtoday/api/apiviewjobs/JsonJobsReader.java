package seedu.techtoday.api.apiviewjobs;

import java.io.IOException;
import org.json.JSONException;
import seedu.techtoday.joblist.JobListPrinter;
import seedu.techtoday.joblist.ViewedJobList;


/** Represents a class that reads a URL as a JSON objecs. */
public class JsonJobsReader {

    /**
     * Takes a url with a list of user stories identified by their story number(check url) and picks 5
     * top stories for the user to view.
     *
     */
    public static void viewNewJobs() throws IOException, JSONException {
        ViewedJobListCreator.execute("https://hacker-news.firebaseio.com/v0/askstories.json?print=pretty");
        JobListPrinter.execute(ViewedJobList.viewedJobList);
    }
}

