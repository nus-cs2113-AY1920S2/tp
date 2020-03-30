package seedu.techtoday.commands;

import seedu.techtoday.articlelist.ArticleSaver;
import seedu.techtoday.articlelist.SavedArticleList;
import seedu.techtoday.common.Messages;
import seedu.techtoday.exception.TechTodayException;
import seedu.techtoday.joblist.SavedJobList;
import seedu.techtoday.notelist.NoteSaver;
import seedu.techtoday.notelist.SavedNoteList;

/** Class representing the saving of articles, jobs and notes from their respective lists. */
public class InformationSaver {

    /**
     * Executes the saving of a note/article/job.
     * @param userResponse - Input of the user in the command line.
     */
    public static void execute(String userResponse) throws TechTodayException {
        try {
            String type = userResponse.split(" ")[1];
            switch (type) {
            case "article": {
                ArticleSaver.execute(SavedArticleList.savedArticleList, userResponse);
                Messages.printStraightLine();
                break;
            }
            case "job": {
                seedu.techtoday.joblist.JobSaver.execute(SavedJobList.savedJobList, userResponse);
                Messages.printStraightLine();
                break;
            }
            case "note": {
                NoteSaver.execute(SavedNoteList.savedNoteList, userResponse);
                Messages.printStraightLine();
                break;
            }
            default: {
                System.out.println("Save command or its index number is incorrect. "
                        + "It should be of the following form: \n");
                Messages.printInCenter("save [article / job] [index number (optional)]");
                Messages.printStraightLine();
            }
            }
            return;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry! You can only save article/job with valid index number!\n"
                    + "Maybe you havent viewed any articles to save \n\n"
                    + "Try \" view [article \\ job] \" or a differnt command");
            Messages.printStraightLine();
            return;
        } catch (NumberFormatException e) {
            System.out.println("Sorry! Please enter a valid index number for the save command. Try again.");
            Messages.printStraightLine();
            return;
        }
    }
}
