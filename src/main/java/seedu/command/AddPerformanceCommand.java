package seedu.command;

import seedu.module.Performance;

import java.util.List;

public class AddPerformanceCommand extends Command {
    public AddPerformanceCommand() {
    }

    /**
     * Add the performance when the result of student is record as mark.
     *
     * @param performance The Performance of student to be added to
     *                    the performance list.
     * @param mark          An Integer mark result of the student.
     * @return The performance list after the new performance added.
     */
    public static List<Performance> addMark(List<Performance> performances, Performance performance, int mark) {
        performance.recordMark(mark);
        performances.add(performance);
        System.out.println("added mark successfully");
        return performances;
    }

    /**
     * Add the performance when the result of student is record as grade.
     *
     * @param performance The Performance of student to be added to
     *                    the performance list.
     * @param grade         A String grade result of the student.
     * @return The performance list after the new performance added.
     */
    public static List<Performance> addGrade(List<Performance> performances, Performance performance, String grade) {
        performance.recordGrade(grade);
        performances.add(performance);
        System.out.println("added grade successfully");
        return performances;
    }
}
