package seedu.dietmanager.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class FoodTest {

    @Test
    void getFoodName() {
        Food food = new Food("apple");
        assertEquals("apple", food.getFoodName());
    }

    @Test
    void getCalories() {
        Food food = new Food("orange", 100);
        assertEquals(100, food.getCalories().get());
    }

    @Test
    void hasCaloriesData() {
        Food food = new Food("pear");
        assertFalse(food.hasCaloriesData());
    }

    @Test
    void testToString() {
        Food food = new Food("grape", 50);
        assertEquals("Food: grape, Calories: 50.00cal\n", food.toString());
    }

    @Test
    void getPair() {
        Food food = new Food("mango", 500);
        assertEquals("mango(500.00),", food.getPair());
    }
}
