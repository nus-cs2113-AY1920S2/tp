package seedu.happypills.logic.commands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.Patient;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.ui.TextUi;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelpCommandTest {

    private static PatientMap newPatientMap;
    private static AppointmentMap newAppointmentMap;
    private static PatientRecordMap newPatientRecordMap;

    /**
     * Initialize hardcoded test cases for testing.
     */
    @BeforeAll
    public static void setup() {
        newPatientMap = new PatientMap();
        newAppointmentMap = new AppointmentMap();

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
    public void helpCommand_addCommandMessage() throws HappyPillsException {
        String message = new HelpCommand("add").execute(newPatientMap, newAppointmentMap, newPatientRecordMap);
        assertEquals(TextUi.printAddHelp(), message);
    }

    @Test
    public void helpCommand_listCommandMessage() throws HappyPillsException {
        String message = new HelpCommand("list").execute(newPatientMap, newAppointmentMap, newPatientRecordMap);
        assertEquals(TextUi.printListHelp(), message);
    }

    @Test
    public void helpCommand_getCommandMessage() throws HappyPillsException {
        String message = new HelpCommand("get").execute(newPatientMap, newAppointmentMap, newPatientRecordMap);
        assertEquals(TextUi.printGetHelp(), message);
    }

    @Test
    public void helpCommand_editCommandMessage() throws HappyPillsException {
        String message = new HelpCommand("edit").execute(newPatientMap, newAppointmentMap, newPatientRecordMap);
        assertEquals(TextUi.printEditHelp(), message);
    }

    @Test
    public void helpCommand_deleteCommandMessage() throws HappyPillsException {
        String message = new HelpCommand("delete").execute(newPatientMap, newAppointmentMap, newPatientRecordMap);
        assertEquals(TextUi.printDeleteHelp(), message);
    }

    @Test
    public void helpCommand_helpCommandMessage() throws HappyPillsException {
        String message = new HelpCommand("help").execute(newPatientMap, newAppointmentMap, newPatientRecordMap);
        assertEquals(TextUi.printHelpHelp(), message);
    }

    @Test
    public void helpCommand_exitCommandMessage() throws HappyPillsException {
        String message = new HelpCommand("exit").execute(newPatientMap, newAppointmentMap, newPatientRecordMap);
        assertEquals(TextUi.printExitHelp(), message);
    }
}