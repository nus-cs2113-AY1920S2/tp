package jikan.command;

import jikan.activity.Activity;
import jikan.activity.ActivityList;
import jikan.parser.Parser;

import java.time.Duration;

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
        System.out.println(String.format("%-25s %s %-100s", "Name", "|", "Duration"));
        for (int i = 0; i < lastShownList.getSize(); i++) {
            Activity activity = lastShownList.get(i);
            Duration duration = activity.getDuration();
            double minutes = duration.toMinutes() / 10.0;
            int tenMinutes = (int) Math.round(minutes);
            System.out.print(String.format("%-25s %s", activity.getName(), "|"));
            for (int j = 0; j < tenMinutes; j++) {
                System.out.print("*");
            }
            System.out.println("");
        }
    }
}
