package jikan.command;

import jikan.exception.EmptyTagException;
import jikan.log.Log;
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
            int tagDelimiter = parameters.indexOf("/t");
            int allocateDelimiter = parameters.indexOf("/a");

            // check if there exists an activity with this name
            String activityName = getActivityName(tagDelimiter,allocateDelimiter);

            if (activityName.length() > 25) {
                Log.makeInfoLog("Activity name longer than 25 characters");
                Ui.printDivider("Please input an activity name that is shorter than 25 characters.");
                return;
            }

            Parser.activityName = activityName;
            int index = activityList.findActivity(activityName);
            if (index != -1) {
                Ui.printDivider("There is already an activity with this name. Would you like to continue it?");
                continueActivity(activityList, scanner, index);
            } else {
                try {
                    parseActivity(tagDelimiter,allocateDelimiter);
                    Parser.startTime = LocalDateTime.now();
                    Log.makeFineLog("Started: " + activityName);
                    Ui.printDivider("Started: " + activityName);
                } catch (WrongDateFormatException w) {
                    Log.makeInfoLog("Wrong format for allocated time.");
                    Ui.printDivider("Please input in this format HH/MM/SS");
                } catch (EmptyNameException e) {
                    Ui.printDivider("Activity name cannot be empty!");
                } catch (EmptyTagException e) {
                    Ui.printDivider("Tags cannot be empty!");
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
            Parser.activityName = null;
            Ui.printDivider("Okay then, what else can I do for you?");
        }
    }

    /**
     * Parses the started activity for name and tags.
     *
     * @param tagDelimiter the index of the tag delimiter.
     * @param allocateDelimiter the index of the allocation delimiter.
     */
    private void parseActivity(int tagDelimiter, int allocateDelimiter) throws EmptyNameException,
            WrongDateFormatException, EmptyTagException {
        String activityName = getActivityName(tagDelimiter, allocateDelimiter);
        if (activityName.isBlank()) {
            throw new EmptyNameException();
        }
        String activityInfo;
        if (tagDelimiter != -1 || allocateDelimiter != -1) {
            activityInfo = this.parameters.substring(activityName.length() + 1);
        } else {
            return;
        }
        if (allocateDelimiter != -1) {
            String allocatedTime;
            try {
                activityInfo = activityInfo.substring(3);
                int index = activityInfo.indexOf(" ");
                if (index != -1) {
                    allocatedTime = activityInfo.substring(0,index);
                    activityInfo = activityInfo.substring(index + 1);
                } else {
                    allocatedTime = activityInfo;
                }
                parseDuration(allocatedTime);
            } catch (WrongDateFormatException | StringIndexOutOfBoundsException w) {
                throw new WrongDateFormatException();
            }
        }
        if (tagDelimiter != -1) {
            try {
                activityInfo = activityInfo.substring(3);
                String [] tagString = activityInfo.split(" ");
                Parser.tags.addAll(Arrays.asList(tagString));
            } catch (StringIndexOutOfBoundsException e) {
                throw new EmptyTagException();
            }

        }
        //return "Started: " + Parser.activityName;
    }

    private String getActivityName(int tagDelimiter, int allocateDelimiter) {
        if (tagDelimiter != -1) {
            return this.parameters.substring(0,tagDelimiter).strip();
        } else if (allocateDelimiter != -1) {
            return this.parameters.substring(0,allocateDelimiter).strip();
        } else {
            return this.parameters;
        }

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
