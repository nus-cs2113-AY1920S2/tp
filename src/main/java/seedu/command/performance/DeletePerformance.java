package seedu.command.performance;

import seedu.command.Command;
import seedu.exception.DukeException;
import seedu.performance.Performance;
import seedu.performance.PerformanceList;
import seedu.ui.UI;

public class DeletePerformance extends Command {
    PerformanceList performances;
    String eventName;
    UI ui;

    /**
     * Constructor for DeletePerformanceCommand. Takes in performance list and
     * the name of event that the list is under.
     * @param performances A PerformanceList of students.
     * @param eventName    A String indicates the name of Event that the performance
     *                     list belongs to.
     */
    public DeletePerformance(PerformanceList performances, String eventName) {
        this.performances = performances;
        this.eventName = eventName;
        this.ui = new UI();
    }

    public Performance getPerformance() throws DukeException {
        String studentName = ui.getStudentName("delete");
        return performances.getPerformance(studentName);
    }

    @Override
    public void execute() throws DukeException {
        performances.deletePerformance(getPerformance(), eventName);
    }
}