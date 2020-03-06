package seedu.duke.data;

import java.util.ArrayList;

public class ShoppingList {
    private static ArrayList<Item> items = new ArrayList<>();
    private static double budget;
    private static double cost;

    public ShoppingList(){
    }

    public static Item markAsBought(int index) throws ItemNotFoundException {
        if (index >= items.size()) {
            throw new ItemNotFoundException;
        }
        Item itemBought = items.get(index);
        itemBought.markAsBought();
        return itemBought;
    }

}
