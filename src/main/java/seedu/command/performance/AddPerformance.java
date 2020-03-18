package seedu.command.performance;

import seedu.command.Command;
import seedu.exception.DukeException;
import seedu.parser.PerformanceParser;
import seedu.performance.Performance;
import seedu.performance.PerformanceList;
import seedu.ui.UI;

import java.util.ArrayList;

public class AddPerformance extends Command {
    static UI ui = new UI();
    boolean isByNameList;
    PerformanceList performances;
    String eventName;

    /**
     * Constructor for AddPerformanceCommand. Takes String userInput
     * and parse it to get the Performance to be added.
     */
    public AddPerformance(PerformanceList performances, String eventName) {
        this.isByNameList = ui.getTypeOfAddPerformance();
        this.performances = performances;
        this.eventName = eventName;
    }

    /**
     * Add the performance to performance list.
     * The user can choose to import a current student name list
     * and iterate through that name list to add performance of each
     * student. Also the user can choose to manually add a performance
     * of student.
     */
    public void addToList() throws DukeException {
        if (isByNameList) {
            ArrayList<String> studentNameList = new ArrayList<>();
            //todo: add a list of student list, and let user select a student list to be used.
            for (String studentName: studentNameList) {
                performances.addToList(new Performance(studentName,
                        ui.getResultOfStudent(studentName)), eventName);
            }
        } else {
            performances.addToList(getPerformance(), eventName);
        }
    }

    /**
     * If the user chooses to add performance manually, getPerformance()
     * will prompt the user to input student name and result of
     * student and returns a Performance based on input.
     * @return A Performance of student
     * @throws DukeException throws DukeException when the user input
     *                       is insufficient or incorrect.
     */
    private Performance getPerformance() throws DukeException {
        String parameter = ui.getPerformanceParameter();
        return new PerformanceParser().parsePerformance(parameter);
    }

    @Override
    public void execute() throws DukeException {
        addToList();
    }
}