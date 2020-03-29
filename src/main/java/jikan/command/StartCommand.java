package jikan.command;

import jikan.Log;
import jikan.activity.ActivityList;
import jikan.exception.EmptyNameException;
import jikan.exception.WrongDateFormatException;
import jikan.parser.Parser;
import jikan.ui.Ui;


import java.time.Duration;
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
                continueActivity(activityList, scanner, index);
            } else {
                int tagDelimiter = parameters.indexOf("/t");
                int allocateDelimiter = parameters.indexOf("/a");
                try {
                    String line = parseActivity(tagDelimiter,allocateDelimiter);
                    Parser.startTime = LocalDateTime.now();
                    Log.makeFineLog("Started: " + Parser.activityName);
                    Ui.printDivider(line);
                } catch (EmptyNameException e) {
                    Log.makeInfoLog("Activity started without activity name");
                    Ui.printDivider("Activity name cannot be empty!");
                } catch (WrongDateFormatException w) {
                    Log.makeInfoLog("Wrong format for allocated time.");
                    Ui.printDivider("Please input in this format HH/MM/SS");
                }
            }
        }
    }

    /**
     * Received user input on whether or not to continue the activity.
     *
     * @param activityList List of activities.
     * @param scanner Parse user input.
     */
    private static void continueActivity(ActivityList activityList, Scanner scanner, int index) {
        String userInput = scanner.nextLine();
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
     * @param tagDelimiter the index of the tag delimiter.
     * @param allocateDelimiter the index of the allocation delimiter.
     */
    private String parseActivity(int tagDelimiter, int allocateDelimiter) throws EmptyNameException,
            WrongDateFormatException {
        String[] tokenizedInputs = this.parameters.split(" ",2);
        Parser.activityName = tokenizedInputs[0];
        if (Parser.activityName.isEmpty()) {
            throw new EmptyNameException();
        }
        Parser.allocatedTime = Duration.parse("PT0S");
        if (allocateDelimiter != -1) {
            String allocatedTime;
            tokenizedInputs[1] = tokenizedInputs[1].substring(3);
            int index = tokenizedInputs[1].indexOf(" ");
            if (index != -1) {
                allocatedTime = tokenizedInputs[1].substring(0,index);
                tokenizedInputs[1] = tokenizedInputs[1].substring(index + 1);
            } else {
                allocatedTime = tokenizedInputs[1];
            }
            try {
                parseDuration(allocatedTime);
            } catch (WrongDateFormatException w) {
                throw new WrongDateFormatException();
            }
        }
        if (tagDelimiter != -1) {
            tokenizedInputs[1] = tokenizedInputs[1].substring(3);
            String [] tagString = tokenizedInputs[1].split(" ");
            Parser.tags.addAll(Arrays.asList(tagString));
        }
        return "Started: " + Parser.activityName;
    }

    private void parseDuration(String allocatedTime) throws WrongDateFormatException {
        String[] tokenizedInputs;
        tokenizedInputs = allocatedTime.split("/");
        if (tokenizedInputs.length != 3) {
            throw new WrongDateFormatException();
        } else {
            String hours = tokenizedInputs[0] + "H";
            String minutes = tokenizedInputs[1] + "M";
            String seconds = tokenizedInputs[2] + "S";
            String duration = "PT" + hours + minutes + seconds;
            Parser.allocatedTime = Duration.parse(duration);
        }
    }
}
