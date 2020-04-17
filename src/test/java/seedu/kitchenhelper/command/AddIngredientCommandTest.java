package seedu.kitchenhelper.command;

import org.junit.jupiter.api.Test;
import seedu.kitchenhelper.object.ingredient.Ingredient;
import seedu.kitchenhelper.object.ingredient.Meat;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddIngredientCommandTest {
    
    @Test
    void addToCategory_testPass() {
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        new AddIngredientCommand("Beef", "Meat", 30, 20.2, "20/02/2020").addToCategory("Meat", ingredientList);
        assertTrue(ingredientList.get(0) instanceof Meat);
        assertEquals("Beef", ingredientList.get(0).getIngredientName());
        assertEquals("Meat", ingredientList.get(0).getCategoryName());
        assertEquals(30, ingredientList.get(0).getQuantity());
        assertEquals(20.2, ingredientList.get(0).getPrice());
        assertEquals("20/02/2020", ingredientList.get(0).getExpiryDate());
    }
    
    @Test
    void addToCategory_testFail() {
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        new AddIngredientCommand("Beef", "Meat", 30, 20.2, "2020-02-20").addToCategory("Meat", ingredientList);
        assertTrue(ingredientList.get(0) instanceof Meat);
        assertNotEquals("Pork", ingredientList.get(0).getIngredientName());
        assertNotEquals("Vegetable", ingredientList.get(0).getCategoryName());
        assertNotEquals(40, ingredientList.get(0).getQuantity());
        assertNotEquals(50.21, ingredientList.get(0).getPrice());
        assertNotEquals("2020.02-20", ingredientList.get(0).getExpiryDate());
    }
    
}
