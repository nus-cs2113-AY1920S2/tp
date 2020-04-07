package seedu.happypills.logic.commands.appointmentcommands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.happypills.logic.commands.appointmentcommands.DoneAppointmentCommand;
import seedu.happypills.model.data.Appointment;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.Patient;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoneAppointmentCommandTest {
    private static PatientMap newPatientMap;
    private static AppointmentMap newAppointmentMap;
    private static PatientRecordMap newPatientRecordMap;
    public static final String DIVIDER = "    =====================================================";

    @BeforeAll
    public static void setup() {
        newPatientMap = new PatientMap();
        newAppointmentMap = new AppointmentMap();
        newPatientRecordMap = new PatientRecordMap();

        //Add test patient
        Patient patientOne = new Patient("P1", "S1234567A", 123,
                "01/01/2000", "O+", "None", "NIL");
        Patient patientTwo = new Patient("P2", "S4567890B", 456,
                "01/02/1990", "O+", "None", "NIL");
        try {
            newPatientMap.add(patientOne);
            newPatientMap.add(patientTwo);
        } catch (HappyPillsException e) {
            e.printStackTrace();
        }

        //add test appointment
        Appointment appointmentOne = new Appointment("1","S1234567A", "01/02/2020", "12:00:00","reason1",false);
        Appointment appointmentTwo = new Appointment("2","S1234567A", "01/03/2020", "13:00:00","reason2", false);

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
                + "        NRIC     : S1234567A\n"
                + "        Date     : 01/02/2020\n"
                + "        Time     : 12:00:00\n"
                + "        Reason   : reason1\n"
                + "        ID       : 1\n"
                + "        Attended : Yes\n"
                + "    =====================================================";
        String message = new DoneAppointmentCommand("S1234567A", "1").execute(
                newPatientMap, newAppointmentMap, newPatientRecordMap);
        assertEquals(expectedOutputMissingInput, message);
    }


}
