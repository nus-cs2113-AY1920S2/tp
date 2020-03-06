package seedu.duke;

public class DeletePerformanceCommand extends Command {
    public DeletePerformanceCommand() {
    }

    /**
     * Delete the performance that was input by the user.
     *
     * @param nameOfModule  A String input by user, that is the name of the module
     *                      of the result saved.
     * @param nameOfStudent A String input by user, the name of student who scored
     *                      the result.
     * @param assignment    A String input by user, the name of the assignment of the
     *                      result.
     */
    public static void execute(String nameOfModule, String assignment, String nameOfStudent) {
        Performance performance = new Performance(nameOfModule, nameOfStudent, assignment);
        PerformanceList.deletePerformance(performance);
    }
}