package stock;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
import ingredient.Ingredient;
import ingredient.IngredientNotFoundException;
import org.junit.jupiter.api.Test;
import stock.Stock;
import utils.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class StockTest {
    
    public static Map<String, Pair<Integer, Double>> createStockCopy() {
        Map<String, Pair<Integer, Double>> stockCopy = new HashMap<>();
        stockCopy.put("tomato", Pair.of(1, 0.50));
        stockCopy.put("tomato", Pair.of(2, 0.50));
        return stockCopy;
    }
    
    @Test
    public void testAddIngredient() {
        Stock stock = new Stock();
        stock.addIngredient(new Ingredient("tomato", Optional.of(1), Optional.of(0.50)));
        stock.addIngredient(new Ingredient("tomato", Optional.of(2), Optional.of(0.50)));
        
        Map<String, Pair<Integer, Double>> stockCopy = createStockCopy();
        assertEquals(stock.getStock().keySet(), (stockCopy.keySet()));
    }
    
    @Test
    public void testDeleteIngredient() throws IngredientNotFoundException {
        Stock stock = new Stock();
        stock.addIngredient(new Ingredient("tomato", Optional.of(1), Optional.of(0.50)));
        stock.deleteIngredient(new Ingredient("tomato", Optional.of(1), Optional.empty()));
        
        Map<String, Pair<Integer, Double>> stockCopy = createStockCopy();
        assertFalse(stock.getStock().equals(stockCopy));
    }
}
