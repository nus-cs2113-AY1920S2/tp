package seedu.happypills.commands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.happypills.data.Patient;
import seedu.happypills.data.PatientList;
import seedu.happypills.exception.HappyPillsException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListCommandTest {

    private static PatientList filledPatientList;
    private static PatientList emptyPatientList;
    public static final String DIVIDER = "    ===================================================";
    private static String expectedOutputFromEmptyList = "    There are no patients in the list.\n" + DIVIDER;
    private static String expectedOutputFromFilledList = "    Nyan | S1234Z\n" + "    Nadiah | S9988N\n"
            + "    Jan | S9888F\n" + DIVIDER;

    /**
     * Initialize hardcoded test cases for testing.
     */
    @BeforeAll
    public static void setup() {
        filledPatientList = new PatientList();
        emptyPatientList = new PatientList();

        Patient patientOne = new Patient("Nyan", "S1234Z", 999,
                "22 Aug", "O+", "Peanuts", "NIL");
        Patient patientTwo = new Patient("Nadiah", "S9988N", 888,
                "25 Sept", "A-", "School", "NIL");
        Patient patientThree = new Patient("Jan", "S9888F", 912,
                "10 March", "B", "School", "NIL");

        filledPatientList.add(patientOne);
        filledPatientList.add(patientTwo);
        filledPatientList.add(patientThree);
    }

    @Test
    public void printList_emptyList_emptyListMessage() throws HappyPillsException {
        String message = new ListCommand().execute(emptyPatientList);
        assertEquals(expectedOutputFromEmptyList, message);
    }

    @Test
    public void printList_filledList_filledListMessage() throws HappyPillsException {
        String message = new ListCommand().execute(filledPatientList);
        assertEquals(expectedOutputFromFilledList, message);
    }

}