package jikan.command;

import jikan.activity.Activity;
import jikan.activity.ActivityList;
import jikan.ui.Ui;

import java.time.Duration;
import java.util.HashMap;
import java.util.Set;

import static jikan.Jikan.lastShownList;

public class GraphCommand extends Command {
    /**
     * Constructor to create a new command.
     * @param parameters
     */
    public GraphCommand(String parameters) {
        super(parameters);
    }

    @Override
    public void executeCommand(ActivityList activityList) {
        if (parameters.equals("tags")) {
            graphTags();
        } else {
            int interval = Integer.parseInt(parameters);
            Ui.printActivityGraph(interval);
        }
    }

    private void graphTags() {
        HashMap<String, Duration> tags = new HashMap<>();
        for (Activity activity : lastShownList.activities) {
            extractTags(tags, activity);
        }
        Ui.printTagsGraph(tags);
    }

    private void extractTags(HashMap<String, Duration> tags, Activity activity) {
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
