package seedu.duke.storage;

import seedu.duke.record.Patient;

import java.util.ArrayList;
import java.util.List;

/**
 * This class acts an ADT for the list containing existing Patient objects. It contains various getter/setter methods
 * for accessing the different attributes and methods for manipulating the data structure.
 *
 * @author Brandon Chong
 * @version 1.0
 * @since 2020-03-14
 */
public class PatientList {
    private static List<Patient> patientList;

    public PatientList() {
        patientList = new ArrayList<>();
    }

    public PatientList(List<Patient> savedPatientList) {
        patientList = savedPatientList;
    }

    public static List<Patient> getPatientList() {
        return patientList;
    }


    public void setPatientList(List patientListInput) {
        patientList = patientListInput;
    }

    public static Patient getPatientRecord(int patientIndex) {
        return getPatientList().get(patientIndex);
    }

    public static int getTotalPatients() {
        return patientList.size();
    }

    public void removePatientRecord(int patientIndex) {
        getPatientList().remove(patientIndex);
    }
}
