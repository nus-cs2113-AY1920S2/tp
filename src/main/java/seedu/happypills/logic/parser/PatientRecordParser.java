package seedu.happypills.logic.parser;

import seedu.happypills.HappyPills;
import seedu.happypills.logic.commands.patientrecordcommands.AddPatientRecordCommand;
import seedu.happypills.logic.commands.patientrecordcommands.DeletePatientRecordCommand;
import seedu.happypills.logic.commands.patientrecordcommands.EditPatientRecordCommand;
import seedu.happypills.logic.commands.patientrecordcommands.FindPatientRecordCommand;
import seedu.happypills.logic.commands.patientrecordcommands.IncorrectPatientRecordCommand;
import seedu.happypills.logic.commands.patientrecordcommands.ListPatientRecordCommand;
import seedu.happypills.logic.commands.patientrecordcommands.PatientRecordCommand;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.ui.Messages;
import seedu.happypills.ui.PatientRecordTextUi;
import seedu.happypills.ui.TextUi;

import java.util.Scanner;

//@@author NyanWunPaing
/**
 * This class is used to parse the user input for patient record commands.
 */
public class PatientRecordParser extends Parser {
    public static final String NRIC_TAG = "ic";
    public static final String SYMPTOM_TAG = "sym";
    public static final String DIAGNOSIS_TAG = "diag";
    public static final String DATE_TAG = "d";
    public static final String TIME_TAG = "t";

