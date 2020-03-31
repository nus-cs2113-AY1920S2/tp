package seedu.happypills.logic.parser;

import seedu.happypills.logic.commands.Command;
import seedu.happypills.logic.commands.ExitCommand;
import seedu.happypills.logic.commands.HelpCommand;
import seedu.happypills.logic.commands.appointmentcommands.IncorrectAppointmentCommand;
import seedu.happypills.model.data.PatientRecord;
import seedu.happypills.model.exception.HappyPillsException;

import java.time.format.DateTimeFormatter;

public class Parser {

    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Parses the command given by the user to the other command parses.
     * @param fullCommand the full command given by the user.
     * @return the command entered by the user.
     * @throws HappyPillsException throws an exception for invalid commands.
     */
    public static Command parse(String fullCommand) throws HappyPillsException {
        String[] userCommand = fullCommand.split(" ", 3);
        if (userCommand.length == 1) {
            // help & exit
            if (userCommand[0].equalsIgnoreCase("help")) {
                return new HelpCommand(fullCommand);
            } else if (userCommand[0].equalsIgnoreCase("exit")) {
                // exit command
                return new ExitCommand();
            } else {
                // incorrect command
                return new IncorrectAppointmentCommand("    Command is invalid. "
                        + "Enter help to know how to use HappyPills.\n");
            }
        } else if (userCommand[0].equalsIgnoreCase("help")) {
            return new HelpCommand(fullCommand);
        } else if (userCommand[1].equalsIgnoreCase("patient")) {
            return PatientParser.parse(fullCommand);
        } else if (userCommand[1].equalsIgnoreCase("appt")) {
            return AppointmentParser.parse(fullCommand);
        } else if (userCommand[1].equalsIgnoreCase("pr")) {
            return PatientRecordParser.parse(fullCommand);
        } else {
            return new IncorrectAppointmentCommand("    Command is invalid. "
                   + "Enter help to know how to use HappyPills.\n");
        }
    }
}
