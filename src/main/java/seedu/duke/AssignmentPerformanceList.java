package seedu.duke;

import java.util.ArrayList;
import java.util.List;

public class AssignmentPerformanceList {
    /**
     * For a particular assignment, record the student and
     * respective result in a list.
     */
    public static List<String> assignmentPerformances = new ArrayList<>();

    public AssignmentPerformanceList() {
        assignmentPerformances = new ArrayList<>();
    }

    public static void addAssignmentPerformance(String storeToPerformanceList) {
        assignmentPerformances.add(storeToPerformanceList);
    }

    public static int getSize() {
        return assignmentPerformances.size();
    }

    /**
     * This method prints the list of students' result in the assignment.
     * The output format will be [name of module][name of student] result of student.
     */
    public static void showList() {
        if (getSize() == 0) {
            System.out.println("empty");
        } else {
            int index = 1;
            for (String a : assignmentPerformances) {
                System.out.print(index + ". " + a);
                index++;
            }
        }
    }
}
