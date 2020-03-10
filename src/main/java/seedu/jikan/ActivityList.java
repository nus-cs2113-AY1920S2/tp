package seedu.jikan;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the list of activities.
 */
public class ActivityList {
    ArrayList<Activity> activities;
    //int size;

    /**
     * Constructor for a new activity list.
     */
    public ActivityList() {
        this.activities = new ArrayList<>();
        //this.size = activities.size();
    }

    /**
     * Loads activityList from data file.
     *
     * @param dataFile Data file to load from.
     */
    public ActivityList(File dataFile) {
        this.activities = new ArrayList<>();
        populateTaskList(dataFile);
    }

    public Activity get(int i) {
        return activities.get(i);
    }

    public void add(Activity activity) {
        activities.add(activity);
    }

    public int getSize() {
        return activities.size();
    }

    /**
     * Prints the activityList.
     */
    public void printList() {
        System.out.println("Your completed activities:");
        for (int i = 0; i < this.getSize(); i++) {
            long durationInNanos = (activities.get(i).duration).toNanos();
            String duration = String.format("%02d:%02d:%02d",
                    TimeUnit.NANOSECONDS.toHours(durationInNanos),
                    TimeUnit.NANOSECONDS.toMinutes(durationInNanos)
                            - TimeUnit.HOURS.toMinutes(TimeUnit.NANOSECONDS.toHours(durationInNanos)),
                    TimeUnit.NANOSECONDS.toSeconds(durationInNanos)
                            - TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes(durationInNanos)));
            System.out.print(i + 1 + ". " + activities.get(i).name + " " + duration);
            if (activities.get(i).tags != null) {
                System.out.println(" " + Arrays.toString(activities.get(i).tags));
            } else {
                System.out.print("\n");
            }
        }
    }

    /**
     * Populates task list from file.
     *
     * @param dataFile Data file to populate from.
     */
    private void populateTaskList(File dataFile) {
        try {
            Scanner dataScanner = new Scanner(dataFile);
            while (dataScanner.hasNext()) {
                parseDataLine(dataScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: data file not found. Could not load into the current session's task list.");
        }
    }

    /**
     * Parses the current line in the data file to an seedu.jikan.Activity object.
     *
     * @param s String to parse.
     */
    private void parseDataLine(String s) {

        List<String> strings = Arrays.asList(s.split(","));

        String[] tags;

        // if there are tags
        if (strings.size() > 3) {
            tags = strings.get(3).split(" ");
        } else {
            tags = null;
        }

        // strings[0] = name, strings[1] = startDate, string[2] = endDate
        Activity e = new Activity(strings.get(0), LocalDateTime.parse(strings.get(1)),
                LocalDateTime.parse(strings.get(2)), tags);
        activities.add(e);
    }
}
