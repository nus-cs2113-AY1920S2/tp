package jikan.command;

import jikan.exception.InvalidTimeFrameException;
import jikan.log.Log;
import jikan.activity.ActivityList;
import jikan.exception.EmptyNameException;
import jikan.exception.InvalidEditFormatException;
import jikan.exception.NoSuchActivityException;
import jikan.parser.Parser;
import jikan.ui.Ui;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
                        activityList.updateTags(index, newTags);
                    }
                    if (!tmpAlloc.isEmpty()) {
                        activityList.updateAlloc(index, newAllocTime);
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
        }
    }

}
