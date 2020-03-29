package jikan.command;

import jikan.activity.Activity;
import jikan.activity.ActivityList;
import jikan.exception.EmptyQueryException;
import jikan.parser.Parser;
import jikan.ui.Ui;

import static jikan.Jikan.lastShownList;
import java.util.ArrayList;

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
        if (parameters.contains("-s")) {
            findSubList();
        } else {
            findFullList(activityList);
        }
    }

    private void findFullList(ActivityList activityList) {
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
        } catch (EmptyQueryException e) {
            Ui.printDivider("No keyword was given.");
        }
    }

    private void findSubList() {
        try {
            String keyword = parameters.replace("-s ", "");
            ArrayList<Activity> prevList = new ArrayList<>();
            prevList.addAll(lastShownList.activities);
            if (keyword.length() < 1) {
                throw new EmptyQueryException();
            } else {
                lastShownList.activities.clear();
                for (Activity i : prevList) {
                    if (i.getName().contains(keyword)) {
                        lastShownList.activities.add(i);
                    }
                }
            }
            Ui.printResults(lastShownList);
        } catch (ArrayIndexOutOfBoundsException | EmptyQueryException e) {
            Ui.printDivider("No keyword was given.");
        }
    }
}