package seedu.duke.record;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatientTest {
    @Test
    void getName() {
        Patient patient = new Patient("peony", 33, "clementi road", "61234567", 12);
        assertEquals("peony", patient.getName());
    }

    @Test
    void testGetAge() {
        Patient patient = new Patient("peony", 33, "clementi road", "61234567", 12);
        assertEquals(33, patient.getAge());
    }

    @Test
    void testGetAddress() {
        Patient patient = new Patient("peony", 33, "clementi road", "61234567", 12);
        assertEquals("clementi road", patient.getAddress());
    }

    @Test
    void testGetContactNumber() {
        Patient patient = new Patient("peony", 33, "clementi road", "61234567", 12);
        assertEquals("61234567", patient.getContactNumber());
    }


    @Test void testToString() {
        Patient patient1 = new Patient("john", 45, "jail", "91234567", 12);
        Patient patient2 = new Patient("johnny", -1, "jail", "95345695", 14);
        assertEquals("{[Name]: john | [Age]: 45 | [Address]: jail | [Contact Number]: 91234567 | [PatientID]: 12}",
                patient1.toString());
        assertEquals("{[Name]: johnny | [Age]:  | [Address]: jail | [Contact Number]: 95345695 | [PatientID]: 14}",
                patient2.toString());
    }

    @Test
    void testSetName() {
        Patient patient = new Patient("peony", 33, "clementi road", "91234567", 12);
        patient.setName("peony");
        assertEquals("peony", patient.getName());
    }


    @Test
    void testSetAge() {
        Patient patient = new Patient("peony", 33, "clementi road", "91234567", 12);
        patient.setAge(33);
        assertEquals(33, patient.getAge());
    }


    @Test
    void testSetAddress() {
        Patient patient = new Patient("peony", 33, "clementi road", "91234567", 12);
        patient.setAddress("clementi road");
        assertEquals("clementi road", patient.getAddress());
    }


    @Test
    void testSetContactNumber() {
        Patient patient = new Patient("peony", 33, "clementi road", "91234567", 12);
        patient.setContactNumber("11111111");
        assertEquals("11111111", patient.getContactNumber());
    }


    @Test
    void testSetPatientInfo() {
        Patient patient = new Patient("peony", 33, "clementi road", "91234567", 12);
        patient.setPatientInfo("chucky", 11, "im going to kill you", "44444444");
        assertEquals("chucky", patient.getName());
        assertEquals(11, patient.getAge());
        assertEquals("im going to kill you", patient.getAddress());
        assertEquals("44444444", patient.getContactNumber());
    }

    @Test
    void testSetPatientID() {
        int patientID1 = 12;
        int patientID2 = 43;
        int patientID3 = 100;
        String patientName = "Pikachu";
        int patientAge = 34;
        String patientAddress = "NUS";
        String patientPhone = "63399999";
        Patient patient1 = new Patient(patientName, patientAge, patientAddress, patientPhone, 40);
        Patient patient2 = new Patient(patientName, patientAge, patientAddress, patientPhone, 40);
        Patient patient3 = new Patient(patientName, patientAge, patientAddress, patientPhone, 40);
        patient1.setPatientID(patientID1);
        patient2.setPatientID(patientID2);
        patient3.setPatientID(patientID3);
        assertEquals(12, patient1.getPatientID());
        assertEquals(43, patient2.getPatientID());
        assertEquals(100, patient3.getPatientID());

    }
}