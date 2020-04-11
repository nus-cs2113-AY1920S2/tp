package report;

import dish.Dish;
import exceptions.InvalidLoadException;
import menu.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadDish {

    private String fp;
    private Menu menu;

    private static LoadDish ld;

    /**
     * Constructor for LoadDish.
     * @param fp filepath
     */
    private LoadDish(String fp) {
        this.fp = fp;
        this.menu = new Menu();
    }

    /**
     * Singleton for LoadDish.
     * @param fp filepath
     * @return LoadDish object
     */
    public static LoadDish getInstance(String fp) {
        if (ld == null) {
            ld = new LoadDish(fp);
        }
        return ld;
    }

    /**
     * Main method for reading dishes.
     * @return a menu
     * @throws InvalidLoadException exception for an invalid load command
     * @throws FileNotFoundException exception for file not found
     */
    public Menu readDishes() throws InvalidLoadException, FileNotFoundException {
        File f = new File(fp);
        if (!f.exists()) {
            throw new InvalidLoadException();
        }
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String cl = sc.nextLine();
            if (cl.equals("Menu Items")) {
                if (sc.hasNext()) {
                    convertDishLines(sc);
                }
            }
        }
        return menu;
    }

    /**
     * Convert dish lines to a dishes.
     * @param sc scanner
     */
    private void convertDishLines(Scanner sc) {
        sc.nextLine();
        while (sc.hasNext()) {
            String dish = sc.nextLine();
            if (!dish.isEmpty() && dish.contains("Name: ")) {
                processDishLine(dish);
            }
        }
    }

    /**
     * Convert an input dish line to a dish in Menu.
     * @param inputDish input dish line
     */
    private void processDishLine(String inputDish) {
        String name = parseName(inputDish);
        ArrayList<String> ingredients = parseIngredients(inputDish);
        double price = parsePrice(inputDish);
        Dish d = new Dish(name, ingredients, price);
        Menu.getDishMap().put(name, d);
    }

    /**
     * Parses ingredients from input string from load file.
     * @param input string of ingredients
     * @return ArrayList of ingredients
     */
    public static ArrayList<String> parseIngredients(String input) {
        ArrayList<String> ingredients = new ArrayList<String>();
        input = input.substring(input.indexOf("Ingredients:") + 13);
        String[] splitString = input.split(",");
        for (String str: splitString) {
            ingredients.add(str.trim());
        }
        return ingredients;
    }

    /**
     * Parses name from input string for load file.
     * @param input input string
     * @return name of dish
     */
    public static String parseName(String input) {
        input = input.substring(input.indexOf("Name:") + 5);
        input = input.substring(0, input.indexOf("Price:"));
        input = input.trim();
        return input;
    }

    /**
     * Parse price from input string for load file.
     * @param input input string
     * @return price of dish
     */
    public static double parsePrice(String input) {
        input = input.substring(input.indexOf("Price: $") + 8);
        input = input.substring(0, input.indexOf("Ingredients:"));
        input = input.trim();
        return Double.parseDouble(input);
    }
}
