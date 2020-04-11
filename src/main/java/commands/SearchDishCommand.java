package commands;

import dish.Dish;
import exceptions.InvalidSearchDishCommandException;
import exceptions.KeywordMissingException;
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
        try {
            checkFormat(input);
            if (Menu.getDishMap().isEmpty()) {
                System.out.println("The menu is currently empty!");
            } else {
                String keyword = parseKeyword(input);
                generateMatches(matchingDishes, keyword);
                if (matchingDishes.keySet().isEmpty()) {
                    System.out.println("There are no dishes that match the keyword " + keyword + "!");
                } else {
                    System.out.println("Here are the dishes that match your keyword:");
                    for (String name: matchingDishes.keySet()) {
                        generateIngredientList(matchingDishes, name);
                    }
                }
            }
        } catch (KeywordMissingException e) {
            System.out.println("You must include a keyword!");
            System.out.println("The correct format is: search dish; k/KEYWORD;");
        } catch (InvalidSearchDishCommandException | StringIndexOutOfBoundsException e) {
            System.out.println("Invalid search dish command!");
            System.out.println("The correct format is: search dish; k/KEYWORD;");
        }
        return matchingDishes;
    }

    /**
     * Generate matching dishes.
     * @param matchingDishes hashMap of matching dishes
     * @param keyword keyword to search for
     */
    private static void generateMatches(HashMap<String, Dish> matchingDishes, String keyword) {
        for (String name: Menu.getDishMap().keySet()) {
            if (name.contains(keyword)) {
                matchingDishes.put(name, Menu.getDishMap().get(name));
            }
        }
    }

    /**
     * Generates ingredient list for a dish, then prints dish.
     * @param matchingDishes hashMap of dishes that match keyword
     * @param name name of dish
     */
    private static void generateIngredientList(HashMap<String, Dish> matchingDishes, String name) {
        String ingredientList = "";
        for (String str: matchingDishes.get(name).getIngredients()) {
            ingredientList += str + ", ";
        }
        ingredientList = ingredientList.substring(0, ingredientList.length() - 2);
        System.out.println("Name: " + name + "     Price: $"
                + matchingDishes.get(name).getPrice() + "     Ingredients: " + ingredientList);
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

    /**
     * Check format for delete dish command.
     * @param input input string
     * @throws KeywordMissingException exception for missing keyword
     * @throws InvalidSearchDishCommandException exception for invalid delete format
     */
    public static void checkFormat(String input) throws KeywordMissingException, InvalidSearchDishCommandException {
        if (!input.contains("k/")) {
            throw new KeywordMissingException();
        } else if (input.chars().filter(ch -> ch == ';').count() != 1) {
            throw new InvalidSearchDishCommandException();
        }
    }

}