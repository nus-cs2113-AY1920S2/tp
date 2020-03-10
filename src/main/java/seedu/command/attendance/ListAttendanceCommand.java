package seedu.command.attendance;

import seedu.command.Command;
import seedu.module.attendance.AttendanceList;

public class ListAttendanceCommand extends Command {

    public ListAttendanceCommand(AttendanceList list) {
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                System.out.print("\t");
                System.out.print(i + 1);
                System.out.print(". ");
                System.out.println(list.get(i));
            }
        } else {
            System.out.println("\tEMPTY!!");
        }
    }
}
