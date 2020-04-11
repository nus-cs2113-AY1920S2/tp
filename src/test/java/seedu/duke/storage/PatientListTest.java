package seedu.duke.storage;

import org.junit.jupiter.api.Test;
import seedu.duke.record.Patient;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatientListTest {
    private PatientList testPatientList = new PatientList();
    private Patient testPatient1 = new Patient("alice", 11, "bukit timah", "61111111", 13);
    private Patient testPatient2 = new Patient("bob", 22, "novena", "822222222", 14);
    private Patient testPatient3 = new Patient("chong", 33, "pasir ris", "93333333", 15);

    @Test
    void patientListTest_Add() {
        PatientList.getPatientList().add(testPatient1);
        PatientList.getPatientList().add(testPatient2);
        PatientList.getPatientList().add(testPatient3);
        assertEquals(3, PatientList.getTotalPatients());
    }

    @Test
    void patientListTest_GetInfo() {
        PatientList.getPatientList().add(testPatient1);
        assertEquals("alice", PatientList.getPatientRecord(0).getName());
        assertEquals(11, PatientList.getPatientRecord(0).getAge());
        assertEquals("bukit timah", PatientList.getPatientRecord(0).getAddress());
        assertEquals("61111111", PatientList.getPatientRecord(0).getContactNumber());
    }

    @Test
    void patientListTest_Delete() {
        PatientList.getPatientList().add(testPatient1);
        PatientList.getPatientList().add(testPatient2);
        PatientList.getPatientList().add(testPatient3);
        testPatientList.removePatientRecord(0);
        assertEquals(2, PatientList.getTotalPatients());
        assertEquals("bob", PatientList.getPatientRecord(0).getName());
    }

}