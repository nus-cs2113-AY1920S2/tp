package jikan.command;

import jikan.activity.Activity;
import jikan.activity.ActivityList;
import jikan.exception.ExtraParametersException;
import jikan.exception.MissingParametersException;
import jikan.log.Log;
import jikan.ui.Ui;

import java.time.Duration;
import java.util.HashMap;
import java.util.Set;

import static jikan.Jikan.lastShownList;

public class GraphCommand extends Command {

    private static final String ALLOCATIONS = "allocations";
    private static final String TAGS = "tags";
    private static final String ACTIVITIES = "activities";

    String[] inputs;

    /**
     * Constructor to create a new command.
     * @param parameters Either time interval for graph or 'tags' flag
     *                   to graph by tags
     */
    public GraphCommand(String parameters) throws ExtraParametersException {
        super(parameters);

        this.inputs = parameters.split(" ");
        if (inputs.length > 2) {
            throw new ExtraParametersException();
        }
    }

    @Override
    public void executeCommand(ActivityList activityList) {
        try {
            switch (inputs[0]) {
            case ALLOCATIONS:
                Ui.graphAllocation(lastShownList);
                Log.makeInfoLog("Allocations was graphed");
                break;
            case TAGS:
                graphTags();
                Log.makeInfoLog("Tags were graphed");
                break;
            case ACTIVITIES:
                graphActivities();
                Log.makeInfoLog("Activities were graphed");
                break;
            default:
                Ui.printDivider("Please specify whether you want to graph activities / tags / allocations.");
            }
        } catch (NumberFormatException | MissingParametersException e) {
            Ui.printDivider("Please input an integer for the time interval.");
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printDivider("Please specify whether you want to graph activities / tags / allocations.");
        }
    }

    private void graphActivities() throws MissingParametersException {
        if (inputs.length < 2) {
            throw new MissingParametersException();
        } else {
            int interval = Integer.parseInt(inputs[1]);
            Ui.printActivityGraph(interval);
        }
    }

    private void graphTags() throws MissingParametersException {
        HashMap<String, Duration> tags = new HashMap<>();
        for (Activity activity : lastShownList.activities) {
            extractTags(tags, activity);
        }
        if (inputs.length < 2) {
            throw new MissingParametersException();
        } else {
            int interval = Integer.parseInt(inputs[1]);
            Ui.printTagsGraph(tags, interval);
        }
    }

    /**
     * Gets the tags from the activities in the list together with the associated duration.
     * @param tags the HashMap to store the tag name and duration.
     * @param activity the activity containing the tag.
     */
    public static void extractTags(HashMap<String, Duration> tags, Activity activity) {
        Set<String> activityTags = activity.getTags();
        for (String tag : activityTags) {
            if (tags.containsKey(tag)) {
                Duration oldDuration = tags.get(tag);
                Duration newDuration = oldDuration.plus(activity.getDuration());
                tags.put(tag, newDuration);
            } else {
                tags.put(tag, activity.getDuration());
            }
        }
    }
}
