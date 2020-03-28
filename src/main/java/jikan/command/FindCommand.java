package jikan.command;

import jikan.activity.Activity;
import jikan.activity.ActivityList;
import jikan.exception.EmptyQueryException;
import jikan.parser.Parser;
import jikan.ui.Ui;

import static jikan.Jikan.lastShownList;

/**
 * Represents a command to find activities in the activity list.
 */
public class FindCommand extends Command {

    /**
     * Constructor to create a new find command.
     */
    public FindCommand(String parameters) {
        super(parameters);
    }

    /**
     * Shows the user all past activities that has names which match the keyword queried by the user.
     * @param activityList the activity list to search for matching activities
     */
    @Override
    public void executeCommand(ActivityList activityList) {
        try {
            // Parser.parseFind(activityList, lastShownList, tokenizedInputs[1]);
            String keyword = parameters;
            if (keyword.length() < 1) {
                throw new EmptyQueryException();
            } else {
                lastShownList.activities.clear();
                for (Activity i : activityList.activities) {
                    if (i.getName().contains(keyword)) {
                        lastShownList.activities.add(i);
                    }
                }
                Ui.printResults(lastShownList);
            }
        } catch (ArrayIndexOutOfBoundsException | EmptyQueryException e) {
            Ui.printDivider("No keyword was given.");
        }
    }
}
