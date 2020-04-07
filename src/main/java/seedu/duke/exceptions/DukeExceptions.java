package seedu.duke.exceptions;

import seedu.duke.enums.AppointmentFieldKeys;
import seedu.duke.enums.PatientFieldKeys;
import seedu.duke.storage.AppointmentList;
import seedu.duke.storage.PatientList;

import java.util.Map;

/**
 * This class throws custom exceptions.
 */
public class DukeExceptions {
    private static final String BLANK_STRING = "";
    public static final String ADD_PATIENT_COMMAND = "addp";
    private static final String ADD_PATIENT_COMMAND = "addp";

    /**
     * This method throws a NoKeyExistException if the key is not found in the input that the user supplied
     * For example: user input "addp \name Justin \address Pasir Ris"
     * Key is : "\age"
     * Since \age cannot be found in user input, this method will throw NoKeyExistException.
     *
     * @param keyValue the key value pair "Key => value" as a size = 2 String array.
     * @throws NoKeyExistException throws exception when there is no key associated with any value.
     */
    public static void doesKeyExist(String[] keyValue) throws NoKeyExistException {
        if (keyValue.length != 2) {
            throw new NoKeyExistException();
        }
    }

    /**
     * This method throws a UnknownCommandException when the user supplied in an unknown command.
     * All commands available are listed as final Strings in Parser class.
     *
     * @throws UnknownCommandException when user supplied command are not amongst the final Strings.
     */
    public static void throwUnknownCommand() throws UnknownCommandException {
        throw new UnknownCommandException();
    }

    /**
     * Checks the validity of the index. If it is not valid, throws one of the two exceptions.
     *
     * @param indexAsString The index as a string
     * @param command       The command that called this method
     * @throws InvalidIndexException    Throws an InvalidIndexException when the index supplied is <= 0
     * @throws IndexNotIntegerException Throws an IndexNotIntegerException when the index supplied is not an integer
     */
    public static void checkIndexValidity(String indexAsString, String command) throws InvalidIndexException,
            IndexNotIntegerException {
        try {
            int index = Integer.parseInt(indexAsString);
            if (index <= 0) {
                throw new InvalidIndexException(command);
            }

        } catch (NumberFormatException e) {
            throw new IndexNotIntegerException(command);
        }
    }

    /**
     * Checks that at least 1 field is included in the user input for the command addp.
     * If all fields is blank, then NoFieldCommandException is thrown.
     *
     * @param patientFieldsToAdd the HashMap of the Patient Field to add.
     * @throws NoFieldCommandException throws a NoFieldCommandException when all values that match to keys are empty.
     */
    public static void checkFieldEmptyAddPatient(Map<String, String> patientFieldsToAdd)
            throws NoFieldCommandException {

        boolean isEmpty = isPatientFieldEmpty(patientFieldsToAdd);

        if (isEmpty) {
            throw new NoFieldCommandException(ADD_PATIENT_COMMAND);
        }
    }

    /**
     * Checks if the field-values are empty for the command "editp".
     * Throw a NoFieldCommandException if all fields are empty.
     *
     * @param patientFieldsToEdit the HashMap of key values.
     * @throws NoFieldCommandException if all fields are empty.
     */
    public static void checkFieldEmptyEditPatient(Map<String, String> patientFieldsToEdit)
            throws NoFieldCommandException {

        boolean isEmpty = isPatientFieldEmpty(patientFieldsToEdit);

        if (isEmpty) {
            throw new NoFieldCommandException("editp");
        }
    }

