package seedu.duke.commands;

import seedu.duke.data.Item;

import java.util.logging.Level;
import java.util.logging.Logger;

//@@author jiajuinphoon
public class AddCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final String COMMAND_WORD = "ADD";
    public static final String EXCEED_WARNING = "\nNOTE: You have exceeded your budget by %.2f";
    public static final String SUCCESS_ACK = System.lineSeparator() + "Added this item: " + "%s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an item to the list."
            + System.lineSeparator() + "|| Parameters: ADD i/[DESCRIPTION] p/[PRICE] q/[QUANTITY]"
            + System.lineSeparator() + "|| Example: ADD i/apple p/3.00 q/2" + System.lineSeparator();
    public static final String FAILURE_ACK = System.lineSeparator()
            + "Error! Description of an item cannot be empty."
            + "\nExample: ADD i/apple p/4.50 q/2";
    public static final String FAILURE_ACK_2 = System.lineSeparator()
            + "Error! price should follow this format"
            + "\nExample: p/4.50";
    public static final String FAILURE_ACK_3 = System.lineSeparator()
            + "Oops! Invalid Command. Check if these are met:"
            + System.lineSeparator()
            + " - Price of an item should be in positive numerical form."
            + System.lineSeparator()
            + " - Quantity of an item should be in positive numerical form."
            + System.lineSeparator()
            + " - If 'i/', 'p/' or 'q/' is present, i/[DESCRIPTION], "
            + "p/[PRICE] or q/[QUANTITY] must be present."
            + System.lineSeparator()
            + "|| Example: ADD i/apples p/9.90 q/9";
    public static final String FAILURE_ACK_4 = System.lineSeparator()
            + "Oops! It already exists"
            + System.lineSeparator()
            + "Duplication is not allowed in the list.";
    private Item add;

    /**
     * Creates an AddCommand.
     * Description is a compulsory input,
     * while price is an optional input.
     *
     * @param description description of item
     * @param price       price of item
     */
    public AddCommand(String description, double price, int quantity) {
        add = new Item(description, price, quantity);
    }

    @Override
    public void execute() {

        try {
            if (items.getSize() == 0) {
                items.add(add);
                assert add.getDescription() != null : "(Add Command): Item must not be null.";
                LOGGER.log(Level.INFO, "(Add command)  Added item: " + add);
                feedbackToUser = String.format(SUCCESS_ACK,add);
            } else if (items.getSize() > 0) {
                boolean isAdd = false;
                for (int i = 0; i < items.getSize(); i++) {
                    if (items.getItem(i).getDescription().equals(add.getDescription())) {
                        isAdd = true;
                        feedbackToUser = String.format(FAILURE_ACK_4);
                        break;
                    }
                }
                if (!isAdd) {
                    items.add(add);
                    assert add.getDescription() != null : "(Add Command): Item must not be null.";
                    LOGGER.log(Level.INFO, "(Add command)  Added item: " + add);
                    feedbackToUser = String.format(SUCCESS_ACK,add);
                }
            }

            //@@author kokjoon97
            assert myBudget != null;
            double remainder = myBudget.getRemainingBudget(items.getTotalCost());
            if (remainder < 0) {
                LOGGER.log(Level.INFO,"(Add command) User exceeded budget by: " + (-1) * remainder);
                feedbackToUser += String.format(EXCEED_WARNING,(-1) * remainder);
            }
            //@@author
        } catch (NullPointerException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            feedbackToUser = String.format(FAILURE_ACK);
        } catch (NumberFormatException nfe) {
            LOGGER.log(Level.WARNING, "(Add command) Invoked with invalid price format: '"
                    + add.getPrice() + "'");
            feedbackToUser = String.format(FAILURE_ACK_2);
        }

    }
}
//@@author