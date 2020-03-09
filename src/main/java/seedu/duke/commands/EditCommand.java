package seedu.duke.commands;

import seedu.duke.data.Item;
import seedu.duke.data.ShoppingList;

public class EditCommand extends Command {

    public static final String COMMAND_WORD = "EDIT";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the specified item in the list."
            + System.lineSeparator() + "|| Parameters: EDIT [INDEX] i/[DESCRIPTION] p/[PRICE]"
            + System.lineSeparator() + "|| Example 1: EDIT 1 i/apple p/3.00"
            + System.lineSeparator() + "|| Example 2: EDIT 1 i/apple"
            + System.lineSeparator() + "|| Example 3: EDIT 1 p/6.00" + System.lineSeparator();
    public static final String MESSAGE_SUCCESS = System.lineSeparator()
                                                 + "The item has been updated to: %s";
    public static final String MESSAGE_FAILURE = System.lineSeparator()
                                                 + "OOPS! I don't have that item in the list yet. Try again? :D";

    private int indexOfItem;
    private String newDescription;
    private String newPrice;

    /**
     * Creates an EditCommand and initialises the item index,
     * description and price that needs to be edited.
     *
     * @param index index of item to change
     * @param description new description of item to change
     * @param price new price of item to change
     */
    public EditCommand(int index, String description, String price) {
        this.indexOfItem = index;
        this.newDescription = description;
        this.newPrice = price;

    }

    @Override
    public CommandResult execute() {
        try {
            indexOfItem -= 1;
            Item item = items.getItem(indexOfItem);
            if (newDescription == null && newPrice != null) { //only edit price
                item.setPrice(Double.parseDouble(newPrice));
            } else if (newPrice == null && newDescription != null) { //only edit description
                item.setDescription(newDescription);
            } else if (newPrice != null && newDescription != null) { //edit both price and description
                item.setDescription(newDescription);
                item.setPrice(Double.parseDouble(newPrice));
            }
            return new CommandResult(String.format(MESSAGE_SUCCESS, item.toString()));
        } catch (NullPointerException | NumberFormatException | IndexOutOfBoundsException e) {
            return new CommandResult(MESSAGE_FAILURE);
        }
    }
}
