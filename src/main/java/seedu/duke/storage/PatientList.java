package seedu.duke.storage;

import seedu.duke.record.Patient;

import java.io.IOException;
import java.util.List;

public class PatientList {
    private static List<Patient> patientList;

    public PatientList(List<Patient> savedPatientList) {
        this.patientList = savedPatientList;
    }
  
    public static List<Patient> getPatientList() {
        return patientList;
    }


    public void setPatientList(List patientListInput) {
        this.patientList = patientListInput;
    }

    public static Patient getPatientRecord(int patientIndex) {
        return getPatientList().get(patientIndex);
    }

    public static int getTotalPatients() {
        return patientList.size();
    }
}
