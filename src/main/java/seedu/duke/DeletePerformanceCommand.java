package seedu.duke;

public class DeletePerformanceCommand extends Command {
    public DeletePerformanceCommand() {
    }

    /**
     * Delete the performance that was input by the user.
     *
     * @param performance A Performance set of module, assignment and student.
     */
    public static void execute(Performance performance) {
        PerformanceList.deletePerformance(performance);
    }
}