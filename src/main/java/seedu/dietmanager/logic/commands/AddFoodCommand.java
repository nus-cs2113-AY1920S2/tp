package seedu.dietmanager.logic.commands;

import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.commons.exceptions.InvalidFormatException;
import seedu.dietmanager.logic.Result;
import seedu.dietmanager.logic.parser.DescriptionParser;
import seedu.dietmanager.model.FoodNutritionRecord;
import seedu.dietmanager.model.Profile;
import seedu.dietmanager.ui.UI;

public class AddFoodCommand extends Command {
    private static final int ARGUMENTS_REQUIRED = 1;
    private String foodName;
    private Double calories;
    private boolean noDescription;
    private boolean isInvalidCaloriesInfo;
    private boolean success;

    /**
     * Constructs the Command object.
     *
     * @param command     the command prompt entered by the user.
     * @param description the description of the command.
     */

    public AddFoodCommand(String command, String description) throws InvalidFormatException {
        super(command);
        this.noDescription = false;

        try {
            String[] descriptionArray = DescriptionParser.parseDescription(description, ARGUMENTS_REQUIRED);
            String[] foodDescription = descriptionArray[0].split("--");
            this.foodName = foodDescription[0].trim().toLowerCase();
            this.calories = Double.parseDouble(foodDescription[1].trim());
            if (this.calories <= 0) {
                throw new NumberFormatException();
            }
            this.isInvalidCaloriesInfo = false;
        } catch (NullPointerException e) {
            this.noDescription = true;
        } catch (NumberFormatException e) {
            this.isInvalidCaloriesInfo = true;
        }
    }

    @Override
    public Result execute(Profile profile, UI ui) {
        if (!this.noDescription && !this.isInvalidCaloriesInfo) {
            FoodNutritionRecord foodInfo = FoodNutritionRecord.getInstance();
            this.success = foodInfo.addFoodNutritionRecord(foodName, calories);
        }
        Result result = getResult(profile);
        return result;
    }

    @Override
    public Result getResult(Profile profile) {
        if (this.noDescription) {
            this.resultString = MessageBank.NO_DESCRIPTION_MESSAGE;
        } else if (this.isInvalidCaloriesInfo) {
            this.resultString = MessageBank.INCORRECT_CALORIES_INFO_MESSAGE;
        } else if (!this.success) {
            this.resultString = MessageBank.ADDED_FOOD_ALREADY_EXIST_MESSAGE;
        } else {
            this.resultString = MessageBank.NEW_FOOD_ADDED_MESSAGE
                    + "Food: " + foodName + ", Calories: " + calories;
        }
        return new Result(this.resultString);
    }
}
