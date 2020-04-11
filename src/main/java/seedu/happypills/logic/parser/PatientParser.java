package seedu.happypills.logic.parser;

import seedu.happypills.logic.commands.patientcommands.AddPatientCommand;
import seedu.happypills.logic.commands.patientcommands.DeletePatientCommand;
import seedu.happypills.logic.commands.patientcommands.EditPatientCommand;
import seedu.happypills.logic.commands.patientcommands.GetPatientCommand;
import seedu.happypills.logic.commands.patientcommands.ListPatientCommand;
import seedu.happypills.logic.commands.patientcommands.PatientCommand;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.ui.Messages;
import seedu.happypills.ui.PatientTextUi;
import seedu.happypills.ui.TextUi;

//@@author NyanWunPaing

/**
 * Parses user input.
 */
public class PatientParser extends Parser {
    public static final String NRIC_TAG = "ic";
    public static final String NAME_TAG = "n";
    public static final String PHONE_NUMBER_TAG = "p";
    public static final String DATE_OF_BIRTH_TAG = "dob";
    public static final String BLOOD_TYPE_TAG = "b";
    public static final String ALLERGIES_TAG = "a";
    public static final String REMARKS_TAG = "rm";
    public static final String CLEAR_TAG = "clear";

    /**
     * Parses user input for patient record into a command for execution.
     *
     * @param fullCommand Full command entered by the user.
     * @return The command that the user has entered.
     * @throws HappyPillsException If the wrong command/format is given by the user as an input.
     */
    public static PatientCommand parse(String fullCommand) throws HappyPillsException {
        String[] userCommand = fullCommand.trim().split("\\s+", 3);
        boolean isCommandLengthOne = userCommand.length == 1;

        userCommand = trimArray(userCommand);

        if (userCommand[0].trim().equalsIgnoreCase("list")) {
            return checkListCommand(userCommand, isCommandLengthOne);
        } else if (userCommand[0].trim().equalsIgnoreCase("add")) {
            return checkAddCommand(userCommand, isCommandLengthOne);
        } else if (userCommand[0].trim().equalsIgnoreCase("get")) {
            return checkGetCommand(userCommand, isCommandLengthOne);
        } else if (userCommand[0].equalsIgnoreCase("edit")) {
            return checkEditCommand(fullCommand);
        } else if (userCommand[0].equalsIgnoreCase("delete")) {
            return checkDeleteCommand(userCommand, isCommandLengthOne);
        } else {
            throw new HappyPillsException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private static PatientCommand checkDeleteCommand(String[] userCommand, boolean isCommandLengthOne)
            throws HappyPillsException {
        if (userCommand.length != 3 || isCommandLengthOne) {
            throw new HappyPillsException(Messages.MESSAGE_INCORRECT_INPUT_FORMAT);
        }
        if (!Checker.isValidNric(userCommand[2].toUpperCase())) {
            throw new HappyPillsException(Messages.MESSAGE_INVALID_NRIC);
        }
        return new DeletePatientCommand(userCommand[2]);
    }

    /**
     * Checks whether the user inputs conforms to the list command format.
     *
     * @param userCommand        User command entered by the user.
     * @param isCommandLengthOne Boolean that indicates whether the command conforms to the length.
     * @return PatientCommand The command indicated by the user.
     * @throws HappyPillsException If the user input does not conforms to the expected format.
     */
    private static PatientCommand checkListCommand(String[] userCommand, boolean isCommandLengthOne)
            throws HappyPillsException {
        if (userCommand.length != 2 || isCommandLengthOne) {
            throw new HappyPillsException(Messages.MESSAGE_INCORRECT_INPUT_FORMAT);
        }
        return new ListPatientCommand();
    }

    /**
     * Trims in the user input.
     *
     * @param array The inputs given by the user.
     * @return Trimmed array.
     */
    private static String[] trimArray(String[] array) {
        String[] trimmedArray = new String[array.length];
        for (int size = 0; size < array.length; size++) {
            trimmedArray[size] = array[size].trim();
        }
        return trimmedArray;
    }

    /**
     * Checks whether the user inputs conforms to the get command format.
     *
     * @param userCommand        User input given by the user.
     * @param isCommandLengthOne Boolean that indicates whether the command conforms to the length.
     * @return PatientCommand The command indicated by the user.
     * @throws HappyPillsException If the user input does not conforms to the expected format.
     */
    private static PatientCommand checkGetCommand(String[] userCommand, boolean isCommandLengthOne)
            throws HappyPillsException {
        if (isCommandLengthOne || userCommand[1].isEmpty()) {
            throw new HappyPillsException(Messages.MESSAGE_NRIC_NOT_PROVIDED);
        }
        if (!Checker.isValidNric(userCommand[2].toUpperCase())) {
            throw new HappyPillsException(Messages.MESSAGE_INVALID_NRIC);
        }
        return new GetPatientCommand(userCommand[2].toUpperCase());
    }

    /**
     * Checks whether the user inputs conforms to the edit command format.
     *
     * @param fullCommand The entire line of command entered by the user.
     * @return PatientCommand The command indicated by the user.
     * @throws HappyPillsException If the user input does not conforms to the expected format.
     */
    private static PatientCommand checkEditCommand(String fullCommand) throws HappyPillsException {
        String[] edit = fullCommand.split("\\s+", 4);
        if (edit.length < 3) {
            throw new HappyPillsException(Messages.MESSAGE_MISSING_FIELD);
        }
        if (!Checker.isValidNric(edit[2].toUpperCase())) {
            throw new HappyPillsException(Messages.MESSAGE_INVALID_NRIC);
        }
        return new EditPatientCommand(edit[2], edit[3]);
    }

    /**
     * Checks whether the user inputs conforms to the add command format.
     *
     * @param userCommand        User input given by the user.
     * @param isCommandLengthOne Boolean that indicates whether the command conforms to the length.
     * @return PatientCommand The command indicated by the user.
     * @throws HappyPillsException If the user input does not conforms to the expected format.
     */
    private static PatientCommand checkAddCommand(String[] userCommand, boolean isCommandLengthOne)
            throws HappyPillsException {
        if (userCommand[1].isEmpty() || isCommandLengthOne) {
            throw new HappyPillsException(Messages.MESSAGE_PATIENT_DETAILS_NOT_PROVIDED);
        }
        return parseAddCommand(userCommand[2]);
    }

    private static boolean isInputEmpty(String input) {
        return input.equalsIgnoreCase("");
    }

    /**
     * Checks whether the user input have missing fields or inputs with incorrect format.
     *
     * @param parseInput The array that stores the user input.
     * @return True if there is any missing/incorrect field.
     */
    private static boolean hasMissingFields(String[] parseInput) {
        for (int index = 0; index < 5; index++) {
            if (parseInput[index].equalsIgnoreCase("")) {
                return true;
            }
        }
        boolean isIncorrectFormat = !Checker.isValidNric(parseInput[1].trim())
                || !Checker.isValidDate(parseInput[3].trim()) || !Checker.isValidBloodType(parseInput[4].trim())
                || !Checker.isValidPhoneNum(parseInput[2].trim());
        return isIncorrectFormat;
    }

    /**
     * Displays missing fields to the user.
     *
     * @param parseInput The array that stores the user input.
     */
    private static void printMissingFields(String[] parseInput) {
        System.out.println("    Please input your missing/incorrect detail listed below");
        if (isInputEmpty(parseInput[0])) {
            System.out.println(Messages.MESSAGE_NAME_FORMAT);
        }
        if (parseInput[1].equalsIgnoreCase("") || !Checker.isValidNric(parseInput[1])) {
            System.out.println(Messages.MESSAGE_NRIC_FORMAT);
        }
        if (parseInput[2].equalsIgnoreCase("") || !Checker.isValidPhoneNum(parseInput[2])) {
            System.out.println(Messages.MESSAGE_PHONE_NUMBER_FORMAT);
        }
        if (parseInput[3].equalsIgnoreCase("") || !Checker.isValidDate(parseInput[3])) {
            System.out.println(Messages.MESSAGE_DATE_OF_BIRTH_FORMAT);
        }
        if (parseInput[4].equalsIgnoreCase("") || !Checker.isValidBloodType(parseInput[4])) {
            System.out.println(Messages.MESSAGE_BLOOD_TYPE_FORMAT);
        }
        System.out.println(Messages.MESSAGE_CLEAR_COMMAND);
    }

    /**
     * Handles the add patient command.
     *
     * @param content The entire line of command entered by the user.
     * @return PatientCommand The command indicated by the user.
     * @throws HappyPillsException If the user aborted the add command.
     */
    private static PatientCommand parseAddCommand(String content) throws HappyPillsException {
        String[] details = splitInput(content);
        String[] parseInput = {"", "", "", "", "", "NIL", "NIL"};
        parseInput = parseInput(details, parseInput);

        while (hasMissingFields(parseInput)) {
            printMissingFields(parseInput);
            String input = promptUser().trim();
            if (input.equalsIgnoreCase(CLEAR_TAG)) {
                throw new HappyPillsException(Messages.MESSAGE_COMMAND_ABORTED);
            }
            System.out.println(TextUi.DIVIDER);
            String[] updates = splitInput(input);
            updates = trimArray(updates);
            parseInput(updates, parseInput);
        }

        if (!loopPrompt(promptConfirmation(parseInput))) {
            throw new HappyPillsException(Messages.MESSAGE_PATIENT_RECORD_NOT_ADDED); // check
        }
        return new AddPatientCommand(parseInput[0].trim(), parseInput[1].toUpperCase().trim(),
                Integer.parseInt(parseInput[2].trim()), parseInput[3].trim(), parseInput[4].trim(),
                parseInput[5].trim(), parseInput[6].trim());
    }

    /**
     * Updates the parsedInput array based on the newly entered inputs for add command prompt.
     *
     * @param details    The user input entered by the user.
     * @param parseInput The array that stores the user input.
     * @return parseInput The updated array.
     */
    private static String[] parseInput(String[] details, String[] parseInput) {
        for (String detail : details) {
            if (detail.startsWith(NAME_TAG) && detail.trim().length() > 3) {
                parseInput[0] = detail.substring(1).trim();
            } else if (detail.startsWith(NRIC_TAG) && detail.trim().length() > 3) {
                parseInput[1] = detail.substring(2).trim().toUpperCase();
            } else if (detail.startsWith(PHONE_NUMBER_TAG) && detail.trim().length() > 3) {
                parseInput[2] = detail.substring(1).trim();
            } else if (detail.startsWith(DATE_OF_BIRTH_TAG) && detail.trim().length() > 3) {
                parseInput[3] = detail.substring(3).trim();
            } else if (detail.startsWith(BLOOD_TYPE_TAG) && detail.trim().length() > 3) {
                parseInput[4] = detail.substring(1).trim().toUpperCase();
            } else if (detail.startsWith(ALLERGIES_TAG) && detail.trim().length() > 3) {
                parseInput[5] = detail.substring(1).trim();
            } else if (detail.startsWith(REMARKS_TAG) && detail.trim().length() > 3) {
                parseInput[6] = detail.substring(2).trim();
            } else {
                PatientTextUi.patientNotAddedMessage(detail);
            }
        }
        return parseInput;
    }

    /**
     * Prompts user for confirmation by displaying their previously entered inputs.
     *
     * @param parseInput The array that stores the user input.
     * @return text The string to be displayed for user's confirmation.
     */
    public static String promptConfirmation(String[] parseInput) {
        String text = "        Are you sure all the listed details are correct?\n"
                + "        Name : " + parseInput[0].trim() + "\n"
                + "        NRIC : " + parseInput[1].toUpperCase().trim() + "\n"
                + "        Phone Number : " + parseInput[2].trim() + "\n"
                + "        DOB : " + parseInput[3].trim() + "\n"
                + "        Blood Type : " + parseInput[4].trim() + "\n"
                + "        Allergies : " + parseInput[5].trim() + "\n"
                + "        Remarks : " + parseInput[6].trim() + "\n"
                + "                                                   (Y/N)?";
        return text;
    }
}
