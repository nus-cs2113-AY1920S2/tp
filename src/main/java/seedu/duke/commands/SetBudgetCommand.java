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
        assert correctBudget <= 5000 : "Budget should not exceed $5000";
        assert correctBudget >= 0 : "Budget should not be negative";
        String feedback = SET_BUDGET_MESSAGE + "$" + correctBudget;
        return new CommandResult(feedback);
    }
}
