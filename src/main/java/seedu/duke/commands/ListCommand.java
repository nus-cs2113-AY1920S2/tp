package seedu.duke.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Displays the shopping list, cost of items, and budget details.
 */
public class ListCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static final String COMMAND_WORD = "DISPLAY";

    public static final String LIST_MESSAGE = System.lineSeparator() + "Here is your shopping list:\n";

    public static final String TOTAL_COST_MESSAGE = "Total cost of items: %.2f\n";

    public static final String BUDGET_MESSAGE = "Your remaining budget / total budget: %.2f / %.2f";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all items in the list."
            + System.lineSeparator() + "|| Parameters: DISPLAY"
            + System.lineSeparator() + "|| Example: DISPLAY" + System.lineSeparator();

    @Override
    public void execute() {

        // Prepare info for printing
        double totalCost = items.getTotalCost();
        String budgetDetails = String.format(TOTAL_COST_MESSAGE, totalCost)
                + String.format(BUDGET_MESSAGE, myBudget.getRemainingBudget(totalCost), myBudget.getAmount());

        // Printing gets done here
        System.out.println(LIST_MESSAGE);
        items.showTableOfItems();
        System.out.println(budgetDetails);

        LOGGER.log(Level.INFO,"(List command) Items, total cost, and budget have been displayed.");

        // Feedback is empty
        feedbackToUser = "";
    }
}
