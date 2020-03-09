package seedu.list;

import seedu.module.Performance;

import java.util.ArrayList;
import java.util.List;

public class PerformanceList {
    public List<Performance> performanceList;
    public static int numberOfPerformance;

    public PerformanceList() {
        this.performanceList = new ArrayList<>();
        numberOfPerformance = performanceList.size();
    }

    public List<Performance> getPerformanceList() {
        return performanceList;
    }

    public int getSize() {
        return performanceList.size();
    }

    public void addToList(Performance performance) {
        performanceList.add(performance);
    }

    /**
     * Delete the student's performance from the performance list.
     * It detects whether the current list is empty, contains the
     * input student's performance, and delete whenever is allowed.
     *
     * @param performance The Performance of student to be deleted.
     */
    public void deletePerformance(Performance performance) {
        if (numberOfPerformance <= 0) {
            System.out.println("list is empty");
        } else if (!performanceList.contains(performance)) {
            System.out.println("list does not contain the student's performance");
        } else if (performanceList.contains(performance)) {
            performanceList.remove(performance);
        } else {
            System.out.println("Error while deleting the student's performance.");
        }
    }
}
