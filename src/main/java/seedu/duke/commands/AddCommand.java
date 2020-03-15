package seedu.duke.commands;

import seedu.duke.data.Item;

public class AddCommand extends Command {

    public static final String COMMAND_WORD = "ADD";
    public static final String SUCCESS_ACK = System.lineSeparator() + "Added this item: " + "%s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an item to the list."
            + System.lineSeparator() + "|| Parameters: ADD i/[DESCRIPTION] p/[PRICE]"
            + System.lineSeparator() + "|| Example: ADD i/apple p/3.00" + System.lineSeparator();
    public static final String FAILURE_ACK = System.lineSeparator()
            + "Error! Description of an item cannot be empty."
            + "\nExample: ADD 1 i/apple p/4.50";
    private Item add;

    public AddCommand(String description, double price) {
        this.add = new Item(description, price);
    }

    @Override
    public void execute() {

        try {
            items.add(add);
            feedbackToUser = String.format(SUCCESS_ACK,add);
        } catch (NullPointerException e) {
            feedbackToUser = String.format(FAILURE_ACK);
        }

    }
    //By right should be able to run once the other function is done.
}
