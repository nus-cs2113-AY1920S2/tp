package seedu.happypills.logic.parser;

import seedu.happypills.HappyPills;
import seedu.happypills.logic.commands.patientcommands.AddPatientCommand;
import seedu.happypills.logic.commands.patientcommands.DeletePatientCommand;
import seedu.happypills.logic.commands.patientcommands.EditPatientCommand;
import seedu.happypills.logic.commands.patientcommands.PatientCommand;
import seedu.happypills.logic.commands.patientcommands.IncorrectPatientCommand;
import seedu.happypills.logic.commands.patientcommands.ListPatientCommand;
import seedu.happypills.logic.commands.patientcommands.GetPatientCommand;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.ui.Messages;
import seedu.happypills.ui.PatientTextUi;
import seedu.happypills.ui.TextUi;

import java.util.Scanner;

//@@author NyanWunPaing
/**
 * Parses user input.
 */
public class PatientParser {
    public static final String NRIC_TAG = "ic";
    public static final String NAME_TAG = "n";
    public static final String PHONE_NUMBER_TAG = "p";
    public static final String DATE_OF_BIRTH_TAG = "dob";
    public static final String BLOOD_TYPE_TAG = "b";
    public static final String ALLERGIES_TAG = "a";
    public static final String REMARKS_TAG = "rm";

