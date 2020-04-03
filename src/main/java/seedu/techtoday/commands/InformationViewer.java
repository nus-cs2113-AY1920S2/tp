package seedu.techtoday.commands;

import seedu.techtoday.api.apiviewjobs.JsonJobsReader;
import seedu.techtoday.api.apiviewnews.JsonNewsReader;
import seedu.techtoday.articlelist.ArticleListPrinter;
import seedu.techtoday.articlelist.ViewedArticleList;
import seedu.techtoday.common.Messages;
import seedu.techtoday.joblist.JobListPrinter;
import seedu.techtoday.joblist.ViewedJobList;
import seedu.techtoday.storage.InBuiltArticleListGenerator;
import seedu.techtoday.storage.InBuiltJobListGenerator;

import java.io.IOException;

/** Class representing the viewing of articles, jobs and notes from their respective lists. */
public class InformationViewer {

    /**
     * Executes the viewing of a note/article/job.
     * @param userResponse - Input of the user in the command line.
     */
    public static void execute(String userResponse) {
        try {
            String type = userResponse.split(" ")[1];
            if (type.equals("job") || type.equals("article")) {
                System.out.println("Connecting to the internet and loading information... \n");
            }
            if (type.equals("job")) {
                if (ViewedJobList.viewedJobList.size() != 0) {
                    ViewedJobList.viewedJobList.clear();
                }
                try {
                    JsonJobsReader.viewNewJobs();
                } catch (IOException e) {
                    System.out.println("Your device is not connected to the internet, "
                            + "we will load pre-existing jobs \n");
                    InBuiltJobListGenerator.execute();
                    JobListPrinter.execute(ViewedJobList.viewedJobList);
                }
            } else if (type.equals("article")) {
                if (ViewedArticleList.viewedArticleList.size() != 0) {
                    ViewedArticleList.viewedArticleList.clear();
                }
                try {
                    JsonNewsReader.viewNewNews();
                } catch (IOException e) {
                    System.out.println("Your device is not connected to the internet, "
                            + "we will load pre-existing articles \n");
                    InBuiltArticleListGenerator.execute();
                    ArticleListPrinter.execute(ViewedArticleList.viewedArticleList);
                }
            } else {
                System.out.print("Sorry! You can only view article/job! "
                        + "Your command format is incorrect. Try again \n");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("View command is incorrect. It should be of the following form: \n");
            Messages.printInCenter("1. view [article / job]");
        }
    }
}
