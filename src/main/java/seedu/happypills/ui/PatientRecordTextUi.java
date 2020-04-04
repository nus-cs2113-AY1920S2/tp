package seedu.happypills.ui;

import seedu.happypills.model.data.PatientRecord;
import seedu.happypills.model.data.PatientRecordMap;

import java.util.ArrayList;

public class PatientRecordTextUi extends TextUi {

    public static String emptyPatientRecordMessage = "    There are no patient record.";
    public static String getEmptyPatientRecordList = "    There are no patient record in the list.";

    /**
     * format a list patient record success message for user.
     * Includes a specified string, requested patient details and a divider.
     *
     * @param patientRecords patient object to be returned as a part of the string.
     * @return a formatted success message string to be displayed to user.
     */
    public static String getPatientRecordListSuccessMessage(ArrayList<PatientRecord> patientRecords) {
        String message = "    Here is your list of patient's records:\n"
                + "    ID | date       | time     \n";
        for (int index = 0; index < patientRecords.size(); index++) {
            int id = index + 1;
            String date = patientRecords.get(index).getDate();
            String time = patientRecords.get(index).getTime();
            message += "    " + id + "  | " + date + " | " + time + "\n";
        }
        message += DIVIDER;
        return message;
    }

    /**
     * format a get patient record success message for user.
     * Includes a specified string, requested patient details and a divider.
     *
     * @param patientRecordMap patient object to be returned as a part of the string.
     * @param patientNric      nric of the patient
     * @param index            index for the record of the searched patient
     * @return a formatted success message string to be displayed to user.
     */
    public static String getPatientRecordSuccessMessage(
            PatientRecordMap patientRecordMap, String patientNric, int index) {
        ArrayList<PatientRecord> patientRecord = patientRecordMap.get(patientNric);
        String returnMessage = "    Here are the patient's record details:\n" + patientRecord.get(index) + DIVIDER;
        return returnMessage;
    }

    /**
     * format a edit patient success message for user.
     * Includes a specified string, requested patient details and a divider.
     *
     * @param patientRecord patient object to be returned as a part of the string.
     * @return a formatted success message string to be displayed to user.
     */
    public static String editPatientRecordSuccessMessage(PatientRecord patientRecord) {
        String returnMessage = "    Here is the edited patient's record details:\n" + patientRecord + DIVIDER;
        return returnMessage;
    }

    /**
     * format a delete patient success message for user.
     * Includes a specified string, requested patient details and a divider.
     *
     * @param patientRecordMap patient object to be returned as a part of the string.
     * @param patientNric      nric of the patient
     * @param index            index for the record of the searched patient
     * @return a formatted success message string to be displayed to user.
     */
    public static String deletePatientRecordSuccessMessage(
            PatientRecordMap patientRecordMap, String patientNric, int index) {
        ArrayList<PatientRecord> patientRecord = patientRecordMap.get(patientNric);
        String returnMessage = "    " + patientNric + "'s patient record will be deleted.\n"
                + patientRecord.get(index) + DIVIDER;
        patientRecord.remove(index);
        patientRecordMap.removePersonalRecord(patientRecord, patientNric);
        return returnMessage;
    }

    public static void patientRecordNotAddedMessage(String detail) {
        System.out.println("    " + detail + " is not a valid input.\n"
                + "    " + detail + " will not be added\n" + TextUi.DIVIDER);
    }


    /**
     * Prompt user for conformation with this message.
     *
     * @param parseInput details to be displayed to user for confirmation
     * @return string to be displayed to user for confirmation
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
