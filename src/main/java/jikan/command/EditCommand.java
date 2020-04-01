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

            // edit name
            if (delimiter != -1 && tagDelim == -1 && allocDelim == -1)  {
                Parser.activityName = parameters.substring(0, delimiter).strip();

                //edit tag
            } else if (delimiter == -1 && tagDelim != -1 && allocDelim == -1) {
                Parser.activityName = parameters.substring(0, tagDelim).strip();

                //edit allocatedTime
            } else {
                Parser.activityName = parameters.substring(0, allocDelim).strip();
            }

            if (Parser.activityName.isEmpty()) {
                throw new EmptyNameException();
            }

            int index = activityList.findActivity(Parser.activityName);
            String newName = "";
            String[] tmpTags;
            String tmpAlloc = "";
            Duration newAllocTime = null;
            Set<String> newTags = new HashSet<String>();

            //edit name
            if (delimiter != -1 && tagDelim == -1 && allocDelim == -1) {
                newName = parameters.substring(delimiter + 4);
                Parser.activityName = parameters.substring(0, delimiter).strip();

                //edit tag
            } else if (delimiter == -1 && tagDelim != -1 && allocDelim == -1) {
                tmpTags = (parameters.substring(tagDelim + 4).split(" "));
                for (String t : tmpTags) {
                    newTags.add(t);
                }

                //edit allocatedTime
            } else {
                tmpAlloc = parameters.substring(allocDelim + 4);
                try {
                    newAllocTime = parseDuration(tmpAlloc);
                } catch (InvalidTimeFrameException e) {
                    Ui.printDivider("Invalid time frame entered!");
                }
            }

            if (index != -1) {
                if (!(newName.isEmpty() && newTags.isEmpty() && tmpAlloc.isEmpty())) {
                    if (!newName.isEmpty()) {
                        activityList.updateName(index, newName);
                    }
                    if (!newTags.isEmpty()) {
                        if (existsInTag(activityList.get(index).getTags())) {
                            throw new ExistingTagGoalException();
                        } else {
                            activityList.updateTags(index, newTags);
                        }

                    }
                    if (!tmpAlloc.isEmpty()) {
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
            Ui.printDivider("Activity name cannot be empty!");
            Log.makeInfoLog("Edit command failed as there was no activity name provided.");
        } catch (StringIndexOutOfBoundsException | InvalidEditFormatException e) {
            Ui.printDivider("New details not provided!");
            Log.makeInfoLog("Edit command failed as there was no updated activity detail provided.");
        } catch (ExistingTagGoalException e) {
            Ui.printDivider("Tag cannot be edited as there is an existing tag goal!");
            Log.makeInfoLog("Edit command failed as there was an existing tag goal tied to the tag.");
        } catch (IOException e) {
            Ui.printDivider("Error in loading the tag file!");
            Log.makeInfoLog("Edit command failed as there was an error in loading the tag file.");
        } catch (NegativeDurationException e) {
            Ui.printDivider("Please enter a positive target time!");
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
