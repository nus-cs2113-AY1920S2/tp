package seedu.happypills.controller.commands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.happypills.controller.commands.patientcommands.ListPatientCommand;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.Patient;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.VisitMap;
import seedu.happypills.model.exception.HappyPillsException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListPatientCommandTest {

    private static PatientMap filledPatientMap;
    private static PatientMap emptyPatientMap;
    private static AppointmentMap newAppointmentMap;
    private static VisitMap newVisitMap;

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
                "22/08/1996", "O+", "Peanuts", "NIL");
        Patient patientTwo = new Patient("Nadiah", "S9988N", 888,
                "25/09/1998", "A-", "School", "NIL");
        Patient patientThree = new Patient("Jan", "S9888F", 912,
                "10/03/1998", "B", "NIL", "NIL");

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
        String message = new ListPatientCommand().execute(emptyPatientMap, newAppointmentMap, newVisitMap);
        assertEquals(expectedOutputFromEmptyList, message);
    }

    @Test
    public void printList_filledList_filledListMessage() throws HappyPillsException {
        String message = new ListPatientCommand().execute(filledPatientMap, newAppointmentMap, newVisitMap);
        assertEquals(expectedOutputFromFilledList, message);
    }

}