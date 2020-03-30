package seedu.command.interpreter;

import seedu.attendance.AttendanceList;
import seedu.command.Command;
import seedu.command.attendance.AddAttendanceList;
import seedu.command.attendance.ClearAttendanceList;
import seedu.command.attendance.SortAttendanceListByName;
import seedu.command.attendance.ViewAttendanceList;
import seedu.command.attendance.SortAttendanceListByStatus;
import seedu.event.EventList;
import seedu.exception.DukeException;
import seedu.ui.UI;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;

public class AttendanceCommandInterpreter extends CommandInterpreter {

    AttendanceList attendances;
    String eventName;
    UI ui;

    private static final Logger logger = Logger.getLogger(AttendanceCommandInterpreter.class.getName());

    public AttendanceCommandInterpreter(EventList eventList) {
        super(eventList);
        this.ui = new UI();
    }

    public static void setupLogger() throws DukeException {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);

        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        logger.addHandler(ch);

        try {
            FileHandler fh = new FileHandler("myLogger.log");
            fh.setLevel(Level.FINE);
            logger.addHandler(fh);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }

    }

    /**
     * Execute the command from userInput.
     *
     * @param commandDescription The userInput from the Ui.
     * @throws DukeException If the command is undefined.
     */
    public Command decideCommand(String commandDescription) throws DukeException {

        String commandType = getFirstWord(commandDescription);

        assert commandType.isBlank() : "Attendance: Unknown command";

        AttendanceCommandInterpreter.setupLogger();
        logger.info("My First Log");
        logger.fine("My Second Log");

        switch (commandType) {
        case "add":
            eventName = ui.getEventNameForAttendance();
            attendances = getAttendance(eventName);
            return new AddAttendanceList(attendances, eventName);
        case "list":
            eventName = ui.getEventNameForAttendance();
            attendances = getAttendance(eventName);
            return new ViewAttendanceList(attendances);
        case "clear":
            eventName = ui.getEventNameForAttendance();
            attendances = getAttendance(eventName);
            return new ClearAttendanceList(attendances, eventName);
        case "sort":
            eventName = ui.getEventNameForAttendance();
            attendances = getAttendance(eventName);
            return new SortAttendanceListByName(attendances);
        case "sort/":
            eventName = ui.getEventNameForAttendance();
            attendances = getAttendance(eventName);
            return new SortAttendanceListByStatus(attendances);
        default:
            throw new DukeException("Attendance: Unknown command.");
        }
    }

    private AttendanceList getAttendance(String eventName) throws DukeException {
        return eventList.getEvent(eventName).getAttendanceList();
    }
}
