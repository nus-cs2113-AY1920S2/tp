package jikan.command;

import jikan.Jikan;
import jikan.Log;
import jikan.activity.ActivityList;
import jikan.exception.EmptyNameException;
import jikan.parser.Parser;
import jikan.ui.Ui;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Represents a command to start an activity.
 */
public class StartCommand extends Command {

    private Scanner scanner;

    /**
     * Constructor to create a new start command.
     */
    public StartCommand(String parameters, Scanner scanner) {
        super(parameters);
        this.scanner = scanner;
    }

    @Override
    public void executeCommand(ActivityList activityList) {
        try {
            // Parser.parseStart(activityList, Jikan.in);
            // check if an activity has already been started
            if (Parser.startTime != null) {
                String line = Parser.activityName + " is ongoing!";
                Log.makeInfoLog("Could not start activity due to already ongoing activity.");
                Ui.printDivider(line);
            } else {
                // tags should be reset
                assert Parser.tags.isEmpty();

                // check if there exists an activity with this name
                int index = activityList.findActivity(parameters);
                if (index != -1) {
                    Ui.printDivider("There is already an activity with this name. Would you like to continue it?");
                    String userInput = scanner.nextLine();
                    continueActivity(activityList, userInput, index);
                } else {
                    int delimiter = parameters.indexOf("/t");
                    String line = parseActivity(delimiter);
                    Parser.startTime = LocalDateTime.now();
                    Log.makeFineLog("Started: " + Parser.activityName);
                    Ui.printDivider(line);
                }
            }
        } catch (EmptyNameException | NullPointerException | ArrayIndexOutOfBoundsException e) {
            Log.makeInfoLog("Activity started without activity name");
            Ui.printDivider("Activity name cannot be empty!");
        }
    }

    /**
     * Received user input on whether or not to continue the activity.
     *
     * @param activityList List of activities.
     * @param userInput Whether the user wants to continue the activity.
     */
    private static void continueActivity(ActivityList activityList, String userInput, int index) {
        if (userInput.equalsIgnoreCase("yes") || userInput.equalsIgnoreCase("y")) {
            Parser.activityName = activityList.get(index).getName();
            Parser.startTime = LocalDateTime.now();
            Parser.tags = activityList.get(index).getTags();
            Parser.continuedIndex = index;
            Ui.printDivider(Parser.activityName + " was continued");
            Log.makeFineLog(Parser.activityName + " was continued.");
        } else {
            Ui.printDivider("Okay then, what else can I do for you?");
        }
    }

    /**
     * Parses the started activity for name and tags.
     *
     * @param delimiter The index of the tag delimiter.
     */
    private String parseActivity(int delimiter) throws EmptyNameException {
        if (delimiter == -1) {
            // no tags
            Parser.activityName = this.parameters.strip();
            if (Parser.activityName.isEmpty()) {
                throw new EmptyNameException();
            }
        } else {
            Parser.activityName = this.parameters.substring(0, delimiter).strip();
            if (Parser.activityName.isEmpty()) {
                throw new EmptyNameException();
            }
            String[] tagString = this.parameters.substring(delimiter + 3).split(" ");
            Parser.tags.addAll(Arrays.asList(tagString));
        }
        return "Started: " + Parser.activityName;
    }
}
