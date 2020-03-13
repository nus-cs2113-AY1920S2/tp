package seedu.duke.commands;

public class SetBudgetCommand extends Command {

    private double amount;

    public static final String COMMAND_WORD = "SET";

    public static final String SET_BUDGET_MESSAGE = System.lineSeparator() + "Setting budget to ";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets the budget based on user input."
            + System.lineSeparator() + "|| Parameters: SET /b[BUDGET]"
            + System.lineSeparator() + "|| Example: SET /b 100.00" + System.lineSeparator();

    public SetBudgetCommand(double amount) {
        super();
        this.amount = amount;
    }

    @Override
    public CommandResult execute() {
        double correctBudget = myBudget.setBudget(amount);
        String feedback = SET_BUDGET_MESSAGE + "$" + correctBudget;
        return new CommandResult(feedback);
    }
}
