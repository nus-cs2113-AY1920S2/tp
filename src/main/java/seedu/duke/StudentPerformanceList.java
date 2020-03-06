package seedu.duke;

import java.util.ArrayList;
import java.util.List;

public class StudentPerformanceList {
    /**
     * For a particular student, record the assignment and
     * respective result in a list.
     */
    public static List<String> studentPerformances = new ArrayList<>();

    public StudentPerformanceList() {
        studentPerformances = new ArrayList<>();
    }

    public static void addStudentPerformance(String storeToStudentList) {
        studentPerformances.add(storeToStudentList);
    }

    public static int getSize() {
        return studentPerformances.size();
    }

    /**
     * This method prints the list of students' result for different assignments.
     * The output format will be [name of module][assignment] result of student.
     */
    public static void showList() {
        if (getSize() == 0) {
            System.out.println("empty");
        } else {
            int index = 1;
            for (String s : studentPerformances) {
                System.out.print(index + ". " + s);
                index++;
            }
        }
    }
}
