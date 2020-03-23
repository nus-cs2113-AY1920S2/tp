package jikan.command;

import jikan.activity.ActivityList;
import jikan.parser.Parser;
import jikan.ui.Ui;

import java.io.IOException;

import static jikan.parser.Parser.tokenizedInputs;

/**
 * Represents a command to clear previously saved log files.
 */
public class CleanCommand extends Command {

    /**
     * Constructor to create a new clean command.
     */
    public CleanCommand() {
    }

    @Override
    public void executeCommand(ActivityList activityList) {
        try {
            Parser.parseClean(tokenizedInputs[1]);
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            Ui.printDivider("No keyword was given.");
        }
    }

}