    /**
     * A helper method to check if the HashMap's values are all empty.
     * Returns false immediately if at least 1 is not empty.
     *
     * @param patientFieldsToEdit the HashMap of key values.
     * @return true if all empty, false if at least 1 is not empty.
     */
    private static boolean isPatientFieldEmpty(Map<String, String> patientFieldsToEdit) {

        for (PatientFieldKeys pf : PatientFieldKeys.values()) {
            String field = pf.toString();

            /* ignores the index field */
            assert field != null;
            if (field.equals(PatientFieldKeys.INDEX.toString())) {
                continue;
            }

            String value = patientFieldsToEdit.get(field);
            if (!value.equals(BLANK_STRING)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the field-values are empty for the command "adda".
     * Throw a NoFieldCommandException if all fields are empty.
     *
     * @param appointmentFieldsToChange the HashMap of key values.
     * @throws NoFieldCommandException if all fields are empty.
     */
    public static void checkFieldEmptyAddAppointment(Map<String, String> appointmentFieldsToChange)
            throws NoFieldCommandException {

        boolean isEmpty = isAppointmentFieldEmpty(appointmentFieldsToChange);

        if (isEmpty) {
            throw new NoFieldCommandException("adda");
        }
    }

    /**
     * A helper method to check if the HashMap's values are all empty.
     * Returns false immediately if at least 1 is not empty.
     *
     * @param appointmentFieldsToChange the HashMap of key values.
     * @return true if all empty, false if at least 1 is not empty.
     */
    private static boolean isAppointmentFieldEmpty(Map<String, String> appointmentFieldsToChange) {

        for (AppointmentFieldKeys af : AppointmentFieldKeys.values()) {
            String field = af.toString();

            assert field != null;
            if (field.equals(AppointmentFieldKeys.INDEX.toString())) {
                continue;
            }

            String value = appointmentFieldsToChange.get(field);
            if (!value.equals(BLANK_STRING)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the field-values are empty for the command "edita".
     * Throw a NoFieldCommandException if all fields are empty.
     *
     * @param appointmentFieldsToEdit the HashMap of key values.
     * @throws NoFieldCommandException if all fields are empty.
     */
    public static void checkFieldEmptyEditAppointment(Map<String, String> appointmentFieldsToEdit)
            throws NoFieldCommandException {

        boolean isEmpty = isAppointmentFieldEmpty(appointmentFieldsToEdit);

        if (isEmpty) {
            throw new NoFieldCommandException("edita");
        }
    }

    /**
     * Checks if the value of key "pid" is empty.
     * If it is empty, throw a PidEmptyException.
     *
     * @param appointmentFieldsToAdd the HashMap of KeyValues
     * @throws PidEmptyException If the value for the key "pid" is empty.
     */
    public static void checkPidEmpty(Map<String, String> appointmentFieldsToAdd) throws PidEmptyException {
        String pid = appointmentFieldsToAdd.get("pid");
        if (pid.isEmpty()) {
            throw new PidEmptyException();
        }
    }

    /**
     * Check if both lists are empty.
     * If it is empty, throw EmptyListsException.
     *
     * @throws EmptyListsException when both lists are empty
     * @author Duc
     */
    public static void checkEmptyLists() throws EmptyListsException {
        if (AppointmentList.getTotalAppointments() == 0 && PatientList.getTotalPatients() == 0) {
            throw new EmptyListsException();
        }
    }

    /**
     * Check if appointment's list is empty.
     * If it is empty, throw EmptyAppointmentsException.
     *
     * @throws EmptyAppointmentsException when appointment's list is empty
     * @author Duc
     */
    public static void checkEmptyAppointments() throws EmptyAppointmentsException {
        if (AppointmentList.getTotalAppointments() == 0) {
            throw new EmptyAppointmentsException();
        }
    }

    /**
     * Check if patient's list is empty.
     * If it is empty, throw EmptyPatientsException.
     *
     * @throws EmptyPatientsException when patient's list is empty
     * @author Duc
     */
    public static void checkEmptyPatients() throws EmptyPatientsException {
        if (PatientList.getTotalPatients() == 0) {
            throw new EmptyPatientsException();
        }
    }

    /**
     * Uses Regex matches the given phone number for validation.
     * Returns true if the phone number is valid.
     *
     * @param phoneNumber the phone number to check.
     * @return returns true if the phone number is valid.
     */
    public static boolean checkIsValidPhoneNumber(String phoneNumber) {

        //phoneNumber first digits matches 6 or 8 or 9, follow by any 7 digits.
        if (phoneNumber.matches("(6|8|9)\\d{7}")) {
            return true;
        //phoneNumber first digits matches 6 or 8 or 9, then 3 digits and a whitespace and then 4 digits.
        } else if (phoneNumber.matches("(6|8|9)\\d{3}[\\s]\\d{4}")) {
            return true;
        }
        return false;
    }
}
