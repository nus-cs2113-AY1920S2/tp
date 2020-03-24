package jikan.command;

import jikan.Log;
import jikan.activity.ActivityList;
import jikan.exception.EmptyNameException;
import jikan.exception.NoSuchActivityException;
import jikan.parser.Parser;
import jikan.ui.Ui;

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
            // Parser.parseEdit(activityList);
            int delimiter = parameters.indexOf("/e");
            Parser.activityName = parameters.substring(0, delimiter).strip();
            if (Parser.activityName.isEmpty()) {
                throw new EmptyNameException();
            }
            int index = activityList.findActivity(Parser.activityName);
            String newName = parameters.substring(delimiter + 3);
            if (index != -1) {
                activityList.updateName(index,newName);
                Ui.printDivider("Activity named " +  Parser.activityName + " has been updated to " + newName);
            } else {
                // activity is not found
                throw new NoSuchActivityException();
            }
        } catch (NoSuchActivityException e) {
            Ui.printDivider("No activity with this name exists!");
            Log.makeInfoLog("Edit command failed as there was no such activity saved.");
        } catch (ArrayIndexOutOfBoundsException | EmptyNameException e) {
            Ui.printDivider("Activity name cannot be empty!");
            Log.makeInfoLog("Edit command failed as there was no existing activity name provided.");
        } catch (StringIndexOutOfBoundsException e) {
            Ui.printDivider("New activity name not provided!");
            Log.makeInfoLog("Edit command failed as there was no new activity name provided.");
        }
    }


}
