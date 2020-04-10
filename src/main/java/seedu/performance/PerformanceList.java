package seedu.performance;

import seedu.exception.PacException;
import seedu.ui.DisplayTable;
import seedu.ui.UI;

import java.util.ArrayList;

import static seedu.performance.Performance.performanceListNameComparator;
import static seedu.performance.Performance.performanceListGradeComparator;

public class PerformanceList {
    protected ArrayList<Performance> performanceList;
    UI ui;
    DisplayTable displayTable;

    public PerformanceList() {
        this.ui = new UI();
        this.displayTable = new DisplayTable();
        performanceList = new ArrayList<>();
    }

    public void addToList(Performance performance, String eventName) {
        if (isRepeated(performance)) {
            UI.display("You have already stored " + performance.studentName + "'s result. If you wish "
                    + "to edit, please input: Performance edit");
        } else {
            performanceList.add(performance);
            ui.addPerformanceMessage(performance.studentName, eventName);
        }
    }

    /**
     * Add performance to performanceList.
     * @param performance the performance to be added
     */
    public void add(Performance performance) {
        performanceList.add(performance);
    }

    private boolean isRepeated(Performance performance) {
        if (isEmpty()) {
            return false;
        }
        for (Performance p : performanceList) {
            return isSameString(p.getStudent(), performance.getStudent());
        }
        return false;
    }

    private boolean isSameString(String string1, String string2) {
        return string1.toLowerCase().trim().equals(string2.toLowerCase().trim());
    }

    public ArrayList<Performance> getPerformanceList() {
        return performanceList;
    }

    /**
     * Delete the student's performance from the performance list.
     * It detects whether the current list is empty, contains the
     * input student's performance, and delete whenever is allowed.
     *
     * @param performance The Performance of student to be deleted.
     */
    public void deletePerformance(Performance performance, String eventName) {
        boolean hasDeleted = false;
        if (isEmpty()) {
            UI.display("No performance list under this event");
        }
        for (Performance p : performanceList) {
            if (p != null && isSameString(p.getStudent(), performance.getStudent())) {
                performanceList.remove(p);
                hasDeleted = true;
                break;
            }
        }
        ui.deletePerformanceMessage(performance, eventName, hasDeleted);
    }

    /**
     * This method edit the parameters in Performance. The type of parameter to change is
     * determined by the String type passed into this method.
     *
     * @param performance The Performance to be edited.
     * @param type        The type of parameter to be edited.
     */
    public void editPerformance(Performance performance, String type) {
        boolean hasEdited = false;
        if (isEmpty()) {
            UI.display("No performance list under this event");
        }

        for (Performance p : performanceList) {
            if (p != null
                    && isSameString(p.getStudent(), performance.getStudent())
                    && isSameString(type, "name")) {
                editName(p);
                hasEdited = true;
                break;
            } else if (p != null
                    && isSameString(p.getStudent(), performance.getStudent())
                    && isSameString(type, "result")) {
                editResult(p);
                hasEdited = true;
            }
        }

        if (!hasEdited) {
            UI.display("Performance not found in list");
        }
    }

    private void editName(Performance performance) {
        performance.studentName = ui.getPerformanceName("name");
        ui.editPerformanceMessage(performance, "name");
        performanceList.remove(performance);
        performanceList.add(performance);
    }

    private void editResult(Performance performance) {
        performance.result = ui.getPerformanceName("result");
        ui.editPerformanceMessage(performance, "result");
        performanceList.remove(performance);
        performanceList.add(performance);
    }

    /**
     * This method iterates and prints the Performance list in a table format.
     */
    public void printList() {
        if (isEmpty()) {
            UI.display("No performance list under this event");
        }
        int i = 1;
        displayTable.printHeaderOfThree("index", "Name of Student", "Result");
        for (Performance performance : performanceList) {
            displayTable.printBodyOfThree(i, performance.studentName, performance.getResult());
            i++;
        }
    }

    /**
     * This method compares the input String student name with Performance.studentName
     * and returns the Performance when the two Strings are equal.
     * @param studentName A String input to be compared.
     * @return The Performance with studentName matches input String.
     */
    public Performance getPerformance(String studentName) throws PacException {
        if (isEmpty()) {
            throw new PacException("No performance list under this event");
        }
        for (Performance performance: performanceList) {
            if (isSameString(performance.studentName,studentName)) {
                return performance;
            }
        }
        throw new PacException("There is no record for " + studentName + "'s performance.");
    }

    public boolean isEmpty() {
        return performanceList.isEmpty();
    }

    public void sortByName() {
        performanceList.sort(performanceListNameComparator);
    }

    public void sortByGrade() {
        performanceList.sort(performanceListGradeComparator);
    }
}
