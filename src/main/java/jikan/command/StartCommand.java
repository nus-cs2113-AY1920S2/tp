package jikan.command;

import jikan.log.Log;
import jikan.activity.ActivityList;
import jikan.parser.Parser;
import jikan.ui.Ui;


import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a command to start an activity.
 */
public class StartCommand extends Command {

    private Scanner scanner;
    private boolean hasAllocation = false;
    private boolean hasTag = false;
    private boolean hasAllocationAndTag = false;
    private static final int MAX_ACTIVITY_LENGTH = 25;

    /**
     * Constructor to create a new start command.
     */
    public StartCommand(String parameters, Scanner scanner) {
        super(parameters);
        this.scanner = scanner;
    }

    @Override
    public void executeCommand(ActivityList activityList) {
        boolean hasStarted = hasStarted(Parser.startTime);
        if (hasStarted) {
            stopExecution();
        } else {
            assert Parser.tags.isEmpty();
            continueExecution(activityList);
        }
    }

    /**
     * Checks if there is a concurrently running activity.
     * @param startTime a LocalDateTime object to check if parser is waiting for a running activity.
     * @return true if there is a concurrent running activity and false otherwise.
     */
    private boolean hasStarted(LocalDateTime startTime) {
        return startTime != null;
    }

    /**
     * Stops executing current activity if a concurrent running activity is found.
     */
    private void stopExecution() {
        assert Parser.startTime != null;
        String line = Parser.activityName + " is ongoing!";
        Log.makeInfoLog("Could not start activity due to already ongoing activity.");
        Ui.printDivider(line);
    }

    /**
     * Continues execution as no concurrent running activity is found.
     * @param activityList a list of tracked activities.
     */
    private void continueExecution(ActivityList activityList) {
        String activityName = parseActivityName(this.parameters);
        if (activityName.isEmpty()) {
            Log.makeFineLog("Empty activity name was provided.");
            Ui.printDivider("Activity name cannot be empty");
        } else {
            checkActivity(activityName, activityList);
        }
    }


    /**
     * Get activity name from parameters to start command.
     * @param parameters the parameters to start command.
     * @return activity name of the activity to be started.
     */
    private String parseActivityName(String parameters) {
        String scenario;
        String activityName;
        int tagDelimiter = parameters.indexOf("/t");
        int allocateDelimiter = parameters.indexOf("/a");
        scenario = getScenario(tagDelimiter, allocateDelimiter);
        switch (scenario) {
        case "hasTagAndAllocation":
            assert tagDelimiter != -1 && allocateDelimiter != -1;
            activityName = handleTagAndAllocation(this.parameters, tagDelimiter, allocateDelimiter);
            break;
        case "hasTagOnly":
            assert tagDelimiter != -1 && allocateDelimiter == -1;
            activityName = handleTagOrAllocation(this.parameters, tagDelimiter);
            break;
        case "hasAllocationOnly":
            assert tagDelimiter == -1 && allocateDelimiter != -1;
            activityName = handleTagOrAllocation(this.parameters, allocateDelimiter);
            break;
        case "hasNoTagAndAllocation":
            assert tagDelimiter == -1 && allocateDelimiter == -1;
            activityName = this.parameters.trim();
            break;
        default:
            activityName = "";
            break;
        }
        return activityName;
    }

    /**
     * Method to check for tags and allocated time.
     * @param tagDelimiter index where tag flag is found.
     * @param allocateDelimiter index where allocate flag is found.
     * @return a string with information about whether tags and allocated time are found.
     */
    private String getScenario(int tagDelimiter, int allocateDelimiter) {
        if (tagDelimiter != -1 && allocateDelimiter != -1) {
            this.hasAllocationAndTag = true;
            return "hasTagAndAllocation";
        } else if (tagDelimiter != -1) {
            this.hasTag = true;
            return "hasTagOnly";
        } else if (allocateDelimiter != -1) {
            this.hasAllocation = true;
            return "hasAllocationOnly";
        } else {
            return "hasNoTagAndAllocation";
        }
    }

