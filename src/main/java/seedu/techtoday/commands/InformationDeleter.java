package seedu.techtoday.commands;

import seedu.techtoday.articlelist.ArticleDeleter;
import seedu.techtoday.common.Messages;
import seedu.techtoday.joblist.JobDeleter;
import seedu.techtoday.joblist.SavedJobList;
import seedu.techtoday.notelist.NoteDeleter;
import seedu.techtoday.notelist.SavedNoteList;

/** Class representing the deletion of articles, jobs and notes from their respective lists. */
public class InformationDeleter {

    /**
     * Executes the deletion of a note/article/job.
     * @param userResponse - Input of the user in the command line.
     */
    public static void execute(String userResponse) {
        try {
            String type = userResponse.split(" ")[1];
            switch (type) {
            case "article": {
                ArticleDeleter.execute(userResponse);
                Messages.printStraightLine();
                break;
            }
            case "job": {
                JobDeleter.execute(SavedJobList.savedJobList, userResponse);
                Messages.printStraightLine();
                break;
            }
            case "note": {
                NoteDeleter.execute(SavedNoteList.savedNoteList, userResponse);
                Messages.printStraightLine();
                break;
            }
            default: {
                System.out.println("Delete command or its index incorrect. It should be of the following form: \n");
                Messages.printInCenter("delete [article / job / note] [valid index number]");
                Messages.printStraightLine();
            }
            }
            return;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry! You can only delete article/job/note with valid index number! "
                    + "Your command format is incorrect. Try again ");
            Messages.printStraightLine();
            return;
        } catch (NumberFormatException e) {
            System.out.println("Sorry! Please enter a valid index number for the delete command. Try again.");
            Messages.printStraightLine();
            return;
        }
    }
}
