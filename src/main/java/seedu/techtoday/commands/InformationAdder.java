package seedu.techtoday.commands;

import seedu.techtoday.articlelist.ArticlePrinter;
import seedu.techtoday.articlelist.SavedArticleList;
import seedu.techtoday.common.CommonMethods;
import seedu.techtoday.common.Messages;
import seedu.techtoday.joblist.JobPrinter;
import seedu.techtoday.joblist.SavedJobList;
import seedu.techtoday.notelist.NotePrinter;
import seedu.techtoday.notelist.SavedNoteList;
import seedu.techtoday.objects.Article;
import seedu.techtoday.objects.Job;
import seedu.techtoday.objects.Note;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

/** Class that represents the addition of extracts to information objects. */
public class InformationAdder {

    private static final Logger LOGGER = Logger.getLogger(CommonMethods.class.getName());
    private static final String LOGGER_FILE_PATH =  "command.log";

    /**
     * Initializes the LOGGER.
     *
     * @throws IOException when logger set up fails.
     */
    public static void startLogger() throws IOException {
        LOGGER.setLevel(Level.ALL);
        LOGGER.setUseParentHandlers(false);
        FileHandler fileHandler = new FileHandler(LOGGER_FILE_PATH, true);
        fileHandler.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(fileHandler);
    }

    /**
     * Executes the addinfo feature.
     *
     * @param userResponse - Command that the user adds to the command line.
     */
    public static void execute(String userResponse) {
        LOGGER.log(Level.INFO, "\n going to start processing at Information Adder \n");
        try {
            String type = userResponse.split(" ")[1];
            int index = Integer.parseInt(userResponse.split(" ")[2]) - 1;
            String extract = userResponse.split(" ", 4)[3];
            switch (type) {
            case "article": {
                addInfoToArticle(index, extract);
                break;
            }
            case "job": {
                addInfoToJob(index, extract);
                break;
            }
            case "note": {
                addInfoToNote(index, extract);
                break;
            }
            default: {
                Messages.printAddInfoException(" addinfo [article / job / note] INDEX_NUMBER EXTRACT");
            }
            }
            LOGGER.log(Level.INFO, "\n Successful \n");
        } catch (IndexOutOfBoundsException e) {
            LOGGER.log(Level.INFO, "\n Index out of bounds for Information Adder. \n");
            Messages.printAddInfoException(" addinfo [article / job / note] INDEX_NUMBER EXTRACT");
        } catch (NumberFormatException e) {
            LOGGER.log(Level.INFO, "\n Incorrect format alltogether for Information Adder.. \n");
            Messages.printAddInfoException(" addinfo [article / job / note] INDEX_NUMBER EXTRACT");
        }
    }

    /**
     * Adds extract to an existing Note.
     *
     * @param index - Index of the article in the note list.
     * @param extract - Extract to be added.
     */
    public static void addInfoToNote(int index, String extract) {
        try {
            Note note = SavedNoteList.savedNoteList.get(index);
            System.out.println("Done, the note description now looks like the following \n");
            note.setExtract(note, extract);
            NotePrinter.printIsolatedNote(note);
            Messages.printStraightLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, Index number is incorrect!");
            Messages.printStraightLine();
        }
    }

    /**
     * Adds extract to an existing job.
     *
     * @param index - Index of the article in the job list.
     * @param extract - Extract to be added.
     */
    public static void addInfoToJob(int index, String extract) {
        try {
            Job job = SavedJobList.savedJobList.get(index);
            System.out.println("Done, the job description now looks like the following \n");
            job.setExtract(job, extract);
            JobPrinter.printIsolatedJob(job);
            Messages.printStraightLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, Index number is incorrect!");
            Messages.printStraightLine();
        }
    }

    /**
     * Adds extract to an existing article.
     *
     * @param index - Index of the article in the article list.
     * @param extract - Extract to be added.
     */
    public static void addInfoToArticle(int index, String extract) {
        try {
            Article article = SavedArticleList.savedArticleList.get(index);
            article.setExtract(article, extract);
            System.out.println("Done, the article description now looks like the following \n");
            ArticlePrinter.printIsolatedArticle(article);
            Messages.printStraightLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, Index number is incorrect!");
            Messages.printStraightLine();
        }
    }
}
