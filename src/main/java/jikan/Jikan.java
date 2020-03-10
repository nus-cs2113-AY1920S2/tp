package jikan;

import jikan.activity.ActivityList;
import jikan.parser.Parser;
import jikan.storage.Storage;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the jikan.Jikan time tracker.
 */
public class Jikan {
    /** Constant file path of data file. */
    private static final String DATA_FILE_PATH = "data/data.csv";

    /** Storage object for data file. */
    private static Storage storage;

    /** jikan.activity.Activity list to store current tasks in. */
    private static ActivityList activityList;

    public static final String GREETING = "___________________________________________\n"
            + "   0101 01010101 01   01  .010.  101   01 \n"
            + "   `10'   `10'   10 ,10' 10' `10 101o  01 \n"
            + "    01     01    01,01   01   01 0101o 10 \n"
            + "    10     10    10`10   10ooo10 10 11o01 \n"
            + "01. 01    .01.   01 `01. 01   01 01  1010 \n"
            + "010101  01010101 10   10 10   10 10   110 \n"
            + "___________________________________________\n"
            + " Hello! I'm jikan.Jikan\n"
            + " What can I do for you today?\n"
            + "-------------------------------------------\n";
    
    /**
     * Creates jikan.activity.ActivityList and loads data from data file if the data file previously existed;
     * otherwise, an empty task list is initialized.
     */
    private static void createActivityList() {
        try {
            if (storage.loadFile()) {
                activityList = new ActivityList(storage);
            } else {
                activityList = new ActivityList();
                activityList.storage = storage;
            }
        } catch (IOException e) {
            System.out.println("Error loading/creating data file.");
        }
    }
  
    /**
     * Main entry-point for the jikan.Jikan application.
     */
    public static void main(String[] args) {
        System.out.println(GREETING);
        Scanner in = new Scanner(System.in);
        storage = new Storage(DATA_FILE_PATH);
        createActivityList();
        Parser.parseUserCommands(in, activityList);
    }
}
