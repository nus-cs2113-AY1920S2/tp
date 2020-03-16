package seedu.duke;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.FileHandler;

public class InfoLogger {

    private static final String LOGGER_FILE_NAME = "DietManagerLogger.log";
    private final Logger logger;

    /**
     * Constructs the InfoLogger to record information when running the application.
     */

    public InfoLogger() {
        this.logger = Logger.getLogger(InfoLogger.class.getName());

        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);

        this.initialiseConsoleHandler();
        this.initialiseFileHandler();
    }

    /**
     * Initialises the File Handler, and sets Log Level priority at FINE.
     */

    public void initialiseFileHandler() {
        try {
            FileHandler fileHandler = new FileHandler(LOGGER_FILE_NAME);
            fileHandler.setLevel(Level.FINE);
            this.logger.addHandler(fileHandler);
        } catch (IOException e) {
            this.logger.log(Level.SEVERE, "File logger error.", e);
        }
    }

    /**
     * Initialises the Console Handler, and sets Log Level priority at INFO.
     */

    public void initialiseConsoleHandler() {
        //Console Handler - What appears in the console
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        logger.addHandler(consoleHandler);
    }

    public void logExecuteProgramme() {
        this.logger.log(Level.INFO, "Starting Diet Manager");
    }

    public void logExitProgramme() {
        this.logger.log(Level.INFO, "Exiting Diet Manager");
    }

}
