package jikan.command;

import jikan.activity.Activity;
import jikan.activity.ActivityList;
import jikan.exception.EmptyQueryException;
import jikan.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static jikan.Jikan.lastShownList;

/**
 * Represents a command to filter activities by specified tags.
 */
public class FilterCommand extends Command {

    /**
     * Constructor to create a new filter command.
     */
    public FilterCommand(String parameters) {
        super(parameters);
    }

    /**
     * Shows the user all past activities that has tags which match the one or more keywords queried by the user.
     * @param activityList the activity list to search for matching activities
     */
    @Override
    public void executeCommand(ActivityList activityList) {
        if (parameters.contains("-s")) {
            filterSubList();
        } else {
            filterFullList(activityList);
        }
    }

    /**
     * Filters activities by tags from the entire list of activities.
     * @param activityList the full list of activities
     */
    private void filterFullList(ActivityList activityList) {
        try {
            String query = parameters;
            if (query.length() < 1) {
                throw new EmptyQueryException();
            } else {
                lastShownList.activities.clear();
                String[] keywords = query.split(" ");
                keywords = removeBlanks(keywords);
                //keywords = keywords.filter(boolean);
                if (keywords.length < 1) {
                    throw new EmptyQueryException();
                }
                for (String keyword : keywords) {
                    populateLastShownList(activityList, lastShownList, keyword);
                }
                Ui.printResults(lastShownList);
            }
        } catch (EmptyQueryException e) {
            Ui.printDivider("No keyword was given.");
        }
    }

    /**
     * Filter activities by tags based on the last shown list.
     */
    private void filterSubList() {
        try {
            String query = parameters.replace("-s ", "");
            ActivityList prevList = new ActivityList();
            prevList.activities.addAll(lastShownList.activities);
            if (query.length() < 1) {
                throw new EmptyQueryException();
            } else {
                lastShownList.activities.clear();
                String[] keywords = query.split(" ");
                if (keywords.length < 1) {
                    throw new EmptyQueryException();
                }
                for (String keyword : keywords) {
                    populateLastShownList(prevList, lastShownList, keyword);
                }
                Ui.printResults(lastShownList);
            }
        } catch (ArrayIndexOutOfBoundsException | EmptyQueryException e) {
            Ui.printDivider("No keyword was given.");
        }
    }

    private void populateLastShownList(ActivityList targetList, ActivityList lastShownList, String keyword) {
        for (Activity i : targetList.activities) {
            // if (!lastShownList.activities.contains(i) && i.getTags().contains(keyword)) {
            if (!lastShownList.activities.contains(i) && containsIgnoreCase(i.getTagsAsString(), keyword)) {
                lastShownList.activities.add(i);
            }
        }
    }

    private boolean containsIgnoreCase(String str, String subString) {
        return str.toLowerCase().contains(subString.toLowerCase());
    }

    private String[] removeBlanks(String [] strings) {
        List<String> list = new ArrayList<>(Arrays.asList(strings));
        list.removeIf(String::isBlank);
        strings = list.toArray(new String[0]);
        return strings;
    }
}