    /**
     * Method to extract activity name.
     * @param parameters parameters to start command.
     * @param tagDelimiter index where tag flag is found.
     * @param allocateDelimiter index where allocate flag is found.
     * @return activity name
     */
    private String handleTagAndAllocation(String parameters, int tagDelimiter, int allocateDelimiter) {
        String activityName;
        int delimiter = 0;
        if (tagDelimiter < allocateDelimiter) {
            delimiter = tagDelimiter;
        } else if (allocateDelimiter < tagDelimiter) {
            delimiter = allocateDelimiter;
        }
        assert delimiter == tagDelimiter || delimiter == allocateDelimiter;
        activityName = parameters.substring(0, delimiter);
        activityName = activityName.trim();
        if (activityName.isEmpty()) {
            return "";
        } else {
            return activityName;
        }
    }

    /**
     * Method to extract activity name.
     * @param parameters parameters to start command.
     * @param delimiter index where tag flag or allocate flag is found.
     * @return activity name.
     */
    private String handleTagOrAllocation(String parameters, int delimiter) {
        assert delimiter != -1;
        String activityName = parameters.substring(0, delimiter);
        activityName = activityName.trim();
        if (activityName.isEmpty()) {
            return "";
        } else {
            return activityName;
        }
    }

    /**
     * Method to check if the activity exists in activity list and does not exceed 25 characters.
     * @param activityName the string representing activity name.
     * @param activityList a list of tracked activities.
     */
    private void checkActivity(String activityName, ActivityList activityList) {
        assert !activityName.isEmpty();
        int index = activityList.findActivity(activityName);
        if (index != -1) {
            Ui.printDivider("There is already an activity with this name. Would you like to continue it?");
            continueActivity(activityList, scanner, index);
        } else if (activityName.length() > MAX_ACTIVITY_LENGTH) {
            Log.makeInfoLog("Activity name longer than 25 characters.");
            Ui.printDivider("Please input an activity name that is shorter than 25 characters.");
        } else {
            addActivityToList(activityName);
        }
    }

    /**
     * Method to add a activity to the activity list.
     * @param activityName the string representing activity name.
     */
    private void addActivityToList(String activityName) {
        if (hasAllocationAndTag) {
            parseActivityWithBothField(activityName, this.parameters);
        } else if (hasAllocation) {
            addActivityWithAllocation(activityName, this.parameters);
        } else if (hasTag) {
            addActivityWithTag(activityName, this.parameters);
        } else {
            addActivity(activityName);
        }
    }

    /**
     * Parse the tag and allocate information.
     * @param activityName the string representing activity name.
     * @param line a line with information on tags and allocated time.
     */
    private void parseActivityWithBothField(String activityName, String line) {
        int allocateIndex = line.indexOf("/a");
        int tagIndex = line.indexOf("/t");
        assert tagIndex != -1 && allocateIndex != -1;
        String tagInfo = "";
        String durationInfo = "";
        if (tagIndex < allocateIndex) {
            tagInfo = line.substring(tagIndex, allocateIndex);
            durationInfo = line.substring(allocateIndex);
            tagInfo = tagInfo.trim();
            durationInfo = durationInfo.trim();
        } else if (allocateIndex < tagIndex) {
            durationInfo = line.substring(allocateIndex, tagIndex);
            tagInfo = line.substring(tagIndex);
            tagInfo = tagInfo.trim();
            durationInfo = durationInfo.trim();
        }
        durationInfo = durationInfo.substring(2);
        durationInfo = durationInfo.trim();
        tagInfo = tagInfo.substring(2);
        tagInfo = tagInfo.trim();
        addActivityWithBothField(durationInfo, tagInfo, activityName);
    }

    /**
     * Add activity with both tag and allocate time.
     * @param durationInfo information about the duration.
     * @param tagInfo information about the tag.
     * @param activityName the string representing activity name.
     */
    private void addActivityWithBothField(String durationInfo, String tagInfo, String activityName) {
        String[] tagStrings = tagInfo.split(" ");
        try {
            checkBothField(durationInfo, tagInfo, activityName, tagStrings);
        } catch (DateTimeException e) {
            Log.makeInfoLog("Allocated time provided was not valid.");
            Ui.printDivider("Time provided is invalid, please provide time in this format"
                    + " HH:MM:SS.");
        }
    }

