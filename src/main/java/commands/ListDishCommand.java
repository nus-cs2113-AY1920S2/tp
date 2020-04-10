package commands;

import menu.Menu;

public class ListDishCommand extends Menu {

    /**
     * Prints out dishes from hashmap.
     */
    public static void printDishes() {
        if (Menu.getDishMap().keySet().isEmpty()) {
            System.out.println("There are no dishes to list!");
        } else {
            for (String name: Menu.getDishMap().keySet()) {
                String ingredientList = "";
                for (String str: Menu.getDishMap().get(name).getIngredients()) {
                    ingredientList += str + ", ";
                }
                ingredientList = ingredientList.substring(0, ingredientList.length() - 2);
                double price = Menu.getDishMap().get(name).getPrice();
                System.out.println("Name: " + name + "     Price: $" + price + "     Ingredients: " + ingredientList);
            }
            System.out.println("Dishes successfully listed.");
        }
    }
}