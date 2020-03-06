package seedu.duke.commands;

import seedu.duke.data.ShoppingList;

public class ResetBudgetCommand extends Command {
    public static final String COMMAND_WORD = "RES";
    public static final String SUCCESS_RESET_BUDGET = "Budget has been reset to $0.00";

    public ResetBudgetCommand() {
    }

    @Override
    public CommandResult execute() {
        ShoppingList.resetBudget();
        return new CommandResult(SUCCESS_RESET_BUDGET);
    }
}
