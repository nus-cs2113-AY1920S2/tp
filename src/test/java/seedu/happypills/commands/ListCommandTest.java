package seedu.happypills.commands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.happypills.data.Patient;
import seedu.happypills.data.PatientMap;
import seedu.happypills.exception.HappyPillsException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListCommandTest {

    private static PatientMap filledPatientMap;
    private static PatientMap emptyPatientMap;
    public static final String DIVIDER = "    =====================================================";
    private static String expectedOutputFromEmptyList = "    There are no patients in the list.\n" + DIVIDER;
    private static String expectedOutputFromFilledList = "    Nyan | S1234Z\n" + "    Nadiah | S9988N\n"
            + "    Jan | S9888F\n" + DIVIDER;

    /**
     * Initialize hardcoded test cases for testing.
     */
    @BeforeAll
    public static void setup() {
        filledPatientMap = new PatientMap();
        emptyPatientMap = new PatientMap();

        Patient patientOne = new Patient("Nyan", "S1234Z", 999,
                "22 Aug", "O+", "Peanuts", "NIL");
        Patient patientTwo = new Patient("Nadiah", "S9988N", 888,
                "25 Sept", "A-", "School", "NIL");
        Patient patientThree = new Patient("Jan", "S9888F", 912,
                "10 March", "B", "School", "NIL");

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
        String message = new ListCommand().execute(emptyPatientMap);
        assertEquals(expectedOutputFromEmptyList, message);
    }

    @Test
    public void printList_filledList_filledListMessage() throws HappyPillsException {
        String message = new ListCommand().execute(filledPatientMap);
        assertEquals(expectedOutputFromFilledList, message);
    }

}