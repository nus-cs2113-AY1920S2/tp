package seedu.dietmanager;

import seedu.dietmanager.ui.MessageBank;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Optional;

public class DailyFoodRecord {
    private String date;
    private ArrayList<Food> breakfast;
    private ArrayList<Food> lunch;
    private ArrayList<Food> dinner;

    /**
     * Constructs the Daily Food Record.
     */

    public DailyFoodRecord(String date) {
        setDate(date);
        breakfast = new ArrayList<Food>();
        lunch = new ArrayList<Food>();
        dinner = new ArrayList<Food>();
    }

    /**
     * Sets the date of the record.
     */

    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Records the meals consumed into their respective categories.
     */

    public void recordMeals(String mealType, ArrayList<Food> foodList) {
        switch (mealType) {
        case "breakfast":
            breakfast.addAll(foodList);
            break;
        case "lunch":
            lunch.addAll(foodList);
            break;
        case "dinner":
            dinner.addAll(foodList);
            break;
        default:
            break;
        }
    }

    /**
     * Displays the Daily Food Record.
     */

    public void showDailyRecord() {
        this.showBreakfast();
        this.showLunch();
        this.showDinner();
    }

    /**
     * Displays the Daily Breakfast Record.
     */

    public String showBreakfast() {
        String message = "";
        for (Food food : breakfast) {
            message = message + food.toString();
        }
        if (message.equals("")) {
            message = "Oops, you have no record for this meal.";
        }
        return message.trim();
    }

    /**
     * Displays the Daily Lunch Record.
     */

    public String showLunch() {
        String message = "";
        for (Food food : lunch) {
            message = message + food.toString();
        }
        if (message.equals("")) {
            message = "Oops, you have no record for this meal.";
        }
        return message.trim();
    }

    /**
     * Displays the Daily Dinner Record.
     */

    public String showDinner() {
        String message = "";
        for (Food food : dinner) {
            message = message + food.toString();
        }
        if (message.equals("")) {
            message = "Oops, you have no record for this meal.";
        }
        return message.trim();
    }

    public boolean isDate(String date) {
        return this.date.equals(date);
    }

    /**
     * Provide a ArrayList of all the foods consumed in a day.
     * @return ArrayList of all the foods consumed in a day
     */
    public ArrayList<Food> getDailyFood() {
        ArrayList<Food> allDailyFood = new ArrayList<>();
        allDailyFood.addAll(breakfast); //Changed to morning after merging
        allDailyFood.addAll(lunch); //Change to afternoon after merging
        allDailyFood.addAll(dinner); //Change to night after merging
        return allDailyFood;
    }

    /**
     * Provide a ArrayList of the foods consumed in a specific time frame of a day.
     * @return ArrayList of all the foods consumed in a day
     */
    public ArrayList<Food> getDailyFood(String timeFrame) {
        switch (timeFrame) {
        case "breakfast": //Changed to morning after merging
            return breakfast; //Changed to morning after merging
        case "lunch": //Changed to afternoon after merging
            return lunch; //Changed to afternoon after merging
        case "dinner": //Changed to night after merging
            return dinner; //Changed to night after merging
        default:
            return new ArrayList<>();
        }
    }

    /**
     * Calculate the total recorded calories for all the meals.
     * @return Optional double of the calories intake if exist, and Optional empty otherwise.
     */
    public Optional<Double> getDailyCalories() {
        ArrayList<Food> allDailyFood = getDailyFood();
        return allDailyFood.stream().filter(Food::hasCaloriesData)
                .map(Food::getCalories)
                .map(Optional::get)
                .reduce(Double::sum);
    }

    /**
     * Calculate the total recorded calories for meals in a specific time frame of a day .
     * @return Optional double of the calories intake if exist, and Optional empty otherwise.
     */
    public Optional<Double> getDailyCalories(String timeFrame) {
        ArrayList<Food> foodInTimeFrame = getDailyFood(timeFrame);
        return foodInTimeFrame.stream().filter(Food::hasCaloriesData)
                .map(Food::getCalories)
                .map(Optional::get)
                .reduce(Double::sum);
    }

    /**
     * Check if the DailyFoodRecord has any Food with missing calories data.
     * @return False if there exist food without calories, true otherwise.
     */
    public boolean isAllFoodCaloriesPresent() {
        ArrayList<Food> allDailyFood = getDailyFood();
        return allDailyFood.stream().filter(food -> !food.hasCaloriesData())
                .map(food -> false)
                .reduce((first, second) -> first)
                .orElse(true);
    }

    /**
     * Check if the DailyFoodRecord has any Food in a specific time frame of a day with missing calories data.
     * @return False if there exist food without calories, true otherwise.
     */
    public boolean isAllFoodCaloriesPresent(String timeFrame) {
        ArrayList<Food> allDailyFood = getDailyFood(timeFrame);
        return allDailyFood.stream().filter(food -> !food.hasCaloriesData())
                .map(food -> false)
                .reduce((first, second) -> first)
                .orElse(true);
    }

    /**
     * Express the calculable calories intake for all the meals as a String.
     * @return String representation of the calculable calories.
     */
    public String showDailyCalories() {
        return getDailyCalories()
                .map(calories -> MessageBank.CALCULABLE_CALORIES_MESSAGE + calories + "cal.\n")
                .map(result -> (isAllFoodCaloriesPresent()) ? result : (result + MessageBank.MISSING_CALORIES_MESSAGE))
                .orElse(MessageBank.NO_CALCULABLE_CALORIES_MESSAGE);
    }

    /**
     * Express the calculable calories intake for meals within a time frame of the day as a String.
     * @return String representation of the calculable calories within a time frame.
     */
    public String showDailyCalories(String timeFrame) {
        return getDailyCalories(timeFrame)
                .map(calories -> MessageBank.CALCULABLE_CALORIES_MESSAGE + calories + "cal.\n")
                .map(result -> (isAllFoodCaloriesPresent()) ? result : (result + MessageBank.MISSING_CALORIES_MESSAGE))
                .orElse(MessageBank.NO_CALCULABLE_CALORIES_MESSAGE);
    }
}
