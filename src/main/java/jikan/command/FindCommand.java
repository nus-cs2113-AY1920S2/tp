package jikan.command;

import jikan.activity.ActivityList;
import jikan.exception.EmptyQueryException;
import jikan.parser.Parser;
import jikan.ui.Ui;

import static jikan.parser.Parser.lastShownList;
import static jikan.parser.Parser.tokenizedInputs;

/**
 * Represents a command to find activities in the activity list.
 */
public class FindCommand extends Command {

    /**
     * Constructor to create a new find command.
     */
    public FindCommand() {
    }

    @Override
    public void executeCommand(ActivityList activityList) {
        try {
            Parser.parseFind(activityList, lastShownList, tokenizedInputs[1]);
        } catch (ArrayIndexOutOfBoundsException | EmptyQueryException e) {
            Ui.printDivider("No keyword was given.");
        }
    }
}
