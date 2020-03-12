package seedu.performance;

import seedu.ui.UI;

import java.util.ArrayList;
import java.util.List;

public class PerformanceList {
    public static List<Performance> performanceList;
    public static int numberOfPerformance;

    public PerformanceList() {
        performanceList = new ArrayList<>();
        numberOfPerformance = performanceList.size();
    }

    public List<Performance> getPerformanceList() {
        return performanceList;
    }

    public int getSize() {
        return performanceList.size();
    }

    public static void addToList(Performance performance, String eventName) {
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
    public static void deletePerformance(Performance performance) {
        boolean hasDeleted = false;
        if (numberOfPerformance > 0) {
            for (Performance p : performanceList) {
                if (p != null
                        && performance.getAssignment().equals(p.getAssignment())
                        && performance.getEvent().equals(p.getEvent())
                        && performance.getStudent().equals(p.getStudent())) {
                    performanceList.remove(p);
                    hasDeleted = true;
                }
            }
        }
        UI.deletePerformanceMessage(performance, hasDeleted);
    }
}
