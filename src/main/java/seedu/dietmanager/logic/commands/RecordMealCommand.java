package seedu.dietmanager.logic.commands;

import seedu.dietmanager.commons.exceptions.InvalidFormatException;
import seedu.dietmanager.logic.parser.Parser;
import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.ui.UI;
import seedu.dietmanager.model.DailyFoodRecord;
import seedu.dietmanager.model.Food;
import seedu.dietmanager.commons.core.FoodNutritionInfo;
import seedu.dietmanager.model.Profile;
import seedu.dietmanager.commons.core.Weekday;


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
     * @param command the command prompt entered by the user.
     * @param description the description of the command.
     * @throws InvalidFormatException if the command doesn't contain correct number of parameters.
     */

    public RecordMealCommand(String command, String description) throws InvalidFormatException {
        super(command);
        this.noDescription = false;
        this.isInvalidDate = false;

        try {
            String[] descriptionArray = Parser.parseDescription(description, ARGUMENTS_REQUIRED);
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
    public void execute(Profile profile, UI ui) {
        if (this.noDescription | this.isInvalidDate) {
            saveResult(profile);
            return;
        }

        DailyFoodRecord record = profile.getRecordOfDay(date);
        ArrayList<Food> foodList = new ArrayList<>();
        String[] foodDescriptionSplit;
        String foodName;
        Double foodCalories;
        FoodNutritionInfo foodInfo = FoodNutritionInfo.getInstance();

        for (String singleFoodDescription : foodDescription) {
            if (singleFoodDescription.equals("")) {
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
        record.recordMeals(mealType,foodList);
        saveResult(profile);
    }

    @Override
    public void saveResult(Profile profile) {
        if (this.noDescription) {
            this.result = MessageBank.NO_DESCRIPTION_MESSAGE;
            return;
        } else if (this.isInvalidDate) {
            this.result = MessageBank.INVALID_DATE_MESSAGE;
            return;
        }

        boolean isValidType = true;
        switch (mealType) {
        case "morning":
            this.result = MessageBank.BREAKFAST_RECORD_MESSAGE;
            break;
        case "afternoon":
            this.result = MessageBank.LUNCH_RECORD_MESSAGE;
            break;
        case "night":
            this.result = MessageBank.DINNER_RECORD_MESSAGE;
            break;
        default:
            isValidType = false;
            this.result = MessageBank.MEAL_TYPE_ERROR;
            break;
        }
        if (isValidType) {
            this.result = this.result + date + ".";
        }
        if (!isValidFoodFormat) {
            this.result = this.result + System.lineSeparator() + MessageBank.INVALID_FOOD_FORMAT_ERROR;
        }
    }
}
