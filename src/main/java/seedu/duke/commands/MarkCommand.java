package seedu.duke.commands;

import seedu.duke.data.Item;
import seedu.duke.data.ShoppingList;
import java.util.ArrayList;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "MARK";
    public static final String SUCCESS_MESSAGE = System.lineSeparator()
                                    + "Yes! I've marked this item as bought:"
                                    + System.lineSeparator() + "%s"
                                    + System.lineSeparator();
    public static final String FAIL_MESSAGE = System.lineSeparator()
                                    + "Oh No! This item does not exist in the list"
                                    + System.lineSeparator();
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks an item as bought."
                                    + System.lineSeparator() + "|| Parameters: MARK [INDEX]"
                                    + System.lineSeparator() + "|| Example: MARK 1"
                                    + System.lineSeparator();

    private int indexOfItem;

    public MarkCommand(int index) {
        this.indexOfItem = index;
    }

    @Override
    public void execute() {
        try {
            Item markItem = items.markAsBought(indexOfItem);
            feedbackToUser = String.format(SUCCESS_MESSAGE, markItem);
        } catch (IndexOutOfBoundsException e) {
            feedbackToUser = FAIL_MESSAGE;
        }
    }
}
