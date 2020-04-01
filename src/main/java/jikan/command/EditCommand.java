package jikan.command;

import jikan.exception.ExistingTagGoalException;
import jikan.exception.InvalidTimeFrameException;
import jikan.exception.NegativeDurationException;
import jikan.log.Log;
import jikan.activity.ActivityList;
import jikan.exception.EmptyNameException;
import jikan.exception.InvalidEditFormatException;
import jikan.exception.NoSuchActivityException;
import jikan.parser.Parser;
import jikan.ui.Ui;

import java.io.IOException;
import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static jikan.Jikan.tagFile;
import static jikan.command.GoalCommand.parseDuration;

/**
 * Represents a command to edit an activity in the activity list.
 */
public class EditCommand extends Command {

    /**
     * Constructor to create a new edit command.
     */
    public EditCommand(String parameters) {
        super(parameters);
    }

    @Override
    public void executeCommand(ActivityList activityList) {
        try {
            int delimiter = parameters.indexOf("/en");
            int tagDelim = parameters.indexOf("/et");
            int allocDelim = parameters.indexOf("/ea");

            String newName = "";
            String[] tmpTags;
            String tmpAlloc = "";
            Duration newAllocTime = null;
            Set<String> newTags = new HashSet<String>();

            //edit name
            if (delimiter != -1)  {
                Parser.activityName = parameters.substring(0, delimiter).strip();
                newName = parameters.substring(delimiter + 4).strip();
                if (newName.isEmpty()) {
                    Ui.printDivider("New activity name cannot be empty!");
                    throw new EmptyNameException();
                }

                //edit tag
            } else if (tagDelim != -1) {
                Parser.activityName = parameters.substring(0, tagDelim).strip();
                tmpTags = (parameters.substring(tagDelim + 4).split(" "));
                newTags.addAll(Arrays.asList(tmpTags));

            //edit allocated time
            } else if (allocDelim != -1) {
                Parser.activityName = parameters.substring(0, allocDelim).strip();
                tmpAlloc = parameters.substring(allocDelim + 4).strip();
                if (tmpAlloc.isEmpty()) {
                    throw new InvalidTimeFrameException();
                }
                newAllocTime = parseDuration(tmpAlloc);

            //invalid format
            } else {
                throw new InvalidEditFormatException();
            }

            if (Parser.activityName.isEmpty()) {
                Ui.printDivider("Activity name cannot be empty!");
                throw new EmptyNameException();
            }

            int index = activityList.findActivity(Parser.activityName);

            if (index != -1) {
                if (!(newName.isEmpty() && newTags.isEmpty() && tmpAlloc.isEmpty())) {
                    if (!newName.isEmpty()) {
                        activityList.updateName(index, newName);
                    } else if (!newTags.isEmpty()) {
                        if (existsInTag(activityList.get(index).getTags())) {
                            throw new ExistingTagGoalException();
                        } else {
                            activityList.updateTags(index, newTags);
                        }
                    } else {
                        assert newAllocTime != null;
                        if (newAllocTime.isNegative()) {
                            throw new NegativeDurationException();
                        } else {
                            activityList.updateAlloc(index, newAllocTime);
                        }
                    }
                } else {
                    // no new details provided
                    throw new InvalidEditFormatException();
                }
                Ui.printDivider("Activity named " + Parser.activityName + " has been updated!");

            } else {
                // activity is not found
                throw new NoSuchActivityException();
            }
        } catch (NoSuchActivityException e) {
            Ui.printDivider("No activity with this name exists!");
            Log.makeInfoLog("Edit command failed as there was no such activity saved.");
        } catch (EmptyNameException e) {
            Log.makeInfoLog("Edit command failed as there was no activity name provided.");
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException | InvalidEditFormatException e) {
            Ui.printDivider("Incorrect edit command format entered!");
            Log.makeInfoLog("Edit command failed as an incorrect format was provided.");
        } catch (ExistingTagGoalException e) {
            Ui.printDivider("Tag cannot be edited as there is an existing tag goal!");
            Log.makeInfoLog("Edit command failed as there was an existing tag goal tied to the tag.");
        } catch (IOException e) {
            Ui.printDivider("Error in loading the tag file!");
            Log.makeInfoLog("Edit command failed as there was an error in loading the tag file.");
        } catch (NegativeDurationException e) {
            Ui.printDivider("Please enter a positive target time!");
            Log.makeInfoLog("Edit command failed as a negative target time was provided.");
        } catch (InvalidTimeFrameException e) {
            Ui.printDivider("New target time cannot be empty!");
            Log.makeInfoLog("Edit command failed as an empty target time was provided");
        } catch (NumberFormatException e) {
            Ui.printDivider("Please enter integers in the format HH:MM:SS");
            Log.makeInfoLog("Edit command failed as an incorrect format for the target time was provided.");
        }
    }

    /**
     * Check if the tags have associated tag goals.
     * @param oldTags the tags to be edited.
     * @return true or false.
     * @throws IOException if there is an error loading the file.
     */
    public static boolean existsInTag(Set<String> oldTags) throws IOException {
        for (String i : oldTags) {
            if (GoalCommand.checkIfExists(i) != -1) {
                return true;
            }
        }
        return false;
    }

}
