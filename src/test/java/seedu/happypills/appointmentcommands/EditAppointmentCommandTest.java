package seedu.happypills.appointmentcommands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.happypills.controller.commands.appointmentcommands.EditAppointmentCommand;
import seedu.happypills.model.data.Appointment;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.Patient;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.exception.HappyPillsException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EditAppointmentCommandTest {
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
        Appointment appointmentThree = new Appointment("3","S123A",  "01/04/2020", "14:00:00","reason3",false);
        Appointment appointmentOne = new Appointment("1","S123A", "01/02/2020", "12:00:00","reason1",false);
        Appointment appointmentTwo = new Appointment("2","S123A", "01/03/2020", "13:00:00","reason2", false);


        try {
            newAppointmentMap.addAppointment(appointmentOne);
            patientOne.addAppointment(appointmentOne);
            newAppointmentMap.addAppointment(appointmentTwo);
            patientOne.addAppointment(appointmentTwo);
            newAppointmentMap.addAppointment(appointmentThree);
            patientOne.addAppointment(appointmentThree);
        } catch (HappyPillsException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void editAppointment_missingInput() throws HappyPillsException {
        String expectedOutputMissingInput = "    To edit a patient's appointment, run the following command:\n"
                + "      edit appt [NRIC] [APPTID] /d [DD/MM/YYYY] to edit appointment's phone number,\n"
                + "      edit appt [NRIC] [APPTID] /t [HH:MM] to edit appointment's allergies,\n"
                + "      edit appt [NRIC] [APPTID] /r [REASON] to edit appointment's remarks\"\n"
                + "    Do note that editing the patient's records will overwrite any previous information.\n"
                + "    The apptID can be found using \"find appt [NRIC]\"\n"
                + "    =====================================================";
        String message = new EditAppointmentCommand("S123A", "2", "/r ").execute(newPatientMap, newAppointmentMap);
        assertEquals(expectedOutputMissingInput, message);
    }

    @Test
    public void editAppointment_editReason() throws HappyPillsException {
        String expectedOutputeditReason = "    Patient appointment have been updated as follows:\n"
                + "        NRIC     : S123A\n"
                + "        Date     : 01/03/2020\n"
                + "        Time     : 13:00:00\n"
                + "        Reason   : Very sick hehe\n"
                + "        ID       : 2\n"
                + "        Attended : No\n"
                + "    =====================================================";
        String message = new EditAppointmentCommand("S123A", "2",
                "/r Very sick hehe").execute(newPatientMap, newAppointmentMap);
        assertEquals(expectedOutputeditReason, message);
    }

    @Test
    public void editAppointment_editDate_invalidDate() throws HappyPillsException {
        String expectedOutputEditDateInvalid = "    Invalid date or date format(DD/MM/YYYY).\n"
                + "    =====================================================";
        String message = new EditAppointmentCommand("S123A", "3",
                "/d 01/01/202s").execute(newPatientMap, newAppointmentMap);
        assertEquals(expectedOutputEditDateInvalid, message);
    }

    @Test
    public void editAppointment_editDate_validDate() throws HappyPillsException {
        String expectedOutputEditDateValid = "    Patient appointment have been updated as follows:\n"
                + "        NRIC     : S123A\n"
                + "        Date     : 01/01/2020\n"
                + "        Time     : 14:00:00\n"
                + "        Reason   : reason3\n"
                + "        ID       : 3\n"
                + "        Attended : No\n"
                + "    =====================================================";
        String message = new EditAppointmentCommand("S123A", "3",
                "/d 01/01/2020").execute(newPatientMap, newAppointmentMap);
        assertEquals(expectedOutputEditDateValid, message);
    }

    @Test
    public void editAppointment_editTime_invalidTime() throws HappyPillsException {
        String expectedOutputEditTimeInvalid = "    Invalid time or time format(HH:MM).\n"
                + "    =====================================================";
        String message = new EditAppointmentCommand("S123A", "1",
                "/t 24:00").execute(newPatientMap, newAppointmentMap);
        assertEquals(expectedOutputEditTimeInvalid, message);
    }

    @Test
    public void editAppointment_editTime_validTime() throws HappyPillsException {
        String expectedOutputEditTimeValid = "    Patient appointment have been updated as follows:\n"
                + "        NRIC     : S123A\n"
                + "        Date     : 01/02/2020\n"
                + "        Time     : 23:59:00\n"
                + "        Reason   : reason1\n"
                + "        ID       : 1\n"
                + "        Attended : No\n"
                + "    =====================================================";
        String message = new EditAppointmentCommand("S123A", "1",
                "/t 23:59").execute(newPatientMap, newAppointmentMap);
        assertEquals(expectedOutputEditTimeValid, message);
    }

}
