package seedu.nuke;

import seedu.nuke.command.addCommand.AddModuleCommand;

import java.io.IOException;
import java.util.logging.*;

public class NukeLogger {
    private static Logger logger = Logger.getLogger(AddModuleCommand.class.getName());

    public static Logger getLogger() {
        return logger;
    }

    public static void setUpLogger() {
        logger.addHandler(new ConsoleHandler());
    }
}
