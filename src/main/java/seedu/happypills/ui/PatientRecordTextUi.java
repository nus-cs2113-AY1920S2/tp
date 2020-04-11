package seedu.happypills.ui;

import seedu.happypills.model.data.PatientRecord;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;

import java.util.ArrayList;

public class PatientRecordTextUi extends TextUi {

    /**
     * Formats a list patient record success message for user.
     * Includes a specified string, requested patient details and a divider.
     *
     * @param patientRecords Patient records of the patient.
     * @return message A formatted success message string to be displayed to user.
     * @throws HappyPillsException If the list is empty
     */
    public static String getPatientRecordListSuccessMessage(ArrayList<PatientRecord> patientRecords)
            throws HappyPillsException {
        if (patientRecords.isEmpty()) {
            throw new HappyPillsException(Messages.MESSAGE_EMPTY_PATIENT_RECORD);
        }
        String nric = patientRecords.get(0).getNric();
        String message = "    Here is the list of " + nric + "'s records:\n"
                + "    ID    | Date       | Time     \n";
        for (int index = 0; index < patientRecords.size(); index++) {
            String id = Integer.toString(index + 1);
            String date = patientRecords.get(index).getDate();
            String time = patientRecords.get(index).getTime();
            message += "    " + id + repeat(6 - id.length()) + "| "
                    + date + repeat(11 - date.length()) + "| " + time + "\n";
        }
        message += DIVIDER;
        return message;
    }

    /**
     * Formats a get patient record success message for user.
     * Includes a specified string, requested patient details and a divider.
     *
     * @param patientRecordMap Patient object to be returned as a part of the string.
     * @param patientNric      Nric of the patient.
     * @param index            Index for the record of the searched patient.
     * @return returnMessage A formatted success message string to be displayed to user.
     */
    public static String getPatientRecordSuccessMessage(
            PatientRecordMap patientRecordMap, String patientNric, int index) {
        ArrayList<PatientRecord> patientRecord = patientRecordMap.get(patientNric);
        String returnMessage = "    Here are the patient's record details:\n" + patientRecord.get(index) + DIVIDER;
        return returnMessage;
    }

    /**
     * Formats a edit patient success message for user.
     * Includes a specified string, requested patient details and a divider.
     *
     * @param patientRecord Patient object to be returned as a part of the string.
     * @return returnMessage A formatted success message string to be displayed to user.
     */
    public static String editPatientRecordSuccessMessage(PatientRecord patientRecord) {
        String returnMessage = "    Here is the edited patient's record details:\n" + patientRecord + DIVIDER;
        return returnMessage;
    }

    /**
     * Formats a delete patient success message for user.
     * Includes a specified string, requested patient details and a divider.
     *
     * @param patientRecord Patient record object to be returned as a part of the string.
     * @param patientNric   Nric of the patient.
     * @return a formatted success message string to be displayed to user.
     */
    public static String deletePatientRecordSuccessMessage(
            PatientRecord patientRecord, String patientNric) {
        String returnMessage = "    " + patientNric + "'s patient record will be deleted.\n"
                + patientRecord + DIVIDER;
        return returnMessage;
    }

    public static void patientRecordNotAddedMessage(String detail) {
        System.out.println("    " + detail + " is not a valid input.\n"
                + "    " + detail + " will not be added\n" + TextUi.DIVIDER);
    }


    /**
     * Prompts user for conformation with this message.
     *
     * @param parseInput Details to be displayed to user for confirmation.
     * @return text The string to be displayed to user for confirmation.
     */
    public static String promptConfirmation(String[] parseInput) {
        String text = "        Are you sure all the listed details are correct?\n"
                + "        NRIC : " + parseInput[0].trim().toUpperCase() + "\n"
                + "        Symptom : " + parseInput[1].trim() + "\n"
                + "        Diagnosis : " + parseInput[2].trim() + "\n"
                + "        Date : " + parseInput[3].trim() + "\n"
                + "        time : " + parseInput[4].trim() + "\n"
                + "                                                   (Y/N)?";
        return text;
    }
}