    /**
     * Parses user input for patient record into a command for execution.
     *
     * @param fullCommand the full command entered by the user
     * @return the command that the user has entered
     * @throws HappyPillsException if the user input does not conform the expected format
     */
    public static PatientRecordCommand parse(String fullCommand) throws HappyPillsException {
        String[] userCommand = fullCommand.split(" ", 3);
        boolean isCommandLengthOne = userCommand.length == 1;

        userCommand = trimArray(userCommand);

        if (userCommand[0].equalsIgnoreCase("list")) {
            return parsePatientRecordList(userCommand, isCommandLengthOne);
        } else if (userCommand[0].equalsIgnoreCase("add")) {
            return parsePatientRecordAdd(userCommand, isCommandLengthOne);
        } else if (userCommand[0].equalsIgnoreCase("find")) {
            return parsePatientRecordFind(userCommand, isCommandLengthOne);
        } else if (userCommand[0].equalsIgnoreCase("edit")) {
            return parsePatientRecordEdit(fullCommand);
        } else if (userCommand[0].equalsIgnoreCase("delete")) {
            return parsePatientRecordDelete(userCommand, isCommandLengthOne);
        } else {
            throw new HappyPillsException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private static PatientRecordCommand parsePatientRecordDelete(String[] userCommand, boolean isCommandLengthOne)
            throws HappyPillsException {
        checkEmptyNric(userCommand, isCommandLengthOne);
        String[] input = userCommand[2].split(" ", 2);
        input = trimArray(input);
        checkIndexValidity(input);
        checkNricValidity(input);
        return new DeletePatientRecordCommand(input[0].toUpperCase(), Integer.parseInt(input[1]));
    }

    private static void checkNricValidity(String[] input) throws HappyPillsException {
        if (!Checker.isValidNric(input[0].toUpperCase())) {
            throw new HappyPillsException(Messages.MESSAGE_INVALID_NRIC);
        }
    }

    private static void checkIndexValidity(String[] input) throws HappyPillsException {
        if (!Checker.isPositiveInteger(input[1])) {
            throw new HappyPillsException(Messages.MESSAGE_INVALID_INDEX);
        }
    }

    private static void checkEmptyNric(String[] userCommand, boolean isCommandLengthOne) throws HappyPillsException {
        if (isCommandLengthOne || userCommand[1].isEmpty()) {
            throw new HappyPillsException(Messages.MESSAGE_NRIC_NOT_PROVIDED);
        }
    }

    private static PatientRecordCommand parsePatientRecordFind(String[] userCommand, boolean isCommandLengthOne)
            throws HappyPillsException {
        checkEmptyNric(userCommand, isCommandLengthOne);
        String[] input = userCommand[2].split(" ", 2);
        input = trimArray(input);
        checkIndexValidity(input);
        checkNricValidity(input);
        return new FindPatientRecordCommand(input[0].toUpperCase(), Integer.parseInt(input[1]));
    }

    private static PatientRecordCommand parsePatientRecordAdd(String[] userCommand, boolean isCommandLengthOne)
            throws HappyPillsException {
        if (isCommandLengthOne || userCommand[1].isEmpty()) {
            throw new HappyPillsException(Messages.MESSAGE_PATIENT_RECORD_DETAILS_NOT_PROVIDED);
        }
        return parseAddCommand(userCommand[2]);
    }

    private static PatientRecordCommand parsePatientRecordList(String[] userCommand, boolean isCommandLengthOne)
            throws HappyPillsException {
        checkEmptyNric(userCommand, isCommandLengthOne);
        if (!Checker.isValidNric(userCommand[2].toUpperCase())) {
            throw new HappyPillsException(Messages.MESSAGE_INVALID_NRIC);
        }
        return new ListPatientRecordCommand(userCommand[2].toUpperCase());
    }

    private static String[] trimArray(String[] array) {
        String[] trimmedArray = new String[array.length];
        for (int size = 0; size < array.length; size++) {
            trimmedArray[size] = array[size].trim();
        }
        return trimmedArray;
    }

    private static PatientRecordCommand parsePatientRecordEdit(String fullCommand) throws HappyPillsException {
        String[] edit = fullCommand.split(" ", 5);
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
            String[] updates = splitInput(input);
            updates = trimArray(updates);
            updateInput(parseInput, updates);
        }

        if (!loopPrompt(PatientRecordTextUi.promptConfirmation(parseInput))) {
            return new IncorrectPatientRecordCommand(Messages.MESSAGE_PATIENT_RECORD_NOT_ADDED);
        }

        return new AddPatientRecordCommand(parseInput[0].toUpperCase(), parseInput[1],
                parseInput[2], parseInput[3], parseInput[4]);
    }

    private static String[] parseInput(String[] details, String[] parseInput) {
        for (String detail : details) {
            if (detail.startsWith(NRIC_TAG)) {
                parseInput[0] = detail.substring(2).trim().toUpperCase();
            } else if (detail.startsWith(SYMPTOM_TAG)) {
                parseInput[1] = detail.substring(3).trim();
            } else if (detail.startsWith(DIAGNOSIS_TAG)) {
                parseInput[2] = detail.substring(4).trim();
            } else if (detail.startsWith(DATE_TAG)) {
                parseInput[3] = detail.substring(1).trim();
            } else if (detail.startsWith(TIME_TAG)) {
                parseInput[4] = detail.substring(1).trim();
            } else {
                PatientRecordTextUi.patientRecordNotAddedMessage(detail);
            }
        }
        return parseInput;
    }

    private static void updateInput(String[] parseInput, String[] updates) {
        for (String update : updates) {
            if (update.startsWith(NRIC_TAG) && (parseInput[0].equalsIgnoreCase("")
                    || !Checker.isValidNric(parseInput[0]))) {
                parseInput[0] = update.substring(2).toUpperCase().trim();
            } else if (update.startsWith(SYMPTOM_TAG) && (parseInput[1].equalsIgnoreCase(""))) {
                parseInput[1] = update.substring(3).trim();
            } else if (update.startsWith(DIAGNOSIS_TAG) && parseInput[2].equalsIgnoreCase("")) {
                parseInput[2] = update.substring(4).trim();
            } else if (update.startsWith(DATE_TAG) && (parseInput[3].equalsIgnoreCase("")
                    || !Checker.isValidDate(parseInput[3]))) {
                parseInput[3] = update.substring(1).trim();
            } else if (update.startsWith(TIME_TAG) && (parseInput[4].equalsIgnoreCase("")
                    || !Checker.isValidTime(parseInput[4]))) {
                parseInput[4] = update.substring(1).trim();
            }
        }
    }

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
        System.out.println("    To abort, enter \"clear\"");
    }
}
