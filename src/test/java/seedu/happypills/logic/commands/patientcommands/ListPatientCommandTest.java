package seedu.happypills.logic.commands.patientcommands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.Patient;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;
import static org.junit.jupiter.api.Assertions.assertEquals;


//@@author itskesin
/**
 * Contains all the tests related to the ListPatientCommand class.
 */
class ListPatientCommandTest {

    private static PatientMap filledPatientMap;
    private static PatientMap emptyPatientMap;
    private static AppointmentMap newAppointmentMap;
    private static PatientRecordMap newPatientRecordMap;

    public static final String DIVIDER = "    =====================================================";
    private static String expectedOutputFromEmptyList = "    There are no patients in the list.\n" + DIVIDER;
    private static String expectedOutputFromFilledList = "    Here is your list of patients:\n"
            + "    NRIC      | Name\n"
            + "    T9999999N | Mallory\n"
            + "    S9876543F | Eve\n"
            + "    S9888888G | Bob\n"
            + DIVIDER;

    /**
     * Initialize hardcoded test cases for testing.
     */
    @BeforeAll
    public static void setup() {
        filledPatientMap = new PatientMap();
        emptyPatientMap = new PatientMap();

        Patient patientOne = new Patient("Eve", "S9876543F", 91265432,
                "22/08/1996", "O+", "Peanuts", "Friend with Mallory");
        Patient patientTwo = new Patient("Mallory", "T9999999N", 81234567,
                "25/09/1998", "A-", "School", "NIL");
        Patient patientThree = new Patient("Bob", "S9888888G", 91234567,
                "10/03/1998", "B+", "NIL", "NIL");

        try {
            filledPatientMap.add(patientOne);
            filledPatientMap.add(patientTwo);
            filledPatientMap.add(patientThree);
        } catch (HappyPillsException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void printList_emptyList_emptyListMessage() throws HappyPillsException {
        String message = new ListPatientCommand().execute(emptyPatientMap, newAppointmentMap, newPatientRecordMap);
        assertEquals(expectedOutputFromEmptyList, message);
    }

    @Test
    public void printList_filledList_filledListMessage() throws HappyPillsException {
        String message = new ListPatientCommand().execute(filledPatientMap, newAppointmentMap, newPatientRecordMap);
        assertEquals(expectedOutputFromFilledList, message);
    }

}