package seedu.techtoday.commands;

import seedu.techtoday.common.Messages;

/** Class used to represent the help command. */
public class Helper {

    /** Executes the printing of the help manual. */
    public static void execute() {
        Messages.printCommand();
        Messages.printStraightLine();
    }
}
