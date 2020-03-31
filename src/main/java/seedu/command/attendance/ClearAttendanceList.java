package seedu.command.attendance;

import seedu.attendance.AttendanceList;
import seedu.command.Command;
import seedu.exception.PACException;
import seedu.ui.UI;

/**
 * Class representing an attendance related command to clear an existing attendanceList of a specific event.
 */
public class ClearAttendanceList extends Command {

    protected UI ui;
    protected AttendanceList attendances;
    protected String eventName;

    public ClearAttendanceList(AttendanceList attendances, String eventName) {
        this.attendances = attendances;
        this.ui = new UI();
        this.eventName = eventName;
    }

    /**
     * Method to clear an existing attendanceList in a specific event.
     * @throws PACException If attendanceList fail to clear.
     */
    private void clear() throws PACException {
        try {
            attendances.clearList();
            ui.clearAttendanceMessage(eventName);
        } catch (Exception e) {
            throw new PACException("Attendance List fail to clear");
        }
    }

    @Override
    public void execute() throws PACException {
        clear();
    }
}
