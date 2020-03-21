package seedu.performance;

import seedu.exception.DukeException;
import seedu.ui.UI;

import java.util.ArrayList;
import java.util.List;

public class PerformanceList {
    public static ArrayList<Performance> performanceList;
    UI ui;

    public PerformanceList() {
        this.ui = new UI();
        performanceList = new ArrayList<>();
    }

    public List<Performance> getPerformanceList() {
        return performanceList;
    }

    public void addToList(Performance performance, String eventName) {
        performanceList.add(performance);
        UI.addPerformanceMessage(performance.studentName, eventName);
    }

    /**
     * Delete the student's performance from the performance list.
     * It detects whether the current list is empty, contains the
     * input student's performance, and delete whenever is allowed.
     *
     * @param performance The Performance of student to be deleted.
     */
    public void deletePerformance(Performance performance, String eventName) throws DukeException {
        boolean hasDeleted = false;
        if (isEmpty()) {
            throw new DukeException("No performance list under this event");
        }
        for (Performance p : performanceList) {
            if (p != null
                    && performance.getStudent().equals(p.getStudent())) {
                performanceList.remove(p);
                hasDeleted = true;
            }
        }
        ui.deletePerformanceMessage(performance, eventName, hasDeleted);
    }

    public void printList() throws DukeException {
        if (isEmpty()) {
            throw new DukeException("No performance list under this event");
        }
        int i = 1;
        ui.printHeaderOfThree("Index", "Name of Student", "Result");
        for (Performance performance : performanceList) {
            ui.printBodyOfThree(Integer.toString(i), performance.studentName, performance.getResult());
            i++;
        }
    }

    public Performance getPerformance(String studentName) throws DukeException {
        if (isEmpty()) {
            throw new DukeException("No performance list under this event");
        }
        for (Performance performance: performanceList) {
            if (performance.studentName.equals(studentName)) {
                return performance;
            }
        }
        throw new DukeException("There is no record for " + studentName + "'s performance.");
    }

    public boolean isEmpty() {
        return performanceList.isEmpty();
    }
}
