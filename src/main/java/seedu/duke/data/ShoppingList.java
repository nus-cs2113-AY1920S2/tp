package seedu.duke.data;

import java.util.ArrayList;

public class ShoppingList {
    private static ArrayList<Item> items = new ArrayList<>();
    private static double budget;
    private static double cost;

    public ShoppingList(){
    }

    public ArrayList<Item> getList() {
        return items;
    }

    public void clearList() {
        items.clear();
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


}
