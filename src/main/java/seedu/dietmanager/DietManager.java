package seedu.dietmanager;

import seedu.dietmanager.commands.Command;
import seedu.dietmanager.exceptions.InvalidCommandException;
import seedu.dietmanager.exceptions.InvalidFormatException;
import seedu.dietmanager.parser.Parser;
import seedu.dietmanager.ui.UI;

public class DietManager {

    /**
     * Begins the application and creates the relevant objects required for the application to function.
     */

    public static void main(String[] args) {

        InfoLogger infoLogger = new InfoLogger();
        FoodNutritionInfo foodNutritionInfo = new FoodNutritionInfo();
        Profile profile = new Profile();
        UI ui = new UI();

        //Conduct checks using assert
        assert !ui.isExitStatus();
        assert !profile.isProfileExist();

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
}
