package seedu.command.interpreter;

import seedu.attendance.AttendanceList;
import seedu.command.Command;
import seedu.command.attendance.AddAttendanceList;
import seedu.command.attendance.ClearAttendanceList;
import seedu.command.attendance.SortAttendanceListByName;
import seedu.command.attendance.ViewAttendanceList;
import seedu.command.attendance.SortAttendanceListByStatus;
import seedu.command.attendance.FindAttendance;
import seedu.command.attendance.EditAttendance;
import seedu.event.EventList;
import seedu.exception.PacException;
import seedu.ui.UI;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * To interpret the attendance command.
 */
public class AttendanceCommandInterpreter extends CommandInterpreter {

    protected AttendanceList attendances;
    protected String eventName;
    protected UI ui;
    private static final Logger logger = Logger.getLogger(AttendanceCommandInterpreter.class.getName());

    public AttendanceCommandInterpreter(EventList eventList) {
        super(eventList);
        this.ui = new UI();
    }

    static void setupLogger() throws PacException {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);

        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        logger.addHandler(ch);

        try {
            FileHandler fh = new FileHandler("AttendanceCommandInterpreter.log");
            fh.setLevel(Level.FINE);
            logger.addHandler(fh);
        } catch (IOException e) {
            throw new PacException(e.getMessage());
        }
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

        assert commandType.isBlank() : "Attendance: Unknown command";

        AttendanceCommandInterpreter.setupLogger();
        logger.info("Attendance List Log");
        logger.finest(commandType);

        switch (commandType.toLowerCase().trim()) {
        case "add":
            return new AddAttendanceList(attendances, eventName);
        case "view":
            return new ViewAttendanceList(attendances);
        case "clear":
            return new ClearAttendanceList(attendances, eventName);
        case "sort":
            return sortAttendanceList();
        case "find":
            return new FindAttendance(attendances);
        case "edit":
            return new EditAttendance(attendances);
        default:
            throw new PacException("Attendance: Unknown command.");
        }
    }

    private AttendanceList getAttendance(String eventName) throws PacException {
        return eventList.getEvent(eventName).getAttendanceList();
    }

    private String getSortType() {
        UI.display("Please Key in either 'name' or 'status'.");
        ui.readUserInput();
        return ui.getUserInput().toLowerCase().trim();
    }

    private Command sortAttendanceList() throws PacException {
        switch (getSortType()) {
        case "name":
            return new SortAttendanceListByName(attendances, eventName);
        case "status":
            return new SortAttendanceListByStatus(attendances, eventName);
        default:
            throw new PacException("Unknown Attendance Sort Command");
        }
    }
}
