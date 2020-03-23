package jikan.command;

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

    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructor to create a new exit command.
     */
    public ByeCommand() {
    }

    @Override
    public void executeCommand(ActivityList activityList) {

        try {
            Parser.parseBye(activityList, scanner);
        } catch (InvalidTimeFrameException e) {
            Log.makeInfoLog("End date must be before start date");
            Ui.printDivider("End date must be before start date.");
        }
    }

    public static boolean isExit(Command command) {
        return command instanceof ByeCommand; // instanceof returns false if it is null
    }

}
