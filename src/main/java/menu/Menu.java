package menu;

import com.sun.tools.javac.Main;
import dish.Dish;

import java.util.ArrayList;
import java.util.HashMap;

public class Menu {

    /**
     * Hashmap of all dishes on menu.
     */
     private static HashMap<String, Dish> dishMap;

    /**
     * Constructor for Menu
     */
    public Menu() {
        dishMap = new HashMap<String, Dish>();
    }

    /**
     * Prints out dishes from hashmap
     * @param hm hashmap to print from
     */
    public void printDishes() {
        for (String name: dishMap.keySet()) {
            String ingredientList = "";
            for (String str: dishMap.get(name).getIngredients()) {
                ingredientList += str + ", ";
            }
            ingredientList = ingredientList.substring(0, ingredientList.length() - 2);
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
