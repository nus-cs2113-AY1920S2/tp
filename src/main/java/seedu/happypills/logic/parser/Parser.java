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

    /**
     * Parses the command given by the user to the other command parses.
     *
     * @param fullCommand the full command given by the user
     * @return the command entered by the user
     * @throws HappyPillsException throws an exception for invalid commands
     */
    public static Command parse(String fullCommand) throws HappyPillsException {
        fullCommand = fullCommand.trim();
        String[] userCommand = fullCommand.trim().split(" ", 3); // leading spaces removed
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
     * @param fullCommand the full command given by the user
     * @param userCommand the full command given by the user
     * @return the command based on the user input
     * @throws HappyPillsException if the user input does not conform the expected format
     */
    private static Command parseGeneralCommands(String fullCommand, String[] userCommand) throws HappyPillsException {
        if (userCommand[0].equalsIgnoreCase("help")) {
            return new HelpCommand(fullCommand);
        } else if (userCommand[0].equalsIgnoreCase("exit")) {
            return new ExitCommand();
        } else {
            throw new HappyPillsException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Prompt the user.
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
     * Loop the user prompting (y/n).
     *
     * @param output The output after the user enters (y).
     * @return true if the user enters y, false if the user enters n.
     */
    public static boolean loopPrompt(String output) {
        boolean userConfirmation = false;
        System.out.println(output);
        while (!userConfirmation) {
            String confirmation = promptUser();
            System.out.println(TextUi.DIVIDER);
            if (confirmation.equalsIgnoreCase("y")) {
                userConfirmation = true;
            } else if (confirmation.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("    Please input [y] for yes or [n] for no");
            }
        }
        return true;
    }

    /**
     * split the user input according to the tags.
     *
     * @param content the user input.
     * @return the array containing the split input.
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
