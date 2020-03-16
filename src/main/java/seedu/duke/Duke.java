package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.exceptions.InvalidFormatException;
import seedu.duke.parser.Parser;
import seedu.duke.ui.UI;

public class Duke {

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
