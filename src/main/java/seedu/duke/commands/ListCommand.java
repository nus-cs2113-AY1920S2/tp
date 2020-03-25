package seedu.duke.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Displays the shopping list, cost of items, and budget details.
 */
public class ListCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static final String COMMAND_WORD = "DISPLAY";

    public static final String LIST_MESSAGE = "Your shopping list:";

    public static final String TOTAL_COST_MESSAGE = "Cost of items:    $%.2f";

    public static final String BUDGET_MESSAGE = "Budget:           $%.2f";

    public static final String REMAINING_BUDGET_MESSAGE = "Remaining budget: $%.2f";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all items in the list."
            + System.lineSeparator() + "|| Parameters: DISPLAY"
            + System.lineSeparator() + "|| Example: DISPLAY" + System.lineSeparator();

    @Override
    public void execute() {

        // Prepare info for printing
        double totalCost = items.getTotalCost();
        String budgetDetails =
                System.lineSeparator()
                + String.format(BUDGET_MESSAGE, myBudget.getAmount())
                + System.lineSeparator()
                + String.format(TOTAL_COST_MESSAGE, totalCost)
                + System.lineSeparator()
                + String.format(REMAINING_BUDGET_MESSAGE, myBudget.getRemainingBudget(totalCost));

        // Printing gets done here
        System.out.println(System.lineSeparator() + LIST_MESSAGE + System.lineSeparator());
        items.showTableOfItems();
        System.out.println(budgetDetails);

        LOGGER.log(Level.INFO,"(List command) Items, total cost, and budget have been displayed.");

        // Feedback is empty
        feedbackToUser = "";
    }
}
