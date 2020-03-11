package seedu.duke.commands;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.commands.*;
import seedu.duke.data.Item;
import seedu.duke.data.ShoppingList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnmarkCommandTest {
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
    void testUnmark_MarkedItem_Success() {
        command.setData(items, null);
        command = new MarkCommand(1);
        command = new UnmarkCommand(1);
        CommandResult result = command.execute();
        String expectedFeedback = System.lineSeparator()
                + "Yes! I've unmarked this item as bought: "
                + System.lineSeparator() + items.getList().get(1).toString()
                + System.lineSeparator();
        assertEquals(expectedFeedback, result.feedbackToUser);
    }

    @Test
    void testUnmark_outOfLimitIndex_Success() {
        CommandResult result1 = null;
        try {
            command.setData(items, null);
            command = new UnmarkCommand(10);
            result1 = command.execute();
        } catch (IndexOutOfBoundsException e) {
            String expectedFeedback1 = UnmarkCommand.FAIL_MESSAGE;
            assertEquals(expectedFeedback1, result1.feedbackToUser);
        }
    }

    @Test
    void testUnmark_negativeIndex_Success() {
        command.setData(items, null);
        command = new UnmarkCommand(-5);
        CommandResult result2 = command.execute();
        String expectedFeedback2 = UnmarkCommand.FAIL_MESSAGE;
        assertEquals(expectedFeedback2, result2.feedbackToUser);
    }
}
