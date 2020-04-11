package report;

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
        ArrayList<String> ingredients = new ArrayList<String>();
        ingredients.add("cheese");
        ingredients.add("sauce");
        String input = "1. Name: pizza      \t Price: $3.00       \t Ingredients: cheese, sauce ";
        assertEquals(ingredients, LoadDish.parseIngredients(input));
    }

    @Test
    public void parseIngredientsB() {
        ArrayList<String> ingredients = new ArrayList<String>();
        ingredients.add("chicken");
        ingredients.add("salsa");
        ingredients.add("sour cream");
        ingredients.add("eggs");
        String input = "2. Name: taco       \t Price: $4.50       \t Ingredients: chicken, salsa, sour cream, eggs ";
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

    /*
    @Test
    public void parseFileTestA() throws InvalidLoadException, FileNotFoundException {
        String name = "pizza";
        double price = 3.00;
        ArrayList<String> ingredients = new ArrayList<String>();
        ingredients.add("cheese");
        ingredients.add("sauce");
        HashMap dishMapA = new HashMap<String, Dish>();
        dishMapA.put("pizza", new Dish(name, ingredients, price));
        String fp = "/Users/ganeshmuthu/Documents/cs2113/tp/sampleReportA.txt";
        LoadDish ld = LoadDish.getInstance(fp);
        Menu m = new Menu();
        m.setDishMap(dishMapA);
        assertEquals(m.getDishMap(), ld.readDishes().getDishMap());
    }
     */


}
