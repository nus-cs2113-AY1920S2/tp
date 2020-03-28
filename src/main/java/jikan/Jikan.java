package jikan;

import jikan.activity.ActivityList;
import jikan.command.ByeCommand;
import jikan.command.Command;
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

    public static ActivityList lastShownList = new ActivityList();

    /** Ui to handle printing. */
    private static Ui ui = new Ui();

    /** Parser to parse commands. */
    private static Parser parser = new Parser();

    /** CLeaner to delete entries in data.csv when it gets too long */
    private static StorageCleaner cleaner;

    public static final Scanner in = new Scanner(System.in);

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
        lastShownList.activities.addAll(activityList.activities);
        //public static final Scanner in = new Scanner(System.in);

        while (true) {
            Command command = parser.parseUserCommands(in,activityList,cleaner);
            if (command == null) {
                continue;
            }
            if (ByeCommand.isExit(command)) {
                command.executeCommand(activityList);
                break;
            }
            command.executeCommand(activityList);
        }
    }
}
