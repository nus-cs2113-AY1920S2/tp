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

    public static void setupLogger() throws PacException {
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
            try {
                UI.display("Please Key in either 'name' or 'status'.");
                ui.readUserInput();
                String sortType = ui.getUserInput();
                switch (sortType) {
                case "name":
                    try {
                        eventName = ui.getEventNameForAttendance();
                        attendances = getAttendance(eventName);
                        return new SortAttendanceListByName(attendances, eventName);
                    } catch (Exception e) {
                        throw new PacException("Attendance Command Sort By Name failed.");
                    }
                case "status":
                    try {
                        eventName = ui.getEventNameForAttendance();
                        attendances = getAttendance(eventName);
                        return new SortAttendanceListByStatus(attendances, eventName);
                    } catch (Exception e) {
                        throw new PacException("Attendance Command Sort By Status failed.");
                    }
                default:
                    throw new PacException("Unknown Attendance Sort Command");
                }
            } catch (Exception e) {
                throw new PacException("Attendance Command Sort failed.");
            }
        default:
            throw new PacException("Attendance: Unknown command.");
        }
    }

    private AttendanceList getAttendance(String eventName) throws PacException {
        return eventList.getEvent(eventName).getAttendanceList();
    }
}
