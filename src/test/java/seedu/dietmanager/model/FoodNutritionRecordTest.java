package seedu.dietmanager.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FoodNutritionRecordTest {

    @Test
    void getInstance() {
        assertEquals(ArrayList.class, (FoodNutritionRecord.getInstance().getFoodNutritionRecordList()).getClass());
    }

    @Test
    void findFood() {
        FoodNutritionRecord test = FoodNutritionRecord.getInstance();
        assertTrue(test.findFood("chicken-rice").isPresent());
    }

    @Test
    void testFindFood() {
        FoodNutritionRecord test = FoodNutritionRecord.getInstance();
        assertEquals(Food.class, test.findFood(0).getClass());
    }

    @Test
    void isInDatabase() {
        FoodNutritionRecord test = FoodNutritionRecord.getInstance();
        assertTrue(test.isInDatabase("chicken-rice"));
    }

    @Test
    void showFoodDatabase() {
        FoodNutritionRecord test = FoodNutritionRecord.getInstance();
        assertEquals(String.class, test.showFoodDatabase().getClass());
    }

    @Test
    void addFoodNutritionRecord() {
        FoodNutritionRecord test = FoodNutritionRecord.getInstance();
        assertTrue(test.addFoodNutritionRecord("Muffin", 200.00));
        assertFalse(test.addFoodNutritionRecord("Chicken-Rice", 200.00));
    }

    @Test
    void deleteFoodNutritionRecord() {
        FoodNutritionRecord test = FoodNutritionRecord.getInstance();
        assertFalse(test.deleteFoodNutritionRecord("Cookie"));
        assertTrue(test.deleteFoodNutritionRecord("Chicken-Rice"));
    }

    @Test
    void getFoodNutritionRecordList() {
        assertEquals(ArrayList.class, (FoodNutritionRecord.getInstance().getFoodNutritionRecordList()).getClass());
    }

    @Test
    void clearFoodNutritionRecordList() {
        FoodNutritionRecord test = FoodNutritionRecord.getInstance();
        test.clearFoodNutritionRecordList();
        assertTrue(test.getListSize() == 0);
    }

    @Test
    void getListSize() {
        FoodNutritionRecord test = FoodNutritionRecord.getInstance();
        assertTrue(test.getListSize() >= 0);
    }

    @Test
    void getFoodCalories() {
        FoodNutritionRecord test = FoodNutritionRecord.getInstance();
        assertTrue(test.getFoodCalories(0) >= 0);
    }
}