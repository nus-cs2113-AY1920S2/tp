package jikan;

import jikan.activity.ActivityList;
import jikan.command.ByeCommand;
import jikan.command.Command;
import jikan.exception.EmptyNameException;
import jikan.exception.ExtraParametersException;
import jikan.cleaner.LogCleaner;
import jikan.parser.Parser;
import jikan.storage.Storage;
import jikan.cleaner.StorageCleaner;
import jikan.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Represents the Jikan time tracker.
 */
public class Jikan {
    /** Constant file path of data file. */
    private static final String DATA_FILE_PATH = "data/data.csv";

    private static final String TAG_FILE_PATH = "data/tag/tag.csv";

    /** Storage object for data file. */
    private static Storage storage;

    private static Storage tagStorage;

    /** Activity list to store current tasks in. */
    private static ActivityList activityList;

    public static ActivityList lastShownList = new ActivityList();

    /** Ui to handle printing. */
    private static Ui ui = new Ui();

    /** Parser to parse commands. */
    private static Parser parser = new Parser();

    /** CLeaner to delete entries in data.csv when it gets too long */
    private static StorageCleaner storageCleaner;

    private static LogCleaner logCleaner = new LogCleaner();

    public static final Scanner in = new Scanner(System.in);

    /**
     * Main entry-point for the Jikan application.
     */
    public static void main(String[] args) {
        ui.printGreeting();
        storage = new Storage(DATA_FILE_PATH);
        tagStorage = new Storage(TAG_FILE_PATH);
        storageCleaner = new StorageCleaner(storage);
        try {
            storageCleaner.storageAutoClean();
            logCleaner.logAutoClean();
            activityList = storage.createActivityList();
        } catch (IOException e) {
            Ui.printDivider("Error while preparing application.\n"
                    + "If any data files are open, please close them and try again.");
            exit(0);
        }

        lastShownList.activities.addAll(activityList.activities);
        parser.cleaner = storageCleaner;
        parser.logcleaner = logCleaner;
        parser.tagStorage = tagStorage;

        while (true) {
            try {
                Command command = parser.parseUserCommands(in);
                if (command == null) {
                    continue;
                }
                if (ByeCommand.isExit(command)) {
                    command.executeCommand(activityList);
                    break;
                }
                command.executeCommand(activityList);
                // This block should theoretically never be entered (if command is empty, it just continues)
                // However, you never know..
            } catch (EmptyNameException | ExtraParametersException e) {
                Ui.printDivider("Error parsing command. Please try again.");
            }
        }
    }
}
