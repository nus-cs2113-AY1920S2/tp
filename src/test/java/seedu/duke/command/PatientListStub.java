package seedu.duke.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Stub class for PatientList class.
 */
public class PatientListStub {
    private static List<PatientStub> patientList = new ArrayList<>();

    /**
     * Stub constructor.
     *
     * @param choice Stub choice
     */
    public PatientListStub(int choice) {
    }

    /**
     * Stub method to return patient list.
     *
     * @return a patient list
     */
    public static List<PatientStub> getPatientList() {
        return patientList;
    }

    /**
     * Stub method to get patient list size.
     *
     * @return size of patient list
     */
    public static int getTotalPatients() {
        return patientList.size();
    }

    /**
     * Stub method to create and initialize list.
     *
     * @param choice Stub choice
     */
    public static void createList(int choice) {
        patientList.clear();
        if (choice == 1) {
            PatientStub newPatient1 = new PatientStub("s;dlskd;l", 23, "Li", "121");
            PatientStub newPatient2 = new PatientStub(" ", 15, " ", "15454455");
            PatientStub newPatient3 = new PatientStub("ewuioaiwoe", 33, "Lo", "1989");
            PatientStub newPatient4 = new PatientStub("aeiwae", 13, "to", " ");
            PatientStub newPatient5 = new PatientStub("LSDs", -1, "Lis", "12");
            patientList.add(newPatient1);
            patientList.add(newPatient2);
            patientList.add(newPatient3);
            patientList.add(newPatient4);
            patientList.add(newPatient5);
        }
    }
}
