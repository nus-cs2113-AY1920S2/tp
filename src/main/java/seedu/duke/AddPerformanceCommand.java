package seedu.duke;

public class AddPerformanceCommand extends Command {
    public AddPerformanceCommand() {
    }

    /**
     * Add the performance when the result of student is record as mark.
     *
     * @param nameOfModule  A String input by user, that is the name of the module
     *                      of the result saved.
     * @param nameOfStudent A String input by user, the name of student who scored
     *                      the result.
     * @param assignment    A String input by user, the name of the assignment of the
     *                      result.
     * @param mark          An Integer mark result of the student.
     */
    public static void addMark(String nameOfModule, String nameOfStudent, String assignment, int mark) {
        Performance performance = new Performance(nameOfModule, nameOfStudent, assignment);
        performance.recordMark(mark);
        PerformanceList.addToList(performance);
    }

    /**
     * Add the performance when the result of student is record as grade.
     *
     * @param nameOfModule  A String input by user, that is the name of the module
     *                      of the result saved.
     * @param nameOfStudent A String input by user, the name of student who scored
     *                      the result.
     * @param assignment    A String input by user, the name of the assignment of the
     *                      result.
     * @param grade         A String grade result of the student.
     */
    public static void addGrade(String nameOfModule, String nameOfStudent, String assignment, String grade) {
        Performance performance = new Performance(nameOfModule, nameOfStudent, assignment);
        performance.recordGrade(grade);
        PerformanceList.addToList(performance);
    }
}
