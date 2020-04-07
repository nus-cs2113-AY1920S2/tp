package commands;

import exceptions.DishIngredientsMissingException;
import exceptions.DishNameMissingException;
import exceptions.DishPriceMissingException;
import exceptions.InvalidAddDishCommandException;
import exceptions.InvalidDeleteDishCommandException;
import menu.Menu;

public class DeleteDishCommand extends Menu {

    /**
     * Removes dish from menu.
     * @param input name of dish to remove
     */
    public static void deleteDish(String input) {
        try {
            checkFormat(input);
            String name = AddDishCommand.parseName(input);
            if (Menu.getDishMap().isEmpty()) {
                System.out.println("There are no dishes to delete!");
            } else if (!Menu.getDishMap().containsKey(name)) {
                System.out.println("Dish " + name + " does not exist!");
            } else {
                Menu.getDishMap().remove(name);
                System.out.println("Dish " + name + " successfully remove!");
            }
        } catch (DishNameMissingException e) {
            System.out.println("Must include name of dish to delete!");
        } catch (InvalidDeleteDishCommandException | StringIndexOutOfBoundsException e) {
            System.out.println("Invalid delete dish command!");
            System.out.println("The correct format is: delete dish; n/NAME;");
        }

    }

     public static void checkFormat(String input) throws DishNameMissingException, InvalidDeleteDishCommandException {
        if (!input.contains("n/")) {
            throw new DishNameMissingException();
        } else if (input.chars().filter(ch -> ch == ';').count() != 2) {
            throw new InvalidDeleteDishCommandException();
        }
    }

}
