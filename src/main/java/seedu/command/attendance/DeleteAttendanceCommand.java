package seedu.command.attendance;

import seedu.command.Command;
import seedu.module.attendance.AttendanceList;

public class DeleteAttendanceCommand extends Command {

    /**
     * Creates a new DeleteCommand with the given task.
     *
     * @param index The index of the task to be deleted.
     */

    public DeleteAttendanceCommand(int index, AttendanceList list) {
        list.remove(index);
    }

}
