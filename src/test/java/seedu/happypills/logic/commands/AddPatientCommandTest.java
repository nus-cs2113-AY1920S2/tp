package seedu.happypills.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.happypills.logic.commands.patientcommands.AddPatientCommand;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddPatientCommandTest {
    private static AppointmentMap newAppointmentMap;

    @Test
    void testExecute() {
        PatientMap patients = new PatientMap();
        PatientRecordMap newPatientRecordMap = new PatientRecordMap();
        AddPatientCommand testAddCommand = new AddPatientCommand(
                "kesin", "S0618", 912, "22/09/1998",
                "B-","", ""
        );

        try {
            testAddCommand.execute(patients, newAppointmentMap, newPatientRecordMap);
        } catch (HappyPillsException e) {
            e.printStackTrace();
        }
        assertEquals(1,patients.size());
        assertTrue(patients.containsKey("S0618"));
    }
}