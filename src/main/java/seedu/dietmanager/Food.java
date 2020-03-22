package seedu.dietmanager;

import java.util.Optional;

public class Food {
    private String foodName;
    private Optional<Double> calories;

    /**
     * Public constructor for Food with the food name and calories value.
     * @param foodName Name of the food.
     * @param calories Amount of calories the food has.
     */
    public Food(String foodName, double calories) {
        this.foodName = foodName;
        this.calories = Optional.of(calories);
    }

    /**
     * Public constructor for Food with the food name.
     * @param foodName Name of the food.
     */
    public Food(String foodName) {
        this.foodName = foodName;
        this.calories = Optional.empty();
    }

    /**
     * Getter for food name.
     * @return Name of the food.
     */
    public String getFoodName() {
        return foodName;
    }

    /**
     * Getter for the calories of the food.
     * @return Amount of calories the food has
     */
    public Optional<Double> getCalories() {
        return calories;
    }

    /**
     * Check if the food has calories.
     * @return True if food has calories, False otherwise.
     */
    public boolean hasCaloriesData() {
        return calories.isPresent();
    }

    public String toString() {
        return calories.map(value -> "Food: " + foodName + ", Calories: " + String.format("%.2f", value) + "cal\n")
                .orElseGet(() -> "Food: " + foodName + ", Calories: " + "No value found.\n");
    }
}
