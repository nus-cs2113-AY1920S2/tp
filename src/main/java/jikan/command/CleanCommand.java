package jikan.command;

import jikan.Log;
import jikan.activity.ActivityList;
import jikan.exception.InvalidCleanCommandException;
import jikan.parser.Parser;
import jikan.storage.Storage;
import jikan.storage.StorageCleaner;
import jikan.ui.Ui;

import java.io.IOException;

import static jikan.parser.Parser.tokenizedInputs;

/**
 * Represents a command to clear previously saved log files.
 */
public class CleanCommand extends Command {

    StorageCleaner cleaner;

    /**
     * Constructor to create a new clean command.
     */
    public CleanCommand(String parameters, StorageCleaner cleaner) {
        super(parameters);

        this.cleaner = cleaner;
    }

    /**
     * Method to enable or disable cleaning of log files.
     */
    @Override
    public void executeCommand(ActivityList activityList) {
        try {
            String line;
            if (parameters.equals("on")) {
                cleaner.setStatus(true);
                assert cleaner.toClean;
                line = "Auto cleaning enabled";
                Ui.printDivider(line);
                Log.makeInfoLog("User has turned on automated cleaning");
            } else if (parameters.equals("off")) {
                cleaner.setStatus(false);
                assert !cleaner.toClean;
                line = "Auto cleaning disabled";
                Ui.printDivider(line);
                Log.makeInfoLog("User has turned off automated cleaning");
            } else if (parameters.contains("set")) {
                try {
                    int value = getNumberFromCommand(parameters);
                    cleaner.setNumberOfActivitiesToClean(value);
                    line = "Number of activities to clean is set to " + value;
                    Ui.printDivider(line);
                } catch (NumberFormatException | IndexOutOfBoundsException n) {
                    Ui.printDivider("please provide a number");
                }
            } else {
                if (parameters.isEmpty()) {
                    throw new IOException();
                } else {
                    throw new InvalidCleanCommandException();
                }
            }
        } catch (IOException e) {
            Ui.printDivider("No keyword was given.");
        } catch (InvalidCleanCommandException e) {
            Ui.printDivider("Invalid clean command!");
        }
    }

    /**
     * Method to extract the number of activities to clean from user input.
     * @param line full user input with command header extracted out.
     * @return the number of activities to clean.
     */
    private int getNumberFromCommand(String line) {
        int index = line.indexOf("set");
        try {
            String numberString = line.substring(index + 4);
            try {
                return Integer.parseInt(numberString);
            } catch (NumberFormatException n) {
                throw new NumberFormatException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }
}
