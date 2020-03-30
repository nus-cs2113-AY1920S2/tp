package commands;

import dish.Dish;
import menu.Menu;

import java.util.HashMap;

public class SearchDishCommand extends Menu {

    /**
     * Searches menu for dish names containing keyword.
     * @param input input string containing keyword
     */
    public static HashMap<String, Dish> searchDish(String input) {
        String keyword = parseKeyword(input);
        HashMap<String, Dish> matchingDishes = new HashMap<String, Dish>();
        for (String name: Menu.getDishMap().keySet()) {
            if (name.contains(keyword)) {
                matchingDishes.put(name, Menu.getDishMap().get(name));
            }
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
