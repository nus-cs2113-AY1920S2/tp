package jikan.command;

import jikan.activity.ActivityList;
import jikan.exception.EmptyNameException;
import jikan.exception.NoSuchActivityException;
import jikan.ui.Ui;

/**
 * Represents a command to delete an activity from the activity list.
 */
public class DeleteCommand extends Command {

    /**
     * Constructor to create a new delete command.
     */
    public DeleteCommand(String parameters) {
        super(parameters.trim());
    }

    @Override
    public void executeCommand(ActivityList activityList) {
        try {
            int index = activityList.findActivity(parameters);
            if (index != -1) {
                // activity was found
                Ui.printDivider("You have deleted " + parameters + ".");
                activityList.delete(index);
            } else {
                if (parameters.isEmpty()) {
                    throw new EmptyNameException();
                } else {
                    throw new NoSuchActivityException();
                }
            }
        } catch (NoSuchActivityException e) {
            Ui.printDivider("No activity with this name exists.");
        } catch (EmptyNameException e) {
            Ui.printDivider("Activity name cannot be empty.");
        }
    }
}
