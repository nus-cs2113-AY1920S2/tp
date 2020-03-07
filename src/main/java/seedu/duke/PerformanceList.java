package seedu.duke;

import java.util.ArrayList;
import java.util.List;

public class PerformanceList {
    public static List<Performance> performanceList = null;
    public static int numberOfPerformance;

    public PerformanceList() {
        performanceList = new ArrayList<>();
        numberOfPerformance = performanceList.size();
    }

    public static List<Performance> getPerformanceList() {
        return performanceList;
    }

    public static int getSize() {
        return performanceList.size();
    }

    public static void addToList(Performance performance) {
        performanceList.add(performance);
    }

    /**
     * Delete the student's performance from the performance list.
     * It detects whether the current list is empty, contains the
     * input student's performance, and delete whenever is allowed.
     *
     * @param performance The Performance of student to be deleted.
     */
    public static void deletePerformance(Performance performance) {
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
