package seedu.command.performance;

import seedu.command.Command;
import seedu.exception.PacException;
import seedu.performance.PerformanceList;
import seedu.ui.UI;

public class ViewStudentPerformanceList extends Command {
    UI ui;
    private PerformanceList performanceList;

    /**
     * Constructor for ViewAssignmentResultCommand. Takes in the
     * performance list.
     */
    public ViewStudentPerformanceList(PerformanceList performanceList) {
        this.performanceList = performanceList;
        this.ui = new UI();
    }

    @Override
    public void execute() throws PacException {
        performanceList.printList();
    }
}
