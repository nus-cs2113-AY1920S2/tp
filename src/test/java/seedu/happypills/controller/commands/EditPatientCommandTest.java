package seedu.happypills.controller.commands;

import org.junit.jupiter.api.Test;
import seedu.happypills.controller.commands.patientcommands.AddPatientCommand;
import seedu.happypills.controller.commands.patientcommands.EditPatientCommand;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.Patient;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.VisitMap;
import seedu.happypills.model.exception.HappyPillsException;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EditPatientCommandTest {
    private static AppointmentMap newAppointmentMap;

    @Test
    void testExecute() {
        PatientMap patients = new PatientMap();
        VisitMap newVisitMap = new VisitMap();
        AddPatientCommand testAddCommand = new AddPatientCommand(
                "kesin", "S0618", 912, "22/08/1998", "B-","meat", "Strong"
        );
        try {
            testAddCommand.execute(patients, newAppointmentMap, newVisitMap);
        } catch (HappyPillsException e) {
            // catch exception
            e.printStackTrace();
        }

        EditPatientCommand testEditCommand = new EditPatientCommand("S0618", "/rWeak");
        try {
            testEditCommand.execute(patients, newAppointmentMap, newVisitMap);
        } catch (HappyPillsException e) {
            // catch exception
        }
        assertEquals(1, patients.size());
        if (patients.containsKey("S0618")) {
            Patient patient = patients.get("S0618");
            assertEquals("Weak", patient.getRemarks());
        }
    }

}
