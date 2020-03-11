package seedu.performance;

import java.util.ArrayList;
import java.util.List;

public class PerformanceList {
    public static List<Performance> performanceList;
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

    public static void addToList(Performance performance) {
        performanceList.add(performance);
        System.out.println("[" + performance.moduleName + "]["
                + performance.assignment + "]["
                + performance.studentName + "] "
                + performance.getResult() + " has been added.");
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
        } else {
            for (Performance p : performanceList) {
                if (p != null
                        && performance.getAssignment().equals(p.getAssignment())
                        && performance.getModule().equals(p.getModule())
                        && performance.getStudent().equals(p.getStudent())) {
                    performanceList.remove(p);
                    System.out.println("deleted successfully");
                } else {
                    System.out.println("list does not contain the student's performance");
                }
            }
        }
    }
}
