package seedu.tp.commands;

import seedu.tp.exceptions.HistoryFlashcardException;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static seedu.tp.utils.Constants.LOG_FOLDER;

/**
 * Command used in the application.
 */
public abstract class Command {

    protected static final Logger LOGGER = Logger.getLogger(Command.class.getName());
    private static final String FILE_PATH = LOG_FOLDER + "command.log";

    /**
     * Set up the command logger. Call once at the start of the program.
     *
     * @throws IOException when logger set up failed
     */
    public static void setupLogger() throws IOException {
        LOGGER.setLevel(Level.ALL);
        LOGGER.setUseParentHandlers(false);
        FileHandler fileHandler = new FileHandler(FILE_PATH, true);
        fileHandler.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(fileHandler);
    }

    /**
     * Executes the command.
     */
    public abstract CommandFeedback execute()
        throws HistoryFlashcardException;

    /**
     * Checks whether the command is a bye command.
     *
     * @return Boolean value indicating whether or not the command is a bye command.
     */
    public boolean isBye() {
        return false;
    }
}