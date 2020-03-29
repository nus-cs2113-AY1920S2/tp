package seedu.happypills.commands;

import org.junit.jupiter.api.Test;
import seedu.happypills.commands.patient_commands.AddPatientPatientCommand;
import seedu.happypills.data.PatientMap;
import seedu.happypills.exception.HappyPillsException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddPatientPatientCommandTest {
    @Test
    void testExecute() {
        PatientMap patients = new PatientMap();
        AddPatientPatientCommand testAddCommand = new AddPatientPatientCommand(
                "kesin", "S0618", 912, "22aug", "B-","", ""
        );

        try {
            testAddCommand.execute(patients);
        } catch (HappyPillsException e) {
            e.printStackTrace();
        }
        assertEquals(1,patients.size());
        assertTrue(patients.containsKey("S0618"));
    }
}