    /**
     * Check if allocated time and tags are formatted correctly/not empty.
     * @param durationInfo information about the duration.
     * @param tagInfo information about the tag.
     * @param activityName the string representing activity name.
     * @param tagStrings tokenized information about the tags.
     */
    private void checkBothField(String durationInfo, String tagInfo, String activityName, String[] tagStrings) {
        LocalTime startTime = LocalTime.MIN;
        LocalTime endTime;
        endTime = LocalTime.parse(durationInfo);
        Duration allocatedTime = Duration.between(startTime, endTime);
        ArrayList<String> tags = getTags(tagStrings);
        if (tagInfo.isEmpty()) {
            Log.makeInfoLog("No tags found with tag flag.");
            Ui.printDivider("Please provide a valid tag");
        } else if (allocatedTime == Duration.parse("PT0S")) {
            Log.makeInfoLog("Allocated time is zero.");
            Ui.printDivider("Please provide a non zero allocated time.");
        } else if (tags.size() > 2) {
            Log.makeInfoLog("Activity has more than 2 tags");
            Ui.printDivider("There cannot be more than 2 tags.");
        } else {
            Parser.tags.addAll(tags);
            Parser.allocatedTime = allocatedTime;
            addActivity(activityName);
        }
    }

    /**
     * Add activity with allocated time only.
     * @param activityName the string representing activity name.
     * @param line a line with information about allocated time.
     */
    private void addActivityWithAllocation(String activityName, String line) {
        int index = line.indexOf("/a");
        String durationInfo = line.substring(index + 2);
        durationInfo = durationInfo.trim();
        LocalTime startTime = LocalTime.MIN;
        try {
            checkTime(activityName, durationInfo, startTime);
        } catch (DateTimeException e) {
            Log.makeInfoLog("Allocated time provided was not valid.");
            Ui.printDivider("Time provided is invalid, please provide time in this format"
                    + " HH:MM:SS.");
        }
    }

    private void checkTime(String activityName, String durationInfo, LocalTime startTime) {
        LocalTime endTime;
        endTime = LocalTime.parse(durationInfo);
        Duration allocatedTime = Duration.between(startTime, endTime);
        if (allocatedTime == Duration.parse("PT0S")) {
            Log.makeInfoLog("Allocated time is zero.");
            Ui.printDivider("Please provide a non zero allocated time.");
        } else {
            Parser.allocatedTime = allocatedTime;
            addActivity(activityName);
        }
    }

    /**
     * Add activity with tags only.
     * @param activityName the string representing activity name.
     * @param line a line with information about tags.
     */
    private void addActivityWithTag(String activityName, String line) {
        int index = line.indexOf("/t");
        String tagInfo = line.substring(index + 2);
        tagInfo = tagInfo.trim();
        String[] tagStrings = tagInfo.split(" ");
        ArrayList<String> tags = getTags(tagStrings);
        if (tagInfo.isEmpty()) {
            Log.makeInfoLog("No tags found with tag flag.");
            Ui.printDivider("Please provide a valid tag.");
        } else if (tags.size() > 2) {
            Log.makeInfoLog("Activity has more than 2 tags");
            Ui.printDivider("There cannot be more than 2 tags.");
        } else {
            Parser.tags.addAll(tags);
            addActivity(activityName);
        }
    }

    /**
     * Method to get valid tags from an array of tags.
     * @param tagStrings array of tags
     * @return an array list consisting of valid tags
     */
    private ArrayList<String> getTags(String[] tagStrings) {
        ArrayList<String> tags = new ArrayList<>();
        for (String s : tagStrings) {
            String tag = s.trim();
            if (!tag.isEmpty()) {
                tags.add(tag);
            }
        }
        return tags;
    }


    /**
     * Add activity to activity list.
     * @param activityName the string representing activity name.
     */
    private void addActivity(String activityName) {
        Parser.activityName = activityName;
        Parser.startTime = LocalDateTime.now();
        Log.makeFineLog(activityName + " was started.");
        Ui.printDivider(activityName + " was started.");
    }


    /**
     * Received user input on whether or not to continue the activity.
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
            Ui.printDivider(Parser.activityName + " was continued.");
            Log.makeFineLog(Parser.activityName + " was continued.");
        } else if (userInput.equalsIgnoreCase("no") || userInput.equalsIgnoreCase("n")) {
            Parser.activityName = null;
            Ui.printDivider("Okay then, what else can I do for you?");
        } else {
            Ui.printDivider("Incorrect format entered, please only enter yes or no.");
        }
    }
}
