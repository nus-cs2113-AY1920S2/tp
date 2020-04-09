package seedu.command.interpreter;

import seedu.attendance.AttendanceList;
import seedu.command.Command;
import seedu.command.attendance.AddAttendanceList;
import seedu.command.attendance.ClearAttendanceList;
import seedu.command.attendance.SortAttendanceListByName;
import seedu.command.attendance.ViewAttendanceList;
import seedu.command.attendance.SortAttendanceListByStatus;
import seedu.event.EventList;
import seedu.exception.PacException;
import seedu.ui.UI;

/**
 * To interpret the attendance command.
 */
public class AttendanceCommandInterpreter extends CommandInterpreter {

    protected AttendanceList attendances;
    protected String eventName;
    protected UI ui;

    public AttendanceCommandInterpreter(EventList eventList) {
        super(eventList);
        this.ui = new UI();
    }

    /**
     * Execute the command from userInput.
     *
     * @param commandDescription The userInput from the Ui.
     * @throws PacException If the command is undefined.
     */
    public Command decideCommand(String commandDescription) throws PacException {

        String commandType = getFirstWord(commandDescription);
        eventName = ui.getEventNameForAttendance();
        attendances = getAttendance(eventName);

        switch (commandType.toLowerCase().trim()) {
        case "add":
            return new AddAttendanceList(attendances, eventName);
        case "view":
            return new ViewAttendanceList(attendances);
        case "clear":
            return new ClearAttendanceList(attendances, eventName);
        case "sort":
            return sortCommand();
        default:
            throw new PacException("Attendance: Unknown command.");
        }
    }

    private AttendanceList getAttendance(String eventName) throws PacException {
        return eventList.getEvent(eventName).getAttendanceList();
    }

    private String sortType() {
        UI.display("Please Key in either 'name' or 'status'.");
        ui.readUserInput();
        return ui.getUserInput().toLowerCase().trim();
    }

    private Command sortCommand() throws PacException {
        switch (sortType()) {
        case "name":
            return new SortAttendanceListByName(attendances, eventName);
        case "status":
            return new SortAttendanceListByStatus(attendances, eventName);
        default:
            throw new PacException("Unknown Attendance Sort Command");
        }
    }
}
