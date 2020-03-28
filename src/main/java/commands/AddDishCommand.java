package commands;

import dish.Dish;
import menu.Menu;

import java.util.ArrayList;

public class AddDishCommand extends Menu {
    /**
     * Adds a dish to the menu based on the input string.
     * @param input input string that include dish name and ingredients in no specific order
     */
    public static void addDish(String input) {
        ArrayList<String> ingredients = parseIngredients(input);
        String name = parseName(input);
        Double price = parsePrice(input);
        Dish d = new Dish(name, ingredients, price);
        Menu.getDishMap().put(name, d);
    }

    /**
     * Parses ingredients from string.
     * @param input string of ingredients
     * @return ArrayList of ingredients
     */
    public static ArrayList<String> parseIngredients(String input) {
        ArrayList<String> ingredients = new ArrayList<String>();
        input = input.substring(input.indexOf("i/") + 2);
        input = input.substring(0, input.indexOf(";"));
        String[] splitString = input.split(",");
        for (String str: splitString) {
            ingredients.add(str.trim());
        }
        return ingredients;
    }

    /**
     * Parses name from input string.
     * @param input input string
     * @return name of dish
     */
    public static String parseName(String input) {
        input = input.substring(input.indexOf("n/") + 2);
        input = input.substring(0, input.indexOf(";"));
        return input;
    }

    /**
     * Parse price from input string.
     * @param input input string
     * @return price of dish
     */
    public static double parsePrice(String input) {
        input = input.substring(input.indexOf("p/") + 2);
        input = input.substring(0, input.indexOf(";"));
        return Double.parseDouble(input);
    }

}
