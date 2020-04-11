package seedu.happypills.ui;

import seedu.happypills.model.data.Patient;
import seedu.happypills.model.data.PatientMap;

import java.util.Map;

//@@ nyanwunpaing
public class PatientTextUi extends TextUi {

    public static final String PATIENT_NOT_FOUND_MESSAGE = "    The patient cannot be found. Please try again.";
    public static final String EMPTY_PATIENT_LIST_MESSAGE = "    There are no patients in the list.\n" + DIVIDER;

    /**
     * Shows a list of patients' name and their NRIC to the user, formatted as an indexed list.
     * @param patients A patient list with all existing patients.
     * @return message A message to be displayed to user.
     */
    public static String getPatientList(PatientMap patients) {
        String message = "    Here is your list of patients:\n"
                + "    NRIC      | Name\n";
        for (Map.Entry patient : patients.entrySet()) {
            String nric = (String)patient.getKey();
            Patient p = (Patient)patient.getValue();
            String name = p.getName();
            message += "    " + nric + repeat(10 - nric.length())
                    + "| " + name + "\n";
        }
        message += DIVIDER;
        return message;
    }

    /**
     * Formats an add patient success message for user verification.
     * Includes a specified string, patient details and a divider.
     * @param patient Patient object added to patientList.
     * @return message A string message to be displayed to the user for successful addCommand executed.
     */
    public static String addPatientSuccessMessage(Patient patient) {
        String message;
        message = "    Got it! I've added this patient:\n" + patient + DIVIDER;
        return message;
    }

    /**
     * Formats a get patient success message for user.
     * Includes a specified string, requested patient details and a divider.
     * @param patient Patient object to be returned as a part of the string.
     * @return returnMessage A formatted success message string to be displayed to user.
     */
    public static String getPatientSuccessMessage(Patient patient) {
        String returnMessage = "    Here are the patient's details:\n" + patient + DIVIDER;
        return returnMessage;
    }

    /**
     * Displays updated patient's records.
     *
     * @param patient The patient whose records were updated.
     * @return message The patient's updated records.
     */
    public static String editPatientSuccessMessage(Patient patient) {
        String message = "    Patient details have been updated as follows:\n"
                + patient + DIVIDER;
        return message;
    }

    /**
     * Displays message that patient is successfully deleted.
     *
     * @param patient Patient to be deleted.
     * @return display message.
     */
    public static String deletePatientSuccessMessage(Patient patient) {
        return "    Patient " + patient.getName() + " | " + patient.getNric()
            + " has been deleted successfully.\n";
    }

    /**
     * Displays confirmation message for deleting patients.
     *
     * @param patient The patient to be deleted.
     */
    public static void printDeleteConfirmation(Patient patient) {
        print("    Are you sure you want to delete this patient:\n      "
                + patient.getName() + " | " + patient.getNric() + "\n"
                + "                                                   (Y/N)?\n"
                + DIVIDER);
    }

    /**
     * Displays confirmation message for deleting patients.
     *
     * @param patient The patient to be deleted.
     */
    public static void printDeleteConfirmationAgain(Patient patient) {
        print(DIVIDER);
        print("    Input is incorrect. Please try again.");
        print(DIVIDER);
        printDeleteConfirmation(patient);
    }

    public static String patientNotDeletedMessage = DIVIDER + "\n    Patient is not deleted.\n";

    public static void patientNotAddedMessage(String detail) {
        System.out.println("    " + detail + " is not a valid input.\n"
                + "    " + detail + " will not be added\n" + TextUi.DIVIDER);
    }
}
