package seedu.command.attendance;

import seedu.attendance.AttendanceList;
import seedu.command.Command;
import seedu.exception.DukeException;
import seedu.ui.UI;

public class ListAttendance extends Command {
    UI ui;
    private AttendanceList attendances;

    public ListAttendance(AttendanceList attendances) {
        this.attendances = attendances;
        this.ui = new UI();
    }


    @Override
    public void execute() throws DukeException {
        attendances.printList();
    }
}
