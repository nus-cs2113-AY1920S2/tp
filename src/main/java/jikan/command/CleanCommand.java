package jikan.command;

import jikan.log.Log;
import jikan.activity.ActivityList;
import jikan.exception.NegativeNumberException;
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
    private boolean toCleanStorage;
    private boolean toCleanLog;

    /**
     * Constructor to create a new clean command.
     */
    public CleanCommand(String parameters, StorageCleaner cleaner, LogCleaner logCleaner) {
        super(parameters);
        this.storageCleaner = cleaner;
        this.logCleaner = logCleaner;
    }

    @Override
    public void executeCommand(ActivityList activityList) {
        boolean isEmpty = isParameterEmpty();
        if (isEmpty) {
            stopExecution();
        } else {
            continueExecution();
        }
    }

    /**
     * Method to check if user input is empty for clean command.
     * @return true if is empty and false otherwise.
     */
    private boolean isParameterEmpty() {
        String parametersTrimmed = this.parameters.trim();
        if (parametersTrimmed.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Stops execution when user input is empty.
     */
    private void stopExecution() {
        Log.makeInfoLog("Clean command received empty parameters");
        Ui.printDivider("No valid parameters to clean command found.");
    }

    /**
     * Method to continue execution given a non empty user input.
     */
    private void continueExecution() {
        toCleanLog = false;
        toCleanStorage = false;
        String parametersTrimmed = this.parameters.trim();
        assert !parametersTrimmed.isEmpty();
        try {
            String firstWord = getFirstWord(parametersTrimmed);
            processCommand(firstWord);
        } catch (InvalidCleanCommandException e) {
            Log.makeInfoLog("Invalid clean command");
            Ui.printDivider("Invalid format received for clean command.");
        }

    }

    /**
     * Method to extract first word for a given line.
     * @param line a string representing a line of information.
     * @return the first word of the line.
     */
    private String getFirstWord(String line) {
        int delimiter = line.indexOf(" ");
        String word;
        if (delimiter == -1) {
            return line;
        } else {
            word = line.substring(0, delimiter);
            return word;
        }
    }

    /**
     * Method that parses in a word and remove the word from the this.parameters string.
     * @param word a word to remove.
     * @return a this.parameters string without the word.
     */
    private String getRemainingParameter(String word) {
        int index = this.parameters.indexOf(word);
        int number = word.length();
        String remainingParameter = this.parameters.substring(index + number);
        remainingParameter = remainingParameter.trim();
        return remainingParameter;
    }

    /**
     * Check if we are dealing with storage or log cleaner.
     * @param firstWord indicative of whether the user is dealing with logs or storage.
     * @throws InvalidCleanCommandException when the format of user input is wrong.
     */
    private void processCommand(String firstWord) throws InvalidCleanCommandException {
        assert !toCleanLog && !toCleanStorage;
        if (firstWord.equals("log")) {
            toCleanLog = true;
            processLogCommand();
        } else {
            toCleanStorage = true;
            handleCase(firstWord);
        }
    }

    /**
     * Handle the different functions on a case by case basis.
     * @param firstWord indicative of the function being called.
     * @throws InvalidCleanCommandException when the format of user input is wrong.
     */
    private void handleCase(String firstWord) throws InvalidCleanCommandException {
        switch (firstWord) {
        case "on":
            handleOnFunction();
            break;
        case "off":
            handleOffFunction();
            break;
        case "/n":
            handleSetFunction();
            break;
        default:
            throw new InvalidCleanCommandException();
        }
    }

    /**
     * Use to process log commands, thus all the function called in this method
     * will only deal with log cleaner.
     * @throws InvalidCleanCommandException when the format of user input is wrong.
     */
    private void processLogCommand() throws InvalidCleanCommandException {
        String remainingCommand = getRemainingParameter("log");
        if (remainingCommand.isEmpty()) {
            throw new InvalidCleanCommandException();
        } else {
            String firstWord = getFirstWord(remainingCommand);
            handleCase(firstWord);
        }
    }

    /**
     * A general purpose function to handle the "on" command.
     * @throws InvalidCleanCommandException when the format of user input is wrong.
     */
    private void handleOnFunction() throws InvalidCleanCommandException {
        String remainingParameter = getRemainingParameter("on");
        if (remainingParameter.isEmpty()) {
            setStatusOn();
        } else {
            throw new InvalidCleanCommandException();
        }
    }

    /**
     * Forwards the "on" command to the appropriate cleaner (either storage or log cleaner).
     */
    private void setStatusOn() {
        assert !toCleanStorage || !toCleanLog;
        if (toCleanStorage) {
            setStorageCleanerOn();
        } else if (toCleanLog) {
            setLogCleanerOn();
        }
    }

    /**
     * Method to switch on the storage cleaner.
     */
    private void setStorageCleanerOn() {
        try {
            storageCleaner.setStatus(true, storageCleaner.numberOfActivitiesToClean);
        } catch (IOException e) {
            Ui.printDivider("Error in loading/writing to storage status file");
            Log.makeInfoLog("Error in accessing the storage status file");
        }
        assert storageCleaner.toClean;
        Ui.printDivider("Auto cleaning enabled for storage.");
        Log.makeInfoLog("User has turned on automated cleaning for storage.");
    }

    /**
     * Method to switch on the log cleaner.
     */
    private void setLogCleanerOn() {
        try {
            logCleaner.setStatus(true, logCleaner.numberOfLogsToClean);
        } catch (IOException e) {
            Ui.printDivider("Error in loading/writing to log status file");
            Log.makeInfoLog("Error in accessing the log status file");
        }
        assert logCleaner.toClean;
        Ui.printDivider("Auto cleaning enabled for logs.");
        Log.makeInfoLog("User has turned on automated cleaning for logs.");
    }

    /**
     * A general purpose function to handle the "off" command.
     * @throws InvalidCleanCommandException when the format of user input is wrong.
     */
    private void handleOffFunction() throws InvalidCleanCommandException {
        String remainingParameter = getRemainingParameter("off");
        if (remainingParameter.isEmpty()) {
            setStatusOff();
        } else {
            throw new InvalidCleanCommandException();
        }
    }

    /**
     * Forwards the "off" command to the appropriate cleaner (either storage or log cleaner).
     */
    private void setStatusOff() {
        assert !toCleanStorage || !toCleanLog;
        if (toCleanStorage) {
            setStorageCleanerOff();
        } else if (toCleanLog) {
            setLogCleanerOff();
        }
    }

    /**
     * Method to switch off the storage cleaner.
     */
    private void setStorageCleanerOff() {
        try {
            storageCleaner.setStatus(false, storageCleaner.numberOfActivitiesToClean);
        } catch (IOException e) {
            Ui.printDivider("Error in loading/writing to storage status file");
            Log.makeInfoLog("Error in accessing the storage status file");
        }
        assert !storageCleaner.toClean;
        Ui.printDivider("Auto cleaning disabled for storage.");
        Log.makeInfoLog("User has turned off automated cleaning for storage.");
    }

    /**
     * Method to switch off the log cleaner.
     */
    private void setLogCleanerOff() {
        try {
            logCleaner.setStatus(false, logCleaner.numberOfLogsToClean);
        } catch (IOException e) {
            Ui.printDivider("Error in loading/writing to log status file");
            Log.makeInfoLog("Error in accessing the log status file");
        }
        assert !logCleaner.toClean;
        Ui.printDivider("Auto cleaning disabled for logs.");
        Log.makeInfoLog("User has turned off automated cleaning for logs.");
    }

    /**
     * A general purpose function to handle the "/n" command.
     * @throws InvalidCleanCommandException when the format of user input is wrong.
     */
    private void handleSetFunction() throws InvalidCleanCommandException {
        String remainingParameter = getRemainingParameter("/n");
        if (remainingParameter.isEmpty()) {
            throw new InvalidCleanCommandException();
        } else {
            setValue(remainingParameter);
        }
    }

    /**
     * Forwards the "/n" command to the appropriate cleaner (either storage or log cleaner).
     * @param remainingParameter a string with information on the value to set to.
     * @throws InvalidCleanCommandException when the format of user input is wrong.
     */
    private void setValue(String remainingParameter) throws InvalidCleanCommandException {
        assert !toCleanStorage || !toCleanLog;
        if (toCleanStorage) {
            setValueForStorage(remainingParameter);
        } else if (toCleanLog) {
            setValueForLogs(remainingParameter);
        }
    }

    /**
     * Method to set a value for storage cleaner.
     * @param remainingParameter a string with information on the value to set to.
     * @throws InvalidCleanCommandException when the format of user input is wrong.
     */
    private void setValueForStorage(String remainingParameter) throws InvalidCleanCommandException {
        try {
            int value = getNumber(remainingParameter);
            storageCleaner.setNumberOfActivitiesToClean(value);
            Ui.printDivider("Number of activities to clean is set to " + value);
            Log.makeInfoLog("Storage Cleaner set to " + value);
        } catch (NegativeNumberException e) {
            Ui.printDivider("Please provide a positive number.");
            Log.makeInfoLog("Negative number given in clean command");
        } catch (IOException e) {
            Ui.printDivider("Error in loading/writing to status file");
            Log.makeInfoLog("Error in accessing the status file");
        }
    }

    /**
     * Method to set a value for log cleaner.
     * @param remainingParameter a string with information on the value to set to.
     * @throws InvalidCleanCommandException when the format of user input is wrong.
     */
    private void setValueForLogs(String remainingParameter) throws InvalidCleanCommandException {
        try {
            int value = getNumber(remainingParameter);
            logCleaner.setNumberOfLogsToClean(value);
            Ui.printDivider("Number of logs to clean is set to " + value);
            Log.makeInfoLog("Log Cleaner set to " + value);
        } catch (NegativeNumberException e) {
            Ui.printDivider("Please provide a positive number.");
            Log.makeInfoLog("Negative number given in clean command");
        } catch (IOException e) {
            Ui.printDivider("Error in loading/writing to status file");
            Log.makeInfoLog("Error in accessing the status file");
        }
    }

    /**
     * Method to convert the parameter numberString to an integer.
     * @param numberString a string that represents the value to set to.
     * @return an integer numberString.
     * @throws InvalidCleanCommandException when the format of user input is wrong.
     * @throws NegativeNumberException when the numberString is negative.
     */
    private int getNumber(String numberString) throws InvalidCleanCommandException, NegativeNumberException {
        try {
            int value = Integer.parseInt(numberString);
            if (value < 0) {
                throw new NegativeNumberException();
            } else {
                return value;
            }
        } catch (NumberFormatException e) {
            throw new InvalidCleanCommandException();
        }
    }
}
