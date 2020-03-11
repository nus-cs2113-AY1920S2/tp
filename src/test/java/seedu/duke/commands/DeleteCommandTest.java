package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.data.Item;
import seedu.duke.data.ShoppingList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteCommandTest {

    ShoppingList things;
    Item firstAddition;

    @BeforeEach
    void setUp() {
        things = new ShoppingList();
        firstAddition = new Item("apple",3.0);
        things.add(firstAddition);
    }

    @Test
    void execute_negativeIndex() {
        Command command = new DeleteCommand(-1);
        command.setData(things,null);
        CommandResult result = command.execute();
        assertEquals(System.lineSeparator()
                + "Please enter a valid index within the bounds",result.feedbackToUser);

    }

    @Test
    void execute_zeroIndex() {
        Command command = new DeleteCommand(0);
        command.setData(things,null);
        CommandResult result = command.execute();
        assertEquals(System.lineSeparator()
                + "Please enter a valid index within the bounds",result.feedbackToUser);

    }

    @Test
    void execute_tooLargeIndex() {
        Command command = new DeleteCommand(100);
        command.setData(things,null);
        CommandResult result = command.execute();
        assertEquals(System.lineSeparator()
                + "Please enter a valid index within the bounds",result.feedbackToUser);

    }

}