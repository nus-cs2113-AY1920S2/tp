package menu;

import dish.Dish;

import java.util.ArrayList;
import java.util.HashMap;

public class Menu {

    /**
     * Hashmap of all dishes on menu.
     */
    private static HashMap<String, Dish> dishMap = new HashMap<String, Dish>();

    /**
     * Adds a dish to the menu based on the input string.
     * @param input input string that include dish name and ingredients in no specific order
     */
    public static void addDish(String input) {
        ArrayList<String> ingredients = parseIngredients(input);
        String name = parseName(input);
        Dish d = new Dish(name, ingredients);
        dishMap.put(name, d);
    }

    /**
     * Removes dish from menu.
     * @param name name of dish to remove
     */
    public static void deleteDish(String name) {
        dishMap.remove(name);
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
     * Print's dishes out from dishMap.
     */
    public static void printDishes() {
        for (String name: dishMap.keySet()) {
            String ingredientList = "";
            for (String str: dishMap.get(name).getIngredients()) {
                ingredientList += str + ",";
            }
            ingredientList = ingredientList.substring(0, ingredientList.length() - 1);
            System.out.println("Name: " + name + "; Ingredients: " + ingredientList);
        }
    }

    /**
     * Return's dishMap hashmap.
     * @return dishmap hashmap
     */
    public static HashMap<String, Dish> getDishMap() {
        return dishMap;
    }

}
