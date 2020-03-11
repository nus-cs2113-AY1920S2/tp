package seedu.command.attendance;

import seedu.command.Command;
import seedu.module.attendance.Attendance;
import seedu.module.attendance.AttendanceList;

public class AddAttendanceCommand extends Command {

    /**
     * Creates a new AddCommand with the given task.
     *
     * @param studentAttendance The student attendance to add.
     */

    public AddAttendanceCommand(Attendance studentAttendance, AttendanceList list) {
        list.add(studentAttendance);
    }
}
