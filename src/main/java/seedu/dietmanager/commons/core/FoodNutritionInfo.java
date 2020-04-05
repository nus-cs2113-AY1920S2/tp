package seedu.dietmanager.commons.core;

import seedu.dietmanager.model.Food;

import java.util.ArrayList;
import java.util.Optional;

// To be edited to add more Food items
public class FoodNutritionInfo {
    private ArrayList<Food> foods = new ArrayList<>();
    private static FoodNutritionInfo theOnlyOne = null;

    /**
     * Public constructor for FoodNutritionInfo of Food from our database.
     */
    private FoodNutritionInfo() {
        foods.add(new Food("Chicken", 1)); // Not accurate yet
        foods.add(new Food("Apple", 2)); // Not accurate yet
        foods.add(new Food("Carrots", 3)); // Not accurate yet
        foods.add(new Food("Rice", 4)); // Not accurate yet
        foods.add(new Food("Oil", 5)); // Not accurate yet
        foods.add(new Food("Tea", 6)); // Not accurate yet
    }

    /**
     * Returns the only instance, if it doesn't exist, create one first.
     *
     * @return the only instance of FoodNutritionInfo
     */
    public static FoodNutritionInfo getInstance() {
        if (theOnlyOne == null) {
            theOnlyOne = new FoodNutritionInfo();
        }
        return theOnlyOne;
    }

    /**
     * Search for a food in the database.
     *
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

    public Food findFood(int index) {
        return foods.get(index);
    }

    /**
     * Search if a food exists in the database.
     *
     * @param foodName The name of the food to be searched for.
     * @return True if food exist in database, false otherwise.
     */
    public boolean isInDatabase(String foodName) {
        boolean isFoodFound = false;
        for (Food food : foods) {
            if (food.getFoodName().toLowerCase().equals(foodName.toLowerCase())) {
                isFoodFound = true;
                break;
            }
        }
        return isFoodFound;
    }

    /**
     * Print out all the food stored in our database.
     */
    public String showFoodDatabase() {
        StringBuilder foodDatabase = new StringBuilder(MessageBank.FOOD_DATABASE_MESSAGE);
        for (Food food : foods) {
            foodDatabase.append(food);
        }
        return foodDatabase.toString();
    }

    /**
     * Adds a new food into the database.
     *
     * @param foodName name of the new food
     * @param calories calories content of the new food
     * @return true if operation succeeds, false if food already exists in database
     */

    public boolean addNewFood(String foodName, Double calories) {
        if (!isInDatabase(foodName)) {
            foods.add(new Food(foodName, calories));
            return true;
        } else {
            return false;
        }
    }

    /**
     * Deletes a food from the database.
     *
     * @param foodName name of the to-be-deleted food
     * @return true if operation succeeds, false if referred food doesn't exist
     */

    public boolean deleteFood(String foodName) {
        if (findFood(foodName).isPresent()) {
            Food toBeDeleted = findFood(foodName).get();
            foods.remove(toBeDeleted);
            return true;
        } else {
            return false;
        }
    }

    public int getListSize() {
        return this.foods.size();
    }

    public double getFoodCalories(int index) {
        return foods.get(index).getCalories().get();
    }
}
