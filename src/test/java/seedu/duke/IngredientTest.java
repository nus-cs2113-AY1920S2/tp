package seedu.duke;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import ingredient.Ingredient;

class IngredientTest {

    @Test
    public void testIngredientToString() {
        assertEquals("tomato", new Ingredient("tomato", Optional.empty(), Optional.empty()).toString());
    }
    
    @Test
    public void testIsQuantitySpecified() {
        assertTrue(new Ingredient("tomato", Optional.of(1), Optional.empty()).isQuantitySpecified());
        assertFalse( new Ingredient("tomato", Optional.empty(), Optional.empty()).isQuantitySpecified());
    }
    
    @Test
    public void testEquals() {
        Ingredient tomatoA = new Ingredient("tomato", Optional.of(1), Optional.empty());
        Ingredient tomatoACopy = new Ingredient("tomato", Optional.of(1), Optional.empty());
        Ingredient tomatoB = new Ingredient("tomato", Optional.of(2), Optional.empty());
        
        assertTrue(tomatoA.equals(tomatoACopy));
        assertTrue(tomatoA.equals(tomatoB));
    }
}
