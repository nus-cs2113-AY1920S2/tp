package seedu.duke.commands;

import seedu.duke.data.Item;
import seedu.duke.data.ShoppingList;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "UNMARK";
    public static final String SUCCESS_DONE = "Yes! I've unmarked this item as bought: "
                                             + System.lineSeparator() + "%s";
    public static final String FAIL_DONE = "Oh No! This item does not exist in the list";
    private int indexOfItem;

    public UnmarkCommand(int index) {
        this.indexOfItem = index;
    }

    @Override
    public CommandResult execute() {
        try {
            Item unmarkItem = ShoppingList.unmarkAsBought(indexOfItem);
            return new CommandResult(String.format(SUCCESS_DONE, unmarkItem));
        } catch (ItemNotFoundException Inf) {
            return new CommandResult(FAIL_DONE);
        }
    }
}
