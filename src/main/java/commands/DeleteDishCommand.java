package commands;

import exceptions.DishNameMissingException;
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
                System.out.println("Dish " + name + " does not exist! ");
            } else {
                Menu.getDishMap().remove(name);
                System.out.println("Dish " + name + " successfully removed!");
            }
        } catch (DishNameMissingException e) {
            System.out.println("Must include name of dish to delete!");
        } catch (InvalidDeleteDishCommandException | StringIndexOutOfBoundsException e) {
            System.out.println("Invalid delete dish command!");
            System.out.println("The correct format is: delete dish; n/NAME;");
        }
    }

    /**
     * Check format for delete dish command.
     * @param input input string
     * @throws DishNameMissingException exception for missing name
     * @throws InvalidDeleteDishCommandException exception for invalid delete format
     */
    public static void checkFormat(String input) throws DishNameMissingException, InvalidDeleteDishCommandException {
        if (!input.contains("n/")) {
            throw new DishNameMissingException();
        } else if (input.chars().filter(ch -> ch == ';').count() != 1) {
            throw new InvalidDeleteDishCommandException();
        }
    }

}
