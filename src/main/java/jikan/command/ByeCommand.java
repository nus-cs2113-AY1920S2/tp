package jikan.command;

import jikan.Jikan;
import jikan.Log;
import jikan.activity.ActivityList;
import jikan.exception.InvalidTimeFrameException;
import jikan.parser.Parser;
import jikan.ui.Ui;

import java.util.Scanner;



/**
 * Terminates the program.
 */
public class ByeCommand extends Command {

    //private Scanner scanner = new Scanner(System.in);

    /**
     * Constructor to create a new exit command.
     */
    public ByeCommand(String parameters) {
        super(parameters);
    }

    /**
     * Checks for ongoing activities and ask if user wants to save them
     * before exiting the app.
     */
    @Override
    public void executeCommand(ActivityList activityList) {

        try {
            //Parser.parseBye(activityList, Jikan.in);
            if (Parser.startTime != null) {
                String line = Parser.activityName + " is still running! If you exit now it will be aborted.\n"
                        + "Would you like to end this activity to save it?";
                Ui.printDivider(line);
                Scanner scanner = new Scanner(System.in);
                String userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("yes") || userInput.equalsIgnoreCase("y")) {
                    activityList.saveActivity(activityList);
                }
            }
            Ui.exitFromApp();
        } catch (InvalidTimeFrameException e) {
            Log.makeInfoLog("End date must be before start date");
            Ui.printDivider("End date must be before start date.");
        }
    }

    public static boolean isExit(Command command) {
        return command instanceof ByeCommand; // instanceof returns false if it is null
    }

}
