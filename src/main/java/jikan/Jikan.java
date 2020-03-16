package jikan;

import jikan.activity.ActivityList;
import jikan.parser.Parser;
import jikan.storage.Storage;
import jikan.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the Jikan time tracker.
 */
public class Jikan {
    /** Constant file path of data file. */
    private static final String DATA_FILE_PATH = "data/data.csv";

    /** Storage object for data file. */
    private static Storage storage;

    /** Activity list to store current tasks in. */
    private static ActivityList activityList;

    /** Ui to handle printing. */
    private static Ui ui = new Ui();

    /** Parser to parse commands*/
    private static Parser parser = new Parser();

    private static Log logger = new Log();
    
    /**
     * Creates ActivityList and loads data from data file if the data file previously existed;
     * otherwise, an empty task list is initialized.
     */
    public static void createActivityList() {
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
     * Main entry-point for the Jikan application.
     */
    public static void main(String[] args) {
        ui.printGreeting();
        Scanner in = new Scanner(System.in);
        storage = new Storage(DATA_FILE_PATH);
        createActivityList();
        parser.parseUserCommands(in, activityList, logger);
    }
}
