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
            return new ListPatientCommand();
        } else if (userCommand[0].trim().equalsIgnoreCase("add")) {
            return parsePatientAdd(userCommand, isCommandLengthOne);
        } else if (userCommand[0].trim().equalsIgnoreCase("get")) {
            return parsePatientGet(userCommand, isCommandLengthOne);
        } else if (userCommand[0].equalsIgnoreCase("edit")) {
            return parsePatientEdit(fullCommand);
        } else if (userCommand[0].equalsIgnoreCase("delete")) {
            return new DeletePatientCommand(userCommand[2]);
        } else {
            throw new HappyPillsException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
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
        if (userCommand.length == 1 || isCommandLengthOne) {
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
                && Checker.isValidDate(detail.substring(2).trim().toUpperCase());
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
            } else if (detail.startsWith(NRIC_TAG) && isInputEmpty(parseInput[1])) {
                parseInput[1] = detail.substring(2).trim().toUpperCase();
            } else if (detail.startsWith(PHONE_NUMBER_TAG) && isValidPhoneNum(parseInput[1], detail)) {
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
            System.out.println(parseInput[1]);
            System.out.println(Checker.isValidNric(parseInput[1]));
            printMissingFields(parseInput);
            String input = promptUser().trim();
            if (input.equalsIgnoreCase("clear")) {
                return new IncorrectPatientCommand(TextUi.DIVIDER + "\n    Command has been aborted.\n"
                        + TextUi.DIVIDER);
            }
            String[] updates = checkInput(input);
            updates = trimArray(updates);

            for (String update : updates) {
                if (update.trim().startsWith("n") && parseInput[0].equalsIgnoreCase("")) {
                    parseInput[0] = update.substring(1).trim();
                } else if (update.trim().startsWith("ic") && (parseInput[1].equalsIgnoreCase(""))
                        || !checkNric(parseInput[1].trim())) {
                    parseInput[1] = update.trim().substring(2).toUpperCase().trim();
                } else if (update.trim().startsWith("p") && ((parseInput[2].equalsIgnoreCase("")
                        || !isInteger(parseInput[2].trim()) || !checkPhoneNum(parseInput[2].trim())))) {
                    parseInput[2] = update.substring(1).trim();
                } else if (update.trim().startsWith("dob") && (parseInput[3].equalsIgnoreCase("")
                        || !checkDate(parseInput[3].trim()))) {
                    parseInput[3] = update.trim().substring(3).trim();
                } else if (update.trim().startsWith("b") && (parseInput[4].equalsIgnoreCase(""))
                        || !checkType(parseInput[4].trim())) {
                    parseInput[4] = update.trim().substring(1).trim();
                }
            }
        }

        boolean userConformation = false;
        System.out.println(promptConformation(parseInput));
        while (!userConformation) {
            String conformation = promptUser();
            System.out.println(TextUi.DIVIDER);
            if (conformation.equalsIgnoreCase("y")) {
                userConformation = true;
            } else if (conformation.equalsIgnoreCase("n")) {
                return new IncorrectPatientCommand("    The current information is not added.\n"
                        + "    Please add all the details again! Thank you.\n"
                        + TextUi.DIVIDER);
            } else {
                System.out.println("    Please input [y] for yes or [n] for no");
            }
        }
        return new AddPatientCommand(parseInput[0].trim(), parseInput[1].toUpperCase().trim(),
                Integer.parseInt(parseInput[2].trim()), parseInput[3].trim(), parseInput[4].trim(),
                parseInput[5].trim(), parseInput[6].trim());
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

    private static String promptUser() {
        System.out.println(TextUi.DIVIDER);
        Scanner in = HappyPills.scanner;
        String reInput = in.nextLine();
        return reInput;
    }

    /**
     * Check if the String can be converted to Integer.
     *
     * @param input value to check if is integer
     * @return true if is an integer, false otherwise
     */
    public static boolean isInteger(String input) {
        try {
            int x = Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * To check format for phone.
     *
     * @param phoneNum details of time
     * @return boolean true if the time format is correct otherwise false
     */
    static boolean checkPhoneNum(String phoneNum) {
        String pattern = "([0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9])";
        return phoneNum.matches(pattern);
    }

    /**
     * To check format for nric.
     *
     * @param nric details of nric
     * @return boolean true if the time format is correct otherwise false
     */
    static boolean checkNric(String nric) {
        String pattern = "([S-T][0-9][0-9][0-9][0-9][0-9][0-9][0-9][A-Z])";
        return nric.matches(pattern);
    }

    /**
     * To check format for nric.
     *
     * @param nric details of nric
     * @return boolean true if the time format is correct otherwise false
     */
    static boolean checkType(String nric) {
        String pattern = "([A|B|AB|O][+-])";
        return nric.matches(pattern);
    }

    /**
     * To check format for date.
     *
     * @param date details of date
     * @return boolean true if the date format is correct otherwise false
     */
    static boolean checkDate(String date) {
        String pattern = "(0?[1-9]|[12][0-9]|3[01])\\/(0?[1-9]|1[0-2])\\/([0-9]{4})";
        boolean flag = false;
        if (date.matches(pattern)) {
            flag = true;
        }
        return flag;
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
