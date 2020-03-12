package ingredient;

import ingredient.Ingredient;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    @Test
    public void testIngredientToString() {
        assertEquals("tomato", new Ingredient("tomato", Optional.empty(), Optional.empty()).toString());
        assertEquals("potato", new Ingredient("potato", Optional.of(0), Optional.empty()).toString());
        assertEquals("rice", new Ingredient("rice", Optional.of(1), Optional.of(1.0)).toString());
    }
    
    @Test
    public void testIsQuantitySpecified() {
        assertTrue(new Ingredient("tomato", Optional.of(1), Optional.empty()).isQuantitySpecified());
        assertFalse(new Ingredient("tomato", Optional.empty(), Optional.empty()).isQuantitySpecified());
    }
    
    @Test
    public void testEquals() {
        Ingredient tomatoA = new Ingredient("tomato", Optional.of(1), Optional.empty());
        Ingredient tomatoACopy = new Ingredient("tomato", Optional.of(1), Optional.empty());
        Ingredient tomatoB = new Ingredient("tomato", Optional.of(2), Optional.empty());
        
        assertTrue(tomatoA.equals(tomatoACopy));
        assertTrue(tomatoA.equals(tomatoB));
    }
    
    @Test
    public void testGetIngredientQuantity() {
        Ingredient tomato = new Ingredient("tomato", Optional.of(1), Optional.of(0.5));
        Ingredient rice = new Ingredient("rice", Optional.empty(), Optional.of(0.5));
        assertEquals(1, tomato.getIngredientQuantity());
        
        try {
            rice.getIngredientQuantity();
        } catch (NoSuchElementException nsoe) {
            assertEquals("No value present", nsoe.getMessage());
        }
    }
}
