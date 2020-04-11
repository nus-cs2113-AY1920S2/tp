package seedu.dietmanager.logic.commands;

import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.commons.exceptions.InvalidFormatException;
import seedu.dietmanager.logic.Result;
import seedu.dietmanager.logic.parser.DescriptionParser;
import seedu.dietmanager.model.FoodNutritionRecord;
import seedu.dietmanager.model.Profile;
import seedu.dietmanager.ui.UI;

public class DeleteFoodCommand extends Command {
    private static final int ARGUMENTS_REQUIRED = 1;
    private String foodName;
    private boolean noDescription;
    private boolean success;

    /**
     * Constructs the Command object.
     *
     * @param command     the command prompt entered by the user.
     * @param description the description of the command.
     */

    public DeleteFoodCommand(String command, String description) throws InvalidFormatException {
        super(command);
        this.noDescription = false;

        try {
            String[] descriptionArray = DescriptionParser.parseDescription(description, ARGUMENTS_REQUIRED);
            foodName = descriptionArray[0].toLowerCase();
        } catch (NullPointerException e) {
            this.noDescription = true;
        }
    }

    @Override
    public Result execute(Profile profile, UI ui) {
        if (!this.noDescription) {
            FoodNutritionRecord foodInfo = FoodNutritionRecord.getInstance();
            this.success = foodInfo.deleteFoodNutritionRecord(foodName);
        }
        Result result = getResult(profile);
        return result;
    }

    @Override
    public Result getResult(Profile profile) {
        if (this.noDescription) {
            this.resultString = MessageBank.NO_DESCRIPTION_MESSAGE;
        } else if (!this.success) {
            this.resultString = MessageBank.FOOD_TO_DELETE_NOT_EXIST_MESSAGE;
        } else {
            this.resultString = MessageBank.FOOD_DELETED_MESSAGE + foodName;
        }
        return new Result(this.resultString);
    }
}

