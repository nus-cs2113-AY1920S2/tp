package seedu.techtoday.commands;

import seedu.techtoday.articlelist.ArticleSaver;
import seedu.techtoday.articlelist.SavedArticleList;
import seedu.techtoday.articlelist.ViewedArticleList;
import seedu.techtoday.common.Messages;
import seedu.techtoday.joblist.SavedJobList;
import seedu.techtoday.joblist.ViewedJobList;


/** Class representing the saving of articles, jobs and notes from their respective lists. */
public class InformationSaver {

    /**
     * Executes the saving of a note/article/job.
     *
     * @param userResponse - Input of the user in the command line.
     */
    public static void execute(String userResponse) {
        try {
            String type = userResponse.split(" ")[1];
            switch (type) {
            case "article": {
                if (ViewedArticleList.viewedArticleList.size() == 0) {
                    System.out.println("Sorry, you have not viewed any article to save.");
                    System.out.println("Try \"view article\" first");
                    Messages.printStraightLine();
                } else {
                    ArticleSaver.execute(SavedArticleList.savedArticleList, userResponse);
                    Messages.printStraightLine();
                }
                break;
            }
            case "job": {
                if (ViewedJobList.viewedJobList.size() == 0) {
                    System.out.println("Sorry, you have not viewed any job to save.");
                    System.out.println("Try \"view job\" first");
                    Messages.printStraightLine();
                } else {
                    seedu.techtoday.joblist.JobSaver.execute(SavedJobList.savedJobList, userResponse);
                    Messages.printStraightLine();
                }
                break;
            }
            default: {
                System.out.println("Save command is incorrect. "
                        + "It should be of the following form: \n");
                Messages.printInCenter("save [article / job] INDEX_NUMBER");
                Messages.printStraightLine();
            }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry! You can only save article/job with valid index number!\n"
                    + "Try \"save [article \\ job] VALID_INDEX_NUMBER\"");
            Messages.printStraightLine();
        } catch (NumberFormatException e) {
            System.out.println("Sorry! Please enter a valid index number for the save command. Try again.");
            Messages.printStraightLine();
        }
    }
}
