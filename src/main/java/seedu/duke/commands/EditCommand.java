package seedu.duke.commands;

import seedu.duke.data.Item;

import java.util.logging.Level;
import java.util.logging.Logger;

public class EditCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final String COMMAND_WORD = "EDIT";
    public static final String EXCEED_WARNING = "\nNOTE: You have exceeded your budget by %.2f";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the specified item in the list."
            + System.lineSeparator() + "|| Parameters: EDIT [INDEX] i/[DESCRIPTION] p/[PRICE]"
            + System.lineSeparator() + "|| Example 1: EDIT 1 i/apple p/3.00"
            + System.lineSeparator() + "|| Example 2: EDIT 1 i/apple"
            + System.lineSeparator() + "|| Example 3: EDIT 1 p/6.00" + System.lineSeparator();
    public static final String MESSAGE_SUCCESS = System.lineSeparator()
            + "The item has been updated to: %s";
    public static final String MESSAGE_FAILURE_NOT_IN_LIST = System.lineSeparator()
            + "OOPS! I don't have that item in the list yet. Try again? :D";
    public static final String MESSAGE_FAILURE_PRICE_INCORRECT_FORMAT = System.lineSeparator()
            + "OOPS! I couldn't process that because price has to be a number"
            + ", silly!" + System.lineSeparator() + "|| Example: EDIT 2 i/apple p/2.00";

    private int indexOfItem;
    private String newDescription;
    private String newPrice;

    /**
     * Creates an EditCommand and initialises the item index,
     * description and price that needs to be edited.
     *
     * @param index       index of item to change
     * @param description new description of item to change
     * @param price       new price of item to change
     */
    public EditCommand(int index, String description, String price) {
        this.indexOfItem = index;
        this.newDescription = description;
        this.newPrice = price;
        LOGGER.log(Level.INFO, "(Edit command) User entered index: " + indexOfItem
                + " description: '" + newDescription + "' price: " + newPrice);
    }

    @Override
    public void execute() {
        try {
            indexOfItem -= 1;
            Item item = items.getItem(indexOfItem);

            if (newDescription == null && newPrice != null) { //only edit price
                item.setPrice(Double.parseDouble(newPrice));
                assert item.getPrice() == Double.parseDouble(newPrice);
            } else if (newPrice == null && newDescription != null) { //only edit description
                item.setDescription(newDescription);
                assert item.getDescription() == newDescription;

            } else if (newPrice != null && newDescription != null) { //edit both price and description
                item.setDescription(newDescription);
                item.setPrice(Double.parseDouble(newPrice));
                assert item.getDescription() == newDescription;
                assert item.getPrice() == Double.parseDouble(newPrice);
            }

            LOGGER.log(Level.INFO, "(Edit command)  Item has been updated to: " + item.toString());
            feedbackToUser = String.format(MESSAGE_SUCCESS, item.toString());
            double remainder = myBudget.getRemainingBudget(items.getTotalCost());
            if (remainder < 0) {
                feedbackToUser += String.format(EXCEED_WARNING,(-1) * remainder);
            }

        } catch (NullPointerException | IndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING, "(Edit command)  Item to edit is not found in list");
            feedbackToUser = MESSAGE_FAILURE_NOT_IN_LIST;
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "(Edit command) Invoked with invalid price format: '"
                    + this.newPrice + "'");
            feedbackToUser = MESSAGE_FAILURE_PRICE_INCORRECT_FORMAT;
        }
    }
}
