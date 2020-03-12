package seedu.command.performance;

import seedu.command.Command;
import seedu.exception.DukeException;
import seedu.module.performance.Performance;
import seedu.module.performance.PerformanceList;
import seedu.storage.Storage;
import seedu.ui.Ui;

import java.util.List;

public class ViewAssignmentResult extends Command {
    public static List<Performance> performances;
    private String assignment;

    /**
     * Constructor for ViewAssignmentResultCommand. Takes String userInput
     * and parse it to get the assignment result list to be showed.
     * @param userInput A String to be parsed.
     */
    public ViewAssignmentResult(String userInput) {
        String[] instructions = userInput.split(" ", 2);
        assignment = instructions[1];
        performances = new PerformanceList().getPerformanceList();
        assignmentPerformanceList();
    }

    /**
     * Selects specific results of the assignment input by user
     * from the Performance list, and print the list in the format of
     * [module][student]result.
     */
    public void assignmentPerformanceList() {
        int size = performances.size();
        if (size == 0) {
            System.out.println("empty");
        } else {
            int i = 1;
            for (Performance performance : performances) {
                if (performance.assignment.equals(assignment)) {
                    System.out.println(i + performance.formatForStudentList());
                    i++;
                }
            }
        }
    }

    @Override
    public void execute() {

    }
}