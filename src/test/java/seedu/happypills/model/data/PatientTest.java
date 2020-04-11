package seedu.happypills.model.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author itskesin
/**
 * Contains all the tests related to the Patient class.
 */
class PatientTest {

    Patient patientOne = new Patient("Eve", "S9876543F", 91265432,
            "22/08/1996", "O+", "Peanuts", "Friend with Mallory");
    Patient patientTwo = new Patient("Mallory", "T9999999N", 81234567,
            "25/09/1998", "A-", "School", "NIL");
    Patient patientThree = new Patient("Bob", "S9888888G", 91234567,
            "10/03/1998", "B+", "NIL", "NIL");

    @Test
    void getName() {
        assertEquals("Eve", patientOne.getName());
    }

    @Test
    void getNric() {
        assertEquals("T9999999N", patientTwo.getNric());
    }

    @Test
    void getPhoneNumber() {
        assertEquals(91234567, patientThree.getPhoneNumber());
    }

    @Test
    void getDateOfBirth() {
        assertEquals("25/09/1998", patientTwo.getDateOfBirth());
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


    @Test
    void testToString() {
        String correctOutput = "        Name : " + "Eve" + "\n"
                + "        NRIC : " + "S9876543F" + "\n"
                + "        Phone Number : " + 91265432 + "\n"
                + "        DOB : " + "22/08/1996" + "\n"
                + "        Blood Type : " + "O+" + "\n"
                + "        Allergies : " + "Peanuts" + "\n"
                + "        Remarks : " + "Friend with Mallory" + "\n";

        assertEquals(correctOutput, patientOne.toString());
    }

}