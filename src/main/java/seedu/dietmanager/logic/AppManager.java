package seedu.dietmanager.logic;

import seedu.dietmanager.commons.core.LogsCentre;
import seedu.dietmanager.commons.exceptions.InvalidCommandException;
import seedu.dietmanager.commons.exceptions.InvalidFormatException;
import seedu.dietmanager.logic.commands.Command;
import seedu.dietmanager.logic.parser.CommandParser;
import seedu.dietmanager.model.FoodNutritionRecord;
import seedu.dietmanager.model.Profile;
import seedu.dietmanager.storage.Storage;
import seedu.dietmanager.ui.UI;

import java.util.Optional;

/**
 * AppManager is the public class responsible for running and handling the logic flow of the application.
 */

public class AppManager {

    /**
     * LogsCentre handles the recording of logs for the application.
     */

    private static LogsCentre logsCentre;

    /**
     * FoodNutritionRecord handles the storing of food nutritional information.
     */

    private static FoodNutritionRecord foodNutritionRecord;

    /**
     * Profile stores all user-related information.
     */

    private static Profile profile;

    /**
     * UI handles all user-interface-based functions.
     */

    private static UI ui;

    /**
     * Storage handles all storage-based functions.
     */

    private static Storage storage;

    /**
     * Launches the application by initialising the required objects and running the application.
     */

    public static void launch() {
        initialiseApplication();
        runApplication();
    }

    /**
     * Runs the application by handling the logic flow and user inputs.
     */

    private static void runApplication() {

        logsCentre.logExecuteProgramme();
        ui.displayWelcomeMessage();
        ui.displayHelpMenu();

        while (!ui.isExitStatus()) {
            try {
                String userInput = ui.readInput();
                Optional<Command> command = CommandParser.parseInput(userInput);
                if (command.isPresent()) {
                    if (isCommandValid(command.get())) {
                        Result result = command.get().execute(profile, ui);
                        ui.showMessage(result.showResult());
                    } else {
                        ui.displayCreateProfileMessage();
                    }
                } else {
                    ui.displayInvalidCommandMessage();
                }
            } catch (InvalidFormatException | NumberFormatException e) {
                ui.displayInvalidFormatMessage();
            } catch (InvalidCommandException e) {
                ui.displayInvalidCommandMessage();
            } catch (IndexOutOfBoundsException e) {
                ui.displayIndexOutOfBoundMessage();
            }
            if (profile.isProfileExist()) {
                storage.writeProfileFile();
                storage.writeRecipeFile();
                storage.writeFoodRecordFile();
            }
            storage.writeFoodNutritionRecordFile();
        }

        ui.displayExitMessage();
        logsCentre.logExitProgramme();
    }

    /**
     * Initialises the application by creating the relevant objects and checking application status.
     */

    private static void initialiseApplication() {

        ui = new UI();
        logsCentre = new LogsCentre();
        profile = new Profile();
        foodNutritionRecord = FoodNutritionRecord.getInstance();
        storage = new Storage(ui, logsCentre, profile, foodNutritionRecord);

        testAssertions();
    }

    /**
     * Check validity of commands depending on whether profile has been created.
     *
     * @return validity of Command being executed.
     */

    private static boolean isCommandValid(Command command) {
        if (profile.isProfileExist() || command.getCommand().equals("set-profile")
                || command.getCommand().equals("help") || command.getCommand().equals("exit")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Assertion testing for the program before running.
     */

    private static void testAssertions() {
        assert (!ui.isExitStatus());
    }

}
