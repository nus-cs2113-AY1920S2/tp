package seedu.nuke;

import seedu.nuke.command.addcommand.AddModuleCommand;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class NukeLogger {
    private static Logger logger = Logger.getLogger(AddModuleCommand.class.getName());

    public static Logger getLogger() {
        return logger;
    }

    public static void setUpLogger() {
        logger.addHandler(new ConsoleHandler());
    }
}
