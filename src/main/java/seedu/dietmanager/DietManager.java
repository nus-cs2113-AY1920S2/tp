package seedu.dietmanager;

import seedu.dietmanager.commands.Command;
import seedu.dietmanager.exceptions.InvalidCommandException;
import seedu.dietmanager.exceptions.InvalidFormatException;
import seedu.dietmanager.parser.Parser;
import seedu.dietmanager.ui.UI;

public class DietManager {

    private static InfoLogger infoLogger;
    private static FoodNutritionInfo foodNutritionInfo;
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

        infoLogger.logExecuteProgramme();
        ui.displayWelcomeMessage();

        while (!ui.isExitStatus()) {
            try {
                String userInput = ui.readInput();
                Command command = Parser.parseInput(userInput);
                command.execute(profile, ui);
            } catch (InvalidFormatException | NumberFormatException e) {
                ui.displayInvalidFormatMessage();
            } catch (InvalidCommandException e) {
                ui.displayInvalidCommandMessage();
            }
        }
        infoLogger.logExitProgramme();
    }

    private static void initialiseApplication() {
        infoLogger = new InfoLogger();
        foodNutritionInfo = new FoodNutritionInfo();
        profile = new Profile();
        ui = new UI();
        storage = new Storage(ui, infoLogger);

        testAssertions();
    }

    private static void testAssertions() {
        assert !ui.isExitStatus();
        assert !profile.isProfileExist();
    }

}
