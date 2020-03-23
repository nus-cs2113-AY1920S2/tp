package jikan.command;

import jikan.activity.ActivityList;
import jikan.exception.EmptyQueryException;
import jikan.parser.Parser;
import jikan.ui.Ui;

import static jikan.parser.Parser.lastShownList;
import static jikan.parser.Parser.tokenizedInputs;

/**
 * Represents a command to filter activities based on user-specified criterias.
 */
public class FilterCommand extends Command {

    /**
     * Constructor to create a new filter command.
     */
    public FilterCommand() {
    }

    @Override
    public void executeCommand(ActivityList activityList) {
        try {
            Parser.parseFilter(activityList, lastShownList, tokenizedInputs[1]);
        } catch (ArrayIndexOutOfBoundsException | EmptyQueryException e) {
            Ui.printDivider("No keyword was given.");
        }
    }
}
