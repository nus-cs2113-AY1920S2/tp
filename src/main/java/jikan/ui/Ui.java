package jikan.ui;

import jikan.activity.Activity;
import jikan.activity.ActivityList;
import jikan.parser.Parser;

import java.text.DecimalFormat;
import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static jikan.Jikan.lastShownList;

public class Ui {
    public static final String LOGO = "::::::::::: ::::::::::: :::    :::     :::     ::::    :::\n"
            + "    :+:         :+:     :+:   :+:    :+: :+:   :+:+:   :+:\n"
            + "    +:+         +:+     +:+  +:+    +:+   +:+  :+:+:+  +:+\n"
            + "    +#+         +#+     +#++:++    +#++:++#++: +#+ +:+ +#+\n"
            + "    +#+         +#+     +#+  +#+   +#+     +#+ +#+  +#+#+#\n"
            + "#+# #+#         #+#     #+#   #+#  #+#     #+# #+#   #+#+#\n"
            + " #####      ########### ###    ### ###     ### ###    ####";

    public static final String WELCOMEMESSAGE = " Hello there! I'm Jikan, your trusty time tracker.\n"
            + " What can I do for you today?";

    public static final String DIVIDER = "-------------------------------"
            + "-----------------------------------------------------------";

    public static final int PROGRESSCONVERTER = 2;

    public static final int TOTALBARS = 50;

    private static final DecimalFormat df2 = new DecimalFormat("#.##");

    /** Prints the logo and greeting so users know the app is working. */
    public void printGreeting() {
        System.out.println(LOGO);
        System.out.println(DIVIDER);
        System.out.println(WELCOMEMESSAGE);
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
        long allocatedTimeInNanos = (activityList.get(index).getAllocatedTime()).toNanos();
        String duration = formatString(durationInNanos);
        String allocatedTime = formatString(allocatedTimeInNanos);
        String printIndex = String.valueOf(index + 1);
        if (index < 9) {
            printIndex = String.valueOf(index + 1) + " ";
        }
        if (gotTags) {
            System.out.println(String.format("%s %s %-25s %s %-10s %s %-10s %s %-10s %s %s",
                    printIndex, "|", activityList.get(index).getName(), "|", duration, "|",
                    allocatedTime, "|",
                    activityList.get(index).getDate().toString(), "|",
                    activityList.get(index).getTags().toString()));
        } else {
            System.out.println(String.format("%s %s %-25s %s %-10s %s %-10s %s %-10s %s %s",
                    printIndex, "|", activityList.get(index).getName(), "|", duration, "|",
                    allocatedTime, "|",
                    activityList.get(index).getDate().toString(), "|",
                    ""));
        }
    }

    private static String formatString(long timeInNanos) {
        return String.format("%02d:%02d:%02d",
                TimeUnit.NANOSECONDS.toHours(timeInNanos),
                TimeUnit.NANOSECONDS.toMinutes(timeInNanos)
                        - TimeUnit.HOURS.toMinutes(TimeUnit.NANOSECONDS.toHours(timeInNanos)),
                TimeUnit.NANOSECONDS.toSeconds(timeInNanos)
                        - TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes(timeInNanos)));
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
        System.out.println(String.format("   %s %-25s %s %-10s %s %-10s %s %-10s %s %s",
                "|", "Name", "|", "Duration", "|", "Target", "|", "Date", "|", "Tags"));
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

    /** Print goals as a table.
     * Print goals as a table.
     * @param tagsGoals the goals set for each tag.
     * @param tagsActual the actual duration spent for each tag.
     */
    public static void printGoals(HashMap<String, Duration> tagsGoals, HashMap<String, Duration> tagsActual) {
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
            System.out.println(String.format("   %-15s %s %-15s %s %-15s %s %s", key, "|", goalDuration,
                    "|", actualDuration, "|", diffDuration + message));
        });
        System.out.println(DIVIDER);
    }

    /** Prints a progress message and progress bar based on the percentage of allocate time achieved.
     * @param percent percentage of allocated time achieved
     */
    public static void printProgressMessage(double percent) {
        System.out.println(DIVIDER);
        if (percent < 50) {
            System.out.println("Try harder next time! Here's your progress:");
        } else if (percent < 100) {
            System.out.println("Almost there ! Here's your progress:");
        } else {
            System.out.println("Great job! Here's your progress:");
        }
        int starsLeft = (int) (percent / PROGRESSCONVERTER);
        System.out.print("Progress for " + Parser.activityName + ": ");
        System.out.print("|");
        for (int i = 0; i < TOTALBARS; i++) {
            if (starsLeft > 0) {
                System.out.print("*");
                starsLeft--;
            } else {
                System.out.print(" ");
            }
        }
        System.out.print("|");
        System.out.println((int) percent + "% completed");
        System.out.println(DIVIDER);
    }


    /** Method to print progress bar without message.
     * @param percent percentage of allocated time achieved.
     * @param activityName name of a particular activity.
     */
    public static void printProgressBar(double percent, String activityName) {
        int starsLeft = (int) (percent / PROGRESSCONVERTER);
        String line = "Progress for " + activityName + ": ";
        System.out.print(String.format("%-35s", line));
        System.out.print("|");
        for (int i = 0; i < TOTALBARS; i++) {
            if (starsLeft > 0) {
                System.out.print("*");
                starsLeft--;
            } else {
                System.out.print(" ");
            }
        }
        System.out.println("|  " + df2.format(percent) + "%");
    }

    /**
     * Method to graph out all the targets.
     * @param activityList a list of all activities.
     */
    public static void graphTargets(ActivityList activityList) {
        System.out.println(DIVIDER);
        for (int i = 0; i < activityList.getSize(); i++) {
            if (activityList.get(i).getAllocatedTime() == Duration.parse("PT0S")) {
                continue;
            }
            double percent = activityList.get(i).getProgressPercent();
            double max = 100;
            if (percent > max) {
                percent = max;
            }
            String activityName = activityList.get(i).getName();
            printProgressBar(percent, activityName);
        }
        System.out.println(DIVIDER);
    }

    /** Converts duration object to a string for printing.
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
