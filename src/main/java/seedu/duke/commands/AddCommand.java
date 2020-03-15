package seedu.duke.commands;

import seedu.duke.data.Item;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AddCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final String COMMAND_WORD = "ADD";
    public static final String SUCCESS_ACK = System.lineSeparator() + "Added this item: " + "%s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an item to the list."
            + System.lineSeparator() + "|| Parameters: ADD i/[DESCRIPTION] p/[PRICE]"
            + System.lineSeparator() + "|| Example: ADD i/apple p/3.00" + System.lineSeparator();
    public static final String FAILURE_ACK = System.lineSeparator()
            + "Error! Description of an item cannot be empty."
            + "\nExample: ADD 1 i/apple p/4.50";
    public static final String FAILURE_ACK_2 = System.lineSeparator()
            + "Error! price should follow this format"
            + "\nExample: p/4.50";
    private Item add;

    public AddCommand(String description, double price) {
        this.add = new Item(description, price);
    }

    @Override
    public CommandResult execute() {

        try {
            items.add(add);
            LOGGER.log(Level.INFO, "(Add command)  Added item: " + items.toString());
            return new CommandResult(String.format(SUCCESS_ACK,add));
        } catch (NullPointerException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            return new CommandResult(String.format(FAILURE_ACK));
        } catch (NumberFormatException nfe) {
            LOGGER.log(Level.WARNING, "(Add command) Invoked with invalid price format: '"
                    + add.getPrice() + "'");
            return new CommandResult(String.format(FAILURE_ACK_2));
        }

    }
}
