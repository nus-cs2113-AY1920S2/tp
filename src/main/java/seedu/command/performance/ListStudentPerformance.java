package seedu.command.performance;

import seedu.command.Command;
import seedu.exception.DukeException;
import seedu.performance.PerformanceList;
import seedu.ui.UI;

public class ListStudentPerformance extends Command {
    UI ui;
    private PerformanceList performances;

    /**
     * Constructor for ViewAssignmentResultCommand. Takes in the
     * performance list.
     */
    public ListStudentPerformance(PerformanceList performances) {
        this.performances = performances;
        this.ui = new UI();
    }

    @Override
    public void execute() throws DukeException {
        performances.printList();
    }
}
