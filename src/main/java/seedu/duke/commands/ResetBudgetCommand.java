package seedu.duke.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ResetBudgetCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final String COMMAND_WORD = "RES";
    public static final String SUCCESS_RESET_BUDGET = System.lineSeparator() + "Budget has been reset to $0.00";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Resets the budget to $0.00."
            + System.lineSeparator() + "|| Parameters: RES"
            + System.lineSeparator() + "|| Example: RES" + System.lineSeparator();

    public ResetBudgetCommand() {
    }

    @Override
    public void execute() {
        myBudget.resetBudget();
        assert myBudget.getAmount() == 0 : "Budget should be zero";
        LOGGER.log(Level.INFO,"(Reset Budget command) Budget amount is set to zero: " + myBudget.getAmount());
        feedbackToUser = SUCCESS_RESET_BUDGET;
    }
}
