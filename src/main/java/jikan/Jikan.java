package jikan;

import jikan.activity.ActivityList;
import jikan.exception.InvalidTimeFrameException;
import jikan.parser.Parser;
import jikan.storage.Storage;
import jikan.storage.StorageCleaner;
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

    /** Parser to parse commands. */
    private static Parser parser = new Parser();

    /** CLeaner to delete entries in data.csv when it gets too long */
    private static StorageCleaner cleaner;

    /**
     * Main entry-point for the Jikan application.
     */
    public static void main(String[] args) throws InvalidTimeFrameException, IOException {
        ui.printGreeting();
        storage = new Storage(DATA_FILE_PATH);
        cleaner = new StorageCleaner(storage);
        cleaner.autoClean();
        activityList = storage.createActivityList();
        activityList.storage = storage;
        Scanner in = new Scanner(System.in);
        parser.parseUserCommands(in, activityList, cleaner);
    }
}
