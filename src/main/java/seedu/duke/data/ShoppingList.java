package seedu.duke.data;

import java.util.ArrayList;

public class ShoppingList {
    private static ArrayList<Item> items = new ArrayList<>();
    private static double budget;
    private static double cost;

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
