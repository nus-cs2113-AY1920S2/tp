package seedu.techtoday;

import seedu.techtoday.articlelist.SavedArticleList;
import seedu.techtoday.articlelist.ViewedArticleList;
import seedu.techtoday.commands.ProgramExecutor;
import seedu.techtoday.joblist.SavedJobList;
import seedu.techtoday.joblist.ViewedJobList;
import seedu.techtoday.notelist.SavedNoteList;
import seedu.techtoday.exception.TechTodayException;
import static seedu.techtoday.common.Messages.greet;

/**
 * <h1>TechToday News/Jobs/Notes for technology related information for the technologist.</h1>
 * Entry point of the "TechToday" application.
 * Initializes the application and starts the interaction with the user.
 * <b>Note:</b> This application is written for CS2113 for tp at the NUS School of computing.
 * @author  Alaukik, Kate, Melissa, Yolanda
 * @since   2020-03-27
 */
public class TechToday {

    /** Version info of the program. */
    public static final String VERSION = "TechToday - Version 2.1";

    public static boolean isRunning = true;

    public ViewedArticleList viewedArticleList;
    public SavedArticleList savedArticleList;
    public ViewedJobList viewedJobList;
    public SavedJobList savedJobList;
    public SavedNoteList savedNoteList;

    /** Constructor that initializes the data structures that saves technology information. */
    TechToday() {
        viewedArticleList = new ViewedArticleList();
        savedArticleList = new SavedArticleList();
        viewedJobList = new ViewedJobList();
        savedJobList = new SavedJobList();
        savedNoteList = new SavedNoteList();
    }
  
    /**
     * Main entry-point for the java.techtoday application.
     */
    public static void main(String[] args) throws TechTodayException {
        new TechToday();
        greet();
        ProgramExecutor.loadData();
        ProgramExecutor.startLogger();
        ProgramExecutor.executeProgram(isRunning);
    }

}

