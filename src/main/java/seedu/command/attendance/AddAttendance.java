package seedu.command.attendance;

import seedu.attendance.Attendance;
import seedu.command.Command;

public class AddAttendance extends Command {

    private final Attendance attendance;

    /**
     * Creates a new AddAttendanceCommand with the given task.
     *
     * @param attendance The student attendance to add.
     */

    public AddAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    /**
     * Executes this command on the given task list and user interface.
     */

    @Override
    public void execute() {
    }
}