package seedu.duke.data;

import java.util.ArrayList;

public class ShoppingList {
    private static ArrayList<Item> items = new ArrayList<>();
    private static double budget;
    private static double cost;

    public ShoppingList(){
    }

    /**
     * @return copy of task list
     */
    public static ArrayList<Item> copy() {
        // Clone the list
        return (new ArrayList<>(items));
    }

    public static Item retrieve(int targetIndex) {
        return items.get(targetIndex);
    }




}
