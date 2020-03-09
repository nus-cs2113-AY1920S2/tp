import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the list of activities.
 */
public class ActivityList {
    ArrayList<Activity> activities;
    int size;

    /**
     * Constructor for a new activity list.
     */
    public ActivityList() {
        this.activities = new ArrayList<>();
        size = 0;
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
     * Parses the current line in the data file to an Activity object.
     *
     * @param s String to parse.
     */
    private void parseDataLine(String s) {

        List<String> strings = Arrays.asList(s.split(","));

        // strings[0] = name, strings[1] = startDate, string[2] = endDate
        Activity e = new Activity(strings.get(0), strings.get(1), strings.get(2));
        activities.add(e);
    }
}
