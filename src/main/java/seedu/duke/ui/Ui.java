package seedu.duke.ui;

import seedu.duke.record.Appointment;
import seedu.duke.record.Patient;
import seedu.duke.storage.AppointmentList;
import seedu.duke.storage.PatientList;

import java.util.List;

/**
 * Represents the user interface that will interact with the user.
 *
 * @author Justin
 */
public class Ui {

    /**
     * Empty Ui constructor.
     */
    public Ui() {

    }

    /**
     * Prints the entire patientList.
     */
    public static void showEntirePatientList() {
        List<Patient> patientList = PatientList.getPatientList(); //getPatientList() method by @Brandonnn
        int index = 1;
        if (patientList.isEmpty()) {
            System.out.println("No patient records found!");
        } else {
            for (Patient p : patientList) {
                System.out.println(index + ". " + p); //override Patient class toString by @Sammmmm
                index++;
            }
        }
    }

    /**
     * Prints the entire appointmentList.
     */
    public static void showEntireAppointmentList() {
        List<Appointment> appointmentList = AppointmentList.getAppointmentList();
        int index = 1;
        if (appointmentList.isEmpty()) {
            System.out.println("No appointment records present!");
        } else {
            for (Appointment a : appointmentList) {
                System.out.println(index + ". " + a);
                index++;
            }
        }
    }

    public static void showNumberError() {
        System.out.println("Please input an integer for index");
    }

    public static void showIndexError() {
        System.out.println("Index out of bound, please check the correct index from the list");
    }

    public static void showDeleteAppointmentSuccess() {
        System.out.println("Appointment deleted successfully!");
    }

    public static void showDeletePatientSuccess() {
        System.out.println("Patient deleted successfully!");
    }

    public static void showUpdateAppointmentSuccess() {
        System.out.println("Appointment updated successfully!");
    }

    public static void showSetAgeError() {
        System.out.println("Received a non-integer for age, setting age to be blank.");
    }

    public static void showPatientIdError() {
        System.out.println("Something is wrong with the patientId given. Creating new patientId");
    }

    public static void showUpdatePatientSuccess() {
        System.out.println("Patient updated successfully!");
    }

    public static void showByeMessage() {
        System.out.println("Bye!");
    }

    public static void showHelpUsage(String commandsMessageUsage) {
        System.out.println(commandsMessageUsage);
    }

    public static void showFailedToCreateFile() {
        System.out.println("Failed to create file in new directory");
    }

    public static void showFailedToCreateDirectory() {
        System.out.println("No existing 'save' directory found. Directory has been created.");
    }

    public static void showWrongPid() {
        System.out.println("Wrong pid. This will not be added to the appointment list.");

    }

    public static void printSaveFileCorruptedMessage() {
        System.out.println("A save file was corrupted. Clearing save file data...");
    }

    public static void printFileCreationErrorMessage() {
        System.out.println("An error occured while attempting to create the save file.");
    }

    public static void showNoPidExist() {
        System.out.println("Patient Id don't exist! No appointment record will be made.");
    }

    public static void showWrongAge() {
        System.out.println("Received age seems wrong, setting age to be blank");
    }

    public static void printInvalidPhoneNumberMessage() {
        System.out.println("Invalid phone number, phone number field will be set to empty.");
    }

    public void showExceptionError(String localizedMessage) {
        System.out.println(localizedMessage);
    }

    public void showAppointmentAddSuccess() {
        System.out.println("Appointment added successfully!");
    }

    public void showPatientAddSuccess() {
        System.out.println("Patient added successfully");
    }

