package seedu.dietmanager;

import seedu.dietmanager.commons.core.LogsCentre;
import seedu.dietmanager.commons.exceptions.InvalidCommandException;
import seedu.dietmanager.commons.exceptions.InvalidFormatException;
import seedu.dietmanager.logic.Result;
import seedu.dietmanager.logic.commands.Command;
import seedu.dietmanager.logic.parser.CommandParser;
import seedu.dietmanager.model.FoodNutritionRecord;
import seedu.dietmanager.model.Profile;
import seedu.dietmanager.storage.Storage;
import seedu.dietmanager.ui.UI;

public class DietManager {

    private static LogsCentre logsCentre;
    private static FoodNutritionRecord foodNutritionRecord;
    private static Profile profile;
    private static UI ui;
    private static Storage storage;

    /**
     * Begins the application and creates the relevant objects required for the application to function.
     */

    public static void main(String[] args) {
        initialiseApplication();
        runApplication();
    }

    private static void runApplication() {

        logsCentre.logExecuteProgramme();
        ui.displayWelcomeMessage();
        ui.displayHelpMenu();

        while (!ui.isExitStatus()) {
            try {
                String userInput = ui.readInput();
                Command command = CommandParser.parseInput(userInput);
                Result result = command.execute(profile, ui);
                ui.showMessage(result.toString());
            } catch (InvalidFormatException | NumberFormatException e) {
                ui.displayInvalidFormatMessage();
            } catch (InvalidCommandException e) {
                ui.displayInvalidCommandMessage();
            } catch (IndexOutOfBoundsException e) {
                ui.displayIndexOutOfBoundMessage();
            }
        }

        ui.displayExitMessage();
        logsCentre.logExitProgramme();
    }

    private static void initialiseApplication() {
        logsCentre = new LogsCentre();
        foodNutritionRecord = FoodNutritionRecord.getInstance();
        profile = new Profile();
        ui = new UI();
        storage = new Storage(ui, logsCentre);

        testAssertions();
    }

    private static void testAssertions() {
        assert !ui.isExitStatus();
        assert !profile.isProfileExist();
    }

}
