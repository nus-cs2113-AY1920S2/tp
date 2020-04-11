package seedu.happypills.logic.parser;

import seedu.happypills.HappyPills;
import seedu.happypills.logic.commands.patientrecordcommands.AddPatientRecordCommand;
import seedu.happypills.logic.commands.patientrecordcommands.DeletePatientRecordCommand;
import seedu.happypills.logic.commands.patientrecordcommands.EditPatientRecordCommand;
import seedu.happypills.logic.commands.patientrecordcommands.FindPatientRecordCommand;
import seedu.happypills.logic.commands.patientrecordcommands.ListPatientRecordCommand;
import seedu.happypills.logic.commands.patientrecordcommands.PatientRecordCommand;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.ui.Messages;
import seedu.happypills.ui.PatientRecordTextUi;
import seedu.happypills.ui.TextUi;

import java.util.logging.Logger;

//@@author NyanWunPaing

/**
 * Parses the user input for patient record commands.
 */
public class PatientRecordParser extends Parser {
    public static final String NRIC_TAG = "ic";
    public static final String SYMPTOM_TAG = "sym";
    public static final String DIAGNOSIS_TAG = "diag";
    public static final String DATE_TAG = "d";
    public static final String TIME_TAG = "t";
    static Logger logger = Logger.getLogger(HappyPills.class.getName());

