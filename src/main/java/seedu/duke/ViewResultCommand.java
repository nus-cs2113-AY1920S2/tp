package seedu.duke;

import java.util.List;

public class ViewResultCommand extends Command {

    /**
     * To view the performance result list for a particular student.
     * This will print the list in the format of [assignment] result.
     *
     * @param studentPerformances The performance list to be analysed
     *                            to print in the right format.
     */
    public static void studentPerformanceList(List<Performance> studentPerformances) {
        int i = 1;
        if (PerformanceList.getSize() == 0) {
            System.out.println("empty");
        } else if (PerformanceList.getSize() > 0) {
            for (Performance performance : studentPerformances) {
                System.out.println(i + performance.formatForStudentList());
                i++;
            }
        } else {
            System.out.println("Error in printing assignment performance list.");
        }
    }

    /**
     * To view the performance result list for a particular assignment.
     * This will print the list in the format of [assignment] result.
     *
     * @param assignmentPerformances The performance list to be
     *                               analysed to print in the right
     *                               format.
     */
    public static void assignmentPerformanceList(List<Performance> assignmentPerformances) {
        int i = 1;
        if (PerformanceList.getSize() == 0) {
            System.out.println("empty");
        } else if (PerformanceList.getSize() > 0) {
            for (Performance performance : assignmentPerformances) {
                System.out.println(i + performance.formatForAssignmentList());
                i++;
            }
        } else {
            System.out.println("Error in printing assignment performance list.");
        }
    }
}
