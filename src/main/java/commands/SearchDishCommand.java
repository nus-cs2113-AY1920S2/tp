package commands;

import dish.Dish;
import menu.Menu;

import java.util.HashMap;

public class SearchDishCommand extends Menu {

    /**
     * Searches menu for dish names containing keyword
     * @param keyword keyword to search for
     */
    public static HashMap<String, Dish> searchDish(String keyword) {
         HashMap<String, Dish> matchingDishes = new HashMap<String, Dish>();
        for (String name: Menu.getDishMap().keySet()) {
            if (name.contains(keyword)) {
                matchingDishes.put(name, Menu.getDishMap().get(name));
            }
        }
        return matchingDishes;
    }

}
