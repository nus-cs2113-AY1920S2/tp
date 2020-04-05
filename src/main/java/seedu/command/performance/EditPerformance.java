package seedu.command.performance;

import seedu.command.Command;
import seedu.exception.PacException;
import seedu.performance.Performance;
import seedu.performance.PerformanceList;
import seedu.ui.UI;

public class EditPerformance extends Command {
    PerformanceList performances;
    String eventName;
    UI ui;

    public EditPerformance(PerformanceList performances, String eventName) {
        this.ui = new UI();
        this.performances = performances;
        this.eventName = eventName;
    }

    private Performance getPerformance() throws PacException {
        String studentName = ui.getStudentName("edit his/her performance");
        return performances.getPerformance(studentName);
    }

    private void editPerformance() throws PacException {
        Performance performance = getPerformance();
        String editType = ui.getPerformanceParameter();
        if (editType.toLowerCase().trim().equals("name")) { // edit name
            performances.editPerformance(performance, "name");
        } else if (editType.toLowerCase().trim().equals("result")) { // edit result
            performances.editPerformance(performance, "result");
        }
    }

    @Override
    public void execute() throws PacException {
        editPerformance();
    }
}
