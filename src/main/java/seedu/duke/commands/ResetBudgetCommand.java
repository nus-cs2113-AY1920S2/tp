package seedu.duke.commands;

public class ResetBudgetCommand extends Command {
    public static final String COMMAND_WORD = "RES";
    public static final String SUCCESS_RESET_BUDGET = "Budget has been reset to $0.00";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Resets the budget to $0.00."
            + System.lineSeparator() + "|| Parameters: RES"
            + System.lineSeparator() + "|| Example: RES" + System.lineSeparator();

    public ResetBudgetCommand() {
    }

    @Override
    public CommandResult execute() {
        myBudget.resetBudget();
        return new CommandResult(SUCCESS_RESET_BUDGET);
    }
}
