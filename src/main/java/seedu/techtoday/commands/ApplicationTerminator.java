package seedu.techtoday.commands;

import seedu.techtoday.articlelist.SavedArticleList;
import seedu.techtoday.common.Messages;
import seedu.techtoday.joblist.SavedJobList;
import seedu.techtoday.notelist.SavedNoteList;
import seedu.techtoday.storage.ArticleToFileSaver;
import seedu.techtoday.storage.JobToFileSaver;
import seedu.techtoday.storage.NoteToFileSaver;

/** Class used to represent the command to terminate the program. */
public class ApplicationTerminator {

    /** Executes the termination of the program and also saves the jobs, notes and articles in memory. */
    public static void execute() {
        Messages.printInCenter("Saving your data...");
        ArticleToFileSaver.execute(SavedArticleList.savedArticleList, "articleList.json");
        JobToFileSaver.execute(SavedJobList.savedJobList, "jobList.json");
        NoteToFileSaver.execute(SavedNoteList.savedNoteList, "noteList.json");
        Messages.printInCenter("We are exiting the program...");
        Messages.printInCenter("Bye. Come back again later.");
    }
}
