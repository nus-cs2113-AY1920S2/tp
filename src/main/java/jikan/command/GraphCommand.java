package jikan.command;

import jikan.activity.Activity;
import jikan.activity.ActivityList;
import jikan.exception.ExtraParametersException;
import jikan.exception.InvalidGraphCommandException;
import jikan.exception.MissingParametersException;
import jikan.ui.Ui;

import java.time.Duration;
import java.util.HashMap;
import java.util.Set;

import static jikan.Jikan.lastShownList;

public class GraphCommand extends Command {

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
            case "targets":
                Ui.graphTargets(lastShownList);
                break;
            case "tags":
                graphTags();
                break;
            case "activities":
                graphDuration();
                break;
            default:
                Ui.printDivider("Please specify whether you want to graph activities / tags / targets.");
            }
            /*
            if (inputs[0].equals("targets")) {
                Ui.graphTargets(activityList);
            } else if (inputs[0].equals("tags")) {
                graphTags();
            } else if (inputs[0].equals("activities")) {
                graphDuration();
            } else {
                Ui.printDivider("Please specify whether you want to graph activities / tags / targets.");
            }
             */
        } catch (NumberFormatException | InvalidGraphCommandException e) {
            Ui.printDivider("Please input an integer for the time interval.");
        } catch (MissingParametersException e) {
            Ui.printDivider("Please specify whether you want to graph activities / tags / targets.");
        }

    }

    private void graphDuration() throws MissingParametersException {
        if (inputs.length < 2) {
            throw new MissingParametersException();
        } else {
            int interval = Integer.parseInt(inputs[1]);
            Ui.printActivityGraph(interval);
        }
    }

    private void graphTags() throws InvalidGraphCommandException {
        HashMap<String, Duration> tags = new HashMap<>();
        for (Activity activity : lastShownList.activities) {
            extractTags(tags, activity);
        }
        if (inputs.length < 2) {
            throw new InvalidGraphCommandException();
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
