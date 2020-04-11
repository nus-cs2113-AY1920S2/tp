package jikan.activity;

import jikan.exception.InvalidTimeFrameException;
import jikan.exception.NameTooLongException;
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
    public StorageHandler storageHandler;

    /**
     * Constructor for a new activity list.
     */
    public ActivityList() {
        this.activities = new ArrayList<>();
    }

    /**
     * Constructor for a new activity list to be saved to a file.
     * @param storage the storage object to use.
     */
    public ActivityList(Storage storage) {
        assert storage != null : "Input Storage must not be a null pointer";
        this.activities = new ArrayList<>();
        this.storage = storage;
        this.storageHandler = new StorageHandler(storage);
    }

    /**
     * Loads activityList from data file.
     * @param storage the storage object to use.
     * @param dataFile the datafile to be read from.
     */
    public ActivityList(Storage storage, File dataFile) {
        assert storage != null : "Input Storage must not be a null pointer";
        this.activities = new ArrayList<>();
        this.storage = storage;
        this.storageHandler = new StorageHandler(storage);
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
            Ui.printDivider("Error saving task to data file.\n"
                    + "Your changes have not been saved in the data file.\n"
                    + "If the data file is open, please close it, restart the app and try again.");
        }
    }

    public void updateName(int index, String newName) {
        activities.get(index).setName(newName);
        fieldChangeUpdateFile();
    }

    public void updateAlloc(int index, Duration newAllocTime) {
        activities.get(index).setAllocatedTime(newAllocTime);
        fieldChangeUpdateFile();
    }

    public void delete(int index) {
        activities.remove(index);
        deleteUpdateFile(index);
    }

    /**
     * Deletes the line in the file.
     * @param index the index of the line in the file.
     */
    public void deleteUpdateFile(int index) {
        try {
            storageHandler.removeLine(index, storage);
        } catch (IOException e) {
            Ui.printDivider("Error while deleting activity from data file.\n"
                    + "Your changes have not been saved in the data file.\n"
                    + "If the data file is open, please close it, restart the app and try again.");
        }
    }

    private void fieldChangeUpdateFile() {
        try {
            storageHandler.updateField(activities, storage);
        } catch (IOException e) {
            Ui.printDivider("Error while updating activity from data file.\n"
                    + "Your changes have not been saved in the data file.\n"
                    + "If the data file is open, please close it, restart the app and try again.");
        }
    }

    public int getSize() {
        return activities.size();
    }

    /**
     * Saves a new activity to the list of activities.
     * @throws InvalidTimeFrameException if start time is before end time
     */
    public void saveActivity() throws InvalidTimeFrameException, NameTooLongException {
        if (Parser.continuedIndex != -1) {
            Ui.printDivider(Parser.activityName + " was ended.");
            Parser.endTime = LocalDateTime.now();
            Duration duration = Duration.between(Parser.startTime, Parser.endTime);
            Duration oldDuration = this.get(Parser.continuedIndex).getDuration();
            Duration newDuration = duration.plus(oldDuration);
            Duration allocatedTime = this.get(Parser.continuedIndex).getAllocatedTime();
            this.updateDuration(newDuration, Parser.endTime, Parser.continuedIndex);

            if (allocatedTime != Duration.parse("PT0S")) {
                Ui.printProgressMessage(this.get(Parser.continuedIndex).getProgressPercent());
            }
            Parser.continuedIndex = -1;
            Parser.resetInfo();
            assert (Parser.tags == null);
            assert (Parser.activityName == null);
            assert (Parser.startTime == null);

        } else {
            Ui.printDivider(Parser.activityName + " was ended.");
            Parser.endTime = LocalDateTime.now();
            Duration duration = Duration.between(Parser.startTime, Parser.endTime);
            Activity newActivity = new Activity(Parser.activityName, Parser.startTime,
                    Parser.endTime, duration, Parser.tags, Parser.allocatedTime);
            this.add(newActivity);

            if (newActivity.getAllocatedTime() != Duration.parse("PT0S")) {
                Ui.printProgressMessage(newActivity.getProgressPercent());
            }
            // reset activity info
            Parser.resetInfo();
            assert (Parser.tags == null);
            assert (Parser.activityName == null);
            assert (Parser.startTime == null);
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
            Ui.printDivider("Error: data file not found. Could not load into the current session's task list.");
        } catch (InvalidTimeFrameException e) {
            Ui.printDivider("Error: Invalid time frame.");
        } catch (NameTooLongException e) {
            Ui.printDivider("Error: activity name is longer than 25 characters.");
        }
    }

    /**
     * Parses the current line in the data file to an jikan.activity.Activity object.
     *
     * @param s String to parse.
     */
    private void parseDataLine(String s) throws InvalidTimeFrameException, NameTooLongException {

        if (!s.isEmpty()) {
            List<String> strings = Arrays.asList(s.split(","));
            String[] tagStrings;
            Set<String> tags = new HashSet<String>();

            // if there are tags
            if (strings.size() > 5) {
                // remove square brackets surrounding tags
                tagStrings = strings.get(5).split(" ");
                for (String i : tagStrings) {
                    tags.add(i);
                }
            }

            Activity e;
            LocalDateTime startTime = LocalDateTime.parse(strings.get(1));
            LocalDateTime endTime = LocalDateTime.parse(strings.get(2));
            Duration duration = Duration.parse(strings.get(3));
            Duration allocatedTime = Duration.parse(strings.get(4));
            e = new Activity(strings.get(0), startTime, endTime, duration, tags, allocatedTime);
            activities.add(e);
        }
    }
}
