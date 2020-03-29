package jikan.command;

import jikan.Log;
import jikan.activity.ActivityList;
import jikan.exception.InvalidCleanCommandException;
import jikan.parser.Parser;
import jikan.storage.Storage;
import jikan.storage.StorageCleaner;
import jikan.ui.Ui;

import java.io.IOException;

import static jikan.parser.Parser.tokenizedInputs;

/**
 * Represents a command to clear previously saved log files.
 */
public class CleanCommand extends Command {

    StorageCleaner cleaner;

    /**
     * Constructor to create a new clean command.
     */
    public CleanCommand(String parameters, StorageCleaner cleaner) {
        super(parameters);

        this.cleaner = cleaner;
    }

    /**
     * Method to enable or disable cleaning of log files.
     */
    @Override
    public void executeCommand(ActivityList activityList) {
        try {
            String line;
            if (parameters.equals("on")) {
                cleaner.setStatus(true);
                assert cleaner.toClean;
                line = "Auto cleaning enabled";
                Log.makeInfoLog("User has turned on automated cleaning");
            } else if (parameters.equals("off")) {
                cleaner.setStatus(false);
                assert !cleaner.toClean;
                line = "Auto cleaning disabled";
                Log.makeInfoLog("User has turned off automated cleaning");
            } else {
                if (parameters.isEmpty()) {
                    throw new IOException();
                } else {
                    throw new InvalidCleanCommandException();
                }
            }
            Ui.printDivider(line);
            // Parser.parseClean(tokenizedInputs[1]);
        } catch (IOException e) {
            Ui.printDivider("No keyword was given.");
        } catch (InvalidCleanCommandException e) {
            Ui.printDivider("Invalid clean command!");
        }
    }

}
