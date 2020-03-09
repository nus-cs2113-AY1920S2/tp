package seedu.duke.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.commands.EditCommand;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditCommandTest {

    ShoppingList items = new ShoppingList();
    Command command = new Command();


    @BeforeEach
    void setUp() {
        items.add(new Item("apple", 2.0));
        items.add(new Item("donut", 3.0));
        items.add(new Item("banana", 4.50));
        command.setData(items, null);
    }

    @Test
    void testEdit_ItemDescriptionOnly_Success() {

        Command command = new EditCommand(1, "apples", null);
        CommandResult result = command.execute();
        String expectedFeedback = System.lineSeparator() + "The item has been updated to: " + items.getList().get(0).toString();
        assertEquals(expectedFeedback, result.feedbackToUser);

    }

    @Test
    void testEdit_ItemPriceOnly_Success() {
        Command command1 = new EditCommand(2, null, "3.5");
        CommandResult result1 = command1.execute();
        String expectedFeedback1 = System.lineSeparator() + "The item has been updated to: " + items.getList().get(1).toString();
        assertEquals(expectedFeedback1, result1.feedbackToUser);
    }

    @Test
    void testEdit_ItemPriceAndDescription_Success() {
        Command command2 = new EditCommand(3, "banana", "5.00");
        CommandResult result2 = command2.execute();
        String expectedFeedback2 = System.lineSeparator() + "The item has been updated to: " + items.getList().get(2).toString();
        assertEquals(expectedFeedback2, result2.feedbackToUser);
    }

    @Test
    public void testEdit_exceptionThrown() {
        CommandResult result3 = null;
        try{
            Command command3 = new EditCommand(0, "banana", "5.00");
            result3 = command3.execute();
        }catch (NullPointerException | IndexOutOfBoundsException | NumberFormatException e){
            String expectedFeedback3 = System.lineSeparator() + "OOPS! You have entered an invalid index no. ..";
            assert result3 != null;
            assertEquals(expectedFeedback3, result3.feedbackToUser);
        }
    }


}