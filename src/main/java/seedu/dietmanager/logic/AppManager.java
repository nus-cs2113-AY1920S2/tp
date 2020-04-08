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

public class AppManager {

    private static LogsCentre logsCentre;
    private static FoodNutritionRecord foodNutritionRecord;
    private static Profile profile;
    private static UI ui;
    private static Storage storage;

    public static void launch() {
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
                Optional<Command> command = CommandParser.parseInput(userInput);
                if (command.isPresent()) {
                    Result result = command.get().execute(profile, ui);
                    ui.showMessage(result.showResult());
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
            }
            storage.writeFoodNutritionRecordFile();
        }

        ui.displayExitMessage();
        logsCentre.logExitProgramme();
    }

    private static void initialiseApplication() {

        ui = new UI();
        logsCentre = new LogsCentre();
        profile = new Profile();
        foodNutritionRecord = FoodNutritionRecord.getInstance();
        storage = new Storage(ui, logsCentre, profile, foodNutritionRecord);

        testAssertions();
    }

    private static void testAssertions() {
        assert !ui.isExitStatus();
    }

}
