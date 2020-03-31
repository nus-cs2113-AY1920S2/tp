package seedu.dietmanager.commands;

import seedu.dietmanager.FoodNutritionInfo;
import seedu.dietmanager.Profile;
import seedu.dietmanager.exceptions.InvalidFormatException;
import seedu.dietmanager.parser.Parser;
import seedu.dietmanager.ui.MessageBank;
import seedu.dietmanager.ui.UI;

public class AddFoodCommand extends Command {
    private static final int ARGUMENTS_REQUIRED = 2;
    private String foodName;
    private Double calories;
    private boolean noDescription;
    private boolean isInvalidCaloriesInfo;
    private boolean success;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     * @param description the description of the command.
     */

    public AddFoodCommand(String command, String description) throws InvalidFormatException {
        super(command);
        this.noDescription = false;

        try {
            String[] descriptionArray = Parser.parseDescription(description, ARGUMENTS_REQUIRED);
            this.foodName = descriptionArray[0].toLowerCase();
            this.calories = Double.parseDouble(descriptionArray[1]);
            this.isInvalidCaloriesInfo = false;
        } catch (NullPointerException e) {
            this.noDescription = true;
        } catch (NumberFormatException e) {
            this.isInvalidCaloriesInfo = true;
        }
    }

    @Override
    public void execute(Profile profile, UI ui) {
        if (!this.noDescription && !this.isInvalidCaloriesInfo) {
            FoodNutritionInfo foodInfo = FoodNutritionInfo.getInstance();
            this.success = foodInfo.addNewFood(foodName,calories);
        }
        saveResult(profile);
    }

    @Override
    public void saveResult(Profile profile) {
        if (this.noDescription) {
            this.result = MessageBank.NO_DESCRIPTION_MESSAGE;
        } else if (this.isInvalidCaloriesInfo) {
            this.result = "Sorry, to add new food to database you must specify its calories content.";
        } else if (!this.success) {
            this.result = "No need to add! We already have this food in our database!";
        } else {
            this.result = "You have added a new food into the database:" + System.lineSeparator()
                    + "Food: " + foodName + ", Calories: " + calories;
        }
    }
}
