package seedu.command.performance;

import seedu.command.Command;
import seedu.exception.PacException;
import seedu.performance.PerformanceList;
import seedu.ui.UI;

/**
 * Class representing an performance related command to sort the performanceList of a specific event.
 * Sorts the performanceList in alphabetical order.
 */
public class SortPerformanceListByName extends Command {
    protected UI ui;
    protected PerformanceList performanceList;
    protected String eventName;

    public SortPerformanceListByName(PerformanceList performances, String eventName) {
        this.eventName = eventName;
        this.performanceList = performances;
        this.ui = new UI();
    }

    /**
     * Method to sort an performance list according to name.
     */
    private void sortPerformanceByName() {
        if (performanceList.isEmpty()) {
            UI.display("An empty list cannot be sorted");
        } else {
            performanceList.sortByName();
            ui.sortPerformanceByName(eventName);
        }
    }

    @Override
    public void execute() throws PacException {
        sortPerformanceByName();
    }
}
