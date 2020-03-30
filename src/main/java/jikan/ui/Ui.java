package jikan.ui;

import jikan.activity.Activity;
import jikan.activity.ActivityList;

import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static jikan.Jikan.lastShownList;

public class Ui {
    public static final String GREETING = "Jikan";

    public static final String DIVIDER = "-----------------------------------------------------------------";

    /** Prints the logo and greeting so users know the app is working. */
    public void printGreeting() {
        System.out.println(GREETING);
        System.out.println(DIVIDER);
    }

    /** Prints exit message and exits the app. */
    public static void exitFromApp() {
        System.out.println(DIVIDER);
        System.out.println(" Bye! See you again.");
        System.out.println(DIVIDER);
        System.exit(0);
    }

    /** Prints divider between user input and app feedback. */
    public static void printDivider(String line) {
        System.out.println(DIVIDER);
        System.out.println(line);
        System.out.println(DIVIDER);
    }

    private static void printTableFormat(ActivityList activityList, int index, boolean gotTags) {
        long durationInNanos = (activityList.get(index).getDuration()).toNanos();
        String duration = String.format("%02d:%02d:%02d",
                TimeUnit.NANOSECONDS.toHours(durationInNanos),
                TimeUnit.NANOSECONDS.toMinutes(durationInNanos)
                        - TimeUnit.HOURS.toMinutes(TimeUnit.NANOSECONDS.toHours(durationInNanos)),
                TimeUnit.NANOSECONDS.toSeconds(durationInNanos)
                        - TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes(durationInNanos)));
        String printIndex = String.valueOf(index + 1);
        if (index < 9) {
            printIndex = String.valueOf(index + 1) + " ";
        }
        if (gotTags) {
            System.out.println(String.format("%s %s %-25s %s %-10s %s %-10s %s %s",
                    printIndex, "|", activityList.get(index).getName(), "|", duration, "|",
                    activityList.get(index).getDate().toString(), "|",
                    activityList.get(index).getTags().toString()));
        } else {
            System.out.println(String.format("%s %s %-25s %s %-10s %s %-10s %s %s",
                    printIndex, "|", activityList.get(index).getName(), "|", duration, "|",
                    activityList.get(index).getDate().toString(), "|", ""));
        }
    }

    /**
     * Prints the results from a 'find' or 'filter' command.
     * @param resultsList the list of activities to print
     */
    public static void printResults(ActivityList resultsList) {
        if (resultsList.activities.size() > 0) {
            System.out.println(DIVIDER);
            System.out.println("Here are the matching activities in your list:\n");
            System.out.println(String.format("   %s %-25s %s %-10s %s %-10s %s %s",
                    "|", "Name", "|", "Duration", "|", "Date", "|", "Tags"));
            for (int i = 0; i < resultsList.getSize(); i++) {
                if (resultsList.get(i).getTags() != null && !resultsList.get(i).getTags().isEmpty()) {
                    printTableFormat(resultsList, i, true);
                } else {
                    printTableFormat(resultsList, i, false);
                }
            }
            System.out.println(DIVIDER);
        } else {
            System.out.println("There are no activities matching that description.\n");
        }
    }

    /** Prints all the activities in a list. */
    public static void printList(ActivityList activityList) {
        System.out.println(DIVIDER);
        System.out.println("Your completed activities:");
        System.out.println(String.format("   %s %-25s %s %-10s %s %-10s %s %s",
                "|", "Name", "|", "Duration", "|", "Date", "|", "Tags"));
        for (int i = 0; i < activityList.getSize(); i++) {
            if (activityList.get(i).getTags() != null && !activityList.get(i).getTags().isEmpty()) {
                printTableFormat(activityList, i, true);
            } else {
                printTableFormat(activityList, i, false);
            }
        }
        System.out.println(DIVIDER);
    }

    /**
     * Prints a graph of the last shown list.
     * @param interval The time interval for the graph.
     */
    public static void printActivityGraph(int interval) {
        System.out.println(DIVIDER);
        System.out.println(String.format("%-25s %s %s", "Name", "|", "Duration"));
        for (int i = 0; i < lastShownList.getSize(); i++) {
            Activity activity = lastShownList.get(i);
            Duration duration = activity.getDuration();
            double minutes = duration.toMinutes() / (double) interval;
            int scaledMinutes = (int) Math.round(minutes);
            System.out.print(String.format("%-25s %s", activity.getName(), "|"));
            for (int j = 0; j < scaledMinutes; j++) {
                System.out.print("*");
            }
            System.out.println("");
        }
        System.out.println(DIVIDER);
    }

    /**
     * Prints a graph based on activity tags.
     * @param tags The set of tags to be graphed.
     */
    public static void printTagsGraph(HashMap<String, Duration> tags) {
        System.out.println(DIVIDER);
        System.out.println(String.format("%-10s %s %s", "Tag", "|", "Duration"));
        tags.forEach((key,value) -> {
            double minutes = value.toMinutes() / 10.0;
            int scaledMinutes = (int) Math.round(minutes);
            System.out.print(String.format("%-10s %s", key, "|"));
            for (int j = 0; j < scaledMinutes; j++) {
                System.out.print("*");
            }
            System.out.println("");
        });
        System.out.println(DIVIDER);
    }

    /**
     * Print goals as a table.
     * @param tagsGoals the goals set for each tag.
     * @param tagsActual the actual duration spent for each tag.
     */
    public static void printGoals(HashMap<String, Duration> tagsGoals, HashMap<String,
            Duration> tagsActual) {
        System.out.println(DIVIDER);
        System.out.println(String.format("   %-15s %s %-15s %s %-15s %s %s",
                "Tag", "|", "Goal", "|", "Actual", "|", "Duration left"));
        tagsGoals.forEach((key, value) -> {
            String message;
            String goalDuration = convertDuration(tagsGoals.get(key));
            String actualDuration = convertDuration(tagsActual.get(key));
            Duration difference = tagsGoals.get(key).minus(tagsActual.get(key));
            String diffDuration = convertDuration(difference);
            if (difference.isNegative()) {
                if (diffDuration.equals("00:00:00")) {
                    message = " [You have met your goal!]";
                } else {
                    message = " [You have exceeded your goal!]";
                }
            } else {
                message = " [You have not met your goal!]";
            }
            System.out.println(String.format("   %-15s %s %-15s %s %-15s %s %s", key,
                    "|", goalDuration, "|", actualDuration, "|", diffDuration + message));
        });
        System.out.println(DIVIDER);
    }

    /**
     * Converts duration object to a string for printing.
     * @param dur the duration object.
     * @return duration the duration as a string.
     */
    public static String convertDuration(Duration dur) {
        long durationInNanos = dur.toNanos();
        String duration = String.format("%02d:%02d:%02d",
                TimeUnit.NANOSECONDS.toHours(durationInNanos),
                TimeUnit.NANOSECONDS.toMinutes(durationInNanos)
                        - TimeUnit.HOURS.toMinutes(TimeUnit.NANOSECONDS.toHours(durationInNanos)),
                TimeUnit.NANOSECONDS.toSeconds(durationInNanos)
                        - TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes(durationInNanos)));
        return duration;
    }
}
