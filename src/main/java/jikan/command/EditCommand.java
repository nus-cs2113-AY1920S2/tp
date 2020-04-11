package jikan.command;

import jikan.activity.ActivityList;
import jikan.exception.*;
import jikan.log.Log;
import jikan.parser.Parser;
import jikan.ui.Ui;

import java.time.Duration;

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
            Log.makeInfoLog("Activity could not be edited as there is an ongoing activity");
            if (Parser.startTime != null) {
                throw new ActivityIsRunningException();
            }
            int delimiter = parameters.indexOf("/en");
            int allocDelim = parameters.indexOf("/ea");

            String newName = "";
            String tmpAlloc = "";
            Duration newAllocTime = null;

            //edit name
            if (delimiter != -1)  {
                Parser.activityName = parameters.substring(0, delimiter).strip();
                newName = parameters.substring(delimiter + 4).strip();
                if (newName.isEmpty()) {
                    Ui.printDivider("New activity name cannot be empty.");
                    throw new EmptyNameException();
                }
                if (newName.length() > 25) {
                    throw new NameTooLongException();
                }
                // existing activity of the same name is found
                if (activityList.findActivity(newName) != -1) {
                    throw new ExistingNameException();
                }
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
                Ui.printDivider("Activity name cannot be empty.");
                throw new EmptyNameException();
            }

            int index = activityList.findActivity(Parser.activityName);

            if (index != -1) {
                if (!(newName.isEmpty() && tmpAlloc.isEmpty())) {
                    if (!newName.isEmpty()) {
                        activityList.updateName(index, newName);
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
                Ui.printDivider("Activity named " + Parser.activityName + " has been updated.");

            } else {
                // activity is not found
                throw new NoSuchActivityException();
            }
        } catch (NoSuchActivityException e) {
            Ui.printDivider("No activity with this name exists.");
            Log.makeInfoLog("Edit command failed as there was no such activity saved.");
        } catch (EmptyNameException e) {
            Log.makeInfoLog("Edit command failed as there was no activity name provided.");
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException | InvalidEditFormatException e) {
            Ui.printDivider("Incorrect edit command format entered.");
            Log.makeInfoLog("Edit command failed as an incorrect format was provided.");
        } catch (NegativeDurationException e) {
            Ui.printDivider("Please enter a positive target time.");
            Log.makeInfoLog("Edit command failed as a negative target time was provided.");
        } catch (InvalidTimeFrameException e) {
            Ui.printDivider("New target time cannot be empty.");
            Log.makeInfoLog("Edit command failed as an empty target time was provided");
        } catch (NumberFormatException e) {
            Ui.printDivider("Please enter integers in the format HH:MM:SS.");
            Log.makeInfoLog("Edit command failed as an incorrect format for the target time was provided.");
        } catch (NameTooLongException e) {
            Ui.printDivider("Activity name must be shorter than 25 characters.");
        } catch (ActivityIsRunningException e) {
            Ui.printDivider("Cannot edit an activity if there is a current activity running.");
        } catch (ExistingNameException e) {
            Ui.printDivider("There is already an activity with that name. ");
        }
    }

}
