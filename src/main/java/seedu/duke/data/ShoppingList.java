package seedu.duke.data;

import seedu.duke.commands.Command;
import seedu.duke.commands.IncorrectCommand;
import seedu.duke.commands.MarkCommand;
import seedu.duke.commands.UnmarkCommand;

import java.util.ArrayList;

public class ShoppingList {
    private static ArrayList<Item> items = new ArrayList<>();
    private static double budget;
    private static double cost;
    private static Command newCommand;

    public ShoppingList(){
    }

    /** Returns the item at the specified index.
     *
     * @param index Index of the item requested.
     * @return Item at the specified index.
     */
    public static Item getItem(int index) {
        return items.get(index);
    }


    /**
     * Marks item in index as bought
     * @param index index of item to mark
     * @return item that is marked
     */
    public static Item markAsBought(int index) {
        if (index >= items.size()) {
            newCommand = new IncorrectCommand(MarkCommand.FAIL_MESSAGE);
        }
        Item itemBought = items.get(index);
        itemBought.markAsBought();
        return itemBought;
    }


    /**
     * Unmarks item in index as bought
     * @param index index of item to unmark
     * @return item that is unmarked
     */
    public static Item unmarkAsBought(int index) {
        if (index >= items.size()) {
            newCommand = new IncorrectCommand(UnmarkCommand.FAIL_MESSAGE);
        }
        Item itemNotBought = items.get(index);
        itemNotBought.unmarkAsBought();
        return itemNotBought;
    }

    /**
     * Removes an item at the specified index.
     *
     * @param index Index of the item to be removed.
     */
    public static void deleteItem(int index) {
        Item unwantedItem = items.get(index);
        items.remove(unwantedItem);
    }

    public static void add (Item toAdd){

        items.add(toAdd);
    }

}
