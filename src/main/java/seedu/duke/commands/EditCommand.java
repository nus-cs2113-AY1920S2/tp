package seedu.duke.commands;

import seedu.duke.data.Item;
import seedu.duke.data.ShoppingList;

public class EditCommand extends Command {

    public static final String COMMAND_WORD = "EDIT";
    public static final String MESSAGE_SUCCESS = System.lineSeparator() + "The item has been updated to: %s";
    public static final String MESSAGE_FAILURE = System.lineSeparator() + "OOPS! You have entered an invalid index no. ..";

    private int indexOfItem;
    private String newDescription;
    private String newPrice;

    public EditCommand(int index, String description, String price) {
        this.indexOfItem = index;
        this.newDescription = description;
        this.newPrice = price;

    }

    @Override
    public CommandResult execute() {
        try {
            indexOfItem -= 1;
            Item item = ShoppingList.getItem(indexOfItem);
            if(newDescription == null && newPrice != null){ //only edit price
                 item.setPrice(Double.parseDouble(newPrice));
            }
            else if(newPrice == null && newDescription != null){ //only edit description
                item.setDescription(newDescription);
            }
            else if(newPrice != null && newDescription != null){ //edit both price and description
                item.setDescription(newDescription);
                item.setPrice(Double.parseDouble(newPrice));
            }
            return new CommandResult(String.format(MESSAGE_SUCCESS, item.toString()));
        } catch (NullPointerException | NumberFormatException e) {
            return new CommandResult(MESSAGE_FAILURE);
        }
    }
}
