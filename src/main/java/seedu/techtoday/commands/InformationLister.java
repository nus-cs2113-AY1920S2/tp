package seedu.techtoday.commands;

import seedu.techtoday.articlelist.ArticleListPrinter;
import seedu.techtoday.articlelist.SavedArticleList;
import seedu.techtoday.common.Messages;
import seedu.techtoday.joblist.JobListPrinter;
import seedu.techtoday.joblist.SavedJobList;
import seedu.techtoday.notelist.NoteListPrinter;
import seedu.techtoday.notelist.SavedNoteList;

/** Class representing the listing of articles, jobs and notes from their respective lists. */
public class InformationLister {

    /**
     * Executes the listing of a note/article/job.
     * @param userResponse - Input of the user in the command line.
     */
    public static void execute(String userResponse) {
        try {
            String type = userResponse.split(" ")[1];
            switch (type) {
            case "article": {
                Messages.printInCenter("Article List");
                System.out.println("\n");
                ArticleListPrinter.execute(SavedArticleList.savedArticleList);
                break;
            }
            case "job": {
                Messages.printInCenter("Job List");
                System.out.println("\n");
                JobListPrinter.execute(SavedJobList.savedJobList);
                break;
            }
            case "note": {
                Messages.printInCenter("Note List");
                System.out.println("\n");
                NoteListPrinter.execute(SavedNoteList.savedNoteList);
                break;
            }
            default: {
                System.out.println("Invalid command for list!. It should be of "
                        + "the form \"list [article / job / note]\"");
            }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("List command is incorrect. It should be of the following form: \n");
            Messages.printInCenter("list [article / job / note]");
        }
    }
}
