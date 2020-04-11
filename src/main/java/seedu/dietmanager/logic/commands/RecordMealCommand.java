package seedu.dietmanager.logic.commands;

import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.commons.core.Weekday;
import seedu.dietmanager.commons.exceptions.InvalidFormatException;
import seedu.dietmanager.logic.Result;
import seedu.dietmanager.logic.parser.DescriptionParser;
import seedu.dietmanager.model.DailyFoodRecord;
import seedu.dietmanager.model.Food;
import seedu.dietmanager.model.FoodNutritionRecord;
import seedu.dietmanager.model.Profile;
import seedu.dietmanager.ui.UI;

import java.util.ArrayList;

public class RecordMealCommand extends Command {
    private static final int ARGUMENTS_REQUIRED = 3;
    private String date;
    private String mealType;
    private String[] foodDescription;
    private boolean isValidFoodFormat;
    private boolean noDescription;
    private boolean isInvalidDate;

    /**
     * Constructs the Command object.
     *
     * @param command     the command prompt entered by the user.
     * @param description the description of the command.
     * @throws InvalidFormatException if the command doesn't contain correct number of parameters.
     */

    public RecordMealCommand(String command, String description) throws InvalidFormatException {
        super(command);
        this.noDescription = false;
        this.isInvalidDate = false;

        try {
            String[] descriptionArray = DescriptionParser.parseDescription(description, ARGUMENTS_REQUIRED);
            this.date = descriptionArray[0].trim().toUpperCase();
            this.mealType = descriptionArray[1].trim().toLowerCase();
            this.foodDescription = descriptionArray[2].trim().split("/");
            this.isValidFoodFormat = true;

            Weekday.valueOf(this.date);

        } catch (NullPointerException e) {
            this.noDescription = true;
        } catch (IllegalArgumentException e) {
            this.isInvalidDate = true;
        }
    }

    @Override
    public Result execute(Profile profile, UI ui) {
        if (this.noDescription | this.isInvalidDate) {
            Result result = getResult(profile);
            return result;
        }

        DailyFoodRecord record = profile.getRecordOfDay(date);
        ArrayList<Food> foodList = new ArrayList<>();
        String[] foodDescriptionSplit;
        String foodName;
        Double foodCalories;
        FoodNutritionRecord foodInfo = FoodNutritionRecord.getInstance();

        for (String singleFoodDescription : foodDescription) {
            if (singleFoodDescription.strip().equals("")) {
                continue;
            }

            foodDescriptionSplit = singleFoodDescription.trim().split("--");
            foodName = foodDescriptionSplit[0].trim().toLowerCase();

            switch (foodDescriptionSplit.length) {
            case 1:
                Food food;
                if (foodInfo.isInDatabase(foodName)) {
                    food = foodInfo.findFood(foodName).get();
                } else {
                    food = new Food(foodName);
                }
                foodList.add(food);
                break;
            case 2:
                try {
                    foodCalories = Double.parseDouble(foodDescriptionSplit[1].trim());
                    if (foodCalories < 0) {
                        throw new NumberFormatException();
                    }
                    foodList.add(new Food(foodName, foodCalories));
                } catch (NumberFormatException e) {
                    isValidFoodFormat = false;
                }
                break;
            default:
                isValidFoodFormat = false;
                break;
            }
        }
        record.recordMeals(mealType, foodList);
        Result result = getResult(profile);
        return result;
    }

    @Override
    public Result getResult(Profile profile) {
        if (this.noDescription) {
            this.resultString = MessageBank.NO_DESCRIPTION_MESSAGE;
            return new Result(this.resultString);
        } else if (this.isInvalidDate) {
            this.resultString = MessageBank.INVALID_DATE_MESSAGE;
            return new Result(this.resultString);
        }

        boolean isValidType = true;
        switch (mealType) {
        case "morning":
            this.resultString = MessageBank.BREAKFAST_RECORD_MESSAGE;
            break;
        case "afternoon":
            this.resultString = MessageBank.LUNCH_RECORD_MESSAGE;
            break;
        case "night":
            this.resultString = MessageBank.DINNER_RECORD_MESSAGE;
            break;
        default:
            isValidType = false;
            this.resultString = MessageBank.MEAL_TYPE_ERROR;
            break;
        }
        if (isValidType) {
            this.resultString = this.resultString + date + ".";
        }
        if (!isValidFoodFormat) {
            this.resultString = this.resultString + System.lineSeparator() + MessageBank.INVALID_FOOD_FORMAT_ERROR;
        }
        return new Result(this.resultString);
    }
}
