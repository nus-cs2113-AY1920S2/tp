package seedu.command;

import seedu.command.attendance.AddAttendance;
import seedu.command.attendance.DeleteAttendance;
import seedu.event.EventList;
import seedu.exception.DukeException;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;

public class AttendanceCommandInterpreter extends CommandInterpreter {
    private static final Logger logger = Logger.getLogger(AttendanceCommandInterpreter.class.getName());

    public AttendanceCommandInterpreter(EventList eventList) {
        super(eventList);
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

        AttendanceCommandInterpreter.setupLogger();
        logger.info("My First Log");
        logger.fine("My Second Log");

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
