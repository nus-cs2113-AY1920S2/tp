package seedu.happypills.logic.commands.patientcommands;

import org.junit.jupiter.api.Test;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.Patient;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author sitinadiah25
class EditPatientCommandTest {
    private static AppointmentMap newAppointmentMap;

    @Test
    void testExecute() {
        PatientMap patients = new PatientMap();
        PatientRecordMap newPatientRecordMap = new PatientRecordMap();
        AddPatientCommand testAddCommand = new AddPatientCommand(
                "kesin", "S1234567F", 912, "22/08/1998", "B-","meat", "Strong"
        );
        try {
            testAddCommand.execute(patients, newAppointmentMap, newPatientRecordMap);
        } catch (HappyPillsException e) {
            // catch exception
            e.printStackTrace();
        }

        EditPatientCommand testEditCommand = new EditPatientCommand("S1234567F", "/rmWeak");
        try {
            testEditCommand.execute(patients, newAppointmentMap, newPatientRecordMap);
        } catch (HappyPillsException e) {
            // catch exception
        }
        assertEquals(1, patients.size());
        if (patients.containsKey("S1234567F")) {
            Patient patient = patients.get("S1234567F");
            assertEquals("Weak", patient.getRemarks());
        }
    }

}
