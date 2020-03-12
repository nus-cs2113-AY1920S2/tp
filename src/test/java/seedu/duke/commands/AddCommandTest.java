package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.commands.AddCommand;
import seedu.duke.data.Budget;
import seedu.duke.data.ShoppingList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {

    ShoppingList items = new ShoppingList();
    Command command;

    @Test
    void testAdd_ItemDescriptionOnly_Success() {
        command = new AddCommand("apples", 0.0);
        command.setData(items, null);
        CommandResult result = command.execute();
        String expectedFeedback = "Added this item: " + items.getItem(0);
        assertEquals(expectedFeedback, result.feedbackToUser);
    }

    @Test
    void testAdd_ItemPriceAndDescription_Success() {
        command = new AddCommand("banana", 5.50);
        command.setData(items, null);
        CommandResult result = command.execute();
        String expectedFeedback = "Added this item: " + items.getItem(1);
        assertEquals(expectedFeedback, result.feedbackToUser);
    }

    @Test
    public void testEdit_exceptionThrown() {
        CommandResult result3 = null;
        String price = "5.00";
        double prices = Double.parseDouble(price);
        command = new AddCommand(null,prices);
        command.setData(items, null);
        result3 = command.execute();
        String expectedFeedback3 = "Error! Description of an item cannot be empty."
                + "\nExample: ADD i/apple p/4.50";
        assertEquals(expectedFeedback3, result3.feedbackToUser);

    }


}