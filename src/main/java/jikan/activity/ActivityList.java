package jikan.activity;

import jikan.storage.Storage;

import java.io.IOException;
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
    public ArrayList<Activity> activities;
    public Storage storage; // Storage the list was loaded from

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
     * @param storage Storage to load from.
     */
    public ActivityList(Storage storage) {
        this.activities = new ArrayList<>();
        this.storage = storage;
        populateTaskList(storage.dataFile);
    }

    public Activity get(int i) {
        return activities.get(i);
    }

    /**
     * Adds activity to activity list and stores it in the data file.
     * @param activity Activity to add.
     */
    public void add(Activity activity) {
        activities.add(activity);

        String dataLine = activity.toData();

        updateFile(dataLine);
    }

    /**
     * Updates data file with new task.
     *
     * @param dataLine Line to write to file.
     */
    private void updateFile(String dataLine) {
        try {
            storage.writeToFile(dataLine);
        } catch (IOException e) {
            System.out.println("Error saving task to data file.");
        }
    }

    public int getSize() {
        return activities.size();
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
     * Parses the current line in the data file to an jikan.activity.Activity object.
     *
     * @param s String to parse.
     */
    private void parseDataLine(String s) {

        if (!s.isEmpty()) {
            List<String> strings = Arrays.asList(s.split(","));
            String[] tags;

            // if there are tags
            if (!strings.get(3).equals("null")) {
                // remove square brackets surrounding tags
                tags = strings.get(3).substring(1,strings.get(3).length() - 1).split(" ");
            } else {
                tags = null;
            }

            // strings[0] = name, strings[1] = startDate, string[2] = endDate
            Activity e = new Activity(strings.get(0), LocalDateTime.parse(strings.get(1)),
                    LocalDateTime.parse(strings.get(2)), tags);
            activities.add(e);
        }
    }
}
