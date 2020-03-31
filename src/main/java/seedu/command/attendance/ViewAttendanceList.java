package seedu.command.attendance;

import seedu.attendance.AttendanceList;
import seedu.command.Command;
import seedu.exception.PacException;
import seedu.ui.UI;

/**
 * Class representing an attendance related command to List an attendanceList of a specific event.
 */
public class ViewAttendanceList extends Command {

    protected AttendanceList attendances;

    public ViewAttendanceList(AttendanceList attendances) {
        this.attendances = attendances;
    }

    private void view() throws PacException {
        try {
            if (attendances.isEmpty()) {
                UI.display("Attendance List is empty");
            } else {
                attendances.printList();
            }
        } catch (Exception e) {
            throw new PacException("Attendance List fail to view.");
        }
    }

    @Override
    public void execute() throws PacException {
        view();
    }
}
