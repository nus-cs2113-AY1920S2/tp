package seedu.duke.commands;

import seedu.duke.data.Item;

import java.util.logging.Level;
import java.util.logging.Logger;

public class EditCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final String COMMAND_WORD = "EDIT";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the specified item in the list."
            + System.lineSeparator() + "|| Parameters: EDIT [INDEX] i/[DESCRIPTION] p/[PRICE]"
            + System.lineSeparator() + "|| Example 1: EDIT 1 i/apple p/3.00"
            + System.lineSeparator() + "|| Example 2: EDIT 1 i/apple"
            + System.lineSeparator() + "|| Example 3: EDIT 1 p/6.00" + System.lineSeparator();
    public static final String MESSAGE_SUCCESS = System.lineSeparator()
            + "The item has been updated to: %s";
    public static final String MESSAGE_FAILURE_NOT_IN_LIST = System.lineSeparator()
            + "OOPS! I don't have that item in the list yet. Try again? :D";
    public static final String MESSAGE_FAILURE_PRICE_OR_QUANTITY_INCORRECT_FORMAT = System.lineSeparator()
            + "OOPS! I couldn't process that because price (possibly decimal) and quantity have to be a number"
            + ", silly!" + System.lineSeparator() + "|| Example: EDIT 2 i/apple p/2.00 q/4";

    private int indexOfItem;
    private String newDescription;
    private String newPrice;
    private String newQuantity;

    /**
     * Creates an EditCommand and initialises the item index,
     * description and price that needs to be edited.
     *
     * @param index       index of item to change
     * @param description new description of item to change
     * @param price       new price of item to change
     */
    public EditCommand(int index, String description, String price, String quantity) {
        this.indexOfItem = index;
        this.newDescription = description;
        this.newPrice = price;
        this.newQuantity = quantity;
        LOGGER.log(Level.INFO, "(Edit command) User entered index: " + indexOfItem
                + " description: '" + newDescription + "' price: " + newPrice + "' quantity: " + newQuantity);
    }

    @Override
    public void execute() {
        try {
            indexOfItem -= 1;
            Item item = items.getItem(indexOfItem);
            if (newDescription != null) {
                item.setDescription(newDescription);
                assert item.getDescription() == newDescription;
            }
            if (newPrice != null) {
                item.setPrice(Double.parseDouble(newPrice));
                assert Double.parseDouble(newPrice) >= 0.0;
                assert item.getPrice() == Double.parseDouble(newPrice);
            }
            if (newQuantity != null) {
                item.setQuantity(Integer.parseInt(newQuantity));
                assert Integer.parseInt(newQuantity) >= 0;
                assert item.getQuantity() == Integer.parseInt(newQuantity);
            }

            LOGGER.log(Level.INFO, "(Edit command)  Item has been updated to: " + item.toString());
            feedbackToUser = String.format(MESSAGE_SUCCESS, item.toString());

        } catch (NullPointerException | IndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING, "(Edit command)  Item to edit is not found in list");
            feedbackToUser = MESSAGE_FAILURE_NOT_IN_LIST;
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "(Edit command) Invoked with invalid price/quantity format: '"
                    + this.newPrice + "'");
            feedbackToUser = MESSAGE_FAILURE_PRICE_OR_QUANTITY_INCORRECT_FORMAT;
        }
    }
}
