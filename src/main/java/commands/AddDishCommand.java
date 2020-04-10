package commands;

import dish.Dish;
import exceptions.DishIngredientsMissingException;
import exceptions.DishNameMissingException;
import exceptions.DishPriceMissingException;
import exceptions.InvalidAddDishCommandException;
import exceptions.InvalidPriceException;
import menu.Menu;

import java.util.ArrayList;

public class AddDishCommand extends Menu {
    /**
     * Adds a dish to the menu based on the input string.
     * @param input input string that include dish name and ingredients in no specific order
     */
    public static void addDish(String input) {
        try {
            checkFormat(input);
            ArrayList<String> ingredients = parseIngredients(input);
            String name = parseName(input);
            if (Menu.getDishMap().containsKey(name)) {
                System.out.println("Dish " + name + " already exists!");
            } else {
                double price = parsePrice(input);
                Dish d = new Dish(name, ingredients, price);
                Menu.getDishMap().put(name, d);
                System.out.println("Dish " + name + " successfully added.");
            }
        } catch (StringIndexOutOfBoundsException | InvalidAddDishCommandException e) {
            System.out.println("Incorrect add dish format!");
            printFormat();
        } catch (InvalidPriceException e) {
            System.out.println("Invalid price! Price must be a number.");
        } catch (DishIngredientsMissingException e) {
            System.out.println("Dish ingredient(s) are missing!");
            printFormat();
        } catch (DishNameMissingException e) {
            System.out.println("Dish name is missing!");
            printFormat();
        } catch (DishPriceMissingException e) {
            System.out.println("Dish price is missing!");
            printFormat();
        }
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
     * Parse price from input string.
     * @param input input string
     * @return price of dish
     * @throws InvalidPriceException exception for invalid price
     */
    public static double parsePrice(String input) throws InvalidPriceException {
        try {
            input = input.substring(input.indexOf("p/") + 2);
            input = input.substring(0, input.indexOf(";"));
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new InvalidPriceException();
        }
    }

    /**
     * Print add dish format.
     */
    private static void printFormat() {
        System.out.println("The correct format is: add dish; n/NAME; "
                + "[i/INGREDIENT1, INGREDIENT2, ...]; p/PRICE;");
    }

    /**
     * Check format of add dish command.
     * @param input input string
     * @throws DishNameMissingException exception for missing dish name
     * @throws DishIngredientsMissingException exception for missing ingredients
     * @throws InvalidAddDishCommandException exception for invalid add dish format
     * @throws DishPriceMissingException exception for missing dish price
     */
    private static void checkFormat(String input) throws DishNameMissingException,
            DishIngredientsMissingException, InvalidAddDishCommandException, DishPriceMissingException {
        if (!input.contains("n/")) {
            throw new DishNameMissingException();
        } else if (!input.contains("i/")) {
            throw new DishIngredientsMissingException();
        } else if (!input.contains("p/")) {
            throw new DishPriceMissingException();
        } else if (input.chars().filter(ch -> ch == ';').count() != 3) {
            throw new InvalidAddDishCommandException();
        }
    }

}