package seedu.dietmanager.logic.commands;

import seedu.dietmanager.commons.core.FoodNutritionInfo;
import seedu.dietmanager.model.Profile;
import seedu.dietmanager.ui.UI;

public class ListFoodDatabaseCommand extends Command {
    private static final int ARGUMENTS_REQUIRED = 0;
    private FoodNutritionInfo foodNutritionInfo = FoodNutritionInfo.getInstance();

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */
    public ListFoodDatabaseCommand(String command) {
        super(command);
    }

    @Override
    public void execute(Profile profile, UI ui) {
        saveResult(profile);
    }

    @Override
    public void saveResult(Profile profile) {
        this.result = foodNutritionInfo.showFoodDatabase();
    }
}
