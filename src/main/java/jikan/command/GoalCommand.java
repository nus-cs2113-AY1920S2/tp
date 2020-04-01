package jikan.command;

import jikan.activity.Activity;
import jikan.activity.ActivityList;
import jikan.exception.EmptyTagException;
import jikan.exception.InvalidTimeFrameException;
import jikan.exception.NoSuchTagException;
import jikan.ui.Ui;

import java.io.BufferedReader;
import java.io.File;
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

    /**
     * Constructor to create a new goal command.
     * @param parameters the parameters of the goal command.
     * @param scanner to read the user input.
     */
    public GoalCommand(String parameters, Scanner scanner) {
        super(parameters);
        this.scanner = scanner;
    }

    @Override
    public void executeCommand(ActivityList activityList) {
        try {
            int delimiter = parameters.indexOf("/g");
            String tagName = parameters.substring(0, delimiter - 1).strip();
            if (tagName.isEmpty()) {
                throw new EmptyTagException();
            }
            if (delimiter != -1) {
                int index = checkIfExists(tagName);
                String tmpTime = parameters.substring(delimiter + 3);
                Duration goalTime = parseDuration(tmpTime);

                if (index != -1) {
                    Ui.printDivider("The goal for this tag already exists, do you want to update the goal?");
                    String userInput = scanner.nextLine();
                    updateGoal(userInput, tagName, goalTime, index);

                } else {
                    // tag does not exist in the activity list.
                    if (!existInActivity(activityList, tagName)) {
                        throw new NoSuchTagException();
                    } else {
                        addTagLine(tagName + "," + goalTime);
                        Ui.printDivider("The goal for " + tagName + " has been added!");
                    }
                }
            } else {
                throw new InvalidTimeFrameException();
            }
        } catch (EmptyTagException | StringIndexOutOfBoundsException e) {
            Ui.printDivider("Tag name cannot be empty!");
        } catch (InvalidTimeFrameException e) {
            Ui.printDivider("Goal cannot be empty!");
        } catch (IOException e) {
            Ui.printDivider("Error reading the file!");
        } catch (NoSuchTagException e) {
            Ui.printDivider("There is no such tag!");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            Ui.printDivider("Please enter the goal in the format HH:MM:SS");
        }
    }

    /**
     * Creates a new tag file.
     * @param filePath the filepath of the tag file.
     * @param tagFile the File object.
     */
    public static void createFile(String filePath, File tagFile) {
        tagFile = new File(filePath);
        try {
            if (!tagFile.exists()) {
                tagFile.getParentFile().mkdirs(); // Create data directory (does nothing if directory already exists)
                tagFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error loading/creating file");
        }
    }



    /**
     * Check that tag exists in the tag list.
     * @param tagName the tag name.
     * @return index the index of the tag in the tag list.
     * @throws IOException when there is an error loading/creating the file.
     */
    public static int checkIfExists(String tagName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(TAG_FILE_PATH));
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
            removeLine(index);
            writeToFile(tagName + "," + goalTime);
            Ui.printDivider("The goal for " + tagName + " was updated");
        } else {
            Ui.printDivider("Okay then, what else can I do for you?");
        }
    }

    /**
     * Updates tag file with new tag.
     *
     * @param dataLine Line to write to file.
     */
    public static void addTagLine(String dataLine) {
        try {
            writeToFile(dataLine);
        } catch (IOException e) {
            System.out.println("Error saving tag to tag file.");
        }
    }

    /**
     * Removes the line whose index matches lineNumber from file.
     *
     * @param lineNumber Index of line to remove.
     * @throws IOException If an error occurs while writing the new list to file.
     */
    public static void removeLine(int lineNumber) throws IOException {
        // Read file into list of strings, where each string is a line in the file
        List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(TAG_FILE_PATH),
                StandardCharsets.UTF_8));
        fileContent.remove(lineNumber);
    }

    /**
     * Writes the input string to file.
     *
     * @param s The input string.
     * @throws IOException If an error occurs while writing.
     */
    public static void writeToFile(String s) throws IOException {
        FileWriter fw = new FileWriter(TAG_FILE_PATH, true);
        fw.write(s + System.lineSeparator());
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
    public static Duration parseDuration(String input) throws InvalidTimeFrameException,
            ArrayIndexOutOfBoundsException {
        String[] fields = input.split(":");
        int colonIndex = input.indexOf(':');
        String hh = fields[0];
        String mm = fields[1];
        String ss = fields[2];
        return Duration.ofHours(valueOf(hh)).plusMinutes(valueOf(mm)).plusSeconds(valueOf(ss));
    }
}
