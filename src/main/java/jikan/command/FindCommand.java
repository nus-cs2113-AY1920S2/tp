package jikan.command;

import jikan.activity.Activity;
import jikan.activity.ActivityList;
import jikan.exception.EmptyQueryException;
import jikan.ui.Ui;

import static jikan.Jikan.lastShownList;
import java.util.ArrayList;

/**
 * Represents a command to find activities by name.
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
        if (parameters.contains("-s")) {
            findSubList();
        } else {
            findFullList(activityList);
        }
    }

    /**
     * Find activities which has names containing the keywords from the entire list.
     * @param activityList full like of activities
     */
    private void findFullList(ActivityList activityList) {
        try {
            if (parameters.length() < 1) {
                throw new EmptyQueryException();
            } else {
                String[] keywords = parameters.split(" / ");
                lastShownList.activities.clear();
                for (String keyword : keywords) {
                    populateLastShownList(keyword, activityList.activities);
                }
                Ui.printResults(lastShownList);
            }
        } catch (EmptyQueryException e) {
            Ui.printDivider("No keyword was given.");
        }
    }

    /**
     * Find activities which has names containing the keywords from the last shown list.
     */
    private void findSubList() {
        try {
            String query = parameters.replace("-s ", "");
            ArrayList<Activity> prevList = new ArrayList<>();
            prevList.addAll(lastShownList.activities);
            if (query.length() < 1) {
                throw new EmptyQueryException();
            } else {
                String[] keywords = query.split(" / ");
                lastShownList.activities.clear();
                for (String keyword : keywords) {
                    populateLastShownList(keyword, prevList);
                }
            }
            Ui.printResults(lastShownList);
        } catch (ArrayIndexOutOfBoundsException | EmptyQueryException e) {
            Ui.printDivider("No keyword was given.");
        }
    }

    /**
     * Fills the last shown list with the results from matching names of activities to a keyword.
     * @param keyword the keyword to match against
     * @param activities the list of activities to search
     */
    private void populateLastShownList(String keyword, ArrayList<Activity> activities) {
        for (Activity i : activities) {
            if (i.getName().contains(keyword)) {
                lastShownList.activities.add(i);
            }
        }
    }

}