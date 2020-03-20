package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;
import seedu.duke.commands.AddCommand;
import seedu.duke.data.Budget;
import seedu.duke.data.ShoppingList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {

    ShoppingList items = new ShoppingList();
    Command command;

    @Test
    void testAdd_ItemDescriptionOnly_Success() {
        items.clearList();
        command = new AddCommand("apple", 0.0);
        command.setData(items, null);
        command.execute();
        String expectedFeedback = System.lineSeparator() + "Added this item: " + items.getItem(0);
        assertEquals(expectedFeedback, command.feedbackToUser);
    }

    @Test
    void testAdd_ItemPriceAndDescription_Success() {
        items.clearList();
        command = new AddCommand("banana", 5.50);
        command.setData(items, null);
        command.execute();
        String expectedFeedback = System.lineSeparator() + "Added this item: " + items.getItem(0)
                + String.format("\nNOTE: You have exceeded your budget by %.2f",items.getTotalCost());
        assertEquals(expectedFeedback, command.feedbackToUser);
    }

    @Test
    public void testAdd_exceptionThrown() {
        String price = "5.00";
        double prices = Double.parseDouble(price);
        command = new AddCommand(null,prices);
        command.setData(items, null);
        command.execute();
        String expectedFeedback3 = System.lineSeparator()
                + "Error! Description of an item cannot be empty."
                + "\nExample: ADD 1 i/apple p/4.50";
        assertEquals(expectedFeedback3, command.feedbackToUser);

    }


}