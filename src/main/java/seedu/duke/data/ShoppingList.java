package seedu.duke.data;

import java.util.ArrayList;

public class ShoppingList {
    private static ArrayList<Item> items = new ArrayList<>();
    private static double budget;
    private static double cost;

    public ShoppingList(){
    }

    /**
     * Marks item in index as bought
     * @param index index of item to mark
     * @return item that is marked
     * @throws ItemNotFoundException if the index is more than the size of list
     */
    public static Item markAsBought(int index) throws ItemNotFoundException {
        if (index >= items.size()) {
            throw new ItemNotFoundException();
        }
        Item itemBought = items.get(index);
        itemBought.markAsBought();
        return itemBought;
    }


    /**
     * Unmarks item in index as bought
     * @param index index of item to unmark
     * @return item that is unmarked
     * @throws ItemNotFoundException if the index is more than size of list
     */
    public static Item unmarkAsBought(int index) throws ItemNotFoundException {
        if (index >= items.size()) {
            throw new ItemNotFoundException();
        }
        Item itemNotBought = items.get(index);
        itemNotBought.unmarkAsBought();
        return itemNotBought;
    }

}
