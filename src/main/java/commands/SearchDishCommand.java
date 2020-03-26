package commands;

import dish.Dish;
import menu.Menu;

import java.util.HashMap;

public class SearchDishCommand extends Menu {

    /**
     * A hashmap containing dishes with matching names
     */
    private HashMap<String, Dish> matchingDishes = new HashMap<String, Dish>();

    /**
     * Searches menu for dish names containing keyword
     * @param keyword keyword to search for
     */
    public void searchDish(String keyword) {
        for (String name: Menu.getDishMap().keySet()) {
            if (name.contains(keyword)) {
                matchingDishes.put(name, Menu.getDishMap().get(name));
            }
        }
    }

}
