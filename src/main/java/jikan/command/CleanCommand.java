package jikan.command;

import jikan.log.Log;
import jikan.activity.ActivityList;
import jikan.exception.InvalidCleanCommandException;
import jikan.cleaner.LogCleaner;
import jikan.cleaner.StorageCleaner;
import jikan.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to clear previously saved log files.
 */
public class CleanCommand extends Command {

    StorageCleaner storageCleaner;
    LogCleaner logCleaner;

    /**
     * Constructor to create a new clean command.
     */
    public CleanCommand(String parameters, StorageCleaner cleaner, LogCleaner logCleaner) {
        super(parameters);
        this.storageCleaner = cleaner;
        this.logCleaner = logCleaner;
    }

    /**
     * Method to enable or disable cleaning of log files.
     */
    @Override
    public void executeCommand(ActivityList activityList) {
        String function = "";
        try {
            function = getFunctionFromCommand();
        } catch (InvalidCleanCommandException e) {
            Ui.printDivider("Invalid clean command!");
        }
        switch (function) {
        case "on":
            try {
                handleOnFunction();
            } catch (IOException e) {
                String line = "There is a problem with accessing the cleaning file";
                Ui.printDivider(line);
            }
            break;
        case "off":
            try {
                handleOffFunction();
            } catch (IOException e) {
                String line = "There is a problem with accessing the cleaning file";
                Ui.printDivider(line);
            }
            break;
        case "/n":
            try {
                handleSetFunction();
            } catch (NumberFormatException n) {
                Ui.printDivider("please provide a number");
            } catch (IOException e) {
                String line = "There is a problem with accessing the cleaning file";
                Ui.printDivider(line);
            }
            break;
        default:
            break;
        }
    }

    private void handleOnFunction() throws IOException {
        String line = this.parameters;
        if (line.contains("log")) {
            logCleaner.setStatus(true, logCleaner.numberOfLogsToClean);
            assert logCleaner.toClean;
            line = "Auto cleaning enabled for logs";
            Ui.printDivider(line);
            Log.makeInfoLog("User has turned on automated cleaning for logs");
        } else {
            storageCleaner.setStatus(true, storageCleaner.numberOfActivitiesToClean);
            assert storageCleaner.toClean;
            line = "Auto cleaning enabled";
            Ui.printDivider(line);
            Log.makeInfoLog("User has turned on automated cleaning");
        }
    }

    private void handleOffFunction() throws IOException {
        String line = this.parameters;
        if (line.contains("log")) {
            logCleaner.setStatus(false, logCleaner.numberOfLogsToClean);
            assert !logCleaner.toClean;
            line = "Auto cleaning disabled for logs";
            Ui.printDivider(line);
            Log.makeInfoLog("User has turned off automated cleaning for logs");
        } else {
            storageCleaner.setStatus(false, storageCleaner.numberOfActivitiesToClean);
            assert !storageCleaner.toClean;
            line = "Auto cleaning disabled";
            Ui.printDivider(line);
            Log.makeInfoLog("User has turned off automated cleaning");
        }
    }

    private void handleSetFunction() throws IOException, NumberFormatException {
        String line = this.parameters;
        if (line.contains("log")) {
            try {
                int value = getNumberFromCommand(line);
                logCleaner.setNumberOfLogsToClean(value);
                line = "Number of activities to clean is set to " + value;
                Ui.printDivider(line);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new NumberFormatException();
            } catch (IOException e) {
                throw new IOException();
            }
        } else {
            try {
                int value = getNumberFromCommand(line);
                storageCleaner.setNumberOfActivitiesToClean(value);
                line = "Number of activities to clean is set to " + value;
                Ui.printDivider(line);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new NumberFormatException();
            } catch (IOException e) {
                throw new IOException();
            }
        }
    }

    /**
     * Method to check the required function from user command.
     * @return the appropriate function ("on","off","/n").
     * @throws InvalidCleanCommandException when the function is not recognised.
     */
    private String getFunctionFromCommand() throws InvalidCleanCommandException {
        String line = this.parameters;
        if (line.contains("on")) {
            if (!line.contains("off") && !line.contains("/n")) {
                return "on";
            } else {
                throw new InvalidCleanCommandException();
            }
        } else if (line.contains("off")) {
            if (!line.contains("on") && !line.contains("/n")) {
                return "off";
            } else {
                throw new InvalidCleanCommandException();
            }
        } else if (line.contains("/n")) {
            if (!line.contains("on") && !line.contains("off")) {
                return "/n";
            } else {
                throw new InvalidCleanCommandException();
            }
        } else {
            throw new InvalidCleanCommandException();
        }
    }

    /**
     * Method to extract the number of activities to clean from user input.
     * @param line full user input with command header extracted out.
     * @return the number of activities to clean.
     */
    private int getNumberFromCommand(String line) {
        int index = line.indexOf("/n");
        try {
            String numberString = line.substring(index + 3);
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
