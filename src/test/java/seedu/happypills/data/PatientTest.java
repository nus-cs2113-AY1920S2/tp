package seedu.happypills.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatientTest {

    Patient patientOne = new Patient("Nyan", "S1234Z", 999,
            "22 Aug", "O+", "Peanuts", "NIL");
    Patient patientTwo = new Patient("Nadiah", "S9988N", 888,
            "25 Sept", "A-", "School", "NIL");
    Patient patientThree = new Patient("Jan", "S9888F", 912,
            "10 March", "B", "NIL", "NIL");

    @Test
    void getName() {
        assertEquals("Nyan", patientOne.getName());
    }

    @Test
    void getNric() {
        assertEquals("S9988N", patientTwo.getNric());
    }

    @Test
    void getPhoneNumber() {
        assertEquals(912, patientThree.getPhoneNumber());
    }

    @Test
    void getDateOfBirth() {
        assertEquals("25 Sept", patientTwo.getDateOfBirth());
    }

    @Test
    void getBloodType() {
        assertEquals("O+", patientOne.getBloodType());
    }

    @Test
    void getAllergies() {
        assertEquals("NIL", patientThree.getAllergies());
    }

    @Test
    void getRemarks() {
        assertEquals("NIL", patientThree.getRemarks());
    }

    /*
    @Test
    void testToString() {
        String correctOutput = "        Name : Nyan\n"
                + "        NRIC : S1234Z\n"
                + "        Phone Number : 999\n"
                + "        DOB : 22 Aug\n"
                + "        Blood Type : O+\n"
                + "        Allergies : Peanuts\n"
                + "        Remarks : NIL\n";

        assertEquals(correctOutput, patientOne);
    }
    */
}