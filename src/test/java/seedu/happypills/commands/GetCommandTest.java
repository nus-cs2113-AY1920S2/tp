package seedu.happypills.commands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.happypills.data.Patient;
import seedu.happypills.data.PatientMap;
import seedu.happypills.exception.HappyPillsException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetCommandTest {

    private static PatientMap newPatientMap;
    public static final String DIVIDER = "    =====================================================";

    /**
     * Initialize hardcoded test cases for testing.
     */
    @BeforeAll
    public static void setup() {
        newPatientMap = new PatientMap();

        Patient patientOne = new Patient("P1", "S123A", 123,
                "01 Jan", "O+", "None", "NIL");
        Patient patientTwo = new Patient("P2", "S456B", 456,
                "01 Feb", "O+", "None", "NIL");

        try {
            newPatientMap.add(patientOne);
            newPatientMap.add(patientTwo);
        } catch (HappyPillsException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getList_notInList_notFound() throws HappyPillsException {
        String expectedOutputNotInList = "The patient you are looking for cannot be found";
        String message = new GetCommand("S789C").execute(newPatientMap);
        assertEquals(expectedOutputNotInList, message);
    }

    @Test
    public void getList_InList_found() throws HappyPillsException {
        String expectedOutputInList = "    Here are the patient's details:\n"
                + "        Name : P1\n"
                + "        NRIC : S123A\n"
                + "        Phone Number : 123\n"
                + "        DOB : 01 Jan\n"
                + "        Blood Type : O+\n"
                + "        Allergies : None\n"
                + "        Remarks : NIL\n"
                + DIVIDER;
        String message = new GetCommand("S123A").execute(newPatientMap);
        assertEquals(expectedOutputInList, message);
    }
}
