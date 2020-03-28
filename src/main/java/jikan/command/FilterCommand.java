package jikan.command;

import jikan.activity.Activity;
import jikan.activity.ActivityList;
import jikan.exception.EmptyQueryException;
import jikan.parser.Parser;
import jikan.ui.Ui;;

import static jikan.Jikan.lastShownList;

/**
 * Represents a command to filter activities based on user-specified criterias.
 */
public class FilterCommand extends Command {

    /**
     * Constructor to create a new filter command.
     */
    public FilterCommand(String parameters) {
        super(parameters);
    }

    /**
     * Shows the user all past activities that has tags which match the keywords queried by the user.
     * @param activityList the activity list to search for matching activities
     */
    @Override
    public void executeCommand(ActivityList activityList) {
        try {
            // Parser.parseFilter(activityList, lastShownList, tokenizedInputs[1]);
            String query = parameters;
            if (query.length() < 1) {
                throw new EmptyQueryException();
            } else {
                lastShownList.activities.clear();
                String[] keywords = query.split(" ");

                for (String keyword : keywords) {
                    populateLastShownList(activityList, lastShownList, keyword);
                }
                Ui.printResults(lastShownList);
            }
        } catch (ArrayIndexOutOfBoundsException | EmptyQueryException e) {
            Ui.printDivider("No keyword was given.");
        }
    }

    private void populateLastShownList(ActivityList activityList, ActivityList lastShownList, String keyword) {
        for (Activity i : activityList.activities) {
            if (!lastShownList.activities.contains(i) && i.getTags().contains(keyword)) {
                lastShownList.activities.add(i);
            }
        }
    }
}