    /**
     * Prints HAMS logo.
     */
    public void printHello() {
        System.out.println(
                "            .---------.\n" + "       _    |:: [-=-] |\n" + "      | |   |_________|\n" + "      |~|\n"
                        + "      |_|                    ,;;;;,\n" + "       I\\  ,__ ,;;;, __,    ///\\\\\\\\\\\n"
                        + "       I |{   / . . \\   }   / \"  \\\\||\n" + "       I | ) (   _   ) (    \\_= _///\n"
                        + "       I |{___'-. .-'___}\\___ )_\\\n" + "       I ||~/,'~~~~~,\\~~|'---((  \\\n"
                        + "       I \\ //        \\\\ |     \\ \\ \\\n" + "       I  \\/         // |     | /-/\n"
                        + "       I (/         (/  |     |/||\\\n" + "       I  |             |     |    |\n"
                        + "       I  |             |     |____/\n" + "       I  :-----_o_-----:      || |\n"
                        + "       I  | /~~|===|~~\\ |      (( |\n" + "       I  ||   |===|   ||      ||_/\n"
                        + "      /^\\ \"~   '^^^'   \"\"     ((__|");

        System.out.println(" ____      ____  ________  _____       ______    ___   ____    ____  ________  \n"
                + "|_  _|    |_  _||_   __  ||_   _|    .' ___  | .'   `.|_   \\  /   _||_   __  | \n"
                + "  \\ \\  /\\  / /    | |_ \\_|  | |     / .'   \\_|/  .-.  \\ |   \\/   |    | |_ \\_| \n"
                + "   \\ \\/  \\/ /     |  _| _   | |   _ | |       | |   | | | |\\  /| |    |  _| _  \n"
                + "    \\  /\\  /     _| |__/ | _| |__/ |\\ `.___.'\\\\  `-'  /_| |_\\/_| |_  _| |__/ | \n"
                + "     \\/  \\/     |________||________| `.____ .' `.___.'|_____||_____||________| \n"
                + "                                                                               ");
        printLongSeparator();
        System.out.println("Hello I am HAMS. What can I do for you today?");
    }

    private String printSeparator() {
        return "========================================================================";
    }

    public void printLongSeparator() {
        System.out.println(printSeparator() + printSeparator());
    }

    /**
     * This method prints out a list of Appointments containing a specific keyword in a readable format.
     * It is used in the FindAppointmentCommand class.
     *
     * @param foundAppointments list of Appointment search results
     */
    public void printAppointmentSearchResults(List<Appointment> foundAppointments) {
        if (foundAppointments.size() == 0) {
            System.out.println("No appointment records containing the keyword were found.");
            return;
        }
        int currentIndex = 1;
        for (Appointment result : foundAppointments) {
            System.out.println(currentIndex + ". " + result.toString());
            currentIndex++;
        }
    }

    /**
     * This method prints out a list of Patients containing a specific keyword in a readable format.
     * It is used in the FindPatientCommand class.
     *
     * @param foundPatients list of Patient search results
     */
    public void printPatientSearchResults(List<Patient> foundPatients) {
        if (foundPatients.size() == 0) {
            System.out.println("No patient records containing the keyword were found.");
            return;
        }
        int currentIndex = 1;
        for (Patient result : foundPatients) {
            System.out.println(currentIndex + ". " + result.toString());
            currentIndex++;
        }
    }

    public void printInvalidAppointmentSearchDateMessage() {
        System.out.println("The input does not follow the correct time search format: \n'dd/mm/yyyy'\n"
                + "Please try again. ");
    }

    public void printInvalidAppointmentSearchTimeMessage() {
        System.out.println("The input does not follow the correct time search format: \n"
                + "'hh:mm (am/pm)'\nPlease try again. ");
    }

    public void printInvalidAppointmentSearchFormatMessage() {
        System.out.println("The input does not follow the correct date or time search format: "
                + "\n'dd/mm/yyyy' for dates\n'hh:mm (am/pm)' for times\nPlease try again. ");
    }

    public void printSearchValueConversionErrorMessage() {
        System.out.println("An error occured when trying to parse the search input. Please try again.");
    }
    
    //@@author thanhduc2000
    public void showAllItemsDeleted() {
        System.out.println("Both list cleared!");

    }

    public void showNothingToClearBothLists() {
        System.out.println("There is nothing to clear in both lists");
    }

    public void showAppointmentsDeleted() {
        System.out.println("All appointment cleared!");
    }

    public void showNothingToClearAppointments() {
        System.out.println("There is nothing to clear in appointments' list");
    }

    public void showPatientsDeleted() {
        System.out.println("All patients cleared!");
    }

    public void showNothingToClearPatients() {
        System.out.println("There is nothing to clear in patients' list");
    }

    public void showIoExceptionError() {
        System.out.print("There is some problem with the save file");
    }
}
