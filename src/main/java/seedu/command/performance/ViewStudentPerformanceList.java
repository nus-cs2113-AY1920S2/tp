package seedu.command.performance;

import seedu.command.Command;
import seedu.exception.PacException;
import seedu.performance.PerformanceList;

public class ViewStudentPerformanceList extends Command {
    private PerformanceList performanceList;

    /**
     * Constructor for ViewAssignmentResultCommand. Takes in the
     * performance list.
     */
    public ViewStudentPerformanceList(PerformanceList performanceList) {
        this.performanceList = performanceList;
    }

    @Override
    public void execute() throws PacException {
        performanceList.printList();
    }
}
