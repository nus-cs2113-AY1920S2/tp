package seedu.duke;

import java.util.ArrayList;

// To be edited to add more Food items
public class FoodNutritionInfo {
    private ArrayList<Food> nutrients = new ArrayList<>();

    public FoodNutritionInfo() {
        nutrients.add(new Food("Chicken", 0, 0, 0, 0, 0,
                0, 0)); // Need to check the nutrients
    }
}
