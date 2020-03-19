package jikan.activity;

import jikan.storage.Storage;
import jikan.storage.StorageHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

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

    public void delete(int index) {
        activities.remove(index);
        deleteUpdateFile(index);
    }

    private void deleteUpdateFile(int index) {
        try {
            StorageHandler.removeLine(index, storage);
        } catch (IOException e) {
            System.out.println("Error while deleting task from data file.");
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
            String[] tagStrings;
            Set<String> tags = new HashSet<String>();

            // if there are tags
            if (strings.size() > 3) {
                // remove square brackets surrounding tags
                tagStrings = strings.get(3).substring(0,strings.get(3).length() - 1).split(" ");
                tagStrings = strings.get(3).split(" ");
                for (String i : tagStrings) {
                    tags.add(i);
                }
            }
            // strings[0] = name, strings[1] = startDate, string[2] = endDate
            Activity e = new Activity(strings.get(0), LocalDateTime.parse(strings.get(1)),
                    LocalDateTime.parse(strings.get(2)), tags);
            activities.add(e);
        }
    }
}
