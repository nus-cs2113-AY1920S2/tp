package seedu.duke.commands;

import seedu.duke.data.Item;
import seedu.duke.data.ShoppingList;

public class CommandAdd extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String SUCCESS_ACK = "Added this item: " + "%s";
    private Item Add;

    public CommandAdd(String description, double price){

        this.Add = new Item(description, price);
    }

    @Override
    public CommandResult execute()
    {
        ShoppingList.add(Add);
        return new CommandResult(String.format(SUCCESS_ACK,Add));
    }
    //By right should be able to run once the other function is done.
}
