package dish;

import exceptions.InvalidLoadException;
import menu.Menu;
import org.junit.jupiter.api.Test;
import report.LoadDish;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class LoadDishTest {

    @Test
    public void parseIngredientsA() {
        String input = "1. Name: pizza      \t Price: $3.00       \t Ingredients: cheese, sauce ";
        ArrayList<String> ingredients = new ArrayList<String>();
        ingredients.add("cheese");
        ingredients.add("sauce");
        assertEquals(ingredients, LoadDish.parseIngredients(input));
    }

    @Test
    public void parseIngredientsB() {
        String input = "2. Name: taco       \t Price: $4.50       \t Ingredients: chicken, salsa, sour cream, eggs ";
        ArrayList<String> ingredients = new ArrayList<String>();
        ingredients.add("chicken");
        ingredients.add("salsa");
        ingredients.add("sour cream");
        ingredients.add("eggs");
        assertEquals(ingredients, LoadDish.parseIngredients(input));
    }

    @Test
    public void parseNameA() {
        String input = "1. Name: pizza      \t Price: $3.00       \t Ingredients: cheese, sauce ";
        String name = "pizza";
        assertEquals(name, LoadDish.parseName(input));
    }

    @Test
    public void parseNameB() {
        String input = "2. Name: taco       \t Price: $4.50       \t Ingredients: chicken, salsa, sour cream, eggs ";
        String name = "taco";
        assertEquals(name, LoadDish.parseName(input));
    }

    @Test
    public void parsePriceA() {
        String input = "1. Name: pizza      \t Price: $3.00       \t Ingredients: cheese, sauce ";
        double price = 3.00;
        assertEquals(price, LoadDish.parsePrice(input));
    }

    @Test
    public void parsePriceB() {
        String input = "2. Name: taco       \t Price: $4.50       \t Ingredients: chicken, salsa, sour cream, eggs ";
        double price = 4.50;
        assertEquals(price, LoadDish.parsePrice(input));
    }

    @Test
    public void parseFileTestA() throws InvalidLoadException, FileNotFoundException {
        String fp = "sampleReportA.txt";
        Menu m = new Menu();
        HashMap dishMapA = new HashMap<String, Dish>();
        ArrayList<String> ingredients = new ArrayList<String>();
        ingredients.add("cheese");
        ingredients.add("sauce");
        String name = "pizza";
        double price = 3.00;
        dishMapA.put("pizza", new Dish(name, ingredients, price));
        m.setDishMap(dishMapA);
        LoadDish ld = LoadDish.getInstance(fp);
        assertEquals(m.getDishMap(), ld.readDishes().getDishMap());
    }


}
