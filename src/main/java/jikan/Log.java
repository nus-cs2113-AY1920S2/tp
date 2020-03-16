package jikan;

import java.util.logging.*;

public class Log {
    private static Logger logger;
    static private SimpleFormatter formatterTxt;

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
        } catch (java.io.IOException e){
            logger.log(Level.SEVERE, "File logger not working", e);
        }
    }

    public void makeInfoLog(String message) {
        logger.log(Level.INFO, message);
    }

    public void makeWarningLog(String message) {
        logger.log(Level.WARNING, message);
    }

    public void makeSevereLog(String message) {
        logger.log(Level.SEVERE, message);
    }
}
