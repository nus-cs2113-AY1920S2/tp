package jikan.activity;

import jikan.exception.InvalidTimeFrameException;
import jikan.parser.Parser;
import jikan.storage.Storage;
import jikan.storage.StorageHandler;
import jikan.ui.Ui;

import java.io.IOException;
import java.time.Duration;
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

    /**
     * Constructor for a new activity list.
     */
    public ActivityList() {
        this.activities = new ArrayList<>();
    }

    /**
     * Loads activityList from data file.
     * @param dataFile the datafile to be read from.
     */
    public ActivityList(File dataFile) {
        this.activities = new ArrayList<>();
        populateTaskList(dataFile);
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
     * Updates the duration of an activity.
     * @param duration The new duration.
     * @param endTime Thew new end time.
     * @param index Index of the activity to be updated.
     */
    public void updateDuration(Duration duration, LocalDateTime endTime, int index) {
        activities.get(index).setDuration(duration);
        activities.get(index).setEndTime(endTime);
        fieldChangeUpdateFile();
    }

    /**
     * Searches for an activity in activityList by name.
     * @param name Name of the activity to search for.
     * @return Index of activity with that name.
     */
    public int findActivity(String name) {
        for (int i = 0; i < activities.size(); i++) {
            if (activities.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
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

    public void updateName(int index, String newName) {
        activities.get(index).setName(newName);
        fieldChangeUpdateFile();
    }

    public void delete(int index) {
        activities.remove(index);
        deleteUpdateFile(index);
    }

    private void deleteUpdateFile(int index) {
        try {
            StorageHandler.removeLine(index, storage);
        } catch (IOException e) {
            System.out.println("Error while deleting activity from data file.");
        }
    }

    private void fieldChangeUpdateFile() {
        try {
            StorageHandler.updateField(activities, storage);
        } catch (IOException e) {
            System.out.println("Error while updating activity from data file.");
        }
    }

    public int getSize() {
        return activities.size();
    }

    /**
     * Saves a new activity to the list of activities.
     * @param activityList list to save to
     * @throws InvalidTimeFrameException if start time is before end time
     */
    public void saveActivity(ActivityList activityList) throws InvalidTimeFrameException {
        if (Parser.continuedIndex != -1) {
            Ui.printDivider("Ended: " + Parser.activityName);
            Parser.endTime = LocalDateTime.now();
            Duration duration = Duration.between(Parser.startTime, Parser.endTime);
            Duration oldDuration = activityList.get(Parser.continuedIndex).getDuration();
            Duration newDuration = duration.plus(oldDuration);
            activityList.updateDuration(newDuration, Parser.endTime, Parser.continuedIndex);
            Parser.continuedIndex = -1;
            Parser.resetInfo();
        } else {
            Ui.printDivider("Ended: " + Parser.activityName);
            Parser.endTime = LocalDateTime.now();
            Duration duration = Duration.between(Parser.startTime, Parser.endTime);
            Activity newActivity = new Activity(Parser.activityName, Parser.startTime,
                    Parser.endTime, duration, Parser.tags);
            activityList.add(newActivity);
            // reset activity info
            Parser.resetInfo();
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
        } catch (InvalidTimeFrameException e) {
            System.out.println("Error: Invalid time frame.");
        }
    }

    /**
     * Parses the current line in the data file to an jikan.activity.Activity object.
     *
     * @param s String to parse.
     */
    private void parseDataLine(String s) throws InvalidTimeFrameException {

        if (!s.isEmpty()) {
            List<String> strings = Arrays.asList(s.split(","));
            String[] tagStrings;
            Set<String> tags = new HashSet<String>();

            // if there are tags
            if (strings.size() > 4) {
                // remove square brackets surrounding tags
                tagStrings = strings.get(4).split(" ");
                for (String i : tagStrings) {
                    tags.add(i);
                }
            }

            Activity e;
            LocalDateTime startTime = LocalDateTime.parse(strings.get(1));
            LocalDateTime endTime = LocalDateTime.parse(strings.get(2));
            Duration duration = Duration.parse(strings.get(3));
            e = new Activity(strings.get(0), startTime, endTime, duration, tags);
            activities.add(e);
        }
    }
}
