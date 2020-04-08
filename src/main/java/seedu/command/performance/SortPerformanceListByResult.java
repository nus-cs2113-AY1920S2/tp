package seedu.command.performance;

import seedu.command.Command;
import seedu.exception.PacException;
import seedu.performance.PerformanceList;
import seedu.ui.UI;

/**
 * Class representing an performance related command to sort the performanceList of a specific event.
 * Sorts the performanceList in alphabetical order.
 */
public class SortPerformanceListByResult extends Command {
    protected UI ui;
    protected PerformanceList performances;
    protected String eventName;

    public SortPerformanceListByResult(PerformanceList performances, String eventName) {
        this.eventName = eventName;
        this.performances = performances;
        this.ui = new UI();
    }

    /**
     * Method to sort an performance list according to name.
     */
    private void sortPerformanceByResult() {
        if (performances.isEmpty()) {
            UI.display("An empty list cannot be sorted");
        } else {
            performances.sortByGrade();
            ui.sortPerformanceByName(eventName);
        }
    }

    @Override
    public void execute() throws PacException {
        sortPerformanceByResult();
    }
}
