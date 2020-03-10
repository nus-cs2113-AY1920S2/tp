package seedu.duke.data;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;
import seedu.duke.commands.MarkCommand;
import seedu.duke.data.ShoppingList;
import seedu.duke.commands.CommandResult;
import seedu.duke.commands.EditCommand;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarkCommandTest {
    ShoppingList items = new ShoppingList();
    Command command = new Command();

    @BeforeEach
    void setUp() {
        items.add(new Item("apple", 2.0));
        items.add(new Item("donut", 3.0));
        items.add(new Item("banana", 4.5));
        command.setData(items, null);
    }

    @Test
    void testMark_unmarkedItem_Success() {
        command.setData(items, null);
        command = new MarkCommand(1);
        CommandResult result = command.execute();
        String expectedFeedback = System.lineSeparator()
                + "Yes! I've marked this item as bought: "
                + System.lineSeparator() + items.getList().get(1).toString()
                + System.lineSeparator();
        assertEquals(expectedFeedback, result.feedbackToUser);
    }

    @Test
    void testMark_outOfLimitIndex_Success() {
        command.setData(items, null);
        command = new MarkCommand(10);
        CommandResult result1 = command.execute();
        String expectedFeedback1 = MarkCommand.FAIL_MESSAGE;
        assertEquals(expectedFeedback1, result1.feedbackToUser);
    }

    @Test
    void testMark_negativeIndex_Success() {
        command.setData(items, null);
        command = new MarkCommand(-5);
        CommandResult result2 = command.execute();
        String expectedFeedback2 = MarkCommand.FAIL_MESSAGE;
        assertEquals(expectedFeedback2, result2.feedbackToUser);
    }
}
