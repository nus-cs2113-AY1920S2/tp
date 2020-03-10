package seedu.duke.data;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.commands.AddCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {

    ShoppingList items = new ShoppingList();
    Command command = new Command();

    @Test
    void testAdd_ItemDescriptionOnly_Success() {
        command = new AddCommand("apples", 0.0);
        CommandResult result = command.execute();
        String expectedFeedback = "Added this item: " + items.getList().get(0).toString();
        assertEquals(expectedFeedback, result.feedbackToUser);
    }

    @Test
    void testAdd_ItemPriceAndDescription_Success() {
        command = new AddCommand("banana", 5.50);
        CommandResult result = command.execute();
        String expectedFeedback = "Added this item: " + items.getList().get(1).toString();
        assertEquals(expectedFeedback, result.feedbackToUser);
    }

    @Test
    public void testEdit_exceptionThrown() {
        CommandResult result3 = null;
        try {
            String price = "5.00";
            double prices = Double.parseDouble(price);
            command = new AddCommand(null,prices);
            result3 = command.execute();
        } catch (NullPointerException | IndexOutOfBoundsException | NumberFormatException e) {
            String expectedFeedback3 = "Error! Description of an item cannot be empty."
                    + "\nExample: ADD i/apple p/4.50";
            assert result3 != null;
            assertEquals(expectedFeedback3, result3.feedbackToUser);
        }
    }


}