package seedu.dietmanager;

import seedu.dietmanager.ui.MessageBank;

import java.util.ArrayList;
import java.util.Optional;

// To be edited to add more Food items
public class FoodNutritionInfo {
    private ArrayList<Food> foods = new ArrayList<>();

    /**
     * Public constructor for FoodNutritionInfo of Food from our database
     */
    public FoodNutritionInfo() {
        foods.add(new Food("Chicken", 1)); // Not accurate yet
        foods.add(new Food("Apple", 2)); // Not accurate yet
        foods.add(new Food("Carrot", 3)); // Not accurate yet
        foods.add(new Food("Rice", 4)); // Not accurate yet
        foods.add(new Food("Oil", 5)); // Not accurate yet
        foods.add(new Food("Tea", 6)); // Not accurate yet
    }

    public Optional<Food> findFood(String foodName) {
        Optional<Food> foodFound = Optional.empty();
        for (Food food : foods) {
            if (food.getFoodName().equals(foodName)) {
                foodFound = Optional.of(food);
            }
        }
        return foodFound;
    }

    public boolean isInDatabase(String foodName) {
        boolean isFoodFound = false;
        for (Food food : foods) {
            if (food.getFoodName().equals(foodName)) {
                isFoodFound = true;
            }
        }
        return isFoodFound;
    }

    public void showFoodDatabase() {
        System.out.println(MessageBank.FOOD_DATABASE_MESSAGE);
        for (Food food : foods) {
            System.out.println(food);
        }
        System.out.println();
    }
}
