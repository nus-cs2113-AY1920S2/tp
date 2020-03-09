package seedu.happypills.ui;

import seedu.happypills.data.Patient;
import seedu.happypills.data.PatientList;


public class TextUi {
    public static final String DIVIDER = "    ===================================================";

    /**
     * Shows a list of patients' name and their NRIC to the user, formatted as an indexed list.
     */
    public static void printList(PatientList patients) {
        for (Patient patient : patients) {
            System.out.println("    " + patient.getName() + " | " + patient.getNric());
        }
        System.out.println(DIVIDER);
    }

    /**
     * Generates and prints the list of commands.
     */
    public static void printHelp() {
        String helpMessage = "Shows program usage instructions.\n"
                + " add NAME, NRIC, PHONE_NUMBER, DOB, BLOOD_TYPE, [ALLERGIES], [REMARKS]\n"
                + " list \n"
                + " open NRIC\n"
                + " edit phone/allergies/remarks INPUT\n"
                + " close\n"
                + " delete NRIC\n"
                + " help\n"
                + " exit";
        System.out.println(helpMessage);
        System.out.println(DIVIDER);
    }

    /**
     * Generates and prints the welcome message upon the start of the application.
     */
    public void printWelcomeMessage() {
        String logo = "\n"
                + " __   __  _______  _______  _______  __   __  _______  ___   ___      ___      _______ \n"
                + "|  | |  ||   _   ||       ||       ||  | |  ||       ||   | |   |    |   |    |       |\n"
                + "|  |_|  ||  |_|  ||    _  ||    _  ||  |_|  ||    _  ||   | |   |    |   |    |  _____|\n"
                + "|       ||       ||   |_| ||   |_| ||       ||   |_| ||   | |   |    |   |    | |_____ \n"
                + "|       ||       ||    ___||    ___||_     _||    ___||   | |   |___ |   |___ |_____  |\n"
                + "|   _   ||   _   ||   |    |   |      |   |  |   |    |   | |       ||       | _____| |\n"
                + "|__| |__||__| |__||___|    |___|      |___|  |___|    |___| |_______||_______||_______|\n";

        System.out.println("Hello from" + logo);
    }

    /**
     * Generates and prints the welcome message upon the start of the application.
     */
    public static void printPatient(Patient patient, int patientNum) {
        System.out.println("    Got it! I've added this patient:");
        System.out.println(patient + DIVIDER);

    }

    public static void printPatient(Patient patient) {
        System.out.println(patient + DIVIDER);
    }

}
