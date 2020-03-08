package seedu.duke.commands;

public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "CLEAR";

    public static final String MESSAGE_SUCCESS = "Your shopping list has been cleared.\n";

    @Override
    public CommandResult execute() {
        items.clearList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
