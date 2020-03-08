package seedu.duke.commands;

import seedu.duke.data.Item;
import seedu.duke.data.ShoppingList;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "UNMARK";
    public static final String SUCCESS_MESSAGE = "Yes! I've unmarked this item as bought: "
            + System.lineSeparator() + "%s";
    public static final String FAIL_MESSAGE = "Oh No! This item does not exist in the list";
    private int indexOfItem;

    public UnmarkCommand(int index) {
        this.indexOfItem = index;
    }

    @Override
    public CommandResult execute() {
        Item unmarkItem = ShoppingList.unmarkAsBought(indexOfItem);
        return new CommandResult(String.format(SUCCESS_MESSAGE, unmarkItem));
    }
}
