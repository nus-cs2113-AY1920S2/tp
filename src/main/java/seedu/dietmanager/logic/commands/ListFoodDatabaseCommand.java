package seedu.dietmanager.logic.commands;

import seedu.dietmanager.logic.Result;
import seedu.dietmanager.model.FoodNutritionRecord;
import seedu.dietmanager.model.Profile;
import seedu.dietmanager.ui.UI;

public class ListFoodDatabaseCommand extends Command {
    private static final int ARGUMENTS_REQUIRED = 0;
    private FoodNutritionRecord foodNutritionRecord = FoodNutritionRecord.getInstance();

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */
    public ListFoodDatabaseCommand(String command) {
        super(command);
    }

    @Override
    public Result execute(Profile profile, UI ui) {
        Result result = getResult(profile);
        return result;
    }

    @Override
    public Result getResult(Profile profile) {
        this.resultString = foodNutritionRecord.showFoodDatabase();
        return new Result(this.resultString);
    }
}
