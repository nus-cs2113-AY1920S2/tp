package jikan;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;

/**
 * Represents a logger object to log user commands and outcomes to a logfile.
 */
public class Log {
    private static Logger logger;
    private static SimpleFormatter formatterTxt;

    /**
     * Constructor for a new logger.
     */
    public Log() throws IOException {
        logger = Logger.getLogger(Log.class.getName());
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.WARNING);
        logger.addHandler(consoleHandler);

        FileHandler fileHandler = new FileHandler("data/LogRecord.txt", true);

        formatterTxt = new SimpleFormatter();
        fileHandler.setFormatter(formatterTxt);
        fileHandler.setLevel(Level.INFO);
        logger.addHandler(fileHandler);
    }

    /**
     * Creates a long entry at info level.
     * @param message the info message to be logged
     */
    public void makeInfoLog(String message) {
        logger.log(Level.INFO, message);
    }

    /**
     * Creates a long entry at warning level.
     * @param message the warning message to be logged
     */
    public void makeWarningLog(String message) {
        logger.log(Level.WARNING, message);
    }

    /**
     * Creates a long entry at severe level.
     * @param message the severe warning message to be logged
     */
    public void makeSevereLog(String message) {
        logger.log(Level.SEVERE, message);
    }
}
