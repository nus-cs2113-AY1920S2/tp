package seedu.dietmanager.model;

import seedu.dietmanager.commons.core.MessageBank;

import java.util.ArrayList;
import java.util.Optional;

public class DailyFoodRecord {
    private String date;
    private ArrayList<Food> morning;
    private ArrayList<Food> afternoon;
    private ArrayList<Food> night;

    /**
     * Constructs the Daily Food Record.
     */

    public DailyFoodRecord(String date) {
        setDate(date);
        this.morning = new ArrayList<Food>();
        this.afternoon = new ArrayList<Food>();
        this.night = new ArrayList<Food>();
    }

    /**
     * Sets the date of the record.
     */

    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Returns the date of this record.
     *
     * @return the date of this record
     */

    public String getDate() {
        return this.date;
    }

    /**
     * Records the meals consumed into their respective categories.
     *
     * @param mealType type of this meal, divided by time period.
     * @param foodList the list of foods.
     */

    public void recordMeals(String mealType, ArrayList<Food> foodList) {
        switch (mealType) {
        case "morning":
            this.morning.addAll(foodList);
            break;
        case "afternoon":
            this.afternoon.addAll(foodList);
            break;
        case "night":
            this.night.addAll(foodList);
            break;
        default:
            break;
        }
    }

    /**
     * Clear the records of a certain meal.
     *
     * @param mealType type of this meal, divided by time period.
     */
    public void clearRecords(String mealType) {
        switch (mealType) {
        case "morning":
            this.morning.clear();
            break;
        case "afternoon":
            this.afternoon.clear();
            break;
        case "night":
            this.night.clear();
            break;
        default:
            break;
        }
    }

    /**
     * Displays the Daily Breakfast Record.
     */

    public String showBreakfast() {
        String message = "";
        for (Food food : this.morning) {
            message = message + food.toString();
        }
        if (message.equals("")) {
            message = "Oops, you have no record for this meal.\n";
        }
        return message;
    }

    /**
     * Displays the Daily Lunch Record.
     */

    public String showLunch() {
        String message = "";
        for (Food food : this.afternoon) {
            message = message + food.toString();
        }
        if (message.equals("")) {
            message = "Oops, you have no record for this meal.\n";
        }
        return message;
    }

    /**
     * Displays the Daily Dinner Record.
     */

    public String showDinner() {
        String message = "";
        for (Food food : this.night) {
            message = message + food.toString();
        }
        if (message.equals("")) {
            message = "Oops, you have no record for this meal.\n";
        }
        return message;
    }

    public boolean isDate(String date) {
        return this.date.toLowerCase().equals(date.toLowerCase());
    }

    /**
     * Provide a ArrayList of all the foods consumed in a day.
     *
     * @return ArrayList of all the foods consumed in a day
     */
    public ArrayList<Food> getDailyFood() {
        ArrayList<Food> allDailyFood = new ArrayList<>();
        allDailyFood.addAll(this.morning);
        allDailyFood.addAll(this.afternoon);
        allDailyFood.addAll(this.night);
        return allDailyFood;
    }

    /**
     * Provide a ArrayList of the foods consumed in a specific time frame of a day.
     *
     * @return ArrayList of all the foods consumed in a day
     */
    public ArrayList<Food> getDailyFood(String timeFrame) {
        switch (timeFrame) {
        case "morning":
            return this.morning;
        case "afternoon":
            return this.afternoon;
        case "night":
            return this.night;
        default:
            return new ArrayList<>();
        }
    }

    /**
     * Calculate the total recorded calories for all the meals.
     *
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
     *
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
     *
     * @return False if there exist food without calories, true otherwise.
     */
    public boolean isCaloriesPresent() {
        ArrayList<Food> allDailyFood = getDailyFood();
        return allDailyFood.stream().filter(food -> !food.hasCaloriesData())
                .map(food -> false)
                .reduce((first, second) -> first)
                .orElse(true);
    }

    /**
     * Check if the DailyFoodRecord has any Food in a specific time frame of a day with missing calories data.
     *
     * @return False if there exist food without calories, true otherwise.
     */
    public boolean isCaloriesPresent(String timeFrame) {
        ArrayList<Food> allDailyFood = getDailyFood(timeFrame);
        return allDailyFood.stream().filter(food -> !food.hasCaloriesData())
                .map(food -> false)
                .reduce((first, second) -> first)
                .orElse(true);
    }

    /**
     * Express the calculable calories intake for all the meals as a String.
     *
     * @return String representation of the calculable calories.
     */
    public String showDailyCalories() {
        return getDailyCalories()
                .map(calories -> MessageBank.CALORIES_MESSAGE + String.format("%.2f", calories) + "cal.\n")
                .map(result -> (isCaloriesPresent()) ? result : (result + MessageBank.MISSING_CALORIES_MESSAGE))
                .orElse(MessageBank.NO_CALORIES_MESSAGE);
    }

    /**
     * Express the calculable calories intake for meals within a time frame of the day as a String.
     *
     * @return String representation of the calculable calories within a time frame.
     */
    public String showDailyCalories(String timeFrame) {
        return getDailyCalories(timeFrame)
                .map(calories -> "For " + timeFrame + ", " + MessageBank.TIME_CALORIES_MESSAGE
                        + String.format("%.2f", calories) + "cal.\n")
                .orElse("For " + timeFrame + ", " + MessageBank.NO_TIME_CALORIES_MESSAGE);
    }

    /**
     * Returns a record of one day.
     *
     * @return one entry of the weekly recipe
     */

    public String getRecipeEntry() {
        String message = this.date;
        message = String.format("%1$-10s", message);

        for (Food food : morning) {
            message = message + food.getPair();
        }
        message = message.substring(0, message.length() - 1);
        message = String.format("%1$-80s", message);

        for (Food food : afternoon) {
            message = message + food.getPair();
        }
        message = message.substring(0, message.length() - 1);
        message = String.format("%1$-150s", message);

        for (Food food : night) {
            message = message + food.getPair();
        }
        message = message.substring(0, message.length() - 1);

        return message;
    }
}
