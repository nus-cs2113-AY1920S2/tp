package ingredient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import ingredient.Ingredient;

class IngredientTest {

    @Test
    public void print_IngredientToString_toStringIsIngredientName() {
        assertEquals("tomato", 
                new Ingredient("tomato", Optional.empty(), Optional.empty()).toString());
        assertEquals("potato", 
                new Ingredient("potato", Optional.of(0), Optional.empty()).toString());
        assertEquals("rice", 
                new Ingredient("rice", Optional.of(1), Optional.of(1.0)).toString());
    }
    
    @Test
    public void test_IsQuantitySpecified_TrueWhenQuantitySpecified() {
        assertTrue(
                new Ingredient("tomato", Optional.of(1), Optional.empty()).isQuantitySpecified());
        assertFalse(
                new Ingredient("tomato", Optional.empty(), Optional.empty()).isQuantitySpecified());
    }
    
    /**
     * Returns true if both ingredients have the same name.
     */
    @Test
    public void test_Equals_TrueIfIngredientsHaveSameName() {
        Ingredient tomatoA = new Ingredient("tomato", Optional.of(1), Optional.empty());
        Ingredient tomatoACopy = new Ingredient("tomato", Optional.of(1), Optional.empty());
        Ingredient tomatoB = new Ingredient("tomato", Optional.of(2), Optional.empty());
        
        assertTrue(tomatoA.equals(tomatoACopy));
        assertTrue(tomatoA.equals(tomatoB));
    }
    
    @Test
    public void getQuantity_GetIngredientQuantity_QuantityOfIngredient() {
        Ingredient tomato = new Ingredient("tomato", Optional.of(1), Optional.of(0.5));
        Ingredient rice = new Ingredient("rice", Optional.empty(), Optional.of(0.5));
        assertEquals(1, tomato.getIngredientQuantity());
        
        try {
            rice.getIngredientQuantity();
        } catch (NoSuchElementException nsoe) {
            assertEquals("No value present", nsoe.getMessage());
        }
    }
    
    @Test
    public void getPrice_GetIngredientPrice_PriceOfIngredient() {
        Ingredient tomato = new Ingredient("tomato", Optional.of(1), Optional.of(0.5));
        Ingredient rice = new Ingredient("rice", Optional.empty(), Optional.empty());
        assertEquals(0.50, tomato.getIngredientPrice());
        
        try {
            rice.getIngredientPrice();
        } catch (NoSuchElementException nsoe) {
            assertEquals("No value present", nsoe.getMessage());
        }
    }
    
    @Test
    public void getName_GetIngredientNameNameOfIngredient() {
        Ingredient tomato = new Ingredient("tomato", Optional.of(1), Optional.of(0.5));
        Ingredient rice = new Ingredient("rice", Optional.empty(), Optional.empty());
        assertEquals("tomato", tomato.getIngredientName());
        assertFalse(rice.getIngredientName().equals("banana"));
    }
}
