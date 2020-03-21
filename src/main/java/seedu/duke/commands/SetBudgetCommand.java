//@@author kokjoon97

package seedu.duke.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SetBudgetCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private double amount;

    public static final String COMMAND_WORD = "SET";

    public static final String EXCEED_WARNING = "\nNOTE: You have exceeded your budget by %.2f";

    public static final String SET_BUDGET_MESSAGE = System.lineSeparator() + "Setting budget to ";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets the budget based on user input."
            + System.lineSeparator() + "|| Parameters: SET /b[BUDGET]"
            + System.lineSeparator() + "|| Example: SET /b 100.00" + System.lineSeparator();

    /**
     * Constructs the set budget command with the user-specified budget.
     *
     * @param amount Budget specified by the user.
     */
    public SetBudgetCommand(double amount) {
        super();
        this.amount = amount;
        LOGGER.log(Level.INFO,"(Set budget) User entered budget: " + amount);
    }

    @Override
    public void execute() {
        double correctBudget = myBudget.setBudget(amount);
        LOGGER.log(Level.INFO,"(Set budget) Budget was corrected to: " + correctBudget);
        assert correctBudget <= 5000 : "Budget should not exceed $5000";
        assert correctBudget >= 0 : "Budget should not be negative";
        LOGGER.log(Level.INFO,"(Set budget) Budget amount: " + correctBudget + " did not fail assertions.");
        String feedback = SET_BUDGET_MESSAGE + "$" + correctBudget;
        feedbackToUser = feedback;
        assert myBudget != null;
        double remainder = myBudget.getRemainingBudget(items.getTotalCost());
        if (remainder < 0) {
            LOGGER.log(Level.INFO,"(Set budget) User exceeded budget by: " + (-1) * remainder);
            feedbackToUser += String.format(EXCEED_WARNING,(-1) * remainder);
        }
    }
}
