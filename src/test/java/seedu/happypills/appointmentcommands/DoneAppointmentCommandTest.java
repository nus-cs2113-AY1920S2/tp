package seedu.happypills.appointmentcommands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.happypills.controller.commands.appointmentcommands.DoneAppointmentCommand;
import seedu.happypills.model.data.Appointment;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.Patient;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.exception.HappyPillsException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoneAppointmentCommandTest {
    private static PatientMap newPatientMap;
    private static AppointmentMap newAppointmentMap;
    public static final String DIVIDER = "    =====================================================";

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

        //add test appointment
        Appointment appointmentOne = new Appointment("S123A", "reason1", "01/02/2020", "12:00:00");
        Appointment appointmentTwo = new Appointment("S123A", "reason2", "01/03/2020", "13:00:00");

        try {
            newAppointmentMap.addAppointment(appointmentOne);
            patientOne.addAppointment(appointmentOne);
            newAppointmentMap.addAppointment(appointmentTwo);
            patientOne.addAppointment(appointmentTwo);
        } catch (HappyPillsException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void editAppointment_Done() throws HappyPillsException {
        String expectedOutputMissingInput = "    The following appointment has been marked done:\n"
                + "        NRIC     : S123A\n"
                + "        Date     : 01/02/2020\n"
                + "        Time     : 12:00:00\n"
                + "        Reason   : reason1\n"
                + "        ID       : 1\n"
                + "        Attended : Yes\n"
                + "    =====================================================";
        String message = new DoneAppointmentCommand("S123A", "1").execute(newPatientMap, newAppointmentMap);
        assertEquals(expectedOutputMissingInput, message);
    }


}
