package seedu.command.performance;

import seedu.command.Command;
import seedu.exception.PacException;
import seedu.performance.Performance;
import seedu.performance.PerformanceList;
import seedu.ui.UI;

public class DeletePerformanceList extends Command {
    PerformanceList performanceList;
    Performance performance;
    String eventName;
    UI ui;

    /**
     * Constructor for DeletePerformanceCommand. Takes in performance list and
     * the name of event that the list is under.
     * @param performanceList A PerformanceList of students.
     */
    public DeletePerformanceList(PerformanceList performanceList, String eventName) {
        this.ui = new UI();
        this.performanceList = performanceList;
        this.eventName = eventName;
    }

    private Performance getPerformance() throws PacException {
        String studentName = ui.getStudentName("delete his/her performance");
        return performanceList.getPerformance(studentName);
    }

    private void deleteFromList() throws PacException {
        performance = getPerformance();
        performanceList.deletePerformance(performance, eventName);
    }

    @Override
    public void execute() throws PacException {
        if (performanceList.isEmpty()) {
            throw new PacException("No performance list under this event.");
        }
        deleteFromList();
    }
}