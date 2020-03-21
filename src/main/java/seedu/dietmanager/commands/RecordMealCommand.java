package seedu.dietmanager.commands;

import seedu.dietmanager.DailyFoodRecord;
import seedu.dietmanager.Food;
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
    }

    @Override
    public void execute(Profile profile, UI ui) {
        DailyFoodRecord record = profile.getRecordOfDay(date);
        ArrayList<Food> foodList = new ArrayList<>();
        for (String foodName : foodDescription) {
            foodName = foodName.trim();
            if (!foodName.equals("")) {
                foodList.add(new Food(foodName));
            }
        }
        record.recordMeals(mealType,foodList);
        saveResult(profile);
    }

    @Override
    public void saveResult(Profile profile) {
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
    }
}
