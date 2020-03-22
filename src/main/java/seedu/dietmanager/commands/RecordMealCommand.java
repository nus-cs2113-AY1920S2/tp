package seedu.dietmanager.commands;

import seedu.dietmanager.DailyFoodRecord;
import seedu.dietmanager.Food;
import seedu.dietmanager.FoodNutritionInfo;
import seedu.dietmanager.Profile;
import seedu.dietmanager.exceptions.InvalidFormatException;
import seedu.dietmanager.parser.Parser;
import seedu.dietmanager.ui.MessageBank;
import seedu.dietmanager.ui.UI;

import java.util.ArrayList;

public class RecordMealCommand extends Command {
    private static final int ARGUMENTS_REQUIRED = 3;
    private String date;
    private String mealType;
    private String[] foodDescription;
    boolean isValidFoodFormat;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     * @param description the description of the command.
     */

    public RecordMealCommand(String command, String description) throws InvalidFormatException {
        super(command);
        String[] descriptionArray = Parser.parseDescription(description, ARGUMENTS_REQUIRED);
        this.date = descriptionArray[0];
        this.mealType = descriptionArray[1];
        this.foodDescription = descriptionArray[2].split("/");
        this.isValidFoodFormat = true;
    }

    @Override
    public void execute(Profile profile, UI ui) {
        DailyFoodRecord record = profile.getRecordOfDay(date);
        ArrayList<Food> foodList = new ArrayList<>();
        String[] foodDescriptionSplit;
        String foodName;
        int foodCalories;
        FoodNutritionInfo foodInfo = new FoodNutritionInfo();
        for (String singleFoodDescription : foodDescription) {
            foodDescriptionSplit = singleFoodDescription.trim().split(" -- ");
            switch (foodDescriptionSplit.length) {
            case 1:
                foodName = foodDescriptionSplit[0].trim();
                Food food;
                if (foodInfo.isInDatabase(foodName)) {
                    food = foodInfo.findFood(foodName).get(); //Check if food exist in database
                } else {
                    food = new Food(foodName);
                }
                foodList.add(food);
                break;
            case 2:
                try {
                    foodName = foodDescriptionSplit[0].trim();
                    foodCalories = Integer.parseInt(foodDescriptionSplit[1].trim());
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
        boolean isValidType = true;
        switch (mealType) {
        case "breakfast":
            this.result = MessageBank.BREAKFAST_RECORD_MESSAGE;
            break;
        case "lunch":
            this.result = MessageBank.LUNCH_RECORD_MESSAGE;
            break;
        case "dinner":
            this.result = MessageBank.DINNER_RECORD_MESSAGE;
            break;
        default:
            isValidType = false;
            this.result = MessageBank.MEAL_TYPE_ERROR;
            break;
        }
        if (!isValidFoodFormat) {
            this.result = this.result + MessageBank.INVALID_FOOD_FORMAT_ERROR;
        }
        if (isValidType) {
            this.result = this.result + date + ".";
        }
    }
}
