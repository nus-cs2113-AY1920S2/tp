package commands;

import menu.Menu;

public class ListDishCommand extends Menu {

    /**
     * Prints out dishes from hashmap.
     */
    public static void printDishes() {
        for (String name: Menu.getDishMap().keySet()) {
            String ingredientList = "";
            for (String str: Menu.getDishMap().get(name).getIngredients()) {
                ingredientList += str + ", ";
            }
            ingredientList = ingredientList.substring(0, ingredientList.length() - 2);
            System.out.println("Name: " + name + "; Ingredients: " + ingredientList);
        }
    }
}
