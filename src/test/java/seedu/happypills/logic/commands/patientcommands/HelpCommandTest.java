package seedu.happypills.logic.commands.patientcommands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.happypills.logic.commands.HelpCommand;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.Patient;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.ui.HelpTextUi;
import seedu.happypills.ui.TextUi;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author sitinadiah25
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
        String expectedOutput = HelpTextUi.ADD_PATIENT_HELP_MESSAGE + TextUi.DIVIDER;
        String message = new HelpCommand("help add patient").execute(
                newPatientMap, newAppointmentMap, newPatientRecordMap);
        assertEquals(expectedOutput, message);
    }

    @Test
    public void helpCommand_listCommandMessage() throws HappyPillsException {
        String expectedOutput = HelpTextUi.LIST_PATIENT_HELP_MESSAGE + TextUi.DIVIDER;
        String message = new HelpCommand("help list patient").execute(
                newPatientMap, newAppointmentMap, newPatientRecordMap);
        assertEquals(expectedOutput, message);
    }

    @Test
    public void helpCommand_getCommandMessage() throws HappyPillsException {
        String expectedOutput = HelpTextUi.GET_PATIENT_HELP_MESSAGE + TextUi.DIVIDER;
        String message = new HelpCommand("help get patient").execute(
                newPatientMap, newAppointmentMap, newPatientRecordMap);
        assertEquals(expectedOutput, message);
    }

    @Test
    public void helpCommand_editCommandMessage() throws HappyPillsException {
        String expectedOutput = HelpTextUi.EDIT_PATIENT_HELP_MESSAGE + TextUi.DIVIDER;
        String message = new HelpCommand("help edit patient").execute(
                newPatientMap, newAppointmentMap, newPatientRecordMap);
        assertEquals(expectedOutput, message);
    }

    @Test
    public void helpCommand_deleteCommandMessage() throws HappyPillsException {
        String expectedOutput = HelpTextUi.DELETE_PATIENT_HELP_MESSAGE + TextUi.DIVIDER;
        String message = new HelpCommand("help delete patient").execute(
                newPatientMap, newAppointmentMap, newPatientRecordMap);
        assertEquals(expectedOutput, message);
    }

    @Test
    public void helpCommand_helpCommandMessage() throws HappyPillsException {
        String expectedOutput = HelpTextUi.GENERAL_HELP_MESSAGE + TextUi.DIVIDER;
        String message = new HelpCommand("help").execute(
                newPatientMap, newAppointmentMap, newPatientRecordMap);
        assertEquals(expectedOutput, message);
    }

    @Test
    public void helpCommand_exitCommandMessage() throws HappyPillsException {
        String expectedOutput = HelpTextUi.EXIT_HELP_MESSAGE + TextUi.DIVIDER;
        String message = new HelpCommand("help exit").execute(
                newPatientMap, newAppointmentMap, newPatientRecordMap);
        assertEquals(expectedOutput, message);
    }
}