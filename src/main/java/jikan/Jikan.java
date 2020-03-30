package jikan;

import jikan.activity.ActivityList;
import jikan.command.ByeCommand;
import jikan.command.Command;
import jikan.command.GoalCommand;
import jikan.exception.EmptyNameException;
import jikan.exception.EmptyQueryException;
import jikan.exception.InvalidTimeFrameException;
import jikan.log.LogCleaner;
import jikan.parser.Parser;
import jikan.storage.Storage;
import jikan.storage.StorageCleaner;
import jikan.ui.Ui;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the Jikan time tracker.
 */
public class Jikan {
    /** Constant file path of data file. */
    private static final String DATA_FILE_PATH = "data/data.csv";

    private static final String TAG_FILE_PATH = "data/tag/tag.csv";

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

    private static LogCleaner logCleaner = new LogCleaner();

    public static final Scanner in = new Scanner(System.in);

    public static File tagFile;

    /**
     * Main entry-point for the Jikan application.
     */
    public static void main(String[] args) throws InvalidTimeFrameException, IOException, EmptyNameException {
        ui.printGreeting();
        storage = new Storage(DATA_FILE_PATH);
        cleaner = new StorageCleaner(storage);
        cleaner.autoClean();
        logCleaner.autoClean();
        activityList = storage.createActivityList();
        activityList.storage = storage;
        GoalCommand.createFile(TAG_FILE_PATH, tagFile);

        lastShownList.activities.addAll(activityList.activities);
        parser.cleaner = cleaner;
        parser.logcleaner = logCleaner;

        while (true) {
            Command command = parser.parseUserCommands(in,activityList,cleaner,tagFile);
<<<<<<< HEAD
=======

>>>>>>> de74454c9173645ba1795e576c73b5300488a2a7
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
