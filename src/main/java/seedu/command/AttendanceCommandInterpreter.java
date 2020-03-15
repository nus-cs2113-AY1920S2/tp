package seedu.command;

import seedu.attendance.AttendanceList;
import seedu.command.attendance.AddAttendance;
import seedu.command.attendance.DeleteAttendance;
import seedu.command.attendance.ListAttendance;
import seedu.event.EventList;
import seedu.exception.DukeException;


public class AttendanceCommandInterpreter extends CommandInterpreter {

    public AttendanceCommandInterpreter(EventList eventList) {
        super(eventList);
    }

    /**
     * Execute the command from userInput.
     *
     * @param commandDescription The userInput from the Ui.
     * @throws DukeException If the command is undefined.
     */
    public Command decideCommand(String commandDescription) throws DukeException {
        String commandType = getFirstWord(commandDescription);
        String commandParameters = getSubsequentWords(commandDescription);
        assert commandType == "" : "UnknownType";
        assert commandType == "" : "UnknownParameter";
        switch (commandType) {
        case "add":
            return new AddAttendance(commandParameters);
        case "delete":
            return new DeleteAttendance(commandParameters);
        default:
            throw new DukeException("Attendance: Unknown command.");
        }
    }
}
