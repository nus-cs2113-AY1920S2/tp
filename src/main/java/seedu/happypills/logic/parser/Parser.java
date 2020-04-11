package seedu.happypills.logic.parser;

import seedu.happypills.HappyPills;
import seedu.happypills.logic.commands.Command;
import seedu.happypills.logic.commands.ExitCommand;
import seedu.happypills.logic.commands.HelpCommand;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.ui.Messages;
import seedu.happypills.ui.TextUi;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

//@@ nyanwunpaing
/**
 * Parses user input.
 */
public class Parser {
    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final String PATIENT_TAG = "patient";
    public static final String HELP_TAG = "help";
    public static final String APPOINTMENT_TAG = "appt";
    public static final String PATIENT_RECORD_TAG = "pr";
    public static final String EXIT_TAG = "exit";
    public static final String YES_TAG = "y";
    public static final String NO_TAG = "n";

    /**
     * Parses the command given by the user to the other command parses.
     *
     * @param fullCommand Full command given by the user.
     * @return The command entered by the user.
     * @throws HappyPillsException If commands are invalid.
     */
    public static Command parse(String fullCommand) throws HappyPillsException {
        fullCommand = fullCommand.trim();
        String[] userCommand = fullCommand.trim().split("\\s+", 3); // leading spaces removed
        if (userCommand.length == 1) {
            return parseGeneralCommands(fullCommand, userCommand);
        } else if (userCommand[0].equalsIgnoreCase(HELP_TAG)) {
            return new HelpCommand(fullCommand);
        } else if (userCommand[1].equalsIgnoreCase(PATIENT_TAG)) {
            return PatientParser.parse(fullCommand);
        } else if (userCommand[1].equalsIgnoreCase(APPOINTMENT_TAG)) {
            return AppointmentParser.parse(fullCommand);
        } else if (userCommand[1].equalsIgnoreCase(PATIENT_RECORD_TAG)) {
            return PatientRecordParser.parse(fullCommand);
        } else {
            throw new HappyPillsException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses and executes general command.
     *
     * @param fullCommand Full command given by the user.
     * @param userCommand User command given by the user.
     * @return The command based on the user input.
     * @throws HappyPillsException If the user input does not conform the expected format.
     */
    private static Command parseGeneralCommands(String fullCommand, String[] userCommand) throws HappyPillsException {
        if (userCommand[0].equalsIgnoreCase(HELP_TAG)) {
            return new HelpCommand(fullCommand);
        } else if (userCommand[0].equalsIgnoreCase(EXIT_TAG)) {
            return new ExitCommand();
        } else {
            throw new HappyPillsException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Prompts the user.
     *
     * @return The string entered by the user.
     */
    public static String promptUser() {
        System.out.println(TextUi.DIVIDER);
        Scanner in = HappyPills.scanner;
        String reInput = in.nextLine();
        return reInput;
    }

    /**
     * Prompts the user for missing fields or incorrect information.
     * Prompts the user for confirmation for add commands.
     *
     * @param output Output after the user enters (y).
     * @return True if the user enters y, false if the user enters (n).
     */
    public static boolean loopPrompt(String output) {
        boolean userConfirmation = false;
        System.out.println(output);
        while (!userConfirmation) {
            String confirmation = promptUser().trim();
            System.out.println(TextUi.DIVIDER);
            if (confirmation.equalsIgnoreCase(YES_TAG)) {
                userConfirmation = true;
            } else if (confirmation.equalsIgnoreCase(NO_TAG)) {
                return false;
            } else {
                System.out.println(Messages.MESSAGE_USER_CONFIRMATION);
            }
        }
        return true;
    }

    /**
     * Splits the user input according to the '/'.
     *
     * @param content The user input.
     * @return The array containing the split input.
     */
    public static String[] splitInput(String content) {
        String[] details;
        if (content.startsWith("/")) {
            details = content.substring(1).split(" /");
        } else {
            content = "@" + content;
            details = content.split(" /");
        }
        return details;
    }
}
