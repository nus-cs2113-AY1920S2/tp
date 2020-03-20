package seedu.duke.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private int index;

    public static final String COMMAND_WORD = "DEL";
    public static final String EXCEED_WARNING = "\nNOTE: You have exceeded your budget by %.2f";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an item in the list."
            + System.lineSeparator() + "|| Parameters: DEL [INDEX]"
            + System.lineSeparator() + "|| Example: DEL 1" + System.lineSeparator();
    public static final String DELETE_MESSAGE = "\nGot it! I have removed the following item:\n";
    public static final String DELETE_MESSAGE_FAILURE = System.lineSeparator()
            + "Please enter a valid index within the bounds";

    /**
     * Constructs the delete command with the user-specified index.
     *
     * @param index Index of the item to be removed.
     */
    public DeleteCommand(int index) {
        super();
        this.index = index;
        LOGGER.log(Level.INFO,"(Delete command) User entered index " + index);
    }

    @Override
    public void execute() {
        try {
            index -= 1;
            String feedback = DELETE_MESSAGE + items.getItem(index);
            items.deleteItem(index);
            LOGGER.log(Level.INFO,"(Delete command) Item was removed from index: " + index);
            feedbackToUser = feedback;
            if (myBudget != null) {
                double remainder = myBudget.getRemainingBudget(items.getTotalCost());
                if (remainder < 0) {
                    LOGGER.log(Level.INFO,"(Delete command) User exceeded budget by: " + (-1) * remainder);
                    feedbackToUser += String.format(EXCEED_WARNING,(-1) * remainder);
                }
            } else {
                assert myBudget == null;
                if (items.getTotalCost() > 0) {
                    LOGGER.log(Level.INFO,"(Delete command) User has not added budget but has incurred total cost: "
                            + items.getTotalCost());
                    feedbackToUser += String.format(EXCEED_WARNING,items.getTotalCost());
                }
            }
        } catch (IndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING,"(Delete command) Invoked with invalid index: " + this.index);
            feedbackToUser = DELETE_MESSAGE_FAILURE;
        }

    }

}
