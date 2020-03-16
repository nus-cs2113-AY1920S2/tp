package jikan;

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
    public Log() {
        logger = Logger.getLogger(Log.class.getName());
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.WARNING);
        logger.addHandler(consoleHandler);

        try {
            FileHandler fileHandler = new FileHandler("data/LogRecord.txt");
            formatterTxt = new SimpleFormatter();
            fileHandler.setFormatter(formatterTxt);
            fileHandler.setLevel(Level.INFO);
            logger.addHandler(fileHandler);
        } catch (java.io.IOException e) {
            logger.log(Level.SEVERE, "File logger not working", e);
        }
    }

    /**
     * Creates a long entry at FINE level.
     * @param message the FINE message to be logged
     */
    public void makeFineLog(String message) {
        logger.log(Level.FINE, message);
    }

    /**
     * Creates a long entry at INFO level.
     * @param message the INFO message to be logged
     */
    public void makeINFOLog(String message) {
        logger.log(Level.INFO, message);
    }

    /**
     * Creates a long entry at WARNING level.
     * @param message the WARNING warning message to be logged
     */
    public void makeWarningLog(String message) {
        logger.log(Level.WARNING, message);
    }
}
