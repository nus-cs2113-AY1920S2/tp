package commands;

import menu.Menu;

public class DeleteDishCommand extends Menu {

    /**
     * Removes dish from menu.
     * @param name name of dish to remove
     */
    public static void deleteDish(String name) {
        Menu.getDishMap().remove(name);
    }

}
