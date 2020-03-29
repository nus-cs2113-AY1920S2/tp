package menu;

import dish.Dish;
import java.util.HashMap;

public class Menu {

    /**
     * Hashmap of all dishes on menu.
     */
    private static HashMap<String, Dish> dishMap;

    /**
     * Constructor for Menu.
     */
    public Menu() {
        dishMap = new HashMap<String, Dish>();
    }

    /**
     * Return's dishMap hashmap.
     * @return dishmap hashmap
     */
    public static HashMap<String, Dish> getDishMap() {
        return dishMap;
    }

}
