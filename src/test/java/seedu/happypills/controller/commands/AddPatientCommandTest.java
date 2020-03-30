package seedu.happypills.controller.commands;

import org.junit.jupiter.api.Test;
import seedu.happypills.controller.commands.patientcommands.AddPatientCommand;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.VisitMap;
import seedu.happypills.model.exception.HappyPillsException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddPatientCommandTest {
    private static AppointmentMap newAppointmentMap;

    @Test
    void testExecute() {
        PatientMap patients = new PatientMap();
        VisitMap newVisitMap = new VisitMap();
        AddPatientCommand testAddCommand = new AddPatientCommand(
                "kesin", "S0618", 912, "22/09/1998",
                "B-","", ""
        );

        try {
            testAddCommand.execute(patients, newAppointmentMap, newVisitMap);
        } catch (HappyPillsException e) {
            e.printStackTrace();
        }
        assertEquals(1,patients.size());
        assertTrue(patients.containsKey("S0618"));
    }
}