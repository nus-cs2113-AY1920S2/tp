package seedu.duke.data;

import seedu.duke.commands.Command;
import seedu.duke.commands.IncorrectCommand;
import java.util.ArrayList;

public class ShoppingList {

    private static ArrayList<Item> items = new ArrayList<>();
    private static double budget;
    private static double cost;
    private static Command newCommand;

    public ShoppingList() {
    }

    public ArrayList<Item> getList() {
        return items;
    }

    public String compileList(String feedback) {
        int bulletNum = 1;
        String itemLine;
        for (Item item : items) {
            itemLine = bulletNum + ". " + item.toString() + "\n";
            feedback = feedback + itemLine;
            bulletNum++;
        }
        return feedback;
    }

    public void clearList() {
        items.clear();
    }

    /**
     * Marks item in index as bought
     * @param index index of item to mark
     * @return item that is marked
     */
    public static Item markAsBought(int index) {
        if (index >= items.size()) {
            newCommand = new IncorrectCommand("this item does not exist in the list!");
        }
        Item itemBought = items.get(index);
        itemBought.markAsBought();
        return itemBought;
    }

    /**
     * Returns the item at the specified index.
     *
     * @param index Index of the item requested.
     * @return Item at the specified index.
     */
    public static Item getItem(int index) {
        return items.get(index);
    }

    /**
     * Unmarks item in index as bought
     * @param index index of item to unmark
     * @return item that is unmarked
     */
    public static Item unmarkAsBought(int index) {
        if (index >= items.size()) {
            newCommand = new IncorrectCommand("this item does not exist in the list!");
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

    public static void add(Item toAdd) {
        items.add(toAdd);
    }

}