    /**
     * Parses user input for patient record into a command for execution.
     *
     * @param fullCommand the full command entered by the user
     * @return the command that the user has entered
     * @throws HappyPillsException if the user input does not conform the expected format
     */
    public static PatientCommand parse(String fullCommand) throws HappyPillsException {
        String[] userCommand = fullCommand.trim().split(" ", 3);
        boolean isCommandLengthOne = userCommand.length == 1;

        userCommand = trimArray(userCommand);

        if (userCommand[0].trim().equalsIgnoreCase("list")) {
            return parsePatientList(userCommand, isCommandLengthOne);
        } else if (userCommand[0].trim().equalsIgnoreCase("add")) {
            return parsePatientAdd(userCommand, isCommandLengthOne);
        } else if (userCommand[0].trim().equalsIgnoreCase("get")) {
            return parsePatientGet(userCommand, isCommandLengthOne);
        } else if (userCommand[0].equalsIgnoreCase("edit")) {
            return parsePatientEdit(fullCommand);
        } else if (userCommand[0].equalsIgnoreCase("delete")) {
            if (userCommand.length != 3 || isCommandLengthOne) {
                throw new HappyPillsException(Messages.MESSAGE_INCORRECT_INPUT_FORMAT);
            }
            return new DeletePatientCommand(userCommand[2]);
        } else {
            throw new HappyPillsException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private static PatientCommand parsePatientList(String[] userCommand, boolean isCommandLengthOne)
            throws HappyPillsException {
        if (userCommand.length != 2 || isCommandLengthOne) {
            throw new HappyPillsException(Messages.MESSAGE_INCORRECT_INPUT_FORMAT);
        }
        return new ListPatientCommand();
    }

    private static String[] trimArray(String[] array) {
        String[] trimmedArray = new String[array.length];
        for (int size = 0; size < array.length; size++) {
            trimmedArray[size] = array[size].trim();
        }
        return trimmedArray;
    }

    private static PatientCommand parsePatientGet(String[] userCommand, boolean isCommandLengthOne)
            throws HappyPillsException {
        if (isCommandLengthOne || userCommand[1].isEmpty()) {
            throw new HappyPillsException(Messages.MESSAGE_NRIC_NOT_PROVIDED);
        }
        return new GetPatientCommand(userCommand[2].toUpperCase());
    }

    private static PatientCommand parsePatientEdit(String fullCommand) throws HappyPillsException {
        String[] edit = fullCommand.split(" ", 4);
        if (edit.length < 3) {
            throw new HappyPillsException(Messages.MESSAGE_MISSING_FIELD);
        }
        return new EditPatientCommand(edit[2], edit[3]);
    }

    private static PatientCommand parsePatientAdd(String[] userCommand, boolean isCommandLengthOne)
            throws HappyPillsException {
        if (userCommand[1].isEmpty() || isCommandLengthOne) {
            throw new HappyPillsException(Messages.MESSAGE_PATIENT_DETAILS_NOT_PROVIDED);
        }
        return parseAddCommand(userCommand[2]);
    }

    private static boolean isInputEmpty(String input) {
        return input.equalsIgnoreCase("");
    }

    private static boolean isValidPhoneNum(String input, String detail) {
        return isInputEmpty(input)
                && Checker.isValidPhoneNum(detail.substring(1).trim());
    }

    private static boolean isValidDateOfBirth(String input, String detail) {
        return isInputEmpty(input)
                && Checker.isValidDate(detail.substring(3).trim());
    }

    private static boolean isValidBloodType(String input, String detail) {
        return isInputEmpty(input)
                && Checker.isValidBloodType(detail.substring(1).trim());
    }

    private static boolean isValidNric(String input, String detail) {
        return isInputEmpty(input)
                && Checker.isValidNric(detail.substring(2).trim().toUpperCase());
    }

    private static boolean hasMissingFields(String[] parseInput) {
        for (int index = 0; index < 5; index++) {
            if (parseInput[index].equalsIgnoreCase("")) {
                return true;
            }
        }
        boolean isIncorrectFormat = !Checker.isValidNric(parseInput[1])
                || !Checker.isValidDate(parseInput[3]) || !Checker.isValidBloodType(parseInput[4])
                || !Checker.isValidPhoneNum(parseInput[2].trim());
        return isIncorrectFormat;
    }

    private static void printMissingFields(String[] parseInput) {
        System.out.println(Messages.MESSAGE_INFORM_MISSING);
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
            System.out.println(Messages.MESSAGE_TIME_FORMAT);
        }
    }

    private static PatientCommand parseAddCommand(String content) throws HappyPillsException {
        String[] details;
        details = checkInput(content);
        String[] parseInput = {"", "", "", "", "", "NIL", "NIL"};

        for (String detail : details) {
            if (detail.startsWith(NAME_TAG) && isInputEmpty(parseInput[0])) {
                parseInput[0] = detail.substring(1).trim();
            } else if (detail.startsWith(NRIC_TAG) && isValidNric(parseInput[1],detail)) {
                parseInput[1] = detail.substring(2).trim().toUpperCase();
            } else if (detail.startsWith(PHONE_NUMBER_TAG) && isValidPhoneNum(parseInput[2], detail)) {
                parseInput[2] = detail.substring(1).trim();
            } else if (detail.startsWith(DATE_OF_BIRTH_TAG) && isValidDateOfBirth(parseInput[3],detail)) {
                parseInput[3] = detail.substring(3).trim();
            } else if (detail.startsWith(BLOOD_TYPE_TAG) && isValidBloodType(parseInput[4],detail)) {
                parseInput[4] = detail.substring(1).trim().toUpperCase();
            } else if (detail.startsWith(ALLERGIES_TAG) && parseInput[5].equalsIgnoreCase("NIL")) {
                parseInput[5] = detail.substring(1).trim();
            } else if (detail.startsWith(REMARKS_TAG) && parseInput[6].equalsIgnoreCase("NIL")) {
                parseInput[6] = detail.substring(2).trim();
            } else {
                PatientTextUi.patientNotAddedMessage(detail);
            }
        }

        while (hasMissingFields(parseInput)) {
            printMissingFields(parseInput);
            String input = readUserInput().trim();
            if (input.equalsIgnoreCase("clear")) {
                throw new HappyPillsException(Messages.MESSAGE_COMMAND_ABORTED);
            }
            String[] updates = checkInput(input);
            updates = trimArray(updates);
            updateInput(parseInput, updates);
        }

        boolean hasConfirmation = false;
        System.out.println(promptConformation(parseInput));

        while (!hasConfirmation) {
            String confirmation = readUserInput();
            boolean isConfirmed = confirmation.equalsIgnoreCase("y");
            boolean isNotConfirmed = confirmation.equalsIgnoreCase("n");
            if (isConfirmed) {
                hasConfirmation = true;
            } else if (isNotConfirmed) {
                return new IncorrectPatientCommand(Messages.MESSAGE_PATIENT_RECORD_NOT_ADDED);
            } else {
                System.out.println(Messages.MESSAGE_USER_CONFIRMATION);
            }
        }
        return new AddPatientCommand(parseInput[0], parseInput[1].toUpperCase(),
                Integer.parseInt(parseInput[2]), parseInput[3], parseInput[4],
                parseInput[5], parseInput[6]);
    }

    private static void updateInput(String[] parseInput, String[] updates) {
        for (String update : updates) {
            if (update.trim().startsWith("n") && parseInput[0].equalsIgnoreCase("")) {
                parseInput[0] = update.substring(1).trim();
            } else if (update.trim().startsWith("ic") && (parseInput[1].equalsIgnoreCase(""))
                    || !Checker.isValidNric(parseInput[1].trim())) {
                parseInput[1] = update.trim().substring(2).toUpperCase().trim();
            } else if (update.trim().startsWith("p") && ((parseInput[2].equalsIgnoreCase("")
                    || !Checker.isPositiveInteger(parseInput[2].trim())
                    || !Checker.isValidPhoneNum(parseInput[2].trim())))) {
                parseInput[2] = update.substring(1).trim();
            } else if (update.trim().startsWith("dob") && (parseInput[3].equalsIgnoreCase("")
                    || !Checker.isValidDate(parseInput[3].trim()))) {
                parseInput[3] = update.trim().substring(3).trim();
            } else if (update.trim().startsWith("b") && (parseInput[4].equalsIgnoreCase(""))
                    || !Checker.isValidBloodType(parseInput[4].trim())) {
                parseInput[4] = update.trim().substring(1).trim();
            }
        }
    }

    private static String[] checkInput(String content) {
        String[] details;
        if (content.startsWith("/")) {
            details = content.substring(1).split(" /");
        } else {
            content = "@" + content;
            details = content.split(" /");
        }
        return details;
    }

    private static String readUserInput() {
        System.out.println(TextUi.DIVIDER);
        Scanner in = HappyPills.scanner;
        String reInput = in.nextLine();
        System.out.println(TextUi.DIVIDER);
        return reInput;
    }

    /**
     * Prompt user for conformation with this message.
     *
     * @param parseInput details to be displayed to user for confirmation
     * @return string to be displayed to user for confirmation
     */
    public static String promptConformation(String[] parseInput) {
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
