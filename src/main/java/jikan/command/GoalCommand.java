package jikan.command;

import jikan.activity.Activity;
import jikan.activity.ActivityList;
import jikan.exception.EmptyGoalException;
import jikan.exception.EmptyTagException;
import jikan.exception.InvalidGoalCommandException;
import jikan.exception.NegativeDurationException;
import jikan.exception.NoSuchTagException;
import jikan.log.Log;
import jikan.storage.Storage;
import jikan.storage.StorageHandler;
import jikan.ui.Ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.valueOf;


/**
 * Represents a command to set a goal for activities with a specific tag in the activity list.
 */

public class GoalCommand extends Command {
    private static Scanner scanner;
    private static final String TAG_FILE_PATH = "data/tag/tag.csv";
    public static Storage tagStorage; // Storage the list was loaded from
    public static StorageHandler tagStorageHandler;

    /**
     * Constructor to create a new goal command.
     * @param parameters the parameters of the goal command.
     * @param scanner to read the user input.
     */
    public GoalCommand(String parameters, Scanner scanner, Storage tagStorage) {
        super(parameters);
        this.scanner = scanner;
        this.tagStorage = tagStorage;
        this.tagStorageHandler = new StorageHandler(tagStorage);
    }

    @Override
    public void executeCommand(ActivityList activityList) {
        try {
            int delimiter = parameters.indexOf("/g");
            int deleteDelim = parameters.indexOf("/d");
            String tagName = "";
            int index;
            if (delimiter != -1) {
                tagName = parameters.substring(0, delimiter - 1).strip();
                if (tagName.isEmpty()) {
                    throw new EmptyTagException();
                }
                index = checkIfExists(tagName, TAG_FILE_PATH);
                String tmpTime = parameters.substring(delimiter + 3);
                if (tmpTime.isEmpty()) {
                    throw new EmptyGoalException();
                }
                Duration goalTime = parseDuration(tmpTime);

                if (goalTime.isNegative()) {
                    throw new NegativeDurationException();
                }

                if (index != -1) {
                    Ui.printDivider("The goal for this tag already exists, do you want to update the goal?");
                    String userInput = scanner.nextLine();
                    updateGoal(userInput, tagName, goalTime, index);

                } else {
                    // tag does not exist in the activity list.
                    if (!existInActivity(activityList, tagName)) {
                        throw new NoSuchTagException();
                    } else {
                        tagStorage.writeToFile(tagName + "," + goalTime);
                        Ui.printDivider("The goal for " + tagName + " has been added.");
                    }
                }
            } else if (deleteDelim != -1) {
                tagName = parameters.substring(0, deleteDelim - 1).strip();
                index = checkIfExists(tagName, TAG_FILE_PATH);
                if (index != -1) {
                    Ui.printDivider("The goal for this tag has been deleted.");
                    deleteLine(index);
                } else {
                    throw new NoSuchTagException();
                }
            } else {
                throw new InvalidGoalCommandException();
            }

        } catch (EmptyTagException e) {
            Ui.printDivider("Tag name cannot be empty.");
            Log.makeInfoLog("Goal command failed as no tag name was provided.");
        } catch (InvalidGoalCommandException e) {
            Ui.printDivider("Invalid command format entered.");
            Log.makeInfoLog("Goal command failed as an incorrect format was provided.");
        } catch (IOException e) {
            Ui.printDivider("Error reading the file.\n"
                    + "If the file was open, please close it and try again.");
        } catch (NoSuchTagException e) {
            Ui.printDivider("There is no such tag.");
            Log.makeInfoLog("Goal command failed as there was no such tag saved.");
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException
                | EmptyGoalException | NumberFormatException e) {
            Ui.printDivider("Please enter the goal in the format HH:MM:SS.");
            Log.makeInfoLog("Goal command failed as an incorrect format for the goal time was provided.");
        } catch (NegativeDurationException e) {
            Ui.printDivider("Please enter a positive goal time.");
            Log.makeInfoLog("Goal command failed as a negative goal time was provided.");
        }
    }

    /**
     * Check that tag exists in the tag list.
     * @param tagName the tag name.
     * @param filePath the file path of the tag file.
     * @return index the index of the tag in the tag list.
     * @throws IOException when there is an error loading/creating the file.
     */
    public static int checkIfExists(String tagName, String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        int index = 0;
        int status = 0;
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            String[] name;
            while (line != null) {
                name = line.split(",");
                if (name[0].equals(tagName)) {
                    status = 1;
                    break;
                }
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
                index++;
            }
        } finally {
            br.close();
        }
        if (status == 0) {
            index = -1;
        }
        return index;
    }

    /**
     * Update the goal for the existing specified tag.
     * @param userInput the user response.
     * @param tagName the tag name.
     * @param goalTime the amount of time the user wants to assign to the tag.
     * @param index the index of the tag in the tag list.
     */
    private static void updateGoal(String userInput, String tagName, Duration goalTime, int index) throws IOException {
        if (userInput.equalsIgnoreCase("yes") || userInput.equalsIgnoreCase("y")) {
            deleteLine(index);
            tagStorage.writeToFile(tagName + "," + goalTime);
            Ui.printDivider("The goal for " + tagName + " was updated");
        } else if (userInput.equalsIgnoreCase("no") || userInput.equalsIgnoreCase("n")) {
            Ui.printDivider("Okay then, what else can I do for you?");
        } else {
            Ui.printDivider("Incorrect format entered, please only enter yes or no.");
        }
    }

    /**
     * Removes the line whose index matches lineNumber from file.
     *
     * @param lineNumber Index of line to remove.
     * @throws IOException If an error occurs while writing the new list to file.
     */
    public static void deleteLine(int lineNumber) throws IOException {
        // Read file into list of strings, where each string is a line in the file
        List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(TAG_FILE_PATH),
                StandardCharsets.UTF_8));
        fileContent.remove(lineNumber);
        saveNewTags(fileContent);
    }

    /**
     * Saves the updated tags to the csv file.
     *
     * @param newList The list containing the updated data.
     * @throws IOException If an error occurs while writing the new list to file.
     */
    public static void saveNewTags(List<String> newList) throws IOException {
        tagStorage.clearFile();
        FileWriter fw = new FileWriter(TAG_FILE_PATH, true);

        for (String s : newList) {
            fw.write(s + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Check if the tag exists in the activity list.
     * @param targetList the activity list to check.
     * @param tagName the specified tag name.
     * @return true or false.
     */
    private boolean existInActivity(ActivityList targetList, String tagName) {
        for (Activity i : targetList.activities) {
            if (i.getTags().contains(tagName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Converts the user input into a duration object.
     * @param input the user input.
     * @return the duration object.
     */
    public static Duration parseDuration(String input) throws
            ArrayIndexOutOfBoundsException {
        String[] fields = input.split(":");
        int colonIndex = input.indexOf(':');
        String hh = fields[0];
        String mm = fields[1];
        String ss = fields[2];
        return Duration.ofHours(valueOf(hh)).plusMinutes(valueOf(mm)).plusSeconds(valueOf(ss));
    }
}
