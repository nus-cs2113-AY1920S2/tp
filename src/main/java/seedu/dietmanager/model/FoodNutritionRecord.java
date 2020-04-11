package seedu.dietmanager.model;

import seedu.dietmanager.commons.core.MessageBank;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FoodNutritionRecord {

    private List<Food> foodNutritionRecordList = new ArrayList<>();
    private static FoodNutritionRecord theOne = null;

    /**
     * Public constructor for FoodNutritionInfo of Food from our database.
     */

    private FoodNutritionRecord() {
        foodNutritionRecordList.add(new Food("chicken-rice", 702));
        foodNutritionRecordList.add(new Food("fried-rice", 508));
        foodNutritionRecordList.add(new Food("chicken-curry", 450));
        foodNutritionRecordList.add(new Food("prawn-noodles", 459));
        foodNutritionRecordList.add(new Food("fishball-noodles", 330));
        foodNutritionRecordList.add(new Food("cheeseburger", 300));
        foodNutritionRecordList.add(new Food("white-bread", 77));
        foodNutritionRecordList.add(new Food("french-fries", 450));
        foodNutritionRecordList.add(new Food("orange-juice", 80));
        foodNutritionRecordList.add(new Food("soft-drink", 120));
        foodNutritionRecordList.add(new Food("fresh-milk", 163));
    }

    /**
     * Returns the only instance, if it doesn't exist, create one first.
     *
     * @return the only instance of FoodNutritionInfo
     */

    public static FoodNutritionRecord getInstance() {
        if (theOne == null) {
            theOne = new FoodNutritionRecord();
        }
        return theOne;
    }

    /**
     * Search for a food in the database.
     *
     * @param foodName The name of the food to be searched for.
     * @return Optional Food that contains the food if exist, and is empty otherwise.
     */
    public Optional<Food> findFood(String foodName) {
        Optional<Food> foodFound = Optional.empty();
        for (Food food : foodNutritionRecordList) {
            if (food.getFoodName().toLowerCase().equals(foodName.toLowerCase())) {
                foodFound = Optional.of(food);
            }
        }
        return foodFound;
    }

    public Food findFood(int index) {
        return foodNutritionRecordList.get(index);
    }

    /**
     * Search if a food exists in the database.
     *
     * @param foodName The name of the food to be searched for.
     * @return True if food exist in database, false otherwise.
     */
    public boolean isInDatabase(String foodName) {
        boolean isFoodFound = false;
        for (Food food : foodNutritionRecordList) {
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
        for (Food food : foodNutritionRecordList) {
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

    public boolean addFoodNutritionRecord(String foodName, Double calories) {
        if (!isInDatabase(foodName)) {
            foodNutritionRecordList.add(new Food(foodName, calories));
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

    public boolean deleteFoodNutritionRecord(String foodName) {
        if (findFood(foodName).isPresent()) {
            Food toBeDeleted = findFood(foodName).get();
            foodNutritionRecordList.remove(toBeDeleted);
            return true;
        } else {
            return false;
        }
    }

    public List<Food> getFoodNutritionRecordList() {
        return this.foodNutritionRecordList;
    }

    public void clearFoodNutritionRecordList() {
        this.foodNutritionRecordList.clear();
    }

    public int getListSize() {
        return this.foodNutritionRecordList.size();
    }

    public double getFoodCalories(int index) {
        return foodNutritionRecordList.get(index).getCalories().get();
    }
}
