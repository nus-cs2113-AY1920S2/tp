package seedu.happypills.appointmentcommands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.happypills.logic.commands.appointmentcommands.FindAppointmentCommand;
import seedu.happypills.model.data.Appointment;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.Patient;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindAppointmentCommandTest {

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
        newAppointmentMap = new AppointmentMap();
        newPatientRecordMap = new PatientRecordMap();

        //Add test patient
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

        Appointment appointmentOne = new Appointment(
                "1","S123A", "01/02/2020", "12:00:00","reason1",false);
        Appointment appointmentTwo = new Appointment(
                "2","S123A", "01/03/2020", "13:00:00","reason2", false);

        patientOne.addAppointment(appointmentOne);
        patientOne.addAppointment(appointmentTwo);
        try {
            newAppointmentMap.addAppointment(appointmentOne);
            newAppointmentMap.addAppointment(appointmentTwo);
        } catch (HappyPillsException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAppointment_patientNotFound() throws HappyPillsException {
        String expectedOutputNotfound = "    The patient cannot be found. Please try again.\n";
        String message = new FindAppointmentCommand("S789C").execute(
                newPatientMap, newAppointmentMap, newPatientRecordMap);
        assertEquals(expectedOutputNotfound, message);
    }

    @Test
    public void getAppointment_EmptyList_notFound() throws HappyPillsException {
        String expectedOutputEmptyList = "    There are no appointments in the list.\n";
        String message = new FindAppointmentCommand("S456B").execute(
                newPatientMap, newAppointmentMap, newPatientRecordMap);
        assertEquals(expectedOutputEmptyList, message);
    }

    @Test
    public void getAppointment_List_found() throws HappyPillsException {
        String expectedOutputInList = "    Here are the patient's appointments:\n"
                + "     | ID | NRIC   | Reason | Date      | Time     |\n"
                + "     | 1 | S123A | reason1 | 01/02/2020 | 12:00:00 | \n"
                + "     | 2 | S123A | reason2 | 01/03/2020 | 13:00:00 | \n"
                + DIVIDER;
        String message = new FindAppointmentCommand("S123A").execute(
                newPatientMap, newAppointmentMap, newPatientRecordMap);
        assertEquals(expectedOutputInList, message);
    }
}
