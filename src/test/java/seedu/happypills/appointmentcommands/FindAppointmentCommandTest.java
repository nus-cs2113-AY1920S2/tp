package seedu.happypills.appointmentcommands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.happypills.controller.commands.appointmentcommands.FindAppointmentCommand;
import seedu.happypills.model.data.Appointment;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.Patient;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.exception.HappyPillsException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindAppointmentCommandTest {

    private static PatientMap newPatientMap;
    private static AppointmentMap newAppointmentMap;
    public static final String DIVIDER = "    =====================================================";

    /**
     * Initialize hardcoded test cases for testing.
     */
    @BeforeAll
    public static void setup() {
        newPatientMap = new PatientMap();
        newAppointmentMap = new AppointmentMap();

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

        Appointment appointmentOne = new Appointment("S123A", "reason1", "01/02/2020", "12:00:00");
        Appointment appointmentTwo = new Appointment("S123A", "reason2", "01/03/2020", "13:00:00");

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
        String expectedOutputNotfound = "The patient you are looking for cannot be found";
        String message = new FindAppointmentCommand("S789C").execute(newPatientMap, newAppointmentMap);
        assertEquals(expectedOutputNotfound, message);
    }

    @Test
    public void getAppointment_EmptyList_notFound() throws HappyPillsException {
        String expectedOutputEmptyList = "    There are no appointments in the list.\n";
        String message = new FindAppointmentCommand("S456B").execute(newPatientMap, newAppointmentMap);
        assertEquals(expectedOutputEmptyList, message);
    }

    @Test
    public void getAppointment_List_found() throws HappyPillsException {
        String expectedOutputInList = "    Here are the patient's appointments:\n"
                + "     | ID | NRIC   | Reason | Date      | Time     |\n"
                + "     | 1 | S123A | reason1 | 01/02/2020 | 12:00:00 | \n"
                + "     | 2 | S123A | reason2 | 01/03/2020 | 13:00:00 | \n"
                + DIVIDER;
        String message = new FindAppointmentCommand("S123A").execute(newPatientMap, newAppointmentMap);
        assertEquals(expectedOutputInList, message);
    }
}
