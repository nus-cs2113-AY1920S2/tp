package commands;

import dish.Dish;
import menu.Menu;

import java.util.HashMap;

public class SearchDishCommand extends Menu {


    /**
     * Searches menu for dish names containing keyword.
     * @param input input string containing keyword
     * @return matchingDishes HashMap
     */
    public static HashMap<String, Dish> searchDish(String input) {
        HashMap<String, Dish> matchingDishes = new HashMap<String, Dish>();
        String keyword = parseKeyword(input);
        for (String name: Menu.getDishMap().keySet()) {
            if (name.contains(keyword)) {
                matchingDishes.put(name, Menu.getDishMap().get(name));
            }
        }
        System.out.println("Here are the dishes that match your keyword:");
        for (String name: matchingDishes.keySet()) {
            String ingredientList = "";
            for (String str: matchingDishes.get(name).getIngredients()) {
                ingredientList += str + ", ";
            }
            ingredientList = ingredientList.substring(0, ingredientList.length() - 2);
            System.out.println("Name: " + name + "; Ingredients: " + ingredientList + "; Price: $" + matchingDishes.get(name).getPrice()+ ";");
        }
        return matchingDishes;
    }

    /**
     * Parses keyword from input string.
     * @param input input string
     * @return keyword
     */
    public static String parseKeyword(String input) {
        input = input.substring(input.indexOf("k/") + 2);
        input = input.substring(0, input.indexOf(";"));
        return input;
    }

}
