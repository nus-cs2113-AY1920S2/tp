package seedu.duke.parser;

import seedu.duke.Duke;
import seedu.duke.command.AddAppointmentCommand;
import seedu.duke.command.AddPatientCommand;
import seedu.duke.command.ClearAllCommand;
import seedu.duke.command.ClearAppointmentCommand;
import seedu.duke.command.ClearPatientCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteAppointmentCommand;
import seedu.duke.command.DeletePatientCommand;
import seedu.duke.command.EditAppointmentCommand;
import seedu.duke.command.EditPatientCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.FindAppointmentCommand;
import seedu.duke.command.FindPatientCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.ListAppointmentCommand;
import seedu.duke.command.ListPatientCommand;
import seedu.duke.enums.AppointmentFieldKeys;
import seedu.duke.enums.PatientFieldKeys;
import seedu.duke.exceptions.DukeExceptions;
import seedu.duke.exceptions.IndexNotIntegerException;
import seedu.duke.exceptions.InvalidFormatException;
import seedu.duke.exceptions.InvalidIndexException;
import seedu.duke.exceptions.NoFieldCommandException;
import seedu.duke.exceptions.NoKeyExistException;
import seedu.duke.exceptions.PidEmptyException;
import seedu.duke.exceptions.UnknownCommandException;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    private static final int COMMAND_INDEX = 0;
    private static final int LIMIT = 2;
    private static final int VALUE_INDEX = 0;
    private static final int VALUE_STRING_INDEX = 1;

    private static final String REGEX_BACKSLASH = "\\\\";
    private static final String BLANK_STRING = "";
    private static final String WHITESPACE = " ";
    private static final String INDEX = "index";

    private static final String HELP_COMMAND = "help";
    private static final String EXIT_COMMAND = "exit";

    private static final String ADD_PATIENT = "addp";
    private static final String EDIT_PATIENT = "editp";
    private static final String DELETE_PATIENT = "deletep";
    private static final String LIST_PATIENT = "listp";

    private static final String ADD_APPOINTMENT = "adda";
    private static final String EDIT_APPOINTMENT = "edita";
    private static final String DELETE_APPOINTMENT = "deletea";
    private static final String LIST_APPOINTMENT = "lista";

    public static final String FIND_APPOINTMENT = "finda";
    public static final String FIND_PATIENTS = "findp";

    public static final String CLEAR_PATIENTS_COMMAND = "clearp";
    public static final String CLEAR_APPOINTMENTS_COMMAND = "cleara";
    public static final String CLEAR_ALL_COMMAND = "clearall";

    /**
     * This methods returns the command from the user input string.
     *
     * @param fullCommand the user input string.
     * @return the actual command to execute.
     */
    private String[] getCommand(String fullCommand) {
        String[] splits = fullCommand.split(" ", LIMIT);
        return splits;
    }

    /**
     * Returns strictly the String that is between the fieldKey and " \" delimiter.
     * It will only return the value that is behind the first key.
     * Returns an empty String if the key supplied cannot be found in the fullCommand
     *
     * @param fullCommand the entire command that the user supplied
     * @param key         the patient field, prepended with REGEX_BACKSLASH. This key is an enum.
     * @return value.trim() the String that is between key and " \" delimiter
     */
    private String findValue(String fullCommand, String key) {
        String[] keyValue = fullCommand.split(key, LIMIT);

        try {
            DukeExceptions.doesKeyExist(keyValue);

            String valueString = WHITESPACE + keyValue[VALUE_STRING_INDEX];

            String delimiter = WHITESPACE + REGEX_BACKSLASH;
            String[] b = valueString.split(delimiter, LIMIT);
            String value = b[VALUE_INDEX];
            return value.trim();

        } catch (NoKeyExistException e) {
            return BLANK_STRING;
        }
    }

    /**
     * This is a helper method to fill the HashMap with the values read from user input.
     *
     * @param fullCommand        the full command read from the user
     * @param patientFieldsToAdd the HashMap to store the values read from the fullCommand
     */
    private void fillPatientFields(String fullCommand, Map<String, String> patientFieldsToAdd) {
        for (PatientFieldKeys pf : PatientFieldKeys.values()) {
            String field = pf.toString();

            assert field != null;
            if (field.equals(PatientFieldKeys.INDEX.toString())) {
                continue;
            }

            String key = WHITESPACE + REGEX_BACKSLASH + field + WHITESPACE;
            String value = findValue(fullCommand, key);
            patientFieldsToAdd.put(field, value);
        }
    }

    /**
     * Returns a HashMap that take keys from PatientFieldKeys and values from user input.
     * The HashMap is guaranteed to contain the keys found in the enum PatientFieldKeys.
     * The values however will be determined by the user.
     * This method is only used for "addp".
     * Throws a noFieldCommandException when all fields are blank.
     *
     * @param fullCommand the user input that the user provided.
     * @return fieldsToChange a HashMap that matches the patient's fieldKey to value.
     * @throws NoFieldCommandException Throws a noFieldCommandException when all fields are blank.
     * @see PatientFieldKeys for the list of keys guaranteed to be in the HashMap.
     * @see #findValue(String fullCommand, String key) value returned by this method will be stored at key.
     */
    private Map<String, String> getPatientFieldsAdd(String fullCommand) throws NoFieldCommandException {

        Map<String, String> patientFieldsToAdd = new HashMap<>();

        fillPatientFields(fullCommand, patientFieldsToAdd);

        //check if there is at least 1 field inside.
        DukeExceptions.checkFieldEmptyAddPatient(patientFieldsToAdd);

        return patientFieldsToAdd;
    }

    /**
     * Returns a HashMap that take keys from PatientFieldKeys and values from user input.
     * The HashMap is guaranteed to contain the keys found in the enum PatientFieldKeys.
     * The values however will be determined by the user.
     * This method is only used for "editp".
     * Throws a InvalidIndexException when the index is <= 0.
     * Throws a IndexNotIntegerException when index is not an integer.
     * Throws a NoFieldCommandException when all fields are blank.
     *
     * @param fullCommand the user input that the user provided.
     * @return fieldsToChange a HashMap that matches the patient's fieldKey to value.
     * @throws InvalidIndexException    when the index is <= 0.
     * @throws IndexNotIntegerException when index is not an integer.
     * @throws NoFieldCommandException  when all fields are blank.
     */
    private Map<String, String> getPatientFieldsEdit(String fullCommand) throws InvalidIndexException,
            IndexNotIntegerException, NoFieldCommandException {

        Map<String, String> patientFieldsToEdit = new HashMap<>();

        String index = WHITESPACE + REGEX_BACKSLASH + PatientFieldKeys.INDEX.toString() + WHITESPACE;
        String indexValue = findValue(fullCommand, index);
        DukeExceptions.checkIndexValidity(indexValue, EDIT_PATIENT);
        patientFieldsToEdit.put(PatientFieldKeys.INDEX.toString(), indexValue);

        fillPatientFields(fullCommand, patientFieldsToEdit);

        //check if there is at least 1 field inside.
        DukeExceptions.checkFieldEmptyEditPatient(patientFieldsToEdit);

        return patientFieldsToEdit;
    }

    /**
     * Returns a HashMap that take keys from PatientFieldKeys and values from user input.
     * The HashMap is guaranteed to contain the keys found in the enum PatientFieldKeys.
     * The values however will be determined by the user.
     * This method is only used for "editp".
     * Throws a InvalidIndexException when the index is <= 0.
     * Throws a IndexNotIntegerException when index is not an integer.
     *
     * @param fullCommand the user input that the user provided.
     * @return fieldsToChange a HashMap that only contains index as key
     * @throws InvalidIndexException    when the index is <= 0.
     * @throws IndexNotIntegerException when index is not an integer.
     */
    private Map<String, String> getPatientFieldsDelete(String fullCommand) throws InvalidIndexException,
            IndexNotIntegerException {

        Map<String, String> patientFieldsToDelete = new HashMap<>();

        String index = WHITESPACE + REGEX_BACKSLASH + PatientFieldKeys.INDEX.toString() + WHITESPACE;
        String indexValue = findValue(fullCommand, index);
        DukeExceptions.checkIndexValidity(indexValue, "deletep");
        patientFieldsToDelete.put(PatientFieldKeys.INDEX.toString(), indexValue);

        return patientFieldsToDelete;
    }

    /**
     * This is a helper method to fill the HashMap with the values read from user input.
     *
     * @param fullCommand               the full command read from the user
     * @param appointmentFieldsToChange the HashMap to store the values read from the fullCommand
     */
    private void fillAppointmentFields(String fullCommand, Map<String, String> appointmentFieldsToChange) {
        for (AppointmentFieldKeys af : AppointmentFieldKeys.values()) {
            String field = af.toString();

            assert field != null;
            if (field.equals(AppointmentFieldKeys.INDEX.toString())) {
                continue;
            }

            String key = WHITESPACE + REGEX_BACKSLASH + field + WHITESPACE;
            String value = findValue(fullCommand, key);
            appointmentFieldsToChange.put(field, value);
        }
    }

    /**
     * Returns a HashMap that takes the key from AppointmentFieldKeys and values from user input.
     * The HashMap is guaranteed to contain the keys found in the enum AppointmentFieldKeys.
     * The values however will be determined by the user. If not provided, EMPTY_STRING will be stored.
     * This method is only for "adda".
     * Throw NoFieldCommandException when all fields are blank.
     *
     * @param fullCommand the user input that the user provided
     * @return a HashMap that matches the appointment's fieldKey to value.
     * @see AppointmentFieldKeys
     * @see #findValue(String fullcommand, String key) value returned by this method will be stored at key.
     */
    private Map<String, String> getAppointmentFieldsAdd(String fullCommand) throws NoFieldCommandException,
            PidEmptyException {

        Map<String, String> appointmentFieldsToAdd = new HashMap<>();
        fillAppointmentFields(fullCommand, appointmentFieldsToAdd);

        /* check if there is at least 1 field inside. */
        DukeExceptions.checkFieldEmptyAddAppointment(appointmentFieldsToAdd);

        DukeExceptions.checkPidEmpty(appointmentFieldsToAdd);

        return appointmentFieldsToAdd;
    }

    /**
     * Returns a HashMap that takes the key from AppointmentFieldKeys and values from user input.
     * The HashMap is guaranteed to contain the keys found in the enum AppointmentFieldKeys.
     * The values however will be determined by the user. If not provided, EMPTY_STRING will be stored.
     * This method is only for "edita".
     * Throw InvalidIndexException when index <= 0.
     * Throw IndexNotIntegerException when index is not an integer.
     * Throw NoFieldCommandException when all fields are blank.
     *
     * @param fullCommand the user input that the user provided
     * @return a HashMap that matches the appointment's fieldKey to value.
     * @throws InvalidIndexException    when index <= 0.
     * @throws IndexNotIntegerException when index is not an integer,
     * @throws NoFieldCommandException  when all fields are blank.
     */
    private Map<String, String> getAppointmentFieldsEdit(String fullCommand) throws InvalidIndexException,
            IndexNotIntegerException, NoFieldCommandException {

        Map<String, String> appointmentFieldsToEdit = new HashMap<>();

        String index = WHITESPACE + REGEX_BACKSLASH + AppointmentFieldKeys.INDEX.toString() + WHITESPACE;
        String indexValue = findValue(fullCommand, index);
        DukeExceptions.checkIndexValidity(indexValue, EDIT_APPOINTMENT);

        appointmentFieldsToEdit.put(AppointmentFieldKeys.INDEX.toString(), indexValue);

        fillAppointmentFields(fullCommand, appointmentFieldsToEdit);

        /* check if there is at least 1 field inside. */
        DukeExceptions.checkFieldEmptyEditAppointment(appointmentFieldsToEdit);

        return appointmentFieldsToEdit;
    }

    /**
     * Returns a HashMap that takes the key from AppointmentFieldKeys and values from user input.
     * The HashMap is guaranteed to contain the keys found in the enum AppointmentFieldKeys.
     * The values however will be determined by the user. If not provided, EMPTY_STRING will be stored.
     * This method is only for "deletea".
     * Throw InvalidIndexException when index <= 0.
     * Throw IndexNotIntegerException when index is not an integer.
     *
     * @param fullCommand the user input that the user provided.
     * @return a HashMap that matches the appointment's fieldKey to value.
     * @throws InvalidIndexException    when index <= 0.
     * @throws IndexNotIntegerException when index is not an integer.
     */
    private Map<String, String> getAppointmentFieldsDelete(String fullCommand) throws InvalidIndexException,
            IndexNotIntegerException {

        Map<String, String> appointmentFieldsToDelete = new HashMap<>();

        String index = WHITESPACE + REGEX_BACKSLASH + AppointmentFieldKeys.INDEX.toString() + WHITESPACE;
        String indexValue = findValue(fullCommand, index);
        DukeExceptions.checkIndexValidity(indexValue, "deletea");

        appointmentFieldsToDelete.put(AppointmentFieldKeys.INDEX.toString(), indexValue);
        return appointmentFieldsToDelete;
    }

    private String getSearchValue(String[] commandParsed) throws NoFieldCommandException {
        if (commandParsed.length == 1) {
            throw new NoFieldCommandException(commandParsed[0]);
        }
        return commandParsed[1];
    }

    /**
     * Returns the specific type of command object.
     * Throws an UnknownCommandException for the caller to catch when user supplied an unknown command.
     * Throws InvalidIndexError when the index supplied for edit and delete commands are invalid (alphabets, <= 0).
     *
     * @param command        the command that was specified.
     * @param fieldsToChange the HashMap of what to add or edit.
     * @return a specific command object that is specified by @param command.
     * @throws UnknownCommandException Throws custom duke exception to catch and print error message.
     */
    private Command getCommandObject(String command, Map<String, String> fieldsToChange) throws
            UnknownCommandException, InvalidFormatException {

        switch (command) {
        case ADD_PATIENT:
            Duke.patientIndexNumber++;
            fieldsToChange.put(INDEX, Integer.toString(Duke.patientIndexNumber));
            return new AddPatientCommand(fieldsToChange);

        case EDIT_PATIENT:
            return new EditPatientCommand(fieldsToChange);

        case DELETE_PATIENT:
            return new DeletePatientCommand(fieldsToChange);

        case LIST_PATIENT:
            return new ListPatientCommand();

        case ADD_APPOINTMENT:
            Duke.appointmentIndexNumber++;
            fieldsToChange.put(INDEX, Integer.toString(Duke.patientIndexNumber));
            return new AddAppointmentCommand(fieldsToChange);

        case EDIT_APPOINTMENT:
            return new EditAppointmentCommand(fieldsToChange);

        case DELETE_APPOINTMENT:
            return new DeleteAppointmentCommand(fieldsToChange);

        case LIST_APPOINTMENT:
            return new ListAppointmentCommand();

        case HELP_COMMAND:
            return new HelpCommand();

        case EXIT_COMMAND:
            return new ExitCommand();

        case CLEAR_PATIENTS_COMMAND:
            return new ClearPatientCommand();

        case CLEAR_APPOINTMENTS_COMMAND:
            return new ClearAppointmentCommand();

        case CLEAR_ALL_COMMAND:
            return new ClearAllCommand();

        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Returns the command object to be executed.
     * Throws an UnknownCommandException for the caller to catch when user supplied an unknown command.
     *
     * @param fullCommand the user input that the user provided.
     * @return a command object to be executed.
     * @throws UnknownCommandException Throws custom duke exception to catch and print error message.
     */
    public Command parseCommand(String fullCommand) throws
            UnknownCommandException, InvalidIndexException, IndexNotIntegerException, NoFieldCommandException,
            PidEmptyException, InvalidFormatException {

        String trimCommand = fullCommand.trim();
        String[] commandParsed = getCommand(trimCommand);
        String commandAsString = commandParsed[COMMAND_INDEX].trim();

        Command command;
        Map<String, String> fieldsToChange;
        String searchValue;
        switch (commandAsString) {

        case ADD_PATIENT:
            fieldsToChange = getPatientFieldsAdd(fullCommand);
            command = getCommandObject(commandAsString, fieldsToChange);
            break;

        case EDIT_PATIENT:
            fieldsToChange = getPatientFieldsEdit((fullCommand));
            command = getCommandObject(commandAsString, fieldsToChange);
            break;

        case DELETE_PATIENT:
            fieldsToChange = getPatientFieldsDelete(fullCommand);
            command = getCommandObject(commandAsString, fieldsToChange);
            break;

        case ADD_APPOINTMENT:
            fieldsToChange = getAppointmentFieldsAdd(fullCommand);
            command = getCommandObject(commandAsString, fieldsToChange);
            break;

        case EDIT_APPOINTMENT:
            fieldsToChange = getAppointmentFieldsEdit(fullCommand);
            command = getCommandObject(commandAsString, fieldsToChange);
            break;

        case DELETE_APPOINTMENT:
            fieldsToChange = getAppointmentFieldsDelete(fullCommand);
            command = getCommandObject(commandAsString, fieldsToChange);
            break;

        case FIND_PATIENTS:
            searchValue = getSearchValue(commandParsed);
            return new FindPatientCommand(searchValue);

        case FIND_APPOINTMENT:
            searchValue = getSearchValue(commandParsed);
            return new FindAppointmentCommand(searchValue);

        default:
            command = getCommandObject(commandAsString, null);
            break;
        }
        return command;
    }
}
