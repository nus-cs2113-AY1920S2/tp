package seedu.happypills.ui;

import seedu.happypills.data.Patient;
import seedu.happypills.data.PatientList;


public class TextUi {
    public static final String DIVIDER = "    ===================================================";

    /**
     * Shows a list of patients' name and their NRIC to the user, formatted as an indexed list.
     */
    public static String getList(PatientList patients) {
        String message = "";
        for (Patient patient : patients) {
            message += "    " + patient.getName() + " | " + patient.getNric() + "\n";
        }
        message += DIVIDER;
        return message;
    }

    /**
     * Generates and prints the list of commands.
     */
    public static String getHelp() {
        String helpMessage = "    Shows program usage instructions:\n"
                + "      add NAME, NRIC, PHONE_NUMBER, DOB, BLOOD_TYPE, [ALLERGIES], [REMARKS]\n"
                + "      list \n"
                + "      open NRIC\n"
                + "      edit phone/allergies/remarks EDITED_INPUT\n"
                + "      close\n"
                + "      delete NRIC\n"
                + "      help\n"
                + "      exit\n";
        helpMessage += DIVIDER;
        return helpMessage;
    }

    /**
     * Displays empty list message when there are no patients in the list.
     */
    public static String getEmptyList() {
        String emptyListMessage = "    There are no patients in the list.\n" + DIVIDER;
        return emptyListMessage;
    }

    public static void printPatient(Patient patient) {
        System.out.println("    Got it! I've added this patient:");
        System.out.println(patient + DIVIDER);
    }

    /**
     * Generates and prints the welcome message upon the start of the application.
     */
    public void printWelcomeMessage() {
        String logo = "\n"
                + " __   __  _______  _______  _______  __   __  _______  ___   ___      ___      _______\n"
                + "|  | |  ||   _   ||       ||       ||  | |  ||       ||   | |   |    |   |    |       |\n"
                + "|  |_|  ||  |_|  ||    _  ||    _  ||  |_|  ||    _  ||   | |   |    |   |    |  _____|\n"
                + "|       ||       ||   |_| ||   |_| ||       ||   |_| ||   | |   |    |   |    | |_____\n"
                + "|       ||       ||    ___||    ___||_     _||    ___||   | |   |___ |   |___ |_____  |\n"
                + "|   _   ||   _   ||   |    |   |      |   |  |   |    |   | |       ||       | _____| |\n"
                + "|__| |__||__| |__||___|    |___|      |___|  |___|    |___| |_______||_______||_______|\n";

        System.out.println("Hello from" + logo);
        System.out.println("What can I do for you today, doctor?");
    }

    /**
     * Generates and prints the welcome message upon the start of the application.
     */
    public static String getPatient(Patient patient, int patientNum) {
        String message;
        message = "    Got it! I've added this patient:\n" + patient + DIVIDER;
        return message;
    }

    public static void printEditSuccess(Patient patient) {
        System.out.println("Patient details have been updated as follows: ");
        System.out.println(patient + DIVIDER);
    }

    public static void printExit() {
        System.out.println("Thank you for using HappyPills! ^_^");
        System.out.println("See you again!");
    }

}
