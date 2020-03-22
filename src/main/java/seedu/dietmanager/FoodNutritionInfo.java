package seedu.dietmanager;

import seedu.dietmanager.ui.MessageBank;

import java.util.ArrayList;
import java.util.Optional;

// To be edited to add more Food items
public class FoodNutritionInfo {
    private ArrayList<Food> foods = new ArrayList<>();

    /**
     * Public constructor for FoodNutritionInfo of Food from our database.
     */
    public FoodNutritionInfo() {
        foods.add(new Food("Chicken", 1)); // Not accurate yet
        foods.add(new Food("Apple", 2)); // Not accurate yet
        foods.add(new Food("Carrot", 3)); // Not accurate yet
        foods.add(new Food("Rice", 4)); // Not accurate yet
        foods.add(new Food("Oil", 5)); // Not accurate yet
        foods.add(new Food("Tea", 6)); // Not accurate yet
    }

    /**
     * Search for a food in the database.
     * @param foodName The name of the food to be searched for.
     * @return Optional Food that contains the food if exist, and is empty otherwise.
     */
    public Optional<Food> findFood(String foodName) {
        Optional<Food> foodFound = Optional.empty();
        for (Food food : foods) {
            if (food.getFoodName().toLowerCase().equals(foodName.toLowerCase())) {
                foodFound = Optional.of(food);
            }
        }
        return foodFound;
    }

    /**
     * Search if a food exists in the database.
     * @param foodName The name of the food to be searched for.
     * @return True if food exist in database, false otherwise.
     */
    public boolean isInDatabase(String foodName) {
        boolean isFoodFound = false;
        for (Food food : foods) {
            if (food.getFoodName().equals(foodName)) {
                isFoodFound = true;
            }
        }
        return isFoodFound;
    }

    /**
     * Print out all the food stored in our database.
     */
    public String showFoodDatabase() {
        String foodDatabase = MessageBank.FOOD_DATABASE_MESSAGE;
        for (Food food : foods) {
            foodDatabase += food;
        }
        return foodDatabase;
    }
}
