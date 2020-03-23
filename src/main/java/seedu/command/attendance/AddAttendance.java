package seedu.command.attendance;

import seedu.attendance.Attendance;
import seedu.attendance.AttendanceList;
import seedu.command.Command;
import seedu.exception.DukeException;
import seedu.parser.AttendanceParser;
import seedu.ui.UI;

import java.util.ArrayList;

public class AddAttendance extends Command {

    static UI ui = new UI();
    AttendanceList attendances;
    boolean isByNameList;
    String eventName;

    public AddAttendance(AttendanceList attendances, String eventName) {
        this.isByNameList = false;
        this.attendances = attendances;
        this.eventName = eventName;
    }

    /**
     * Executes this command on the given task list and user interface.
     */

    public void addToList() throws DukeException {
        System.out.println("By existing nameList? [Y/N] ");
        String reply = ui.getStringInput();
        if (reply.contains("Y")) {
            isByNameList = true;
        } else {
            isByNameList = false;
        }
        if (isByNameList) {
            ArrayList<String> studentNameList = new ArrayList<>();
            //todo: add a list of student list, and let user select a student list to be used.
            if (studentNameList.isEmpty()) {
                throw new DukeException("There is no existing student list.");
            }
            for (String studentName: studentNameList) {
                attendances.addToList(new Attendance(studentName,
                    ui.getResultOfStudent(studentName)), eventName);
            }
        } else {
            int studentNumber = 0;
            String parameter = ui.getAttendancePerimeter();
            do {
                attendances.addToList(getAttendance(parameter), eventName);
                parameter = ui.getStringInput();
                studentNumber++;
            } while (!parameter.equals("done"));
            System.out.println("You have successfully added "
                    + studentNumber + " to the attendance list.");
        }
    }

    private Attendance getAttendance(String parameter) throws DukeException {
        return new AttendanceParser().parseAttendance(parameter);
    }

    @Override
    public void execute() throws DukeException {
        addToList();
    }
}