    /**
     * Parses user input for patient record into a command for execution.
     *
     * @param fullCommand The full command entered by the user.
     * @return The command that the user has entered.
     * @throws HappyPillsException If the user input does not conform the expected format.
     */
    public static PatientRecordCommand parse(String fullCommand) throws HappyPillsException {
        String[] userCommand = fullCommand.split("\\s+", 3);
        boolean isCommandLengthOne = userCommand.length == 1;

        userCommand = trimArray(userCommand);

        if (userCommand[0].equalsIgnoreCase("list")) {
            return checkListPatientRecord(userCommand, isCommandLengthOne);
        } else if (userCommand[0].equalsIgnoreCase("add")) {
            return checkAddPatientRecord(userCommand, isCommandLengthOne);
        } else if (userCommand[0].equalsIgnoreCase("find")) {
            return checkFindPatientRecord(userCommand, isCommandLengthOne);
        } else if (userCommand[0].equalsIgnoreCase("edit")) {
            return checkEditPatientRecord(fullCommand);
        } else if (userCommand[0].equalsIgnoreCase("delete")) {
            return checkDeletePatientRecord(userCommand, isCommandLengthOne);
        } else {
            logger.info("Invalid Command");
            throw new HappyPillsException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Checks whether the user inputs conforms to the delete command format.
     *
     * @param userCommand        The input given by the user.
     * @param isCommandLengthOne Boolean that indicates whether the command conforms to the length.
     * @return PatientRecordCommand The command indicated by the user.
     * @throws HappyPillsException If the user input does not conforms to the expected format.
     */
    private static PatientRecordCommand checkDeletePatientRecord(String[] userCommand, boolean isCommandLengthOne)
            throws HappyPillsException {
        checkEmptyNric(userCommand, isCommandLengthOne);
        String[] input = userCommand[2].split("\\s+", 2);
        input = trimArray(input);
        checkIndexValidity(input);
        checkNricValidity(input);
        return new DeletePatientRecordCommand(input[0].toUpperCase(), Integer.parseInt(input[1]));
    }

    /**
     * Checks whether the NRIC given by the user conforms to the NRIC format.
     *
     * @param input Input given by the user.
     * @throws HappyPillsException If the NRIC does not conforms to the expected format.
     */
    private static void checkNricValidity(String[] input) throws HappyPillsException {
        if (!Checker.isValidNric(input[0].toUpperCase())) {
            throw new HappyPillsException(Messages.MESSAGE_INVALID_NRIC);
        }
    }

    /**
     * Checks whether the index given by the user is an integer.
     *
     * @param input Input given by the user.
     * @throws HappyPillsException If the index does not conforms to the expected format.
     */
    private static void checkIndexValidity(String[] input) throws HappyPillsException {
        if (!Checker.isPositiveInteger(input[1])) {
            throw new HappyPillsException(Messages.MESSAGE_INVALID_INDEX);
        }
    }

    /**
     * Checks whether the user provides the NRIC of the patient in the user input.
     *
     * @param userCommand        User input given by the user.
     * @param isCommandLengthOne Boolean that indicates whether the command conforms to the length.
     * @throws HappyPillsException If the index does not conforms to the expected format.
     */
    private static void checkEmptyNric(String[] userCommand, boolean isCommandLengthOne) throws HappyPillsException {
        if (isCommandLengthOne || userCommand[1].isEmpty()) {
            throw new HappyPillsException(Messages.MESSAGE_NRIC_NOT_PROVIDED);
        }
    }

    /**
     * Checks whether the user inputs conforms to the find command format.
     *
     * @param userCommand        User input given by the user.
     * @param isCommandLengthOne Boolean that indicates whether the command conforms to the length.
     * @return PatientRecordCommand The command indicated by the user.
     * @throws HappyPillsException If the NRIC or index does not conforms to the expected format.
     */
    private static PatientRecordCommand checkFindPatientRecord(String[] userCommand, boolean isCommandLengthOne)
            throws HappyPillsException {
        checkEmptyNric(userCommand, isCommandLengthOne);
        String[] input = userCommand[2].split("\\s+", 2);
        input = trimArray(input);
        checkIndexValidity(input);
        checkNricValidity(input);
        return new FindPatientRecordCommand(input[0].toUpperCase(), Integer.parseInt(input[1]));
    }

    /**
     * Checks whether the user inputs conforms to the add command format.
     *
     * @param userCommand        User input given by the user.
     * @param isCommandLengthOne Boolean that indicates whether the command conforms to the length.
     * @return PatientRecordCommand The command indicated by the user.
     * @throws HappyPillsException If the user input does not conforms to the expected format.
     */
    private static PatientRecordCommand checkAddPatientRecord(String[] userCommand, boolean isCommandLengthOne)
            throws HappyPillsException {
        if (isCommandLengthOne || userCommand[1].isEmpty()) {
            throw new HappyPillsException(Messages.MESSAGE_PATIENT_RECORD_DETAILS_NOT_PROVIDED);
        }
        return parseAddCommand(userCommand[2]);
    }

    /**
     * Checks whether the user inputs conforms to the list command format.
     *
     * @param userCommand        User command entered by the user.
     * @param isCommandLengthOne Boolean that indicates whether the command conforms to the length.
     * @return PatientRecordCommand The command indicated by the user.
     * @throws HappyPillsException If the user input does not conforms to the expected format.
     */
    private static PatientRecordCommand checkListPatientRecord(String[] userCommand, boolean isCommandLengthOne)
            throws HappyPillsException {
        checkEmptyNric(userCommand, isCommandLengthOne);
        if (!Checker.isValidNric(userCommand[2].toUpperCase())) {
            logger.info("Invalid NRIC");
            throw new HappyPillsException(Messages.MESSAGE_INVALID_NRIC);
        }
        return new ListPatientRecordCommand(userCommand[2].toUpperCase());
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
     * Checks whether the user inputs conforms to the edit command format.
     *
     * @param fullCommand The entire line of command entered by the user.
     * @return PatientRecordCommand The command indicated by the user.
     * @throws HappyPillsException If the user input does not conforms to the expected format.
     */
    private static PatientRecordCommand checkEditPatientRecord(String fullCommand) throws HappyPillsException {
        String[] edit = fullCommand.split("\\s+", 5);
        if (edit.length < 4) {
            throw new HappyPillsException(Messages.MESSAGE_MISSING_FIELD);
        }
        if (!Checker.isPositiveInteger(edit[3].trim())) {
            throw new HappyPillsException(Messages.MESSAGE_INVALID_INDEX);
        }
        if (!Checker.isValidNric(edit[2].toUpperCase().trim())) {
            throw new HappyPillsException(Messages.MESSAGE_INVALID_NRIC);
        }
        return new EditPatientRecordCommand(
                edit[2].trim().toUpperCase(), Integer.parseInt(edit[3].trim()), edit[4].trim());
    }

    private static boolean isInputEmpty(String input) {
        return input.equalsIgnoreCase("");
    }

    /**
     * Checks whether there are any missing fields in the add patient record command.
     *
     * @param parseInput The array that stores the user input.
     * @return Boolean which indicates whether the format is correct or incorrect.
     */
    private static boolean hasMissingFields(String[] parseInput) {
        for (int index = 0; index < 5; index++) {
            if (parseInput[index].equalsIgnoreCase("")) {
                return true;
            }
        }
        boolean isIncorrectFormat = !Checker.isValidNric(parseInput[0])
                || !Checker.isValidDate(parseInput[3]) || !Checker.isValidTime(parseInput[4]);
        return isIncorrectFormat;
    }

    /**
     * Handles the add patient command.
     *
     * @param content The entire line of command entered by the user.
     * @return PatientRecordCommand The command indicated by the user.
     * @throws HappyPillsException If the user aborted the add command.
     */
    private static PatientRecordCommand parseAddCommand(String content) throws HappyPillsException {
        String[] details;
        details = splitInput(content);
        String[] parseInput = {"", "", "", "", "", ""};
        parseInput = parseInput(details, parseInput);
        while (hasMissingFields(parseInput)) {
            printMissingFields(parseInput);
            String input = promptUser().trim();
            if (input.equalsIgnoreCase("clear")) {
                throw new HappyPillsException(Messages.MESSAGE_COMMAND_ABORTED);
            }
            System.out.println(TextUi.DIVIDER);
            String[] updates = splitInput(input);
            updates = trimArray(updates);
            parseInput(updates, parseInput);
        }

        if (!loopPrompt(PatientRecordTextUi.promptConfirmation(parseInput))) {
            throw new HappyPillsException(Messages.MESSAGE_PATIENT_RECORD_NOT_ADDED);
        }

        return new AddPatientRecordCommand(parseInput[0].toUpperCase(), parseInput[1],
                parseInput[2], parseInput[3], parseInput[4]);
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
            if (detail.startsWith(NRIC_TAG) && detail.trim().length() > 3) {
                parseInput[0] = detail.substring(2).trim().toUpperCase();
            } else if (detail.startsWith(SYMPTOM_TAG) && detail.trim().length() > 3) {
                parseInput[1] = detail.substring(3).trim();
            } else if (detail.startsWith(DIAGNOSIS_TAG) && detail.trim().length() > 3) {
                parseInput[2] = detail.substring(4).trim();
            } else if (detail.startsWith(DATE_TAG) && detail.trim().length() > 3) {
                parseInput[3] = detail.substring(1).trim();
            } else if (detail.startsWith(TIME_TAG) && detail.trim().length() > 3) {
                parseInput[4] = detail.substring(1).trim();
            } else {
                PatientRecordTextUi.patientRecordNotAddedMessage(detail);
            }
        }
        return parseInput;
    }

    /**
     * Displays missing fields to the user.
     *
     * @param parseInput The array that stores the user input.
     */
    private static void printMissingFields(String[] parseInput) {
        System.out.println("    Please input your missing/incorrect detail listed below");
        if (isInputEmpty(parseInput[0]) || !Checker.isValidNric(parseInput[0])) {
            System.out.println(Messages.MESSAGE_NRIC_FORMAT);
        }
        if (parseInput[1].equalsIgnoreCase("")) {
            System.out.println(Messages.MESSAGE_SYMPTOM_FORMAT);
        }
        if (parseInput[2].equalsIgnoreCase("")) {
            System.out.println(Messages.MESSAGE_DIAGNOSIS_FORMAT);
        }
        if (parseInput[3].equalsIgnoreCase("") || !Checker.isValidDate(parseInput[3])) {
            System.out.println(Messages.MESSAGE_DATE_FORMAT);
        }
        if (parseInput[4].equalsIgnoreCase("") || !Checker.isValidTime(parseInput[4])) {
            System.out.println(Messages.MESSAGE_TIME_FORMAT);
        }
        System.out.println(Messages.MESSAGE_CLEAR_COMMAND);
    }
}
