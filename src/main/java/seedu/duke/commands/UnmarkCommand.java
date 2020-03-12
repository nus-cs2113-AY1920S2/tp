package seedu.duke.commands;

import seedu.duke.data.Item;
import seedu.duke.data.ShoppingList;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "UNMARK";
    public static final String SUCCESS_MESSAGE = System.lineSeparator()
                                    + "Yes! I've unmarked this item as bought: "
                                    + System.lineSeparator() + "%s"
                                    + System.lineSeparator();
    public static final String FAIL_MESSAGE = System.lineSeparator()
                                    + "Oh No! This item does not exist in the list"
                                    + System.lineSeparator();
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unmarks a bought item."
                                    + System.lineSeparator() + "|| Parameters: UNMARK [INDEX]"
                                    + System.lineSeparator() + "|| UNMARK 1" + System.lineSeparator();

    private int indexOfItem;

    public UnmarkCommand(int index) {
        this.indexOfItem = index;
    }

    @Override
    public CommandResult execute() {
        try {
            Item unmarkItem = items.unmarkAsBought(indexOfItem);
            return new CommandResult(String.format(SUCCESS_MESSAGE, unmarkItem));
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(FAIL_MESSAGE);
        }
    }
}
