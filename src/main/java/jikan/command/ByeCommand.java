package jikan.command;

import jikan.exception.NameTooLongException;
import jikan.log.Log;
import jikan.activity.ActivityList;
import jikan.exception.InvalidTimeFrameException;
import jikan.parser.Parser;
import jikan.ui.Ui;

import java.util.Scanner;



/**
 * Terminates the program.
 */
public class ByeCommand extends Command {

    /**
     * Constructor to create a new exit command.
     */
    public ByeCommand(String parameters) {
        super(parameters);
    }

    /**
     * Exits the app. If there is a ongoing activity, asks the user if the activity
     * should be saved.
     */
    @Override
    public void executeCommand(ActivityList activityList) {

        try {
            // checks if there was an ongoing activity
            if (Parser.startTime != null) {
                String line = Parser.activityName + " is still running! If you exit now it will be aborted.\n"
                        + "Would you like to end this activity to save it?";
                Ui.printDivider(line);
                Scanner scanner = new Scanner(System.in);
                String userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("yes") || userInput.equalsIgnoreCase("y")) {
                    activityList.saveActivity();
                }
            }
            Ui.exitFromApp();
        } catch (InvalidTimeFrameException e) {
            Log.makeInfoLog("End date must be before start date");
            Ui.printDivider("Error: end date must be before start date.");
        } catch (NameTooLongException e) {
            Log.makeInfoLog("Activity name longer than 25 characters");
            Ui.printDivider("Error: activity name is longer than 25 characters.");
        }
    }

    public static boolean isExit(Command command) {
        return command instanceof ByeCommand; // instanceof returns false if it is null
    }

}
