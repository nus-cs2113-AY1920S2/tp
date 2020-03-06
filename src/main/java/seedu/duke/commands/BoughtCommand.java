package seedu.duke.commands;

import seedu.duke.data.Item;
import seedu.duke.data.ShoppingList;

public class BoughtCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public static final String SUCCESS_DONE = "Yes! I've marked this item as bought: "
                                                + System.lineSeparator() + "%s";
    public static final String FAIL_DONE = "Oh No! This item does not exist in the list";
    private int indexOfItem;

    public BoughtCommand(int index) {
        this.indexOfItem = index;
    }

    @Override
    public CommandResult execute() {
        try {
            Item boughtItem = ShoppingList.markAsBought(indexOfItem);
            return new CommandResult(String.format(SUCCESS_DONE, boughtItem));
        } catch (ItemNotFoundException Inf) {
            return new CommandResult(FAIL_DONE);
        }
    }
}
