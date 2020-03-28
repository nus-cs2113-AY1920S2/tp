package jikan.command;

import jikan.activity.Activity;
import jikan.activity.ActivityList;
import jikan.parser.Parser;

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
            graphLastShown();
        }
    }

    private void graphLastShown() {
        System.out.println(String.format("%-25s %s %-100s", "Name", "|", "Duration"));
        for (int i = 0; i < lastShownList.getSize(); i++) {
            Activity activity = lastShownList.get(i);
            Duration duration = activity.getDuration();
            int interval = Integer.parseInt(parameters);
            double minutes = duration.toMinutes() / (double) interval;
            int scaledMinutes = (int) Math.round(minutes);
            System.out.print(String.format("%-25s %s", activity.getName(), "|"));
            for (int j = 0; j < scaledMinutes; j++) {
                System.out.print("*");
            }
            System.out.println("");
        }
    }

    private void graphTags() {
        HashMap<String, Duration> tags = new HashMap<>();
        for (Activity activity : lastShownList.activities) {
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

        System.out.println(String.format("%-10s %s %-100s", "Tag", "|", "Duration"));

        tags.forEach((key,value) -> {
            double minutes = value.toMinutes() / 10.0;
            int tenMinutes = (int) Math.round(minutes);
            System.out.print(String.format("%-10s %s", key, "|"));
            for (int j = 0; j < tenMinutes; j++) {
                System.out.print("*");
            }
            System.out.println("");
        });
    }
}
