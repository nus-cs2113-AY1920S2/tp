package seedu.happypills.model.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author NyanWunPaing
/**
 * Contains all the tests related to the Patient Record class.
 */
class PatientRecordTest {

    PatientRecord recordOne = new PatientRecord("S9876543F","High Temperature","Influenza","1/1/2020","10:00");
    PatientRecord recordTwo = new PatientRecord("S9876543F","Fever","Spanish Flu","30/1/2020","11:10");
    PatientRecord recordThree = new PatientRecord("S9888888G","Fever","Spanish Flu","30/1/2020","16:45");

    @Test
    void getNric() {
        assertEquals("S9876543F",recordOne.getNric());
        assertEquals("S9876543F",recordTwo.getNric());
        assertEquals("S9888888G",recordThree.getNric());
    }

    @Test
    void getSymptom() {
        assertEquals("High Temperature",recordOne.getSymptom());
        assertEquals("Fever",recordTwo.getSymptom());
        assertEquals("Fever",recordThree.getSymptom());
    }

    @Test
    void setSymptom() {
        recordOne.setSymptom("Chills");
        recordTwo.setSymptom("Headache");

        assertEquals("Chills",recordOne.getSymptom());
        assertEquals("Headache",recordTwo.getSymptom());
        assertEquals("Fever",recordThree.getSymptom());
    }

    @Test
    void getDiagnosis() {
        assertEquals("Influenza",recordOne.getDiagnosis());
        assertEquals("Spanish Flu",recordTwo.getDiagnosis());
        assertEquals("Spanish Flu",recordThree.getDiagnosis());
    }

    @Test
    void setDiagnosis() {
        recordOne.setDiagnosis("Spanish Flu");
        recordTwo.setDiagnosis("Common Cold");
        recordThree.setDiagnosis("Influenza");

        assertEquals("Spanish Flu",recordOne.getDiagnosis());
        assertEquals("Common Cold",recordTwo.getDiagnosis());
        assertEquals("Influenza",recordThree.getDiagnosis());
    }

    @Test
    void getDate() {
        assertEquals("1/1/2020",recordOne.getDate());
        assertEquals("30/1/2020",recordTwo.getDate());
        assertEquals("30/1/2020",recordThree.getDate());
    }

    @Test
    void setDate() {
        recordOne.setDate("2/1/2020");
        recordTwo.setDate("28/1/2020");
        recordThree.setDate("31/1/2020");

        assertEquals("2/1/2020",recordOne.getDate());
        assertEquals("28/1/2020",recordTwo.getDate());
        assertEquals("31/1/2020",recordThree.getDate());
    }

    @Test
    void getTime() {
        assertEquals("10:00",recordOne.getTime());
        assertEquals("11:10",recordTwo.getTime());
        assertEquals("16:45",recordThree.getTime());
    }

    @Test
    void setTime() {
        recordOne.setTime("9:45");
        recordTwo.setTime("12:00");
        recordThree.setTime("16:33");
        assertEquals("9:45",recordOne.getTime());
        assertEquals("12:00",recordTwo.getTime());
        assertEquals("16:33",recordThree.getTime());
    }

    @Test
    void testToString() {
        String test = "        NRIC : " + "S9876543F" + "\n"
                + "        Symptom : " + "High Temperature" + "\n"
                + "        Diagnosis : " + "Influenza" + "\n"
                + "        Date : " + "1/1/2020" + "\n"
                + "        time : " + "10:00" + "\n";
        assertEquals(test,recordOne.toString());
    }

    @Test
    void toSave() {
        String test = "S9888888G" + "|"
                + "Fever" + "|"
                + "Spanish Flu" + "|"
                + "30/1/2020" + "|"
                + "16:45" + System.lineSeparator();
        assertEquals(test, recordThree.toSave());
    }
}