package seedu.happypills.logic.commands.patientcommands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.happypills.logic.commands.patientcommands.GetPatientCommand;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.Patient;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.ui.TextUi;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetPatientCommandTest {

    private static PatientMap newPatientMap;
    private static AppointmentMap newAppointmentMap;
    private static PatientRecordMap newPatientRecordMap;
    public static final String DIVIDER = "    =====================================================";

    /**
     * Initialize hardcoded test cases for testing.
     */
    @BeforeAll
    public static void setup() {
        newPatientMap = new PatientMap();

        Patient patientOne = new Patient("P1", "S123A", 123,
                "01/01/2000", "O+", "None", "NIL");
        Patient patientTwo = new Patient("P2", "S456B", 456,
                "01/02/1990", "O+", "None", "NIL");

        try {
            newPatientMap.add(patientOne);
            newPatientMap.add(patientTwo);
        } catch (HappyPillsException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getList_notInList_notFound() throws HappyPillsException {
        String expectedOutputNotInList = "    Patient does not exist. Please try again.";
        try {
            new GetPatientCommand("S7890123C").execute(newPatientMap, newAppointmentMap, newPatientRecordMap);
        } catch (HappyPillsException hpe) {
            assertEquals(expectedOutputNotInList, hpe.getMessage());
        }
    }

    @Test
    public void getList_InList_found() throws HappyPillsException {
        String expectedOutputInList = "    Here are the patient's details:\n"
                + "        Name : P1\n"
                + "        NRIC : S123A\n"
                + "        Phone Number : 123\n"
                + "        DOB : 01/01/2000\n"
                + "        Blood Type : O+\n"
                + "        Allergies : None\n"
                + "        Remarks : NIL\n"
                + DIVIDER;
        String message = new GetPatientCommand("S123A").execute(newPatientMap, newAppointmentMap, newPatientRecordMap);
        assertEquals(expectedOutputInList, message);
    }
}
