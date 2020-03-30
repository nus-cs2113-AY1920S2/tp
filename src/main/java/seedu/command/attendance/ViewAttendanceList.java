package seedu.command.attendance;

import seedu.attendance.AttendanceList;
import seedu.command.Command;
import seedu.exception.DukeException;
import seedu.ui.UI;

/**
 * Class representing an attendance related command to List an attendanceList of a specific event.
 */
public class ViewAttendanceList extends Command {

    protected UI ui;
    protected AttendanceList attendances;

    public ViewAttendanceList(AttendanceList attendances) {
        this.attendances = attendances;
        this.ui = new UI();
    }

    private void view() throws DukeException {
        try {
            if (attendances.isEmpty()) {
                ui.displayMessage("Attendance List is empty");
            } else {
                attendances.printList();
            }
        } catch (Exception e) {
            throw new DukeException("Attendance List fail to view.");
        }
    }

    @Override
    public void execute() throws DukeException {
        view();
    }
}
